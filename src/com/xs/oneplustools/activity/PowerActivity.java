package com.xs.oneplustools.activity;

import com.umeng.analytics.MobclickAgent;
import com.xs.oneplustools.R;
import com.xs.oneplustools.tools.RootCmd;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class PowerActivity extends PreferenceActivity {

	private static final String POWER_SHUTDOWN = "power_shutdown";
	private static final String POWER_REBOOT = "power_reboot";
	private static final String POWER_REBOOT_RECOVERY = "power_reboot_recovery";
	private static final String POWER_REBOOT_BOOTLOADER = "power_reboot_bootloader";

	private Preference mPowerShutdown;
	private Preference mPowerReboot;
	private Preference mPowerRebootRecovery;
	private Preference mPowerRebootBootloader;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.activity_power);

		mPowerShutdown = (Preference) findPreference(POWER_SHUTDOWN);
		mPowerReboot = (Preference) findPreference(POWER_REBOOT);
		mPowerRebootRecovery = (Preference) findPreference(POWER_REBOOT_RECOVERY);
		mPowerRebootBootloader = (Preference) findPreference(POWER_REBOOT_BOOTLOADER);

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
		if (preference == mPowerShutdown) {
			new AlertDialog.Builder(PowerActivity.this)
					.setTitle(R.string.confirm)
					.setMessage("您确定关闭手机？")
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									RootCmd.RunRootCmd("reboot -p");
									RootCmd.RunRootCmd("poweroff");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mPowerReboot) {
			new AlertDialog.Builder(PowerActivity.this)
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
			new AlertDialog.Builder(PowerActivity.this)
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
			new AlertDialog.Builder(PowerActivity.this)
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
		return false;

	}
}