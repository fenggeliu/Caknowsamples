package com.caknow.customer.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

/**
 * Created by junu on 1/6/17.
 */

public enum SessionPreferences {
    INSTANCE;

    private Context ctx;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    static {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
    }

    public void init(final Context ctx) {
        this.ctx = ctx;
        this.sharedPreferences = ctx.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        this.sharedPreferencesEditor = this.sharedPreferences.edit();

    }

    public void resetCredentials() {
        final CookieManager cookieManager = (CookieManager) CookieHandler.getDefault();
        final java.net.CookieStore netCookieStore = cookieManager.getCookieStore();
        netCookieStore.removeAll();
        clearSharedPreferences();
    }

    private void clearSharedPreferences() {
        this.sharedPreferencesEditor.clear();
        this.sharedPreferencesEditor.commit();
    }

    public void setLongPref(final String preferenceKey, final Long value){
        this.sharedPreferencesEditor.putLong(preferenceKey, value);
        this.sharedPreferencesEditor.commit();
    }

    public void setBoolPref(final String preferenceKey, final boolean value){
        this.sharedPreferencesEditor.putBoolean(preferenceKey, value);
        this.sharedPreferencesEditor.commit();
    }

    public void setStringPref(final String preferenceKey, final String value){
        this.sharedPreferencesEditor.putString(preferenceKey, value);
        this.sharedPreferencesEditor.commit();
    }

    public void setFloatPref(final String preferenceKey, final float value){
        this.sharedPreferencesEditor.putFloat(preferenceKey, value);
        this.sharedPreferencesEditor.commit();
    }

    public boolean getBooleanPref(final String preferenceKey){
        return this.sharedPreferences.getBoolean(preferenceKey, false);
    }

    public long getLongPref(final String preferenceKey){
        return this.sharedPreferences.getLong(preferenceKey, 0);
    }

    public float getFloatPref(final String preferenceKey){
        return this.sharedPreferences.getFloat(preferenceKey, 0.0f);
    }

    public String getStringPref(final String preferenceKey){
        return this.sharedPreferences.getString(preferenceKey, "");
    }


}
