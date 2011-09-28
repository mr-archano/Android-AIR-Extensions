package com.ssd.ane
{
	import flash.events.Event;
	
	public class AndroidExtensionsEvent extends Event
	{
		public static const TYPE_ERROR:String = "error";
		
		public function AndroidExtensionsEvent(type:String, bubbles:Boolean=true, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
		override public function clone():Event
		{
			return new AndroidExtensionsEvent(this.type, this.bubbles, this.cancelable);
		}
		
		
	}
}