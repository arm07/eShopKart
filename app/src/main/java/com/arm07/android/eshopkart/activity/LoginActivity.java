package com.arm07.android.eshopkart.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arm07.android.eshopkart.MainApplication;
import com.arm07.android.eshopkart.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import net.openid.appauth.AuthState;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private static final String USED_INTENT = "USED_INTENT";
    private static final String SHARED_PREFERENCES_NAME = "AuthStatePreference";
    private static final String AUTH_STATE = "AUTH_STATE";

    MainApplication mMainApplication;
    // state
    AuthState mAuthState;

    AppCompatButton mAuthorize;


    GoogleApiClient mGoogleApiClient;
    GoogleSignInClient mGoogleSignInClient;
    private static final int REQ_CODE=9001;

    private SharedPreferences spref;

    @BindView(R.id.sign_in_button)
    SignInButton mSignInButton;
    @BindView(R.id.appCompatButtonLogin)
    AppCompatButton signInEmail;
    @BindView(R.id.textInputEditTextEmail)
    TextInputEditText email;
    @BindView(R.id.textInputEditTextPassword)
    TextInputEditText password;
    @BindView(R.id.textInputEditTextMobileNum)
    TextInputEditText phone;
    @BindView(R.id.textInputEditTextName)
    TextInputEditText FullName;
    @BindView(R.id.textViewLinkRegister)
    AppCompatTextView register;
    @BindView(R.id.textViewLinkForgotPwd)
    AppCompatTextView forgotPwd;
    @BindView(R.id.textViewAlreadyAccount)
    AppCompatTextView alreadyAccnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //mSignInButton=(SignInButton)findViewById(R.id.sign_in_button);

        ButterKnife.bind(this);
        //mSignInButton.setOnClickListener();
        spref = getSharedPreferences("file5", Context.MODE_PRIVATE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullName.setVisibility(View.VISIBLE);
                register.setVisibility(View.GONE);
                forgotPwd.setVisibility(View.GONE);
                alreadyAccnt.setVisibility(View.VISIBLE);
                signInEmail.setText("SIGN UP");
            }
        });
        alreadyAccnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInEmail.setText("SIGN IN");
                FullName.setVisibility(View.GONE);
                register.setVisibility(View.VISIBLE);
                forgotPwd.setVisibility(View.VISIBLE);
                alreadyAccnt.setVisibility(View.GONE);
            }
        });

        signInEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input_Name = FullName.getText().toString().trim();
                String input_Email = email.getText().toString().trim();
                String input_phone = phone.getText().toString().trim();
                String input_password = password.getText().toString().trim();

                if(input_password.equals("") ||input_phone.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Please Enter Empty Fields", Toast.LENGTH_LONG).show();

                }
                else if(!(input_password.isEmpty()) && (!input_phone.isEmpty()))
                {
                    if (input_phone.length() == 10)
                    {
                        if (input_password.length() >= 6)
                        {
                            if(signInEmail.getText().toString().toLowerCase().equals("sign in")){
                                //Toast.makeText(LoginActivity.this, "few conditions met", Toast.LENGTH_LONG).show();

                                String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_login.php?" +
                                        "mobile=" + input_phone + "&password=" + input_password;
                                SharedPreferences.Editor editPef = spref.edit();
                                editPef.putString("input_phone",input_phone);
                                editPef.apply();

                                signInUsingEmail(url);
                            }

                            else if(signInEmail.getText().toString().toLowerCase().equals("sign up")){

                                if(!(input_Name.equals(""))&& !(input_Email.equals(""))&&!(input_phone.equals(""))&&!(input_password.equals(""))) {

                                    if (input_Email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                                        String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_reg.php?" +
                                                "name=" + input_Name + "&email=" + input_Email + "&mobile=" + input_phone + "&password=" + input_password;
                                        register_user(url);

                                        FullName.setText("");
                                        email.setText("");
                                        phone.setText("");
                                        password.setText("");
                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this,"Please Enter Valid Email" , Toast.LENGTH_LONG).show();
                                    }
                                }
                                else if(input_Name.equals("")|| input_Email.equals("")||input_phone.equals("")||input_password.equals(""))
                                {
                                    Toast.makeText(LoginActivity.this,"Please Enter Empty Fields",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Password Should be 6 or more Characters", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(LoginActivity.this, "Mobile Should be 10 digit Number", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Please Enter Empty Fields", Toast.LENGTH_LONG).show();
                }


            }
        });

        /* mAuthorize = (AppCompatButton) findViewById(R.id.sign_in_button);
        mAuthorize.setOnClickListener(new AuthorizeListener());
        // Configure sign-in to request the user's ID, email address, and basic profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient=GoogleSignIn.getClient(this, gso);
        mGoogleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();*/

    }

    private void register_user(String URL) {
        String  tag_string_req = "string_req";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Result Response:",response);

                if(response.contains("successfully registered")){

                    Toast.makeText(LoginActivity.this,response.toString().toUpperCase()+"YOU CAN LOGIN NOW",Toast.LENGTH_LONG).show();
                    Intent back_to_login = new Intent(LoginActivity.this,LoginActivity.class);
                    startActivity(back_to_login);

                }
                else{
                    Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);

        //AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req_home);
    }

    private void signInUsingEmail(String url) {

        /*RApiInterface rApiInterface = RApiClient.getClient().create(RApiInterface.class);
        Call<FeaturedResponse> featuredResponseCall= rApiInterface.getCategoryDetails(*//*"4c1dbea3d6cd43b13a036fcc684284f5",908*//*);
        featuredResponseCall.enqueue(new Callback<FeaturedResponse>() {
            @Override
            public void onResponse(Call<FeaturedResponse> call, Response<FeaturedResponse> response) {
                Log.i("response",response.body().toString());
            }

            @Override
            public void onFailure(Call<FeaturedResponse> call, Throwable t) {
            }
        });*/

        String tag_string_req_home = "string_req";
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(LoginActivity.this, "got Response", Toast.LENGTH_LONG).show();

                Log.d(TAG,"Result:"+response);
                if(response.contains("success")){
                    //Toast.makeText(LoginActivity.this,"Successfully Logged in",Toast.LENGTH_LONG).show();

                    String apiKey=response.substring(response.indexOf("AppApiKey")+13,response.length()-3);
                    //Toast.makeText(LoginActivity.this,"APi Key"+apiKey,Toast.LENGTH_LONG).show();
                    String userId=response.substring(response.indexOf("UserID")+9,response.indexOf("UserName")-3);
                    //Toast.makeText(LoginActivity.this,"User Id"+userId,Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editPef = spref.edit();
                    editPef.putString("api_key",apiKey);
                    editPef.putString("user_id",userId);
                    editPef.apply();

                    Intent category_intent = new Intent(LoginActivity.this, SignedActivity.class);
                    /*category_intent.putExtra("userIsLogged",true);
                    category_intent.putExtra("apiKey",apiKey);*/
                    startActivity(category_intent);

                } else if(response.contains("Mobile Number not register"))
                {
                    Toast.makeText(LoginActivity.this,"MOBILE NUMBER NOT REGISTERED - CREATE AN ACCOUNT",Toast.LENGTH_LONG).show();
                }
                else if(response.contains("incorrect password"))
                {
                    Toast.makeText(LoginActivity.this,"incorrect password",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);

        //AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req_home);

    }


    /*
    @Override
    protected void onNewIntent(Intent intent) {
        checkIntent(intent);
    }

    private void checkIntent(@Nullable Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            switch (action) {
                case "com.arm07.android.eshopkart.activity.HANDLE_AUTHORIZATION_RESPONSE":
                    if (!intent.hasExtra(USED_INTENT)) {
                        handleAuthorizationResponse(intent);
                        intent.putExtra(USED_INTENT, true);
                    }
                    break;
                default:
                    // do nothing
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkIntent(getIntent());
    }


    private void handleAuthorizationResponse(@NonNull Intent intent) {
        AuthorizationResponse response = AuthorizationResponse.fromIntent(intent);
        AuthorizationException error = AuthorizationException.fromIntent(intent);
        final AuthState authState = new AuthState(response, error);

        if (response != null) {
            Log.i(LOG_TAG, String.format("Handled Authorization Response %s ", authState.toJsonString()));
            AuthorizationService service = new AuthorizationService(this);
            service.performTokenRequest(response.createTokenExchangeRequest(), new AuthorizationService.TokenResponseCallback() {
                @Override
                public void onTokenRequestCompleted(@Nullable TokenResponse tokenResponse, @Nullable AuthorizationException exception) {
                    if (exception != null) {
                        Log.w(LOG_TAG, "Token Exchange failed", exception);
                    } else {
                        if (tokenResponse != null) {
                            authState.update(tokenResponse, exception);
                            persistAuthState(authState);
                            Log.i(LOG_TAG, String.format("Token Response [ Access Token: %s, ID Token: %s ]", tokenResponse.accessToken, tokenResponse.idToken));
                        }
                    }
                }
            });
        }
    }

    private void persistAuthState(@NonNull AuthState authState) {
        getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
                .putString(AUTH_STATE, authState.toJsonString())
                .commit();
        enablePostAuthorizationFlows();
    }

    private void enablePostAuthorizationFlows() {
        mAuthState = restoreAuthState();
        if (mAuthState != null && mAuthState.isAuthorized()) {
            if (mMakeApiCall.getVisibility() == View.GONE) {
                mMakeApiCall.setVisibility(View.VISIBLE);
                mMakeApiCall.setOnClickListener(new MakeApiCallListener(this, mAuthState, new AuthorizationService(this)));
            }
            if (mSignOut.getVisibility() == View.GONE) {
                mSignOut.setVisibility(View.VISIBLE);
                mSignOut.setOnClickListener(new SignOutListener(this));
            }
        } else {
            mMakeApiCall.setVisibility(View.GONE);
            mSignOut.setVisibility(View.GONE);
        }
        }
    }
   private AuthState restoreAuthState() {
        String jsonString = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .getString(AUTH_STATE, null);
        if (!TextUtils.isEmpty(jsonString)) {
            try {
                return AuthState.fromJson(jsonString);
            } catch (JSONException jsonException) {
                // should never happen
            }
        }
        return null;
    }


    public static class AuthorizeListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            AuthorizationServiceConfiguration serviceConfiguration = new AuthorizationServiceConfiguration(
                    Uri.parse("https://accounts.google.com/o/oauth2/v2/auth") //auth endpoint
                    ,
                    Uri.parse("https://www.googleapis.com/oauth2/v4/token") // token endpoint
            );

            String clientId = "786599398890-u5c9hjdhsd0lj3c6oneatgt9gf2haoil.apps.googleusercontent.com ";
            Uri redirectUri = Uri.parse("com.arm07.android.eshopkart.activity:/oauth2callback");
            AuthorizationRequest.Builder builder = new AuthorizationRequest.Builder(
                    serviceConfiguration,
                    clientId,
                    AuthorizationRequest.RESPONSE_TYPE_CODE,
                    redirectUri
            );
            builder.setScopes("profile");
            AuthorizationRequest request = builder.build();

            AuthorizationService authorizationService = new AuthorizationService(view.getContext());

            String action = "com.arm07.android.eshopkart.activity.HANDLE_AUTHORIZATION_RESPONSE";
            Intent postAuthorizationIntent = new Intent(action);
            PendingIntent pendingIntent = PendingIntent.getActivity(view.getContext(), request.hashCode(), postAuthorizationIntent, 0);
            authorizationService.performAuthorizationRequest(request, pendingIntent);
        }
    }
   public static class SignOutListener implements Button.OnClickListener {
        private final LoginActivity mLoginActivity;

        public SignOutListener(@NonNull LoginActivity loginActivity) {
            mLoginActivity = loginActivity;
        }

        @Override
        public void onClick(View view) {
            mLoginActivity.mAuthState = null;
            mLoginActivity.clearAuthState();
            mLoginActivity.enablePostAuthorizationFlows();
        }
    }

    private void clearAuthState() {
        getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .edit()
                .remove(AUTH_STATE)
                .apply();
    }*/



    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account == null) {
            updateUI(false);
        } else {
            updateUI(true);
        }
    }
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.sign_in_button:
                Toast.makeText(getApplicationContext(), "Message- G-login clicked", Toast.LENGTH_LONG).show();
                //Log.i("MYTEST_SIGNIN","signIn clicked");
                signIn();
                break;

        /*case R.id.sign_out_button:
                signOut();
                break;*/

        }
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    private void signIn() {
        Intent intent=Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intent,REQ_CODE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQ_CODE){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

    private void handleResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            GoogleSignInAccount googleSignInAccount=result.getSignInAccount();
            String name=googleSignInAccount.getDisplayName();
            String email=googleSignInAccount.getEmail();


        /*Name.setText(name);
            Email.setText(email);*/

            updateUI(true);
        }
    }

    private void updateUI(boolean isLogin) {
        if(isLogin){
            //Prof_section.visibility(View.VISIBLE);
            mSignInButton.setVisibility(View.GONE);
        }
        else{
            //Prof_section.visibility(View.Gone);
            mSignInButton.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
