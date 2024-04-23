/*
 *
 *  * Copyright (c) 2023
 *  * Created By: @keerthivs
 *  * Project: Qatalyst
 *  *
 *  * The contents should not be copied.
 *
 */

package org.example.fileReaderSetup;

public class FileGetParams {

	// test and return the calling values config file value
	public static String paramLookUp(String key) {
		char first = key.toCharArray()[0];
		char last = key.toCharArray()[key.length() - 1];
		if ((first == '$') && (last == '$')) {
			key = key.replace("$", "");
			key = key.trim();
		}

		return FilePropertyManager.getInstance().GetValueGivenKey(key);
	}

	public static void setVal(String key, String value) {
		FilePropertyManager.getInstance().setValue(key, value);
	}

	public static void replaceVal(String key, String value){
		FilePropertyManager.getInstance().replaceValue(key, value);
	}
}
