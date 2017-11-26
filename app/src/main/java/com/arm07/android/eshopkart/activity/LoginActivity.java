package com.arm07.android.eshopkart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

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


public class LoginActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    private static final String USED_INTENT = "USED_INTENT";
    private static final String SHARED_PREFERENCES_NAME = "AuthStatePreference";
    private static final String AUTH_STATE = "AUTH_STATE";

    MainApplication mMainApplication;
    // state
    AuthState mAuthState;

    AppCompatButton mAuthorize;

    SignInButton mSignInButton;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInClient mGoogleSignInClient;
    private static final int REQ_CODE=9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSignInButton=(SignInButton)findViewById(R.id.sign_in_button);
        //mSignInButton.setOnClickListener();

       /*

         mAuthorize = (AppCompatButton) findViewById(R.id.sign_in_button);
        mAuthorize.setOnClickListener(new AuthorizeListener());

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient=GoogleSignIn.getClient(this, gso);
        mGoogleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this).
                addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();*/

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
            updateUI(true);
        } else {
            updateUI(false);
        }
    }
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.sign_in_button:
                Log.i("MYTEST_SIGNIN","signIn clicked");
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
