package com.pupupon.armenianalphabet.googleanalytics;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.pupupon.armenianalphabet.BuildConfig;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.ACTION_ORIENTATION_SCREEN;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.CATEGORY_USER_EVENTS;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.LABEL_LANDSCAPE;
import static com.pupupon.armenianalphabet.googleanalytics.GoogleAnalyticsConstants.LABEL_PORTRAIT;

@SuppressLint("Registered")
public class GoogleAnalyticsActivity extends AppCompatActivity {

    private final String ACTION = "action";
    private final String LABEL = "label";

    private final boolean DEBUG = BuildConfig.DEBUG;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (DEBUG) {
            Log.d(GoogleAnalyticsActivity.class.getSimpleName(), "screen : " + this.getLocalClassName());
        } else {
            mFirebaseAnalytics.setCurrentScreen(this, this.getLocalClassName(), null);
        }
    }

    protected void userAction(String action) {
        setEvent(CATEGORY_USER_EVENTS, action);
    }

    protected void userAction(String action, String label) {
        setEvent(CATEGORY_USER_EVENTS, action, label);
    }

    protected void setEvent(String category, String action) {
        setEvent(category, action, null);
    }

    protected void setEvent(String category, String action, String label) {
        Bundle bundle = new Bundle();
        bundle.putString(ACTION, action);
        if (label != null) bundle.putString(LABEL, label);
        if (DEBUG) {
            Log.d(GoogleAnalyticsActivity.class.getSimpleName(), "CATEGORY : " + category + " ACTION : " + action + " LABEL : " + label);
        } else {
            mFirebaseAnalytics.logEvent(category, bundle);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == SCREEN_ORIENTATION_PORTRAIT) {
            userAction(ACTION_ORIENTATION_SCREEN, LABEL_PORTRAIT);
        } else {
            userAction(ACTION_ORIENTATION_SCREEN, LABEL_LANDSCAPE);
        }
    }
}
