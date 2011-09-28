package com.ssd.android.ane.functions;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.ssd.android.ane.AndroidExtensions;

/**
 * This function exposes the Android SMS facility. In order to use this function
 * you have to provide:
 * 
 * - the text for the SMS
 * - the telephone number of the recipient (optional)
 * 
 * @author mr_archano (twitter: @mr_archano)
 *
 */
public class SMSFunction implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		if(args == null || args.length < 1) {
			Log.e(AndroidExtensions.TAG, "Invalid arguments number for SMSFunction! (requested: text, optional: recipient)");
			return null;
		}
		
		try{
			String text = args[0].getAsString();
			String recipient = "";
			
			if(args.length == 2) recipient = args[1].getAsString();
			
			Intent sendIntent = new Intent(Intent.ACTION_VIEW);       
			sendIntent.setData(Uri.parse("sms:"+recipient));
			sendIntent.putExtra("sms_body", text); 
			context.getActivity().startActivity(sendIntent);
		}
		catch(Exception e) {
			Log.e(AndroidExtensions.TAG, "Error: "+e.getMessage(), e);
		}
		return null;
	}

}
