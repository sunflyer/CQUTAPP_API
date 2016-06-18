package cn.sunflyer.cqutapi.user;

import cn.sunflyer.tool.Enc;

public class Ticket {
	
	public static String getLoginTicket(String name ,String pass){
		
		return Enc.MD5("yunhua" + name + Enc.MD5(pass));
		
	}
	
	public static String getFunctionTicket(String name , String pass , String functionKey){
		return Enc.MD5("yunhua" + name + Enc.MD5(pass) + functionKey);
	}

}
