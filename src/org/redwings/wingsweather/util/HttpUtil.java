package org.redwings.wingsweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	
	public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {
		//不明白不明白！！！这里的回调监听器的作用是什么，好好想一想！
		new Thread(new Runnable(){
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while((line = reader.readLine()) != null) {
						response.append(line);
					}
					if (listener != null) {
						//回调onFinish()方法
						listener.onFinish(response.toString());      //我不知道回调onFinish()方法的作用何在？？？
					}
				} catch(Exception e) {
					if (listener != null) {
						//回调onError()方法
						listener.onError(e);      //同样的我也不知道回调onError()方法的作用是啥？？？
					}
				} finally {
					if (connection != null) {
						connection.disconnect();
					}
				}
			}			
		}).start();
	}
}
