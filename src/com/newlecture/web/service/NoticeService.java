package com.newlecture.web.service;

import java.util.List;
import com.newlecture.web.entity.Notice;


public class NoticeService  {
	public List<Notice> getNoticeList() {
		return getNoticeList("title", "", 1);
	}
	
	public List<Notice> getNoticeList(int page) {
		return getNoticeList("title", "", page);
	}
	
	public List<Notice> getNoticeList(String field, String query, int page) {
		
		String sql = "SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM" + 
				"      ,NOTICE.*" + 
				"  FROM (" + 
				"        SELECT *" + 
				"          FROM NOTICE" + 
				"         ORDER BY REGDATE DESC" + 
				"        ) AS NOTICE" + 
				"      ,(" + 
				"         SELECT @ROWNUM := 0" + 
				"        )CNT" + 
				"LIMIT 5,10";
		
		
		
		return null;
	}
	
	public int getNoticeCount() {
		return getNoticeCount("title","");
	}
	
	public int getNoticeCount(String field, String query) {
		
		String sql = "SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM" + 
				"      ,NOTICE.*" + 
				"  FROM (" + 
				"        SELECT *" + 
				"          FROM NOTICE" + 
				"         ORDER BY REGDATE DESC" + 
				"        ) AS NOTICE" + 
				"      ,(" + 
				"         SELECT @ROWNUM := 0" + 
				"        )CNT" + 
				"LIMIT 5,10";
		
		
		return 0;
	}
	
	public Notice getNotice(int id) {
		String sql = "SELECT * FROM NOTICE WHERE ID=?";
		
		return null;
	}
	
	public Notice getNextNotice(int id) {
		String sql ="WHERE ID = (" + 
				"	SELECT ID FROM NOTICE " + 
				"	WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE ID = 5)" + 
				"	LIMIT 1" + 
				")";
		
		return null;
	}
	
	public Notice getPrevNotice(int id) {
		String sql = "WHERE ID = (" + 
				"	SELECT ID FROM NOTICE " + 
				"	WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE ID = 5)" + 
				"    ORDER BY ID DESC" + 
				"    LIMIT 1" + 
				")";
		return null;
	}
}
