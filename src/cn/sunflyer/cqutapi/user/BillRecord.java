package cn.sunflyer.cqutapi.user;

public class BillRecord {
	
	//{"RN":1,"CONSUME_AMOUNT":9.50,"CARD_BALANCE":65.68,"CONSUME_DATE":"2016-06-16 18:33:15","SHOP_NAME":"惠宜超市","CONSUME_TYPE":"消费"}
	
	private double gConsumeAmount , gCardBalance;
	
	private String gConsumeDate , gShopName , gConsumeType;
	
	public BillRecord(double amount , double balance , String date , String name , String type){
		this.setConsumeAmount(amount);
		this.setgCardBalance(balance);
		
		this.setgConsumeDate(date);
		this.setgShopName(name);
		this.setgConsumeType(type);
	}

	public double getConsumeAmount() {
		return gConsumeAmount;
	}

	private void setConsumeAmount(double gConsumeAmount) {
		this.gConsumeAmount = gConsumeAmount;
	}

	public double getgCardBalance() {
		return gCardBalance;
	}

	private void setgCardBalance(double gCardBalance) {
		this.gCardBalance = gCardBalance;
	}

	public String getgConsumeDate() {
		return gConsumeDate;
	}

	private void setgConsumeDate(String gConsumeDate) {
		this.gConsumeDate = gConsumeDate;
	}

	public String getgShopName() {
		return gShopName;
	}

	private void setgShopName(String gShopName) {
		this.gShopName = gShopName;
	}

	public String getgConsumeType() {
		return gConsumeType;
	}

	private void setgConsumeType(String gConsumeType) {
		this.gConsumeType = gConsumeType;
	}
	
	public String toString(){
		return "消费金额：" + gConsumeAmount + "，地点：" + gShopName + "，时间：" + gConsumeDate + "，余额：" + gCardBalance + "，类型：" + gConsumeType;
	}

}
