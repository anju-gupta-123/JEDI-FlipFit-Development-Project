package com.flipkart.exceptions;

public class UserNotFoundException extends Exception{

	public String getMessage() {
        return "Oops! Wrong credentials! Sorryy";
    } 
}
