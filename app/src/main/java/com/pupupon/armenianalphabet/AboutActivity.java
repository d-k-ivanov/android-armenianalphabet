package com.pupupon.armenianalphabet;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		Typeface mainFont = Tools.setFont(this);

		TextView aboutText = (TextView) findViewById(R.id.aboutText);
		TextView aboutCopyright = (TextView) findViewById(R.id.aboutCopyright);
		aboutText.setTypeface(mainFont);
		aboutCopyright.setTypeface(mainFont);


	}
}
