package cn.sunflyer.cqutapi.user;

public class Functions {
	
	private String name , key;
	
	private int id;
	
	public Functions(String name  ,int id , String key){
		this.name = name;
		this.key = key;
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getKey(){
		return key;
	}
	
	public String toString(){
		return name + " , ID=" + id + " , key = " + key;
	}

}
