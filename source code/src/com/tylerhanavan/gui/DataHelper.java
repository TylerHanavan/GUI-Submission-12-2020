package com.tylerhanavan.gui;

import java.text.SimpleDateFormat;

public class DataHelper {

	/**
	 * Check if the data is in YYYYMMDD format
	 * @param data The date
	 * @return True if in proper format
	 */
	public static boolean isValidDate(String data) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		format.setLenient(false);
		try {
			format.parse(data);
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	
}
