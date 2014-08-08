package com.xs.oneplustools;

import java.io.IOException;

import com.xs.oneplustools.R;
import com.xs.oneplustools.activity.AboutActivity;
import com.xs.oneplustools.activity.FlashRomActivity;
import com.xs.oneplustools.activity.GestureActivity;
import com.xs.oneplustools.activity.ModemActivity;
import com.xs.oneplustools.activity.PowerActivity;
import com.xs.oneplustools.activity.SoundActivity;
import com.xs.oneplustools.tools.AssetCopyer;
import com.xs.oneplustools.tools.RootCmd;

import android.app.ActivityGroup;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TabHost;
import android.widget.Toast;

//该类需要继承ActivityGroup
public class OneplusToolsMain extends ActivityGroup {
	private TabHost mTabHost;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Preference值
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		// 布局
		setContentView(R.layout.activity_tab_host);

		// 欢迎
		Toast.makeText(getApplicationContext(), R.string.welcome,
				Toast.LENGTH_SHORT).show();

		// 在程序启动时复制数据到SD卡
		AssetCopyer asset = new AssetCopyer(getBaseContext());
		try {
			asset.copy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 创建程序目录
		RootCmd.RunRootCmd("mkdir /sdcard/OneplusTools/");
		RootCmd.RunRootCmd("mkdir /sdcard/OneplusTools/Backup/");
		RootCmd.RunRootCmd("mkdir /sdcard/OneplusTools/Flash/");

		// 设置TabHost
		initTabs();
	}

	// 设置Tab列表
	private void initTabs() {
		mTabHost = (TabHost) findViewById(R.id.tabhost);
		mTabHost.setup(this.getLocalActivityManager());

		// 关于
		mTabHost.addTab(mTabHost
				.newTabSpec("tab_about")
				.setIndicator("关于",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(new Intent(this, AboutActivity.class)));

		// 电源
		mTabHost.addTab(mTabHost
				.newTabSpec("tab_power")
				.setIndicator("电源",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(new Intent(this, PowerActivity.class)));

		// 声音
		mTabHost.addTab(mTabHost
				.newTabSpec("tab_sound")
				.setIndicator("声音",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(new Intent(this, SoundActivity.class)));

		// 网络
		mTabHost.addTab(mTabHost
				.newTabSpec("tab_modem")
				.setIndicator("网络",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(new Intent(this, ModemActivity.class)));

		// 刷机
		mTabHost.addTab(mTabHost
				.newTabSpec("tab_flash")
				.setIndicator("刷机",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(new Intent(this, FlashRomActivity.class)));
		
		//手势
		mTabHost.addTab(mTabHost
				.newTabSpec("tab_gesture")
				.setIndicator("手势",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(new Intent(this, GestureActivity.class)));

		// 程序启动时首Tab
		mTabHost.setCurrentTab(0);
	}
}