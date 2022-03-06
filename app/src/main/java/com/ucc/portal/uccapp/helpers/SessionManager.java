package com.ucc.portal.uccapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {
    private static final String TAG = SessionManager.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    private  final String KEY_IS_LOGGEDIN = "isLoggedIn";
    public SessionManager(Context context){
        mContext = context;
        String PREF_NAME = "androidAPI";
        int PRIVATE_MODE = 0;
        mSharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mSharedPreferences.edit();
    }

    public void setLogIn(boolean isLoggedIn){
        mEditor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        mEditor.commit();
        Log.d(TAG, "Login data has been modified");
    }
    public boolean isLoggedIn(){
        return mSharedPreferences.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}
