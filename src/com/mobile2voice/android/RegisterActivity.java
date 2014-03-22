package com.mobile2voice.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;

public class RegisterActivity extends Activity {

	Button btnGCMRegister;
	Button btnAppShare;

	String regId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		final AppUtil appUtil = (AppUtil) getApplicationContext();

		// need Internet to connect with GCM
		if (!appUtil.isInternetAvailable()) {
			appUtil.showAlert(RegisterActivity.this,
					"Internet Not Available.", "Internet Connection Required.",
					false);
			return;
		}

		btnGCMRegister = (Button) findViewById(R.id.btnGCMRegister);
		btnGCMRegister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(regId)) {
					regId = registerGCM();
					Log.d("RegisterActivity", "GCM RegId: " + regId);
					Toast.makeText(getApplicationContext(),
							"Registered with GCM Server.", Toast.LENGTH_LONG)
							.show();
				} else {
					Toast.makeText(getApplicationContext(),
							"Already Registered with GCM Server!",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		btnAppShare = (Button) findViewById(R.id.btnAppShare);
		btnAppShare.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),
						MainActivity.class);
				i.putExtra("regId", regId);
				startActivity(i);
				finish();
			}
		});
	}

	public String registerGCM() {
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		String regId = GCMRegistrar.getRegistrationId(this);
		if (TextUtils.isEmpty(regId)) {
			GCMRegistrar.register(this, Config.GOOGLE_PROJECT_ID);
			regId = GCMRegistrar.getRegistrationId(this);
		}
		return regId;
	}
}
