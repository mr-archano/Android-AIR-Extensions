package com.ssd.android.ane.functions;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.ssd.android.ane.AndroidExtensions;

/**
 * <p>
 * This function exposes the Android share facility for images.
 * In order to use this function you have to provide:
 * 
 * <li> the absolute path of the image to be shared</li>
 * <li> the title for the native chooser dialog</li>
 * </p>
 * @author mr_archano (twitter: @mr_archano)
 *
 */
public class ShareImgFunction implements FREFunction {
	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		if(args == null || args.length != 2) {
			Log.e(AndroidExtensions.TAG, "Invalid arguments number for ShareFunction! (requested: subject, text and title)");
			return null;
		}
		
		try{
			String imgPath = args[0].getAsString();
			String title = args[1].getAsString();
			
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("image/*");
			shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+imgPath));
			context.getActivity().startActivity(Intent.createChooser(shareIntent, title));
		}
		catch(Exception e) {
			Log.e(AndroidExtensions.TAG, "Error: "+e.getMessage(), e);
		}
		return null;
	}
}
