package com.pupupon.armenianalphabet;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

public class Storage {
    private static String EASTERN_ARMENIAN = "eastern_armenian";

    public static void init (Context context) {
        Hawk.init(context).build();
    }

    public static void setEasternArmenian(boolean bool) {
        Hawk.put(EASTERN_ARMENIAN, bool);
    }

    public static boolean getEasternArmenian() {
        return Hawk.get(EASTERN_ARMENIAN, true);
    }
}
