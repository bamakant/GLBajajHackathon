package com.kiu.glbajajhackathon.rest;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.location.LocationResult;

/**
 * Created by bamakant on 6/1/18.
 */

public class LocationUpdatesIntentService extends IntentService {

    private static final String TAG = "kant";

    LocationResult locationResult;
    Location location;
    public static double lat,lang;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public LocationUpdatesIntentService() {
        super("LocationUpdateIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (LocationResult.hasResult(intent)) {
            locationResult = LocationResult.extractResult(intent);
            location = locationResult.getLastLocation();
            if (location != null) {
                lat = location.getLatitude();
                lang = location.getLongitude();
                Log.d(TAG, "accuracy: " + location.getAccuracy() + " lat: " + location.getLatitude() + " lon: " + location.getLongitude());
                //showNotification(String.valueOf(location.getIn_latitude()), String.valueOf(location.getOut_longitude()));
            }
        }

    }

}
