package com.flipkart.bean;

public class Gym_Owner extends User {
	private String aadharCard;
	private String GSTNo;
	private boolean isApproved;
	/**
	 * @return the isApproved
	 */
	public boolean isApproved() {
		return isApproved;
	}
	/**
	 * @param isApproved the isApproved to set
	 */
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	/**
	 * @return the aadharCard
	 */
	public String getAadharCard() {
		return aadharCard;
	}
	/**
	 * @param aadharCard the aadharCard to set
	 */
	public void setAadharCard(String aadharCard) {
		this.aadharCard = aadharCard;
	}
	/**
	 * @return the gSTNo
	 */
	public String getGSTNo() {
		return GSTNo;
	}
	/**
	 * @param gSTNo the gSTNo to set
	 */
	public void setGSTNo(String gSTNo) {
		GSTNo = gSTNo;
	}
	
	
}