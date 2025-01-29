package com.flipkart.exceptions;

public class GymNotFoundException extends Exception{
		
	public String getMessage() {
        return "No gym found!";
    }
}
