package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Gym_Owner;
import com.flipkart.constants.SQLQueries;
import com.flipkart.utils.DBUtils;


public class AdminDAOImpl implements AdminDAOInterface
{
	public List<Gym_Center> viewPendingGymCenters(){
        List<Gym_Center> pendingReq = new ArrayList<>();
        try {
        	Connection a = DBUtils.connect();
    				
            System.out.println("Getting Pending Requests...");
            PreparedStatement s = a.prepareStatement(SQLQueries.VIEW_GYM_CENTER_REQUESTS);
            ResultSet rs = s.executeQuery();

            while(rs.next()){
                Gym_Center gymCenter = new Gym_Center();
                gymCenter.setCenter_id(rs.getInt("centerId"));
                gymCenter.setCenter_name(rs.getString("centerName"));
                gymCenter.setAddress(rs.getString("address"));
                gymCenter.setNo_of_slots(rs.getInt("numOfSlots"));
                gymCenter.setApproved(false);
                pendingReq.add(gymCenter);
            }
        } catch (SQLException sqlExcep){
            System.out.println(sqlExcep);
        } catch (Exception excep){
            excep.printStackTrace();
        }
        return pendingReq;
    }
	
	public List<Gym_Owner> viewPendinGymOwnerRequests(){
        List<Gym_Owner> pendingReq = new ArrayList<>();
        try {
            Connection con = DBUtils.connect();
            System.out.println("Getting Pending Requests...");
            PreparedStatement smt2= con.prepareStatement(SQLQueries.VIEW_GYM_OWNER_REQUESTS);
            ResultSet rs = smt2.executeQuery();

            while(rs.next()){
                Gym_Owner gymOwner = new Gym_Owner();
                gymOwner.setId(rs.getInt("ownerId"));
                gymOwner.setName(rs.getString("GymOwnerName"));
                gymOwner.setEmail(rs.getString("email"));
                gymOwner.setApproved(false);
                pendingReq.add(gymOwner);
            }
        } catch (SQLException sqlExcep){
            System.out.println(sqlExcep);
        } catch (Exception excep){
            excep.printStackTrace();
        }
        return pendingReq;
    }
	public boolean approveGymRegistration(int GymCenterID)
	{
		try
		{
		Connection connection = DBUtils.connect();
		PreparedStatement statement = connection.prepareStatement(SQLQueries.APPROVE_GYM_CENTER);
		statement.setInt(1, GymCenterID);
	
		statement.executeUpdate();
		return true;
		}catch(Exception e){ System.out.println(e);return false;}
		
	}
	
	public boolean approveGymOwnerRegistration(int GymOwnerID)
	{
		try {
		Connection wht = DBUtils.connect();
		PreparedStatement stm = wht.prepareStatement(SQLQueries.APPROVE_GYM_OWNER);
		stm.setInt(1, GymOwnerID);
		stm.executeUpdate();
		return true;
		}catch(Exception e){ System.out.println(e);return false;}
	}
	
	public List<Gym_Owner> viewApprovedGymOwners()
	{
		List<Gym_Owner> pendingReq = new ArrayList<>();
        try {
            Connection con = DBUtils.connect();
            System.out.println("Getting Pending Requests...");
            PreparedStatement smt2= con.prepareStatement(SQLQueries.VIEW_APPROVED_GYM_OWNERS);
            ResultSet rs = smt2.executeQuery();

            while(rs.next()){
                Gym_Owner gymOwner = new Gym_Owner();
                gymOwner.setId(rs.getInt("ownerId"));
                gymOwner.setName(rs.getString("GymOwnerName"));
                gymOwner.setEmail(rs.getString("email"));
                gymOwner.setApproved(false);
                pendingReq.add(gymOwner);
            }
        } catch (SQLException sqlExcep){
            System.out.println(sqlExcep);
        } catch (Exception excep){
            excep.printStackTrace();
        }
        return pendingReq;
	}
	
	public List<Gym_Center> viewApprovedGymCenters()
	{
		List<Gym_Center> pendingReq = new ArrayList<>();
        try {
        	Connection a = DBUtils.connect();
            System.out.println("Getting Pending Requests...");
            PreparedStatement s = a.prepareStatement(SQLQueries.VIEW_APPROVED_GYM_CENTERS);
            
            ResultSet rs = s.executeQuery();

            while(rs.next()){
                Gym_Center gymCenter = new Gym_Center();
                gymCenter.setCenter_id(rs.getInt("centerId"));
                gymCenter.setCenter_name(rs.getString("centerName"));
                gymCenter.setAddress(rs.getString("address"));
                gymCenter.setNo_of_slots(rs.getInt("numOfSlots"));
                gymCenter.setApproved(false);
                pendingReq.add(gymCenter);
            }
        } catch (SQLException sqlExcep){
            System.out.println(sqlExcep);
        } catch (Exception excep){
            excep.printStackTrace();
        }
        return pendingReq;
	}
	
	
}

