package com.flipkart.DAO;

import com.flipkart.bean.*;
import java.util.*;

public interface GymOwnerDAOInterface {
    
    public boolean addSlot(Slot newSlot);
    
    public Gym_Owner getGymOwnerDetails(String ownerEmail);
    
    public List<Gym_Center> getCenterDetails(int ownerId);
    
    public boolean isApprovedOwner(int gymOwnerId);
    
    public boolean isApprovedCenter(int gymCenterId);
    
    public boolean registerGymCenter(Gym_Center gCenter);
    
}
