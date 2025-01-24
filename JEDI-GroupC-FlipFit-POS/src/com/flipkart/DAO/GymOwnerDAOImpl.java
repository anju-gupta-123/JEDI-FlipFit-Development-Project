package com.flipkart.DAO;
import com.flipkart.utils.DBUtils;
import com.flipkart.bean.*;
import java.sql.*;

public class GymOwnerDAOImpl implements GymOwnerDAOInterface{
	Connection connection = null;
	PreparedStatement statement = null;

	@Override
	public void addSlot(Slot newSlot) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        Connection connection = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/flipfit_db", "root", "t@x1.n00n.t0Ll");

	        String slotSQL = "INSERT INTO flipfitSlot (slotId, centerId, startTime, endTime, capacity) " +
                    "VALUES (?, ?, ?, ?, ?)";
	        	PreparedStatement slotStatement = connection.prepareStatement(slotSQL);
	        	
	        	slotStatement.setInt(1, newSlot.getSlotId()); 
	        	slotStatement.setInt(2, newSlot.getCenterId());	
	        	slotStatement.setTime(3, java.sql.Time.valueOf(newSlot.getStartTime()));
	        	slotStatement.setTime(4, java.sql.Time.valueOf(newSlot.getEndTime()));
	        	slotStatement.setInt(5, newSlot.getNumberofseats());

	        	statement.executeUpdate();
	        	statement.close();
	        	connection.close();

	    } catch (SQLException sqlExcep) {
	        System.out.println("SQL Exception: " + sqlExcep.getMessage());
	    } catch (Exception excep) {
	        excep.printStackTrace();
	    }
	}


	 @Override
	    public boolean isApprovedOwner(int gymOwnerId) {
	        String query = "SELECT isApproved FROM flipfitGymOwner WHERE ownerId = ?";
	        
	        try (Connection connection = DBUtils.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query)) {
	            
	            statement.setInt(1, gymOwnerId);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getBoolean("isApproved");
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("SQL Exception in isApproved(email): " + e.getMessage());
	        }
	        return false;
	    }

	    @Override
	    public boolean isApprovedCenter(int gymCenterId) {
	        String query = "SELECT isApproved FROM flipfitCenter WHERE centerId = ?";
	        
	        try (Connection connection = DBUtils.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query)) {
	            
	            statement.setInt(1, gymCenterId);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getBoolean("isApproved");
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("SQL Exception in isApproved(centerId): " + e.getMessage());
	        }
	        return false;
	    }
}
