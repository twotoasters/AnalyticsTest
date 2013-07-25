package com.twotoasters.analyticstest;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class CampaignReceiver extends BroadcastReceiver {
    static final String TAG = CampaignReceiver.class.getSimpleName();


    @Override
    public void onReceive(Context context, Intent intent) {

        String rawReferrer = intent.getStringExtra("referrer");

        Log.d(TAG, "received broadcast");

        if (rawReferrer != null) {
            Log.d(TAG, "raw: " + rawReferrer);

            String referrer = "";

            try {
                referrer = URLDecoder.decode(rawReferrer, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace(); // This should not happen.
            }

            Log.d(TAG, "decoded: " + referrer);

            SharedPreferences.Editor prefs = App.getAppPrefs().edit();
            prefs.putString(CampaignHelper.REFERRER, referrer).commit();
        } else {
            Log.e(TAG, "referrer is null");
        }
    }



}
