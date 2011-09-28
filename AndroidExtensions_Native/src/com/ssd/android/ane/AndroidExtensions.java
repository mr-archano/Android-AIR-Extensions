package com.ssd.android.ane;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class AndroidExtensions implements FREExtension {
	public static final String TAG = "AndroidExtensions";
	
	@Override
	public FREContext createContext(String arg0) {
		return new AndroidExtensionsContext();
	}
	
	@Override
	public void initialize() {
		Log.d(TAG, "Extension initialized.");
	}

	@Override
	public void dispose() {
		Log.d(TAG, "Extension disposed.");
	}

}
