package com.arm07.android.eshopkart.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.db.SQL;
import com.arm07.android.eshopkart.model.Constants;
import com.arm07.android.eshopkart.model.MyCart;
import com.arm07.android.eshopkart.utility.PayAdapter;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.arm07.android.eshopkart.db.SQL.IMAGE;
import static com.arm07.android.eshopkart.db.SQL.KEY_ID;
import static com.arm07.android.eshopkart.db.SQL.NAME;
import static com.arm07.android.eshopkart.db.SQL.PRICE;
import static com.arm07.android.eshopkart.db.SQL.QUANTITY;
import static com.arm07.android.eshopkart.db.SQL.TABLE;

public class PayActivity extends AppCompatActivity {

    private final ArrayList<MyCart> myCartArrayList = new ArrayList<>();

    private RecyclerView recyclerView;
    private PayAdapter myCartAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private String order_phone_number;
    private SharedPreferences spref;
    //private Button checkout;

    private String url;
    private SQL sqlHelper;
    private SQLiteDatabase db;

    private float total_bill = 0;
    EditText editText_amount;
    Button button_pay;

    String urlExt="&api_key=1fa9fde8966420a223ea80bf99b8a771&user_id=917";

    /*
     * Environment configurations for PayPal
     */
    public static final int PAYPAL_REQUEST_CODE = 123;
    private static PayPalConfiguration payPalConfiguration = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Constants.PAYPAL_CLIENT_ID);

    //String mPaymentAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        sqlHelper = new SQL(this);
        db = sqlHelper.getReadableDatabase();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewPayment);
        recyclerView.setHasFixedSize(false);
        layoutManager=new LinearLayoutManager(PayActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        //editText_amount=findViewById(R.id.editText_amount);
        button_pay=findViewById(R.id.button_payment);

        /*spref = getSharedPreferences("file5", Context.MODE_PRIVATE);
        order_phone_number = spref.getString("input_phone","");
        Log.d("logged_in_phone",order_phone_number+"");*/
        order_phone_number="9849985918";

        getCartItemsFromDB();

        button_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PayActivity.this, PayPalService.class);
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,payPalConfiguration);
                startService(intent);
                getPayment();
            }
        });
    }

    private void getCartItemsFromDB() {

        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE,null);
        cursor.moveToFirst();

        if(cursor.getCount()>0) {

            String Name = (cursor.getString(cursor.getColumnIndex(NAME)));

            do {

                String Product_ID = cursor.getString(cursor.getColumnIndex(KEY_ID));
                String Product_Name = cursor.getString(cursor.getColumnIndex(NAME));
                String Product_QTY = cursor.getString(cursor.getColumnIndex(QUANTITY));
                String Product_Price = cursor.getString(cursor.getColumnIndex(PRICE));
                String Product_Image = cursor.getString(cursor.getColumnIndex(IMAGE));

                total_bill += Float.valueOf(Product_Price);

                MyCart myCart = new MyCart(Product_ID, Product_Name, Product_QTY, Product_Price, Product_Image);

                String place_order_url = "http://rjtmobile.com/ansari/shopingcart/androidapp/orders.php?" +
                        "&item_id=" + Product_ID + "&item_names=" + Product_Name + "&item_quantity=" + Product_QTY
                        + "&final_price=" + Product_Price + "&mobile=" + order_phone_number;

                url=new String(place_order_url+urlExt);
                placeOrder(url);

                myCartArrayList.add(myCart);

            } while (cursor.moveToNext());

            if (myCartArrayList.size() > 0) {

                myCartAdapter = new PayAdapter(this, myCartArrayList);
                recyclerView.setAdapter(myCartAdapter);
            } else {
                ArrayList<MyCart> myCartArrayList = new ArrayList<>();
                myCartAdapter = new PayAdapter(this, myCartArrayList);
                recyclerView.setAdapter(myCartAdapter);
            }
        }

    }

        private void placeOrder(String place_order_url){
            String tag_string_req_order = "string_req_place_order";
            final StringRequest stringRequest = new StringRequest(Request.Method.GET,place_order_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d("place_order","Result:"+response);
                    if(response.contains("Order Confirmed")){
                         Toast.makeText(PayActivity.this,"Your order is confirmed",Toast.LENGTH_LONG).show();
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(PayActivity.this);
            requestQueue.add(stringRequest);
           // AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req_order);
        }

    public void getPayment(){
        String mPaymentAmount = Float.toString(total_bill);
        Log.d("bill",mPaymentAmount);
        //mPaymentAmount = editText_amount.getText().toString();
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(mPaymentAmount)),
                "USD","payment", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent paymentIntent = new Intent(PayActivity.this, PaymentActivity.class);
        paymentIntent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,payPalConfiguration);
        paymentIntent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        startActivityForResult(paymentIntent,PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PAYPAL_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (paymentConfirmation != null){
                    try{
                        String paymentDetails = paymentConfirmation.toJSONObject().toString();
                        Toast.makeText(this, "Payment Details: "+paymentDetails, Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        Toast.makeText(this, "JSONException", Toast.LENGTH_SHORT).show();}
                }
            }
        }
    }
}
