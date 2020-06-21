package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Notice> list = new ArrayList<>();
		
		String url = "jdbc:mysql://192.168.0.33/practice";
		String sql = "SELECT * FROM NOTICE";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection(url, "mysqladmin", "pantal90");
			Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery(sql);

		    SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			while(rs.next()) { 
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE"); 
				Date regdate = transFormat.parse(rs.getString("REGDATE"));   // 시간이 안나옴 
				String writeId = rs.getString("WRITER_ID"); 
				int hit = rs.getInt("HIT"); 
				String files = rs.getString("FILES"); 
				String content = rs.getString("CONTENT"); 
								
				
				Notice notice = new Notice(
						id,
						title,
						writeId,
						regdate,
						hit,
						files,
						content
				);
				
				list.add(notice);
					
			}
			
			rs.close();
			st.close();
			con.close();
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		
		//forward 
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
		
		    
	}

	private Date transFormat(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
