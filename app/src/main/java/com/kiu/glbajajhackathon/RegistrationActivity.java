package com.kiu.glbajajhackathon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kiu.glbajajhackathon.rest.ApiClient;
import com.kiu.glbajajhackathon.rest.ApiInterface;
import com.kiu.glbajajhackathon.rest.Constants;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "kant";

    Button _signupButton;

    TextView _loginLink;

    EditText _nameText;
    EditText _addressText;
    EditText _emailText;
    EditText _mobileText;
    EditText _passwordText;
    EditText _referralText;
    EditText _usernameText;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        _signupButton = findViewById(R.id.btn_signup);
        _loginLink = findViewById(R.id.link_login);

        _nameText = findViewById
                (R.id.input_name);

        _addressText = findViewById
                (R.id.input_address);

        _emailText = findViewById
                (R.id.input_email);

        _mobileText = findViewById
                (R.id.input_mobile);

        _passwordText = findViewById(R.id.input_password);
        _referralText = findViewById(R.id.input_referral);
        _usernameText = findViewById(R.id.input_username);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });


    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String refrral = _referralText.getText().toString();
        String username = _usernameText.getText().toString();
        final String ref;
        if (refrral.length() < 1) {
            ref = "0";


            // TODO: Implement your own signup logic here.

            ApiInterface loginApi = ApiClient.getClient().create(ApiInterface.class);
            Call<JsonObject> signupApi =
                    loginApi.signup(name, email, mobile, address, username, password, ref);

            signupApi.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    JsonObject login = response.body();
                    JsonElement msg = login.get("message");
                    Log.d(TAG, "onResponse: " + msg);
                    if (msg != null) {
                        progressDialog.dismiss();
                        Toast.makeText(RegistrationActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                        //startActivity();
                        finish();

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(RegistrationActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                    Log.d(TAG, "login on server : " + login + call.request().url());
                }


                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.d(TAG, "server failed. " + call.request().url());
                    Log.d(TAG, "onFailure: " + t);
                }
            });

        }


    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
    }


    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String username = _usernameText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (address.isEmpty()) {
            _addressText.setError("Enter Valid Address");
            valid = false;
        } else {
            _addressText.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length() != 10) {
            _mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (username.isEmpty()) {
            _usernameText.setError("Can't be Empty");
            valid = false;
        } else {
            _usernameText.setError(null);
        }


        return valid;
    }
}
