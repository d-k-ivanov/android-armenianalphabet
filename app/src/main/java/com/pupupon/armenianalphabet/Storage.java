package com.pupupon.armenianalphabet;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

class Storage {
    private static final String EASTERN_ARMENIAN = "eastern_armenian";

    static void init(Context context) {
        Hawk.init(context).build();
    }

    static boolean getEasternArmenian() {
        return Hawk.get(EASTERN_ARMENIAN, true);
    }

    static void setEasternArmenian(boolean bool) {
        Hawk.put(EASTERN_ARMENIAN, bool);
    }
}
