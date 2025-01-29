package com.flipkart.exceptions;

public class SlotsNotFoundException extends Exception {

	public String getMessage() {
        return "No slots found!";
    }
}
