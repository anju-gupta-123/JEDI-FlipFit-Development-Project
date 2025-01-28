package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	public int checkCapacity(int slotid)
	{
		try {  
			
			Connection con = DBUtils.connect();
			PreparedStatement stmt=con.prepareStatement("select * from flipfitSlot where slotId=?");
			stmt.setInt(1, slotid);
			
			  
			ResultSet rs = stmt.executeQuery();  
			int res = 0;
			while(rs.next()) {
				res= rs.getInt("capacity");
				
			}
			
			con.close(); 
			return res;
			
			 
			  
		}catch(Exception e){ 
				e.printStackTrace();			
				return 0;
		}
	}
	
	
}
