package cn.sunflyer.cqutapi.user;

public class User {
	
	public static User getInstance(String name , String pass){
		return new User(name ,pass);
	}
	
	private String gUsername , gUserpass;
	
	private User(String name , String pass){
		this.gUsername = name;
		this.gUserpass = pass;
	}
	
	public String getLoginTicket(){
		return Ticket.getLoginTicket(gUsername, gUserpass);
	}
	
	public String getFunctionTicket(String key){
		return Ticket.getFunctionTicket(gUsername, gUserpass, key);
	}
	
	public String getUsername(){
		return gUsername;
	}

}
