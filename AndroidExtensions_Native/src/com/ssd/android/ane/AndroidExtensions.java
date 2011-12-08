package com.ssd.android.ane;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;
import com.adobe.fre.FREFunction;
import com.ssd.android.ane.functions.SMSFunction;
import com.ssd.android.ane.functions.ShareImgFunction;
import com.ssd.android.ane.functions.ShareTextFunction;
import com.ssd.android.ane.functions.ToastFunction;

/**
 * <p>
 * This class defines a simple AIR Native Extension exposing a set of simple
 * and useful Android native facilities:
 * 
 * <li>sharing</li>
 * <li>SMS sending</li>
 * <li>Toast messages</li>
 * </p>
 * 
 * @author mr_archano (twitter: @mr_archano)
 *
 */
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

	/**
	 * 
	 * The <code>FREContext</code> for this AIR Native Extension.
	 *
	 */
	protected class AndroidExtensionsContext extends FREContext {
		public static final String TAG = AndroidExtensions.TAG;
		
		@Override
		public void dispose() {
			Log.d(TAG,"Context disposed.");
		}

		@Override
		public Map<String, FREFunction> getFunctions() {
			Map<String, FREFunction> functions = new HashMap<String, FREFunction>();
			functions.put("shareText", new ShareTextFunction());
			functions.put("shareImage", new ShareImgFunction());
			functions.put("toast", new ToastFunction());
			functions.put("sendSMS", new SMSFunction());
			return functions;
		}
	}
}
