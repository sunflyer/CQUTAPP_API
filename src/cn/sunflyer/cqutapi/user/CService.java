package cn.sunflyer.cqutapi.user;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import cn.sunflyer.tool.HttpResponse;
import cn.sunflyer.tool.HttpUtil;

public class CService {
	
	public static final String APP_ADDR = "http://app.cqut.edu.cn/";
	
	/**
	 * 使用特定用户获取Service对象
	 * @param user 用户对象
	 * */
	public static CService getService(User user){
		return new CService(user);
	}
	
	private User gUser;
	
	/**
	 * 
	 * */
	private CService(User user){
		gUser = user;
	}
	
	//功能列表
	private HashMap<Integer ,Functions> gFunctionMap = new HashMap<>();
	//列表是否已经初始化
	private boolean gFunctionInited = false;
	
	/**
	 * 初始化菜单。
	 * */
	public void initFunctions(){
		String list = HttpUtil.getResponse(HttpUtil.get(APP_ADDR + "UIA/function/list.action?userid=" + gUser.getUsername() + "&ticket=" + gUser.getLoginTicket() + "&Version=1"));
		if(list != null && list.startsWith("\"") && list.endsWith("\"")){
			list = list.substring(1 , list.length() - 1);
		}
		list  = list.replace("\\\"", "\"").replace("\\/", "/");
		//JsonParser json = JsonProvider.provider().createParser(new StringReader(list));
		//while(json.hasNext()){
			
		//}
		JsonReader jr = Json.createReader(new StringReader(list));
		JsonObject jo = jr.readObject();
		if(jo != null && !jo.containsKey("error")){
			for(Object x : jo.keySet()){
				if(x.toString().contains("functionrow")){
					try{
						JsonObject tmpObject = jo.getJsonObject(x.toString());
						if(tmpObject.containsKey("FUNCTION_ID")){
							//System.out.println(tmpObject.getString("FUNCTION_ID") + "  " + tmpObject.getString("INTEGRATE_KEY"));
							gFunctionMap.put(tmpObject.getInt("FUNCTION_ID"), new Functions(tmpObject.getString("FUNCTION_NAME") , tmpObject.getInt("FUNCTION_ID"),tmpObject.getString("INTEGRATE_KEY")));
						}
					}catch(Exception e){
						System.out.println(e.getLocalizedMessage());
					}
				}				
			}
			gFunctionInited = true;
		}
	}
	
	public Functions[] getFunctions(){
		return gFunctionMap.values().toArray(new Functions[]{});
	}

	public HttpResponse route(int functionId){
		if(!gFunctionInited)
			initFunctions();
		
		if(!gFunctionMap.containsKey(functionId))
			return null;
		
		return HttpUtil.get(APP_ADDR + "UIA/rout/rout.action?userid=" + gUser.getUsername() + "&function_id=" + functionId + "&ticket=" + gUser.getFunctionTicket(gFunctionMap.get(functionId).getKey()));
	}
	
	
	public BillRecord[] getBillRecord(int size , int page){
		return getBillRecord(gUser.getUsername() , size , page);
	}
	
	public BillRecord[] getBillRecord(String stuid , int size , int page){
		HttpResponse routResponse = this.route(20160224);
		if(routResponse != null){
			HttpResponse yktResponse = HttpUtil.post("http://" + routResponse.getHost() + "/Home/GetRecords", "userid=" + stuid + "&pagesize=" + size + "&pagenow=" + page);
			if(yktResponse != null){
				JsonReader jr = Json.createReader(new StringReader(HttpUtil.getResponse(yktResponse)));
				JsonArray resultArray = jr.readArray();
				BillRecord[] resultData = new BillRecord[resultArray.size()];
				
				JsonValue jv = null;
				JsonObject jo = null;
				for(int i = 0 ; i < resultData.length ; i++){
				
					jv = resultArray.get(i);
					if(jv.getValueType() == ValueType.OBJECT){
						jo = (JsonObject)jv;
						resultData[i] = new BillRecord(jo.getJsonNumber("CONSUME_AMOUNT").doubleValue(), 
								jo.getJsonNumber("CARD_BALANCE").doubleValue(),
								jo.getString("CONSUME_DATE"), 
								jo.getString("SHOP_NAME"),
								jo.getString("CONSUME_TYPE"));
					}
					
				}
				
				return resultData;
			}
		}
		return null;
	}
	
