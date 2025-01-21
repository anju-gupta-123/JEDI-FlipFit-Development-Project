package com.flipkart.bean;

public class Payment {
	private int paymentID;
	private int bookingId;
	private float amount;
	/**
	 * @return the paymentID
	 */
	public int getPaymentID() {
		return paymentID;
	}
	/**
	 * @param paymentID the paymentID to set
	 */
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	/**
	 * @return the bookingId
	 */
	public int getBookingId() {
		return bookingId;
	}
	/**
	 * @param bookingId the bookingId to set
	 */
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	private String mode;
}
