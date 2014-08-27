package com.xs.oneplustools.tools;

public class CreateDir {
	public static void Mkdir () {
		RootCmd.RunRootCmd("mkdir /sdcard/OneplusTools/");
		RootCmd.RunRootCmd("mkdir /sdcard/OneplusTools/Backup/");
		RootCmd.RunRootCmd("mkdir /sdcard/OneplusTools/Flash/");
	}
}
