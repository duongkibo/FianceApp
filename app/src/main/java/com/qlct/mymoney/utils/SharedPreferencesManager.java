package com.qlct.mymoney.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SharedPreferencesManager {
    private SharedPreferences mPreferences;
    private static SharedPreferencesManager sharedPreferencesManager;

    public SharedPreferencesManager(Context mContext) {
        mPreferences = mContext.getSharedPreferences(Constants.FILE_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesManager getDefault(Context mContext) {
        if (sharedPreferencesManager == null) {
            sharedPreferencesManager = new SharedPreferencesManager(mContext);
        }
        return sharedPreferencesManager;
    }

    public static synchronized SharedPreferencesManager getDefault() {
        return sharedPreferencesManager;
    }

    public void savePreference(String key, Object data) {
        SharedPreferences.Editor editor = mPreferences.edit();
        if (data instanceof String) {
            editor.putString(key, String.valueOf(data));
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Set) {
            editor.putStringSet(key, (Set<String>) data);
        }
        editor.apply();
    }

    public Object getPreference(String key, Object defaultValue) {
        if (defaultValue instanceof String) {
            return mPreferences.getString(key, String.valueOf(defaultValue));
        } else if (defaultValue instanceof Integer) {
            return mPreferences.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Long) {
            return mPreferences.getLong(key, (Long) defaultValue);
        } else if (defaultValue instanceof Float) {
            return mPreferences.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return mPreferences.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Set) {
            return mPreferences.getStringSet(key, (Set<String>) defaultValue);
        }
        return null;
    }

    public void removeKeyPreference(String keyName) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.remove(keyName);
        editor.apply();
    }

    public String getFireBaseToken() {
        return (String) getPreference(SharedPreferencesConstants.KEY_TOKEN_LOGIN, "");
    }

    public void saveFireBaseToken(String apiToken) {
        savePreference(SharedPreferencesConstants.KEY_TOKEN_LOGIN, apiToken);
    }


    public String getCurrentCountry() {
        return (String) getPreference(SharedPreferencesConstants.KEY_CURRENT_COUNTRY, Constants.ISO_VIETNAM);
    }

    public void saveCurrentCountry(String country) {
        savePreference(SharedPreferencesConstants.KEY_CURRENT_COUNTRY, country);
    }

    public String getCountryCodeWithPlus() {
        return (String) getPreference(SharedPreferencesConstants.KEY_COUNTRY_CODE_WITH_PLUS, "+" + Constants.COUNTRY_CODE_VN);
    }

    public void saveCountryCodeWithPlus(String countryCode) {
        savePreference(SharedPreferencesConstants.KEY_COUNTRY_CODE_WITH_PLUS, countryCode);
    }

}
