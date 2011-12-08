package com.ssd.android.ane.functions;

import android.content.Intent;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.ssd.android.ane.AndroidExtensions;

/**
 * <p>
 * This function exposes the Android share facility for text/plain content. 
 * In order to use this function you have to provide:
 * 
 * <li> a subject for the stuff you want to share </li>
 * <li> the text content (may contain links as well)</li>
 * <li> the title for the native chooser dialog</li>
 * </p>
 * @author mr_archano (twitter: @mr_archano)
 *
 */
public class ShareTextFunction implements FREFunction {
	
	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		if(args == null || args.length != 3) {
			Log.e(AndroidExtensions.TAG, "Invalid arguments number for ShareFunction! (requested: subject, text and title)");
			return null;
		}

		try {
			String subject = args[0].getAsString();
			String text = args[1].getAsString();
			String title = args[2].getAsString();

			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
			shareIntent.putExtra(Intent.EXTRA_TEXT, text);
			context.getActivity().startActivity(Intent.createChooser(shareIntent, title));
		}
		catch(Exception e) {
			Log.e(AndroidExtensions.TAG, "Error: "+e.getMessage(), e);
		}
		return null;
	}

}
