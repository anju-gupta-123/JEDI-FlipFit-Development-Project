/**
 * 
 */
package com.flipkart.client;
import java.util.*;

import com.flipkart.business.*;
import com.flipkart.bean.*;
/**
 * 
 */
public class GymOwnerClient {
	
	GymOwnerOperations go = new GymOwnerOperations();
	UserOperations uo = new UserOperations();


	public void getGymOwnerDetails(String GymOwnerEmail) throws Exception{
		Gym_Owner go = GymOwnerOperations.getGymOwnerDetails(GymOwnerEmail);
		System.out.printf("%-15s\t", "Id");
		System.out.printf("%-15s\t", "Name");
		System.out.printf("%-15s\t", "Email");
		System.out.printf("%-15s\t", "Address");
		System.out.printf("%-15s\t", "GstNumber");
		System.out.printf("%-15s\t", "Phone");
		System.out.println();
		System.out.printf("%-15s\t", go.getId());
		System.out.printf("%-15s\t", go.getName());
		System.out.printf("%-15s\t", go.getEmail());
		System.out.printf("%-15s\t", go.getAddress());
		System.out.printf("%-15s\t", go.getGstNumber());
		System.out.printf("%-15s\t", go.getPhone());
	}
	
	public void registerGym(Scanner sc) throws Exception{
		Gym_Center Gym_Center = new Gym_Center();
		System.out.println("Add gym Details: ");
		System.out.print("Enter gym Name: ");
		Gym_Center.setName(sc.next());
		System.out.print("Enter gym location: ");
		Gym_Center.setLocation(sc.next());
		System.out.println("Enter the number of seats: ");
		Gym_Center.setNoOfSeats(sc.nextInt());
		Gym_Center.setGymOwnerEmail(Gym_Owner.getEmail());
		Gym_Center.setApproved(false);
		GymOwnerOperations.addGym(Gym_Center);
	}
	
	public void getAllGymDetails() throws Exception{
		List<Gym_Center> allGyms = GymOwnerOperations.viewAllGymCenters(Gym_Owner.getEmail());
		System.out.printf("%-15s\t", "Id");
		System.out.printf("%-15s\t", "Name");
		System.out.printf("%-15s\t", "Location");
		System.out.printf("%-15s\t", "No of Seats");
		System.out.println();
		for(Gym_Center gym : allGyms) {
			System.out.printf("%-15s\t", gym.getId());
			System.out.printf("%-15s\t", gym.getName());
			System.out.printf("%-15s\t", gym.getLocation());
			System.out.printf("%-15s\t", gym.getNoOfSeats());
		}
		System.out.println("-------------------------------------");
	}

	public void getAllApprovedGymDetails() throws Exception{
		List<Gym_Center> allApprovedGyms = GymOwnerOperations.viewAllApprovedGymCenters();
		System.out.printf("%-15s\t", "Id");
		System.out.printf("%-15s\t", "Name");
		System.out.printf("%-15s\t", "Location");
		System.out.printf("%-15s\t", "No of Seats");
		System.out.println();
		for(Gym_Center gym : allApprovedGyms) {
			System.out.printf("%-15s\t", gym.getId());
			System.out.printf("%-15s\t", gym.getName());
			System.out.printf("%-15s\t", gym.getLocation());
			System.out.printf("%-15s\t", gym.getNoOfSeats());
			System.out.printf("%-15s\t", "Yes");
			System.out.println("");
		}
		System.out.println("-------------------------------------");
	}
	public void addSlots(Scanner sc, String Gym_OwnerEmail) throws Exception{
		getAllGymDetails();
		System.out.println("Enter the Gym_Center id for which you want to add slots: ");
		Gym_Center.setId(sc.nextInt());
		if(!GymOwnerOperations.isApproved(Gym_Center.getId())){
			System.out.println("This Gym is not Authorized");
			GymOwnerPage(sc, Gym_OwnerEmail);
		}
		else {
			viewAllSlots(Gym_Center.getId());
			System.out.println("Add slot timing: ");
			Slot slot = new Slot();
			slot.setTime(sc.next());
			System.out.println("Add slot Id: ");
			slot.setId(sc.nextInt());
			GymOwnerOperations.createSlot(slot);
			GymOwnerOperations.addSlots(Gym_Center.getId(),slot);
			GymOwnerPage(sc, Gym_OwnerEmail);
		}
	}
	

	public void viewAllSlots(int Gym_CenterId) throws Exception{
		System.out.println("Listing all existing slots: ");
		List<Slot> allSlots = GymOwnerOperations.viewAllSlots(Gym_CenterId);
		System.out.printf("%-15s\t", "Id");
		System.out.printf("%-15s\t", "Time");
		System.out.println();
		for(Slot slot : allSlots) {
			System.out.printf("%-15s\t", slot.getId());
			System.out.printf("%-15s\t", slot.getTime());
			System.out.println("");
		}
		System.out.println("-------------------------------------");
	}
	
	public void viewAllSlots(Scanner sc) throws Exception{
		getAllApprovedGymDetails();
		System.out.println("Enter the Gym_Center id for which you want to see all slots: ");
		viewAllSlots(sc.nextInt());
	}
	
	public void GymOwnerPage(Scanner sc, String GymOwnerEmail) throws Exception{
		if(!GymOwnerOperations.isApproved(GymOwnerEmail)) {
			System.out.println("Your Gym Application has been ");
		}
		else {
			boolean loop = true;
			while(loop) {
				System.out.println("------------------------------ \n 1. Add Gyms \n 2. Add Slots in a Gym \n 3. View All Slots of a Gym \n 4. View Own Details \n 5. Exit \n ------------------------------ \n Enter your choice: " );
				System.out.println("------------------------------");
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					registerGym(sc);
					break;
				case 2:
					addSlots(sc, GymOwnerEmail);
					break;
				case 3:
					viewAllSlots(sc);
					break;
				case 4:
					getGymOwnerDetails(GymOwnerEmail);
					break;
				case 5:
					loop = false;
					break;
				default:
					System.out.println("Incorrect Choice");
				}
			}
}
	}

}
