package com.example.owner.nearplace;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;

public class Utility {

    public static String BASE_URL = "https://maps.googleapis.com";

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static String PLACE_PHOTO_BASE_URL = "https://maps.googleapis.com/maps/api/place/photo";


    public static final String RADIUS = "1500";


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean checkPlayServices(Context context, Activity activity) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(context);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(activity, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST);
            } else {
               // finish();
            }

            return false;
        }

        return true;
    }






    public static String buildPlacePhotoUrl(String imagePath) {
        Uri builtUri = Uri.parse(PLACE_PHOTO_BASE_URL).buildUpon()
                .appendQueryParameter("photoreference", imagePath)
                .appendQueryParameter("sensor", "false")
                .appendQueryParameter("maxheight", "1200")
                .appendQueryParameter("maxwidth", "1200")
                .appendQueryParameter("key", BuildConfig.PLACE_API_KEY)
                .build();
        return builtUri.toString();
    }

}
