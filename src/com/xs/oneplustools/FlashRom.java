package com.xs.oneplustools;

import java.io.File;
import android.content.Context;

public class FlashRom {
	private static File recoveryDir = new File("/cache/recovery");
	private static File command = new File(recoveryDir, "command");
	private static File log = new File(recoveryDir, "log");
	private static File extendedcommand = new File(recoveryDir,
			"extendedcommand");

	public static void flashRom(Context paramContext, String file) {

		String fileinstall = "install_zip(\"" + file + "\");" + "\n";
		RootCmd.RunRootCmd("mkdir " + recoveryDir);
		RootCmd.RunRootCmd("chmod 777 " + recoveryDir);
		RootCmd.RunRootCmd("echo '" + fileinstall + "' > " + extendedcommand);
		RootCmd.RunRootCmd("chmod 777 " + extendedcommand);
		RootCmd.RunRootCmd("chmod 777 " + log);
		RootCmd.RunRootCmd("reboot recovery");

	}

}