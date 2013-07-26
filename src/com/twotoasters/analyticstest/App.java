package com.twotoasters.analyticstest;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class App extends Application {

	static App instance;
	static SharedPreferences prefs;
	
	@Override
	public void onCreate() {
		instance = this;
		super.onCreate();
	}
	
	public static App getInstance() {
		return instance;
	}
	
	public static SharedPreferences getAppPrefs() {
        if (prefs == null) {
            prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        }
        return prefs;
	}
	
	
}
