package com.arm07.android.eshopkart.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arm07.android.eshopkart.R;
import com.arm07.android.eshopkart.activity.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by rashmi on 11/30/2017.
 */

public class ResetFragment extends Fragment {


    private static final String TAG = ResetFragment.class.getSimpleName();
    Unbinder mUnbinder;

    @BindView(R.id.textInputEditTextPhoneNum)
    AppCompatEditText phone;

    @BindView(R.id.textInputEditTextPasswordInReset)
    AppCompatEditText oldPassword;

    @BindView(R.id.textInputEditTextNewPasswordInReset)
    AppCompatEditText newPassword;

    @BindView(R.id.appCompatButtonReset)
    AppCompatButton reset;

    private String jsonResponse;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_reset_password,container,false);
        mUnbinder = ButterKnife.bind(this, view);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_phone = phone.getText().toString().trim();
                String user_old_pass = oldPassword.getText().toString().trim();
                String user_new_pass = newPassword.getText().toString().trim();


                if(!(user_old_pass.equals(""))&& !(user_new_pass.equals(""))&&(user_old_pass.length()>=6)&&(user_new_pass.length()>=6))
                {
                    String restPassUrl = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_reset_pass.php?" +
                            "&mobile="+user_phone+"&password="+user_old_pass+"&newpassword="+user_new_pass;

                    resetPass(restPassUrl);
                    Intent back_to_login = new Intent(getActivity(),LoginActivity.class);
                    startActivity(back_to_login);
                }
                else
                {
                    Toast.makeText(getContext(), "Please Enter Valid Password of 6 or more Characters", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    private void resetPass(String restPassUrl) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,restPassUrl,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("ResetPass", response.toString());
                        try {
                            jsonResponse ="";
                            JSONArray msg_array = response.getJSONArray("msg");
                            String msg = msg_array.getString(0);
                            jsonResponse+=msg;

                            Toast.makeText(getActivity(),jsonResponse,Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}
