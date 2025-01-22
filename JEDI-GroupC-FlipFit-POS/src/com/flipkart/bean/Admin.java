package com.flipkart.bean;
import java.util.*;

public class Admin extends User{
	static List<Gym_Center> gymCenters = new ArrayList<>();
	static List<Gym_Center> approvedGymCenters = new ArrayList<>();
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
	
	
	
	

}