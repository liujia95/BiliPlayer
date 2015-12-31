package me.liujia95.biliplayer.utils;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				LogUtils.e(e);
			}
		}
		return true;
	}
}
