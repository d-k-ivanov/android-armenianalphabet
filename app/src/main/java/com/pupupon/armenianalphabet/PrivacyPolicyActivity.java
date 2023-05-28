package com.pupupon.armenianalphabet;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PrivacyPolicyActivity extends Activity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy_activity);

        webView = findViewById(R.id.privacyWebView);

        WebSettings webSetting = webView.getSettings();
        webSetting.setBuiltInZoomControls(true);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/privacy_policy.html");
    }

    private static class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}
