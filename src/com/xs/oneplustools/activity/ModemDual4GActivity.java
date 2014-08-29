package com.xs.oneplustools.activity;

import com.umeng.analytics.MobclickAgent;
import com.xs.oneplustools.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ModemDual4GActivity extends PreferenceActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modem_dual_4g);
	}
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

}