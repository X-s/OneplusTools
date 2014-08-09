package com.xs.oneplustools.activity;

import com.umeng.analytics.MobclickAgent;
import com.xs.oneplustools.R;
import com.xs.oneplustools.tools.FlashRom;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class ModemActivity extends PreferenceActivity {

	private static final String FLASH_CHINAMOBILE = "flash_chinamobile";
	private static final String FLASH_CHINAUNICOM = "flash_chinaunicom";

	private Preference mFlashChinaMobile;
	private Preference mFlashChinaUnicom;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.activity_modem);
		mFlashChinaMobile = (Preference) findPreference(FLASH_CHINAMOBILE);
		mFlashChinaUnicom = (Preference) findPreference(FLASH_CHINAUNICOM);
	}
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {

		if (preference == mFlashChinaMobile) {
			new AlertDialog.Builder(ModemActivity.this)
					.setTitle(R.string.confirm)
					.setMessage("您确定刷入移动信号基带么，这将会重启手机？")
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									FlashRom.InstallZip(
											getApplicationContext(),
											"/sdcard/Android/data/com.xs.oneplustools/files/Oneplus One ChinaMobile.zip");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mFlashChinaUnicom) {
			new AlertDialog.Builder(ModemActivity.this)
					.setTitle(R.string.confirm)
					.setMessage("您确定刷入联通信号基带么，这将会重启手机？")
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									FlashRom.InstallZip(
											getApplicationContext(),
											"/sdcard/Android/data/com.xs.oneplustools/files/Oneplus One ChinaUnicom.zip");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		return false;

	}
}