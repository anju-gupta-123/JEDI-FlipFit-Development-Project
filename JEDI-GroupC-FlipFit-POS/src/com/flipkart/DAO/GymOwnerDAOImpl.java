package com.flipkart.DAO;



import com.flipkart.utils.DBUtils;

import com.flipkart.bean.*;

import java.sql.*;

import java.util.*;



public class GymOwnerDAOImpl implements GymOwnerDAOInterface{

	Connection connection = null;

	PreparedStatement statement = null;



	
	
	public boolean registerGymCenter(Gym_Center gCenter) {
		
		String query = "insert into flipfitCenter (ownerId, centerName, address, numOfSlots) values(?,?,?,?)";
		
		try {
			Connection connection = DBUtils.connect();
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, gCenter.getOwner_id());
			stmt.setString(2, gCenter.getCenter_name());
			stmt.setString(3, gCenter.getAddress());
			stmt.setInt(4, gCenter.getNo_of_slots());
			
			stmt.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		
	}

	

	@Override

	public Gym_Owner getGymOwnerDetails(String ownerEmail) {

		String query = "SELECT * FROM flipfitGymOwner WHERE email = ?";

	    Gym_Owner gymOwner = new Gym_Owner();



	    try (Connection connection = DBUtils.connect();

	         PreparedStatement statement = connection.prepareStatement(query)) {

	        statement.setString(1, ownerEmail);
	        



	        try (ResultSet resultSet = statement.executeQuery()) {
	     

	            while (resultSet.next()) {
	            	

	            	gymOwner.setId(resultSet.getInt("ownerId"));

	            	gymOwner.setEmail(resultSet.getString("email"));

	            	gymOwner.setPassword(resultSet.getString("gymOwnerPassword"));
	            	

	            	gymOwner.setAadharCard(resultSet.getString("aadharCard"));

	            	gymOwner.setApproved(resultSet.getBoolean("isApproved"));

	            	gymOwner.setContact(resultSet.getString("contact"));

	            	gymOwner.setGSTNo(resultSet.getString("gstNo"));

	            	gymOwner.setName(resultSet.getString("gymOwnerName"));

	            	

	            	return gymOwner;

	            }

	        }

	    } catch (SQLException e) {

	        System.out.println("SQL Exception in getCenterDetails: " + e.getMessage());

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	    return gymOwner;

	}

	

	@Override

	public List<Gym_Center> getCenterDetails(int ownerId) {

		String query = "SELECT * FROM flipfitCenter WHERE ownerId = ?";

	    List<Gym_Center> gymCenters = new ArrayList<>();



	    try (Connection connection = DBUtils.connect();

	         PreparedStatement statement = connection.prepareStatement(query)) {

	        statement.setInt(1, ownerId);



	        try (ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {

	                Gym_Center gymCenter = new Gym_Center();

	                gymCenter.setCenter_id(resultSet.getInt("centerId"));

	                gymCenter.setOwner_id(resultSet.getInt("ownerId"));

	                gymCenter.setCenter_name(resultSet.getString("centerName"));

	                gymCenter.setAddress(resultSet.getString("address"));

	                gymCenter.setApproved(resultSet.getBoolean("isApproved"));



	                gymCenters.add(gymCenter);

	            }

	        }

	    } catch (SQLException e) {

	        System.out.println("SQL Exception in getCenterDetails: " + e.getMessage());

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	    return gymCenters;

	}



	@Override

	public boolean isApprovedOwner(int gymOwnerId) {

	    String query = "SELECT isApproved FROM flipfitGymOwner WHERE ownerId = ?";

	    

	    try (Connection connection = DBUtils.connect();

	        PreparedStatement statement = connection.prepareStatement(query)) {

	        

	        statement.setInt(1, gymOwnerId);

	        try (ResultSet resultSet = statement.executeQuery()) {

	            if (resultSet.next()) {

	                return resultSet.getBoolean("isApproved");

	            }

	        }

	    } catch (SQLException e) {

	        System.out.println("SQL Exception in isApproved(email): " + e.getMessage());

	    } catch (Exception e) {

	    	e.printStackTrace();

	    }

	    return false;

	}



	@Override

    public boolean isApprovedCenter(int gymCenterId) {

        String query = "SELECT isApproved FROM flipfitCenter WHERE centerId = ?";

        

        try (Connection connection = DBUtils.connect();

             PreparedStatement statement = connection.prepareStatement(query)) {

            

            statement.setInt(1, gymCenterId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getBoolean("isApproved");

                }

            }

        } catch (SQLException e) {

            System.out.println("SQL Exception in isApproved(centerId): " + e.getMessage());

        } catch (Exception e) {

        	e.printStackTrace();

        }

        return false;

    }



	

}
