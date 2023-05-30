package com.pupupon.armenianalphabet.utils;

import android.text.SpannableString;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

public class DefensiveURLSpan extends URLSpan {
    private final String mUrl;
    private final OnUrlListener mOnUrlListener;

    public DefensiveURLSpan(String url, OnUrlListener onUrlListener) {
        super(url);
        mUrl = url;
        mOnUrlListener = onUrlListener;
    }

    public static void setUrlClickListener(TextView tv, OnUrlListener onUrlListener) {
        SpannableString current = (SpannableString) tv.getText();
        URLSpan[] spans =
            current.getSpans(0, current.length(), URLSpan.class);

        for (URLSpan span : spans) {
            int start = current.getSpanStart(span);
            int end = current.getSpanEnd(span);

            current.removeSpan(span);
            current.setSpan(new DefensiveURLSpan(span.getURL(), onUrlListener), start, end,
                0);
        }

    }

    @Override
    public void onClick(View widget) {
        super.onClick(widget);
        mOnUrlListener.onClick(mUrl);
    }

    public interface OnUrlListener {
        void onClick(String url);
    }
}
