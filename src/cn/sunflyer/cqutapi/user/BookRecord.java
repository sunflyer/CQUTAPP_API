package cn.sunflyer.cqutapi.user;

public class BookRecord {
	
	private String bookNum , bookName , bookStart , bookEnd ;
	
	private int bookTime;
	
	public BookRecord(String no , String name , String start , String back , int time ){
		setBookNum(no);
		setBookName(name);
		setBookStart(start);
		setBookEnd(back);
		setBookTime(time);
	}

	public String getBookNum() {
		return bookNum;
	}

	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}

	public int getBookTime() {
		return bookTime;
	}

	public void setBookTime(int bookTime) {
		this.bookTime = bookTime;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookStart() {
		return bookStart;
	}

	public void setBookStart(String bookStart) {
		this.bookStart = bookStart;
	}

	public String getBookEnd() {
		return bookEnd;
	}

	public void setBookEnd(String bookEnd) {
		this.bookEnd = bookEnd;
	}

	
	public String toString(){
		return bookNum + "\t" + bookName + "\t" + bookStart + "\t" +bookEnd + "\t" +bookTime;
	}
}
