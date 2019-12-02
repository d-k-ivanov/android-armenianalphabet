package com.pupupon.armenianalphabet;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

class Storage {
    private static String EASTERN_ARMENIAN = "eastern_armenian";

    static void init(Context context) {
        Hawk.init(context).build();
    }

    static void setEasternArmenian(boolean bool) {
        Hawk.put(EASTERN_ARMENIAN, bool);
    }

    static boolean getEasternArmenian() {
        return Hawk.get(EASTERN_ARMENIAN, true);
    }
}
