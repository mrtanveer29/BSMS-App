package com.example.asus.busserviceapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.asus.busserviceapp.R;

import IRepository.ILogin;
import StronglyType.LoginModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.APIInitializer;
import model.NetworkManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

//    private AuthenticationCallback authenticationCallback;
    private static final int REQUEST_SIGNUP = 1;
    private static final int REQUEST_LOGIN = 2;
    private static final int MENU_ITEM_ITEM1 = 3;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @BindView(R.id.txtUserName) EditText txtuserName;
    @BindView(R.id.password) EditText password;

    @OnClick(R.id.btnSignin)
    public void getLoggedIn(View view){
        login();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setResult(RESULT_OK, getIntent());

//        authenticationCallback=new AuthenticationCallback(this);

        sharedPreferences = getSharedPreferences("ADASCM", MODE_PRIVATE);
        editor=sharedPreferences.edit();


        if(sharedPreferences.getBoolean("autologin",false)==true) {
            autologin();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(Menu.NONE,1, Menu.NONE, "LogIn").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        login();
//        return super.onOptionsItemSelected(item);
//    }

    public void performLogin(View view) {
        login();
    }



    public void autologin() {
        final String loginemail = sharedPreferences.getString("txtuserName", "");
        final String loginpassword = sharedPreferences.getString("password", "");
        if(loginemail!=null && loginpassword!=null) {
            onLoginSuccess();
        }
    }

    public void login() {
        if (validate()) {
//            boolean isNetworkavailable= NetworkManager.isNetworkAvailable(LoginActivity.this);
//            if(!isNetworkavailable){
//                Toast.makeText(this, "oOPs No internet Connection", Toast.LENGTH_SHORT).show();
//                return;
//            }
            final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();

            final String getUserName = txtuserName.getText().toString();
            final String getPassword = password.getText().toString();

            final String user_name = sharedPreferences.getString("user_name", "");
           final String loginpassword = sharedPreferences.getString("password", "");


            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {

                            LoginAuthentication(getUserName,getPassword,progressDialog);
                            //onLoginSuccess();
                        }
                    }, 2000);
        }
        else {
            Toast.makeText(LoginActivity.this, "Enter valid information.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        super.onBackPressed();
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }

    @Override
    public void onStart() {
        super.onStart();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }

        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }

    public void onLoginSuccess() {
        Intent intent = new Intent(this, SalesInfoActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN);
        editor.putBoolean("autologin",true);
        editor.commit();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Username/Passwrod incorrect.", Toast.LENGTH_LONG).show();
    }
    public void LoginAuthentication(final String getUserName,final String getPassword,final ProgressDialog progressDialog){
        final Retrofit retrofit= APIInitializer.initNetwork(this);

        retrofit.create(ILogin.class).GetLoginInfo(getUserName,getPassword).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                LoginModel result=response.body();

                boolean login=result.getLogin();
                Toast.makeText(LoginActivity.this, login+"", Toast.LENGTH_SHORT).show();
                //String message=result.getMsg();
                if(login) {
                    Toast.makeText(LoginActivity.this, result.getOutputMessage(), Toast.LENGTH_SHORT).show();
                    LoginModel.UserData returnvalue= ( response.body().getUserData());

                    int role_id=returnvalue.getRouteId();

                    editor.putString("user_name",returnvalue.getUserName());
                    editor.putString("password",returnvalue.getPassword());
                    editor.putString("full_name",returnvalue.getEmpFirstname()+" "+returnvalue.getEmpLastname());
                    editor.putString("role_id",returnvalue.getRouteId().toString());
                    editor.putString("company_id",returnvalue.getCompanyId().toString());
                    editor.putString("route_id",returnvalue.getRouteId().toString());
                    editor.putString("direction",returnvalue.getDirection().toString());
                    editor.putString("company_name",returnvalue.getCompanyName().toString());
                    editor.putString("counter_id",returnvalue.getAreaId().toString());
                    onLoginSuccess();
                    progressDialog.dismiss();
                }
                else {
                    onLoginFailed();
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, result.getOutputMessage(), Toast.LENGTH_SHORT).show();
                    txtuserName.setText("");
                    password.setText("");
                }


            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.e("url",call.request().url().toString());
                Log.e("msg",t.getMessage());
                Toast.makeText(LoginActivity.this, "Opps! something went wrong", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public boolean validate() {
        boolean valid = true;

        String getemail = txtuserName.getText().toString();
        String getpassword = password.getText().toString();

        if (getemail.isEmpty()) {
            txtuserName.setError("Enter a valid User name.");
            valid = false;
        }
        else {
            txtuserName.setError(null);
        }

        if (getpassword.isEmpty() ) {
            password.setError("Please Enter your valid password.");
            valid = false;
        }
        else {
            password.setError(null);
        }

        return valid;
    }
}
