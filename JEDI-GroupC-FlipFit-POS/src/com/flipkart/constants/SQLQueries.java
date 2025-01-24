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

	
	

}
