package com.twotoasters.analyticstest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class MainActivity extends Activity {

    TextView helloWorld;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloWorld = (TextView) findViewById(R.id.text_view_hello_world);

        String campaign = getPrefs().getString(CampaignHelper.REFERRER, getString(R.string.hello_world));
        helloWorld.setText(campaign);

        findViewById(R.id.button_reset).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                getPrefs().edit().putString(CampaignHelper.REFERRER, null).commit();
            }
        });

        setTextViews();

    }

    private void setTextViews() {
        ((TextView) findViewById(R.id.text_view_source)).setText(getString(R.string.source, CampaignHelper.getSource()));
        ((TextView) findViewById(R.id.text_view_medium)).setText(getString(R.string.medium, CampaignHelper.getMedium()));
        ((TextView) findViewById(R.id.text_view_term)).setText(getString(R.string.term, CampaignHelper.getTerm()));
        ((TextView) findViewById(R.id.text_view_content)).setText(getString(R.string.content, CampaignHelper.getContent()));
        ((TextView) findViewById(R.id.text_view_campaign)).setText(getString(R.string.campaign, CampaignHelper.getCampain()));
    }

    private SharedPreferences getPrefs() {
        if (prefs == null) {
            prefs = PreferenceManager.getDefaultSharedPreferences(this);
        }
        return prefs;
    }

    OnSharedPreferenceChangeListener listener = new OnSharedPreferenceChangeListener() {

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (sharedPreferences != null && key.equals(CampaignHelper.REFERRER)) {
                helloWorld.setText(sharedPreferences.getString(CampaignHelper.REFERRER, getString(R.string.hello_world)));
                setTextViews();
            }
        }
    };

    @Override
    protected void onPause() {
        getPrefs().unregisterOnSharedPreferenceChangeListener(listener);
        super.onPause();
    }

    @Override
    protected void onResume() {
        getPrefs().registerOnSharedPreferenceChangeListener(listener);
        super.onResume();
    }

}
