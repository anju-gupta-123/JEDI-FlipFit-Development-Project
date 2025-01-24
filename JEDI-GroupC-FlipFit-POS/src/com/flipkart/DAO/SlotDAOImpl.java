package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.flipkart.bean.Slot;
import com.flipkart.utils.DBUtils;

public class SlotDAOImpl implements SlotDAOInterface{

	@Override
	public boolean addSlot(Slot slot) {
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("insert into flipfitSlot(centerId,startTime, capacity) values(?,?,?)");  
			stmt.setInt(1, slot.getCenterId()); 
			stmt.setInt(2, slot.getStartTime());
			stmt.setInt(3, slot.getNumberofseats());
			
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
			con.close(); 
			return true;
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();
				System.out.println(e.getMessage());
				
				return false;
		}		
		
	}
	
	
}
