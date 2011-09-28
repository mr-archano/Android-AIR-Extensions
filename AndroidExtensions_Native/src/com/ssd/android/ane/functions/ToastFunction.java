package com.ssd.android.ane.functions;

import android.util.Log;
import android.widget.Toast;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.ssd.android.ane.AndroidExtensions;

/**
 * <p>
 * This function exposes the Android toast facility. In order to use this function
 * you have to provide:
 * 
 * <li> the text for the toast</li>
 * <li> the duration type for the toast (long or short)</li>
 * </p>
 * 
 * @author mr_archano (twitter: @mr_archano)
 *
 */
public class ToastFunction implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		if(args == null || args.length < 1) {
			Log.e(AndroidExtensions.TAG, "Invalid arguments number for ToastFunction! (requested: text, optional: duration type)");
			return null;
		}
		
		try{
			String text = args[0].getAsString();
			int toastType = Toast.LENGTH_SHORT;
			
			if(args.length == 2 && args[1].getAsBool())
				toastType = Toast.LENGTH_LONG;
			
			Toast.makeText(context.getActivity(), text, toastType).show();
		}
		catch(Exception e) {
			Log.e(AndroidExtensions.TAG, "Error: "+e.getMessage(), e);
		}
		return null;
	}

}
