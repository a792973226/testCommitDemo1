package com.huizhi.yun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	
	public static String doPost2(String url, String body) {
		
		StringBuffer result = null;
		try{
			URL realUrl = new URL(url);
			HttpURLConnection openConnection = (HttpURLConnection) realUrl.openConnection();
			openConnection.setRequestProperty("Method", "POST");
			openConnection.setRequestProperty("Content-Type", "multipart/form-data;");
			openConnection.setDoOutput(true);
			openConnection.setDoInput(true);
			PrintWriter printWriter = new PrintWriter(openConnection.getOutputStream());
			printWriter.print(body);
			printWriter.flush();
			
			if (HttpURLConnection.HTTP_OK != openConnection.getResponseCode()){
				System.out.println("Http 请求失败，状态码：" + openConnection.getResponseCode());
				return null;
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
			String line;
			while((line = reader.readLine()) != null){
				result.append(line);
			}
		}catch (Exception e) {
			
		}
		return result.toString();
	}
}
