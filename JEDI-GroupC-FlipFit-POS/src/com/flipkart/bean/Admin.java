package com.flipkart.bean;
import java.util.*;

public class Admin extends User{
	static List<Gym_Center> gymCenters = new ArrayList<>();
	static List<Gym_Center> approvedGymCenters = new ArrayList<>();
	static List<Gym_Owner> gymOwners = new ArrayList<>();
	static List<Gym_Owner> approvedGymOwners = new ArrayList<>();
	/**
	 * @return the gymCenters
	 */
	public static List<Gym_Center> getGymCenters() {
		return gymCenters;
	}
	
	public static void removeGymCenters(Gym_Center gymcenter) {
		Admin.gymCenters.remove(gymcenter);
	}
	/**
	 * @param gymCenters the gymCenters to set
	 */
	public static void setGymCenters(Gym_Center gymcenter) {
		Admin.gymCenters.add(gymcenter);
	}
	/**
	 * @return the approvedGymCenters
	 */
	public static List<Gym_Center> getApprovedGymCenters() {
		return approvedGymCenters;
	}
	/**
	 * @param approvedGymCenters the approvedGymCenters to set
	 */
	public static void setApprovedGymCenters(Gym_Center gymcenter) {
		Admin.gymCenters.add(gymcenter);
	}

	/**
	 * @return the gymOwners
	 */
	public static List<Gym_Owner> getGymOwners() {
		return gymOwners;
	}

	/**
	 * @param gymOwners the gymOwners to set
	 */
	public static void setGymOwners(Gym_Owner gymOwner) {
		Admin.gymOwners.add(gymOwner);
	}

	/**
	 * @return the approvedGymOwners
	 */
	public static List<Gym_Owner> getApprovedGymOwners() {
		return approvedGymOwners;
	}

	/**
	 * @param approvedGymOwners the approvedGymOwners to set
	 */
	public static void setApprovedGymOwners(Gym_Owner approvedgymowner) {
		Admin.approvedGymOwners.add(approvedgymowner);
	}
	
	public static void removeGymOwner(Gym_Owner gymowner)
	{
		Admin.gymOwners.remove(gymowner);
	}
	
	
	
	
	

}