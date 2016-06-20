package cn.sunflyer.cqutapi.test;

import cn.sunflyer.cqutapi.user.CService;
import cn.sunflyer.cqutapi.user.User;
import cn.sunflyer.tool.HttpUtil;
public class Test {

	public static void main(String[] args) {
		
		/**
		 * !!! Listed with <V> means you can list others information once route success.
		 * 
校内地图 , ID=20150124 , key = AA0EBF55CD534E4B93DFC2BC9FFA02C9
学校概况 , ID=20150125 , key = BD953FD8AF2942679C2FB76CE581F804
校园风光 , ID=20150127 , key = 6BB512E4C13049CBBF8CCBD85A1734F5
新闻平台 , ID=20150114 , key = 6F5E6481840248589C4524AC80D4C864
考试查询 , ID=30000005 , key = 34241A5A61F00479E0531F9BCACADB98
办公电话 , ID=30000002 , key = 6D45838739C74CBCA1C73B0F3923B4CD
智慧课堂 , ID=20150116 , key = AF067B8593174537853C4136586312CC
招生就业 , ID=20160225 , key = 2E3AC928A08D193AC3531F9BCACA2568
一卡通 , ID=20160224 , key = 2E3AC928A08D193AC3531F9BCACA2567			getBillRecord <V>
毕业审核 , ID=30000003 , key = 4F940E0CCF3B4DADFD34EFF6843350D7
掌上离校单 , ID=30000102 , key = 697E39F0237047DBA36B5F280C398630
通知公告 , ID=20160226 , key = 32E4650B45594F21E0531F9BCACADEE3
学分结算 , ID=30000001 , key = 4F940E0CCF3B4DADFD34EFF6843350D7
教室查询 , ID=20150107 , key = 11973BA6EB7F4FF182EE41C440803A1E
成绩查询 , ID=20150106 , key = BFC50080088744F0959AA34D7BEAE5F1			getScoreRecord
课表查询 , ID=20150105 , key = 55A21CB3D5E447779B6D074BA7FC3C62
图书借阅 , ID=20150111 , key = E3512639A33B43C79A138AA0F2C554BF			getBookRecord <V>
班车查询 , ID=20150109 , key = BF4B897FFBC543FB8622B7A1C239A914
校历日程 , ID=20150101 , key = FA8DB7A6F65840D290AE7E97DE503FF7
		 * */
		
		CService s = CService.getService(User.getInstance("", ""));
		s.initFunctions();

		System.out.println(HttpUtil.getResponse(s.route(20160226)));
	}
}
