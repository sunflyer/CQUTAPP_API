package cn.sunflyer.tool;

import java.io.InputStream;

public class HttpResponse {
	
	private InputStream gInputStream;
	
	private String gConnectionCookie;
	
	private String gUrl;
	
	
	private int gErrorCode;
	
	
	public HttpResponse(InputStream is , String cookie , String URL , int code){
		this.gInputStream = is;
		this.gConnectionCookie = cookie;
		this.gUrl = URL;
		this.gErrorCode = code;
	}
	
	public InputStream getInputStream(){
		return gInputStream;
	}
	
	public String getCookie(){
		return gConnectionCookie;
	}
	
	public String getHost(){
		return gUrl;
	}
	
	public int getErrorCode(){
		return gErrorCode;
	}

}
