package com.coderstudio.tomliang.fe_webviewtool.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.coderstudio.tomliang.fe_webviewtool.AppContext;

/**
 * Created by lianghangbing on 16/8/3.
 */
public class SpUtils {
    private SpUtils(){

    }

    private static SharedPreferences.Editor mEditor;
    private static SharedPreferences mSharedPreferences;

    static final String SET_DEBUG = "prefs_set_debug";
    static final String SET_AUTOFLUSH = "prefs_set_auto_flush";

    static {
        mSharedPreferences = AppContext.getInstance().getSharedPreferences("mars_sp", Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public static void setDebugEnable(boolean flag){
        mEditor.putBoolean(SET_DEBUG, flag);
        mEditor.commit();
    }

    public static boolean isDebugEnable(){
        return mSharedPreferences.getBoolean(SET_DEBUG, false);
    }

    public static void setAutoFlush(boolean flag){
        mEditor.putBoolean(SET_AUTOFLUSH, flag);
        mEditor.commit();
    }

    public static boolean isAutoFlush(){
        return mSharedPreferences.getBoolean(SET_AUTOFLUSH, false);
    }
}
