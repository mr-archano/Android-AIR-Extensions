package com.ssd.android.ane;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.ssd.android.ane.functions.SMSFunction;
import com.ssd.android.ane.functions.ShareFunction;
import com.ssd.android.ane.functions.ToastFunction;

public class AndroidExtensionsContext extends FREContext {
	public static final String TAG = AndroidExtensions.TAG;
	
	@Override
	public void dispose() {
		Log.d(TAG,"Context disposed.");
	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		Map<String, FREFunction> functions = new HashMap<String, FREFunction>();
		functions.put("share", new ShareFunction());
		functions.put("toast", new ToastFunction());
		functions.put("sendSMS", new SMSFunction());
		return functions;
	}

}
