package com.example.coolweather.util;

public interface HttpCallBackListener {
	public void onFinish(String message);
	public void onError(Exception e);
}
