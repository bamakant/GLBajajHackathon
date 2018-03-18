package com.kiu.glbajajhackathon;

import android.Manifest;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kiu.glbajajhackathon.rest.ApiClient;
import com.kiu.glbajajhackathon.rest.ApiInterface;
import com.kiu.glbajajhackathon.rest.Constants;
import com.kiu.glbajajhackathon.rest.LocationUpdatesIntentService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDataActivity extends AppCompatActivity {

    private Button updateBtn,cancelBtn;
    private TextView foodView,clothView;
    private EditText itemNameView,itemQuantityView;
    private String itemType = "food";
    SharedPreferences prefs;
    String userid;
    private static final String TAG="kant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        updateBtn = findViewById(R.id.btn_update);
        cancelBtn = findViewById(R.id.btn_cancel);
        foodView = findViewById(R.id.foodtextview);
        clothView = findViewById(R.id.clothtextview);
        itemNameView = findViewById(R.id.edit_text_item_name);
        itemQuantityView = findViewById(R.id.edit_text_item_quantity);

        prefs = getSharedPreferences(Constants.LOGIN_PREF, MODE_PRIVATE);
        userid = prefs.getString("userid", null);



        foodView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItemType(v);
            }
        });

        clothView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItemType(v);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

       cancelBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });

    }

    private void addItem() {

        final ProgressDialog progressDialog = new ProgressDialog(AddDataActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Item Adding...");
        progressDialog.show();

        String itemname = itemNameView.getText().toString().trim();
        String quantity = itemQuantityView.getText().toString().trim();
        String add;
       /* if (MainActivity.address == null){
            add = "NA";
        upload();
        }
        else {
            add = MainActivity.address;
            upload();
        }*/
        ApiInterface loginApi = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> insertItem =
                loginApi.insertitem(itemname, quantity, String.valueOf(LocationUpdatesIntentService.lang), String.valueOf(LocationUpdatesIntentService.lat), userid,"NA");
        insertItem.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject login = response.body();
                JsonElement msg = login.get("message");
                Log.d(TAG, "onResponse: " + msg);
                if (msg != null) {
                    progressDialog.dismiss();
                    Toast.makeText(AddDataActivity.this, "Item Added.", Toast.LENGTH_SHORT).show();
                    //startActivity();

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(AddDataActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG, "on server : " + login + call.request().url());
            }


            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "server failed. " + call.request().url());
                Log.d(TAG, "onFailure: " + t);
            }
        });



    }

    private void setItemType(View view) {
        switch (view.getId()){
            case R.id.foodtextview:
                foodView.setTextColor(getResources().getColor(R.color.colorAccent));
                foodView.setBackgroundColor(getResources().getColor(android.R.color.white));
                clothView.setTextColor(getResources().getColor(android.R.color.white));
                clothView.setBackgroundColor(getResources().getColor(R.color.reportPrimary));
                itemType = "food";
                break;
            case R.id.clothtextview:
                clothView.setTextColor(getResources().getColor(R.color.colorAccent));
                clothView.setBackgroundColor(getResources().getColor(android.R.color.white));
                foodView.setTextColor(getResources().getColor(android.R.color.white));
                foodView.setBackgroundColor(getResources().getColor(R.color.reportPrimary));
                itemType = "cloth";
                break;
        }
    }


}