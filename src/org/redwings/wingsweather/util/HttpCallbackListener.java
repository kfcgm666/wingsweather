package org.redwings.wingsweather.util;

public interface HttpCallbackListener {

	void onFinish(String response);
	
	void onError(Exception e);
}