	public ScoreRecord[] getScoreRecordAll(){
		int year = Integer.parseInt(gUser.getUsername().substring(1 , 3));
		
		ArrayList<ScoreRecord> list = new ArrayList<>();
		
		for(int i = 0 ; i < 4 ; i ++){
			for(int j = 1 ; j <= 2 ; j ++){
				list.addAll(Arrays.asList(this.getScoreRecord((2000 + year + i) + "-" + (2000 + year + i + 1), j)));
			}
		}
		
		return list.toArray(new ScoreRecord[]{});
	}
	
	public ScoreRecord[] getScoreRecord(String xn , int xq){
		
		HttpResponse scoreResp = HttpUtil.post("http://dc.app.cqut.edu.cn/DC/score/getlist.action", "userid=" + gUser.getUsername() + "&function_id=20150106&ticket=" + gUser.getFunctionTicket(gFunctionMap.get(20150106).getKey()) 
				+ "&xn=" + xn + "&xq=" + xq);
		
		if(scoreResp != null){
			String response = HttpUtil.getResponse(scoreResp);
			if(response.startsWith("\"")){
				response = response.substring(1 , response.length() - 1).replace("\\\"", "\"");
			}
			JsonReader jr = Json.createReader(new StringReader(response));
			JsonArray resultArray = jr.readArray();
			ScoreRecord resultData[] = new ScoreRecord[resultArray.size()];
			
			JsonValue jv = null;
			JsonObject jo = null;
			for(int i = 0 ; i < resultData.length ; i++){
			
				jv = resultArray.get(i);
				if(jv.getValueType() == ValueType.OBJECT){
					jo = (JsonObject)jv;
					resultData[i] = new ScoreRecord(jo.getString("courseName"), 
							jo.getString("GPA"),
							jo.getString("courseScore"), 
							jo.getString("courseId"),
							jo.getString("scoreType"),
							jo.getString("courseCredit"),
							jo.getString("courseNature"));
				}
				
			}
			
			return resultData;
		}
		
		return null;
	}
	
	public BookRecord[] getBookRecord(int page , int size){
		return this.getBookRecord(gUser.getUsername(), 1, 20);
	}
	
	public BookRecord[] getBookRecord(String userid , int page , int size){
		HttpResponse bookResp = this.route(20150111);
		if(bookResp  != null){
			HttpResponse realResp = HttpUtil.post("http://" + bookResp.getHost() + "/MV/books/list.action?userid=" + userid + "&pagesize=" + size + "&nowpage=" + page , "");
			if(realResp != null){
				String response = HttpUtil.getResponse(realResp);
				if(response.startsWith("\"")){
					response = response.substring(1 , response.length() - 1).replace("\\\"", "\"");
				}
				JsonReader jr = Json.createReader(new StringReader(response));
				JsonArray resultArray = jr.readArray();
				
				BookRecord[] resultData = new BookRecord[resultArray.size()];
				
				JsonValue jv = null;
				JsonObject jo = null;
				for(int i = 0 ; i < resultData.length ; i++){
				
					jv = resultArray.get(i);
					if(jv.getValueType() == ValueType.OBJECT){
						jo = (JsonObject)jv;
						resultData[i] = new BookRecord(
								jo.getString("BOOK_NUM"),
								jo.getString("BOOK_NAME"),
								jo.getString("BOOK_DATE"),
								jo.getString("BOOK_BACKDATE"),
								Integer.parseInt(jo.getString("BOOK_XJCS"))
								);
					}
					
				}
				
				return resultData;
			}
		}
		return null;
	}
}
