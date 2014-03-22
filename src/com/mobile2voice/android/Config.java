package com.mobile2voice.android;

public interface Config {
	
	//used to share GCM regId with application server
	
	//static final String APP_SERVER_URL = "http://64.20.56.232/gcm/gcm.php?shareRegId=1";
	
	static final String APP_SERVER_URL = "http://64.20.56.232:8080/GCM-App-Server/GCMNotification?shareRegId=1";	

	// Google Project Number
	static final String GOOGLE_PROJECT_ID = "6276034927";
	
	static final String SHOW_MESSAGE = "com.mobile2voice.android.DISPLAY_MESSAGE";	
	static final String EXTRA_MESSAGE = "message";

}
