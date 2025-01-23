package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Gym_Center;
import com.flipkart.bean.Gym_Owner;

public class AdminOperations {

    public void viewAllGymOwners() {
        System.out.println("\n--- All Gym Owners ---");
        if (Admin.getApprovedGymOwners().isEmpty()) {
            System.out.println("No approved gym owners.");
            return;
        }
        for (Gym_Owner owner : Admin.getApprovedGymOwners()) {
            System.out.println("ID: " + owner.getId() + ", Name: " + owner.getName() + ", Email: " + owner.getEmail());
        }
    }

    public void viewAllGyms() {
        System.out.println("\n--- All Approved Gyms ---");
        if (Admin.getApprovedGymCenters().isEmpty()) {
            System.out.println("No approved gyms.");
            return;
        }
        for (Gym_Center gym : Admin.getApprovedGymCenters()) {
            System.out.println("ID: " + gym.getCenter_id() + ", Name: " + gym.getCenter_name() + ", Address: " + gym.getAddress());
        }
    }

    public void viewPendingGymOwnerRequests() {
        System.out.println("\n--- Pending Gym Owner Requests ---");
        if (Admin.getGymOwners().isEmpty()) {
            System.out.println("No pending gym owner requests.");
            return;
        }
        for (Gym_Owner owner : Admin.getGymOwners()) {
            System.out.println("ID: " + owner.getId() + ", Name: " + owner.getName() + ", Email: " + owner.getEmail());
        }
    }

    public void viewPendingGymRequests() {
        System.out.println("\n--- Pending Gym Requests ---");
        if (Admin.getGymCenters().isEmpty()) {
            System.out.println("No pending gym requests.");
            return;
        }
        for (Gym_Center gym : Admin.getGymCenters()) {
            System.out.println("ID: " + gym.getCenter_id() + ", Name: " + gym.getCenter_name() + ", Address: " + gym.getAddress());
        }
    }

    public void approveGymRequest(int gymId) {
        Gym_Center gymToApprove = null;
        for (Gym_Center gym : Admin.getGymCenters()) {
            if (gym.getCenter_id() == gymId) {
                gymToApprove = gym;
                break;
            }
        }
        if (gymToApprove != null) {
            Admin.removeGymCenters(gymToApprove);
            Admin.setApprovedGymCenters(gymToApprove);
            System.out.println("Gym with ID " + gymId + " has been approved.");
        } else {
            System.out.println("Gym with ID " + gymId + " not found.");
        }
    }

    public void approveGymOwnerRequest(int ownerId) {
        Gym_Owner ownerToApprove = null;
        for (Gym_Owner owner : Admin.getGymOwners()) {
            if (owner.getId() == ownerId) {
                ownerToApprove = owner;
                break;
            }
        }
        if (ownerToApprove != null) {
            Admin.removeGymOwner(ownerToApprove);
            Admin.setApprovedGymOwners(ownerToApprove);
            System.out.println("Gym Owner with ID " + ownerId + " has been approved.");
        } else {
            System.out.println("Gym Owner with ID " + ownerId + " not found.");
        }
    }
}