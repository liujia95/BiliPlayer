package me.liujia95.biliplayer.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceUtils {
	
	private static SharedPreferences sp;
	private static SharedPreferences getPreferences(){
		if(sp==null){
			sp = UIUtils.getContext().getSharedPreferences("smartBeiJing",Context.MODE_PRIVATE);
		}
		return sp;
	}
	
	/*------------------ boolean -----------------*/
	public static boolean getBoolean(String key, boolean defValue){
		return getPreferences().getBoolean(key, defValue);
	}
	
	public static boolean getBoolean(String key){
		return getBoolean(key, false);
	}
	
	public static void putBoolean(String key, boolean value){
		Editor edit = getPreferences().edit();
		edit.putBoolean(key, value);
		edit.commit();
	}
	
	/*------------------ String -----------------*/
	public static String getString(String key, String defValue){
		return getPreferences().getString(key, defValue);
	}
	
	public static String getString(String key){
		return getString(key, null);
	}
	
	public static void putString(String key, String value){
		Editor edit = getPreferences().edit();
		edit.putString(key, value);
		edit.commit();
	}
	
}
