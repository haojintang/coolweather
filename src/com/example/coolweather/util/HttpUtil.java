package com.example.coolweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	public static void sendHttpRequest(final String address,
			final HttpCallBackListener listener){
		new Thread(new Runnable(){
			@Override
			public void run(){
				HttpURLConnection conn=null;
				try{
					URL url=new URL(address);
					conn=(HttpURLConnection)url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(15000);
					conn.setReadTimeout(15000);
					InputStream in=conn.getInputStream();
					BufferedReader br=new BufferedReader(new InputStreamReader(in));
					StringBuilder content=new StringBuilder();
					String line=null;
					while((line=br.readLine())!=null){
						content.append(line);
					}
					if(listener!=null){
						//调用回调方法
						listener.onFinish(content.toString());
					}
				}catch(Exception e){
					if(listener!=null){
						listener.onError(e);
					}
				}finally{
					if(conn!=null){
						conn.disconnect();
					}
					
				}
			}
		}).start();
	}
}
