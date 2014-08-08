package com.xs.oneplustools;

import com.xs.oneplustools.activity.GestureActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//开机启动类，黑屏手势开机恢复数据

public class RestoreGesture extends BroadcastReceiver {
	
	public void onReceive(Context paramContext, Intent paramIntent) {
		GestureActivity.RestoreDoubleTapToWake(paramContext);
		GestureActivity.RestoreCameraGesture(paramContext);
		GestureActivity.RestoreMusicGesture(paramContext);
	}

}
