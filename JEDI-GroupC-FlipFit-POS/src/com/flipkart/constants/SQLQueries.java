/**
 * 
 */
package com.flipkart.constants;

/**
 * 
 */
public class SQLQueries {
	
	
	public static final String VIEW_GYM_OWNER_REQUESTS = "SELECT * FROM flipfitGymOwner where isApproved=false";
	public static final String VIEW_GYM_CENTER_REQUESTS = "SELECT * FROM flipfitCenter where isApproved=false";
	public static final String APPROVE_GYM_OWNER = "UPDATE flipfitGymOwner SET isApproved = 1 WHERE ownerid = ?";
	public static final String APPROVE_GYM_CENTER = "UPDATE flipfitCenter SET isApproved = 1 WHERE centerid = ?";
	public static final String VIEW_APPROVED_GYM_OWNERS = "SELECT * FROM flipfitGymOwner where isApproved=true";
	public static final String VIEW_APPROVED_GYM_CENTERS = "SELECT * FROM flipfitCenter where isApproved=true";
	
	public static final String CHECK_SLOTS= "select count(*) as count from flipfitBookedSlot where slotId=? and date=? and bookedSlotStatus=1";
	public static final String ADD_SLOT= "insert into flipfitSlot(centerId,startTime, capacity) values(?,?,?)";
	
	public static final String REGISTER_CUSTOMER= "insert into flipfitCustomer (customerName, email, contact, customerPassword) values(?,?,?,?)";
	public static final String REGISTER_GYM_OWNER= "insert into flipfitGymOwner (gymOwnerName, email, contact, gymOwnerPassword,aadharCard, gstNo) values(?,?,?,?,?,?)";
	public static final String GET_CUSTOMER_DETAILS= "select * from flipfitCustomer where email=?";
	public static final String VIEW_AVAILABLE_SLOTS= "select * from flipfitSlot where centerID=?";
	public static final String VIEW_ALL_BOOKED_SLOTS= "select * from flipfitBookedSlot where customerId=? and bookedSlotStatus=?";
	public static final String DELETE_BOOKED_SLOTS= "update flipfitBookedSlot set bookedSlotStatus=0 where bookedSlotId=?";
	
	
	

	
	

}
