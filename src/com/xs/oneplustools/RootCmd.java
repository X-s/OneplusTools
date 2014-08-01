package com.xs.oneplustools;

import java.io.DataOutputStream;

import android.util.Log;

//RootCmd Shell√¸¡Ó
//RootCmd.RunRootCmd("shell");

public class RootCmd {
	private static final String LOG_TAG = "OneplusTools";

	public static int RunRootCmd(String paramString) {
		try {
			Log.i(LOG_TAG, paramString);
			Process localProcess = Runtime.getRuntime().exec("su");
			DataOutputStream localDataOutputStream = new DataOutputStream(
					localProcess.getOutputStream());
			String str = paramString + "\n";
			localDataOutputStream.writeBytes(str);
			localDataOutputStream.flush();
			localDataOutputStream.writeBytes("exit\n");
			localDataOutputStream.flush();
			localProcess.waitFor();
			return localProcess.exitValue();

		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return 1;

	}
}