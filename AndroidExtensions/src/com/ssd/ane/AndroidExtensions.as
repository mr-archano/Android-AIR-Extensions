package com.ssd.ane
{
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	import flash.system.Capabilities;

	
	/**
	 * This class defines a simple AIR Native Extension exposing a set of simple
	 * and useful Android native facilities:
	 * 
	 * <li>sharing (image or text)</li>
	 * <li>SMS sending</li>
	 * <li>Toast messages</li>
	 * 
	 * 
	 * @author mr_archano (twitter: mr_archano)
	 * 
	 */
	public class AndroidExtensions
	{
		/////////////////////////////////////////
		// Static fields
		//
		
		protected static var _dispatcher:EventDispatcher = new EventDispatcher();
		protected static var _context:ExtensionContext = null;
		
		
		/////////////////////////////////////////
		// Business functions
		//
		
		/**
		 * Used to share some text/plain content via Android native facility. This function pops up
		 * a chooser dialog letting the user to select the app to be used for sharing.
		 * 
		 * 
		 * @param subject a short description, used eventually as subject during sharing.
		 * @param text the content to be shared (text/plain, but can contain links as well).
		 * @param chooserTitle the title of the chooser dialog shown to the user.
		 * 
		 */
		public static function shareText(subject:String, text:String, chooserTitle:String) : void
		{
			if( isSupported )
				context.call("shareText", subject, text, chooserTitle);
			else
				trace(	"DEBUG: called AndroidExtensions.shareText\n" +
						"- subject: "+subject+"\n" +
						"- text: "+text+"\n" +
						"- chooserTitle: "+chooserTitle+"\n");
		}
		
		
		
		/**
		 * Used to share an image via Android native facility. This function pops up
		 * a chooser dialog letting the user to select the app to be used for sharing.
		 * 
		 * 
		 * @param imgPath the absolute path of the image to be shared.
		 * @param chooserTitle the title of the chooser dialog shown to the user.
		 * 
		 */
		public static function shareImage(imgPath:String, chooserTitle:String) : void
		{
			if( isSupported )
				context.call("shareImage", imgPath, chooserTitle);
			else
				trace(	"DEBUG: called AndroidExtensions.shareImage\n" +
					"- imgPath: "+imgPath+"\n" +
					"- chooserTitle: "+chooserTitle+"\n");
		}
		
		
		
		/**
		 * Used to prompt an Android Toast message on the screen.
		 * @param text			the text to be shown.
		 * @param showForLong	set the duration of the toast. 
		 * 						If true, the value <code>Toast.LENGTH_LONG</code> is used.
		 * 						<code>Toast.LENGTH_SHORT</code> otherwise.
		 * 
		 */
		public static function toast(text:String, showForLong:Boolean = false) : void
		{
			if( isSupported )
				context.call("toast", text, showForLong);
			else
				trace(	"DEBUG: called AndroidExtensions.toast\n" +
					"- text: "+text+"\n" +
					"- showForLong: "+showForLong+"\n");
		}
		
		
		/**
		 * Used to compose an SMS via tha native Android application.
		 * <strong>NOTE:</strong> if multiple apps for SMS handling are installed,
		 * the user is asked to select which one to use.
		 * 
		 * @param text the body of SMS
		 * @param recipient the number of recipient (optional)
		 * 
		 */		
		public static function sendSMS(text:String, recipient:String=null) : void
		{
			if( isSupported )
				context.call("sendSMS", text, recipient);
			else
				trace(	"DEBUG: called AndroidExtensions.sendSMS\n" +
					"- text: "+text+"\n" +
					"- recipient: "+recipient+"\n");
		}
		
		
		/**
		 * Used to dispose the extension.
		 * 
		 */
		public static function dispose() : void
		{
			if(_context)
			{
				_context.removeEventListener(StatusEvent.STATUS, onStatusEventHandler);
				_context.dispose();
			}
			_context = null;
		}
		
		/////////////////////////////////////////
		// Event handling functions
		//
		
		public static function addEventListener(type:String, listener:Function) : void
		{
			_dispatcher.addEventListener(type, listener);
		}
		
		public static function removeEventListener(type:String, listener:Function) : void
		{
			_dispatcher.removeEventListener(type,listener);
		}
		
		
		
		
		/////////////////////////////////////////
		// Protected functions
		//
		
		protected static function get context() : ExtensionContext
		{
			if(!_context)
			{
				_context = ExtensionContext.createExtensionContext("com.ssd.ane.androidextensions","");
				_context.addEventListener(StatusEvent.STATUS, onStatusEventHandler);
			}
			return _context;
		}
		
		protected static function get isSupported() : Boolean
		{
			return (Capabilities.os.indexOf("Linux") >= 0);
		}
		
		
		/////////////////////////////////////////
		// Callbacks
		//
		
		protected static function onStatusEventHandler(event:StatusEvent) : void
		{
			if(event == null || event.level == null)
				return;
			
			// debug
			trace("[AndroidExtensions.onStatusHandler] level: "+event.level);
			
			// handle StatusEvent
			switch(event.level)
			{
				case AndroidExtensionsEvent.TYPE_ERROR:
					onError();
					break;
				/* TODO: add cases here ... */
			}
		}
		
		protected static function onError() : void
		{
			_dispatcher.dispatchEvent(new AndroidExtensionsEvent(AndroidExtensionsEvent.TYPE_ERROR));
		}
	}
}