package com.xs.oneplustools;

import java.io.IOException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.widget.Toast;
import android.content.SharedPreferences;

public class OneplusToolsMainActivity extends PreferenceActivity implements
		OnPreferenceChangeListener {

	private static final String FLASH_CHINAMOBILE = "flash_chinamobile";
	private static final String FLASH_CHINAUNICOM = "flash_chinaunicom";
	private static final String POWER_SHUTDOWN = "power_shutdown";
	private static final String POWER_REBOOT = "power_reboot";
	private static final String POWER_REBOOT_RECOVERY = "power_reboot_recovery";
	private static final String POWER_REBOOT_BOOTLOADER = "power_reboot_bootloader";
	private static final String FLASH_COLOR_RECOVERY = "flash_color_recovery";
	private static final String FLASH_OTHER_RECOVERY = "flash_other_recovery";

	private Preference mFlashChinaMobile;
	private Preference mFlashChinaUnicom;
	private Preference mPowerShutdown;
	private Preference mPowerReboot;
	private Preference mPowerRebootRecovery;
	private Preference mPowerRebootBootloader;
	private Preference mFlashColorRecovery;
	private Preference mFlashOtherRecovery;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		addPreferencesFromResource(R.xml.oneplustools);

		Toast.makeText(getApplicationContext(),
				"欢迎使用一加工具箱，作者XS\n微博地址：weibo.com/acexs\n交流群：333932217",
				Toast.LENGTH_SHORT).show();

		// 程序启动时复制assets数据到SD卡
		AssetCopyer asset = new AssetCopyer(getBaseContext());
		try {
			asset.copy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mFlashChinaMobile = (Preference) findPreference(FLASH_CHINAMOBILE);
		mFlashChinaUnicom = (Preference) findPreference(FLASH_CHINAUNICOM);
		mPowerShutdown = (Preference) findPreference(POWER_SHUTDOWN);
		mPowerReboot = (Preference) findPreference(POWER_REBOOT);
		mPowerRebootRecovery = (Preference) findPreference(POWER_REBOOT_RECOVERY);
		mPowerRebootBootloader = (Preference) findPreference(POWER_REBOOT_BOOTLOADER);
		mFlashColorRecovery = (Preference) findPreference(FLASH_COLOR_RECOVERY);
		mFlashOtherRecovery = (Preference) findPreference(FLASH_OTHER_RECOVERY);
	}

	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		if (preference == mFlashOtherRecovery) {
			new AlertDialog.Builder(OneplusToolsMainActivity.this)
					.setTitle(R.string.confirm)
					.setMessage("您确定刷入第三方Recovery？")
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									RootCmd.RunRootCmd("dd if=/sdcard/recovery.img of=/dev/block/platform/msm_sdcc.1/by-name/recovery");
									RootCmd.RunRootCmd("reboot recovery");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mFlashColorRecovery) {
			new AlertDialog.Builder(OneplusToolsMainActivity.this)
					.setTitle(R.string.confirm)
					.setMessage("您确定刷入官方Recovery？")
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									RootCmd.RunRootCmd("dd if=/sdcard/Android/data/com.xs.oneplustools/files/color-recovery.img of=/dev/block/platform/msm_sdcc.1/by-name/recovery");
									RootCmd.RunRootCmd("reboot recovery");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mPowerShutdown) {
			new AlertDialog.Builder(OneplusToolsMainActivity.this)
					.setTitle(R.string.confirm)
					.setMessage("您确定关闭手机？")
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									RootCmd.RunRootCmd("poweroff");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mPowerReboot) {
			new AlertDialog.Builder(OneplusToolsMainActivity.this)
					.setTitle(R.string.confirm)
					.setMessage("您确定重启手机？")
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									RootCmd.RunRootCmd("reboot");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mPowerRebootRecovery) {
			new AlertDialog.Builder(OneplusToolsMainActivity.this)
					.setTitle(R.string.confirm)
					.setMessage("您确定重启进入恢复模式？")
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									RootCmd.RunRootCmd("reboot recovery");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mPowerRebootBootloader) {
			new AlertDialog.Builder(OneplusToolsMainActivity.this)
					.setTitle(R.string.confirm)
					.setMessage("您确定重启进入Fastboot模式？")
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									RootCmd.RunRootCmd("reboot bootloader");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mFlashChinaMobile) {
			new AlertDialog.Builder(OneplusToolsMainActivity.this)
					.setTitle(R.string.confirm)
					.setMessage("您确定刷入移动信号基带么，这将会重启手机？")
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									FlashRom.flashRom(
											getApplicationContext(),
											"/sdcard/Android/data/com.xs.oneplustools/files/Oneplus One ChinaMobile 3G$4G.zip");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mFlashChinaUnicom) {
			new AlertDialog.Builder(OneplusToolsMainActivity.this)
					.setTitle(R.string.confirm)
					.setMessage("您确定刷入联通信号基带么，这将会重启手机？")
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									FlashRom.flashRom(
											getApplicationContext(),
											"/sdcard/Android/data/com.xs.oneplustools/files/Oneplus One ChinaUnicom 3G$4G.zip");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		return false;
	}

	@Override
	public boolean onPreferenceChange(Preference arg0, Object arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}