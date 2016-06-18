package cn.sunflyer.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	
	static{
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
	}
	
	public static HttpResponse get(String url){
		return get(url , null);
	}
	
	public static HttpResponse get(String url , String cookie){
		return request(url , null , cookie , true);
	}
	
	public static HttpResponse post(String url , String data){
		return post(url , data , null);
	}
	
	public static HttpResponse post(String url , String data , String cookie){
		return request(url , data , cookie , false);
	}
	
	public static HttpResponse request(String url , String data , String cookie , boolean isGet){
		try{
			HttpURLConnection hUrlConn = (HttpURLConnection)(new URL(url).openConnection());
			
			hUrlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 5.1.1; NX507J Build/LMY47V; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/46.0.2490.76 Mobile Safari/537.36");
			//hUrlConn.setRequestProperty("", "");
			if(cookie != null){
				hUrlConn.setRequestProperty("Cookie", cookie);
			}
			
			if(!isGet){
				hUrlConn.setRequestMethod("POST");
				hUrlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				hUrlConn.setDoOutput(true);
				
				BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(hUrlConn.getOutputStream()));
				bwriter.write(data);
				bwriter.flush();
			}else{
				hUrlConn.setRequestMethod("GET");
			}
			
			String responseCookie = hUrlConn.getHeaderField("Set-Cookie");
			
			int errorCode = hUrlConn.getResponseCode();
			InputStream is = hUrlConn.getInputStream();
			
			return new HttpResponse(is , responseCookie , hUrlConn.getURL().getHost() , errorCode);
			
		}catch(IOException e){
			System.out.println("Exception occurred when requesting : " + e.getLocalizedMessage());
		}
		return null;
	}
	
	public static String getResponse(HttpResponse h){
		return h == null ? null : getResponse(h.getInputStream());
	}
	
	public static String getResponse(InputStream i){
		if(i != null){
			try{
				BufferedReader breader = new BufferedReader(new InputStreamReader(i));
				StringBuffer sb = new StringBuffer();
				int x = 0;
				while((x = breader.read()) != -1){
					sb.append((char)x);
				}
				return sb.toString();
			}catch(Exception e){
				System.out.println("Exception occurren when parsing : " + e.getMessage());
			}
		}
		return null;
	}

}
