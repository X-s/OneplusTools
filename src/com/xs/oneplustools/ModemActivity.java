package com.xs.oneplustools;

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
		addPreferencesFromResource(R.xml.modem);
		mFlashChinaMobile = (Preference) findPreference(FLASH_CHINAMOBILE);
		mFlashChinaUnicom = (Preference) findPreference(FLASH_CHINAUNICOM);
	}

	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {

		if (preference == mFlashChinaMobile) {
			new AlertDialog.Builder(ModemActivity.this)
					.setTitle(R.string.confirm)
					.setMessage("��ȷ��ˢ���ƶ��źŻ���ô���⽫�������ֻ���")
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									FlashRom.flashRom(
											getApplicationContext(),
											"/sdcard/Android/data/com.xs.oneplustools/files/Oneplus One ChinaMobile.zip");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mFlashChinaUnicom) {
			new AlertDialog.Builder(ModemActivity.this)
					.setTitle(R.string.confirm)
					.setMessage("��ȷ��ˢ����ͨ�źŻ���ô���⽫�������ֻ���")
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									FlashRom.flashRom(
											getApplicationContext(),
											"/sdcard/Android/data/com.xs.oneplustools/files/Oneplus One ChinaUnicom.zip");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		return false;

	}
}