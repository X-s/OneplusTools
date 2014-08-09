package com.xs.oneplustools.activity;

import java.util.Date;

import com.umeng.analytics.MobclickAgent;
import com.xs.oneplustools.R;
import com.xs.oneplustools.tools.RootCmd;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.Toast;

public class FlashRomActivity extends PreferenceActivity {

	private static final String FLASH_COLOR_RECOVERY = "flash_color_recovery";
	private static final String FLASH_OTHER_RECOVERY = "flash_other_recovery";
	private static final String FLASH_BACKUP_RECOVERY = "flash_backup_recovery";
	private static final String FLASH_BACKUP_MODEM = "flash_backup_modem";
	private static final String FLASH_BACKUP_BOOT = "flash_backup_boot";
	private static final String FLASH_BOOT = "flash_boot";

	private Preference mFlashColorRecovery;
	private Preference mFlashOtherRecovery;
	private Preference mFlashBackupRecovery;
	private Preference mFlashBackupModem;
	private Preference mFlashBackupBoot;
	private Preference mFlashBoot;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.activity_flashrom);

		mFlashColorRecovery = (Preference) findPreference(FLASH_COLOR_RECOVERY);
		mFlashOtherRecovery = (Preference) findPreference(FLASH_OTHER_RECOVERY);
		mFlashBackupRecovery = (Preference) findPreference(FLASH_BACKUP_RECOVERY);
		mFlashBackupModem = (Preference) findPreference(FLASH_BACKUP_MODEM);
		mFlashBackupBoot = (Preference) findPreference(FLASH_BACKUP_BOOT);
		mFlashBoot = (Preference) findPreference(FLASH_BOOT);
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
		if (preference == mFlashBackupBoot) {
			new AlertDialog.Builder(FlashRomActivity.this)
					.setTitle(R.string.confirm)
					.setMessage(R.string.flash_backup_boot_confirm)
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// 获取当前系统时间
									Date date = new Date(System
											.currentTimeMillis());
									RootCmd.RunRootCmd("dd if=/dev/block/platform/msm_sdcc.1/by-name/boot of=/sdcard/OneplusTools/Backup/'"
											+ date + "'+boot.img");
									Toast.makeText(getApplicationContext(),
											R.string.flash_backup_ok,
											Toast.LENGTH_SHORT).show();
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mFlashBackupModem) {
			new AlertDialog.Builder(FlashRomActivity.this)
					.setTitle(R.string.confirm)
					.setMessage(R.string.flash_backup_modem_confirm)
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// 获取当前系统时间
									Date date = new Date(System
											.currentTimeMillis());
									RootCmd.RunRootCmd("dd if=/dev/block/platform/msm_sdcc.1/by-name/modem of=/sdcard/OneplusTools/Backup/'"
											+ date + "'+modem.img");
									Toast.makeText(getApplicationContext(),
											R.string.flash_backup_ok,
											Toast.LENGTH_SHORT).show();
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mFlashBackupRecovery) {
			new AlertDialog.Builder(FlashRomActivity.this)
					.setTitle(R.string.confirm)
					.setMessage(R.string.flash_backup_recovery_confirm)
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// 获取当前系统时间
									Date date = new Date(System
											.currentTimeMillis());
									RootCmd.RunRootCmd("dd if=/dev/block/platform/msm_sdcc.1/by-name/recovery of=/sdcard/OneplusTools/Backup/'"
											+ date + "'+recovery.img");
									Toast.makeText(getApplicationContext(),
											R.string.flash_backup_ok,
											Toast.LENGTH_SHORT).show();
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mFlashOtherRecovery) {
			new AlertDialog.Builder(FlashRomActivity.this)
					.setTitle(R.string.confirm)
					.setMessage(R.string.flash_other_recovery_confirm)
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									RootCmd.RunRootCmd("dd if=/sdcard/OneplusTools/Flash/recovery.img of=/dev/block/platform/msm_sdcc.1/by-name/recovery");
									RootCmd.RunRootCmd("reboot recovery");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mFlashColorRecovery) {
			new AlertDialog.Builder(FlashRomActivity.this)
					.setTitle(R.string.confirm)
					.setMessage(R.string.flash_color_recovery_confirm)
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									RootCmd.RunRootCmd("dd if=/sdcard/Android/data/com.xs.oneplustools/files/color-recovery.img of=/dev/block/platform/msm_sdcc.1/by-name/recovery");
									RootCmd.RunRootCmd("reboot recovery");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		if (preference == mFlashBoot) {
			new AlertDialog.Builder(FlashRomActivity.this)
					.setTitle(R.string.confirm)
					.setMessage(R.string.flash_boot_confirm)
					.setNegativeButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									RootCmd.RunRootCmd("dd if=/sdcard/OneplusTools/Flash/boot.img of=/dev/block/platform/msm_sdcc.1/by-name/boot");
									RootCmd.RunRootCmd("reboot");
								}
							}).setPositiveButton(R.string.no, null).show();
		}
		return false;

	}
}