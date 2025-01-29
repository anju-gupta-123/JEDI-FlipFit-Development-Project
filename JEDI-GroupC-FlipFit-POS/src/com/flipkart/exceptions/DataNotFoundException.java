package com.flipkart.exceptions;

public class DataNotFoundException extends Exception{
	public String getMessage(){
        return "No data was found";
    }

}
