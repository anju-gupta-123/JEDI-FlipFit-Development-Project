package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.flipkart.utils.DBUtils;

public class NotificationsDAOImpl implements NotificationsDAOInterface{
	@Override
	public List<String> viewNotifications(int customerid) {
		
		try {  
			List<String> msglist= new ArrayList<>();
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("select * from flipfitNotification where receiverId=?");
			stmt.setInt(1, customerid);
			
			  
			ResultSet rs = stmt.executeQuery();  
			while(rs.next()) {
				String res= rs.getString("message");
				msglist.add(res);				
				break;
			}
			con.close(); 
			return msglist;
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();			
				return null;
		}
	}
	
	
	@Override
	public boolean createNoti(int customerid, String message) {
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("insert into flipfitNotification (receiverId, message) values(?,?)");  
			stmt.setInt(1, customerid); 
			
			stmt.setString(2, message);
			
			
			int i=stmt.executeUpdate();
			con.close(); 
			return true;
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();
				System.out.println(e.getMessage());
				
				return false;
		}	
		
	}
	

}
