package me.liujia95.biliplayer.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class PackageInfoUtils {

	public static int getVersionCode(){
		PackageManager pm = UIUtils.getContext().getPackageManager();
		try {
			PackageInfo packageInfo = pm.getPackageInfo(UIUtils.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static String getVersionName(){
		PackageManager pm = UIUtils.getContext().getPackageManager();
		try {
			PackageInfo packageInfo = pm.getPackageInfo(UIUtils.getPackageName(), 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
