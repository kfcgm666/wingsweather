package org.redwings.wingsweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	
	public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {
		//�����ײ����ף���������Ļص���������������ʲô���ú���һ�룡
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
						//�ص�onFinish()����
						listener.onFinish(response.toString());      //�Ҳ�֪���ص�onFinish()���������ú��ڣ�����
					}
				} catch(Exception e) {
					if (listener != null) {
						//�ص�onError()����
						listener.onError(e);      //ͬ������Ҳ��֪���ص�onError()������������ɶ������
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
