package com.apps.nosacikal.removie.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {

    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor prefEditor;

    // tag
    private static final String SHAREPREF = "walkthrough";
    private static final String FIRSTLAUNCH = "firstLaunch";

    @SuppressLint("CommitPrefEdits")
    public SharePref(Context context){
        this.context   = context;
        int privateMode = 0;
        pref            = context.getSharedPreferences(SHAREPREF, privateMode);
        prefEditor          = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFisTime){
        prefEditor.putBoolean(FIRSTLAUNCH, isFisTime);
        prefEditor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(FIRSTLAUNCH, true);
    }
}
