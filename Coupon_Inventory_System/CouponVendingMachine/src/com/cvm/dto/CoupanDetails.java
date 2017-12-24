/**
 * 
 */
package com.cvm.dto;

import java.util.Date;

/**
 * @author Bhushan
 *
 */
public class CoupanDetails {
	private String coupanProvider;
	private String coupanCatagory;
	private String coupanAccesoryName;
	private String coupanName;
	private int itemPrize;
	private int discountRate;
	private float finalPrize;
	private int numberOfDaysForCoupanValidity;
	private String coupanStatus;
	private Date expiryDate;
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return the finalPrize
	 */
	public float getFinalPrize() {
		return finalPrize;
	}
	/**
	 * @param finalPrize the finalPrize to set
	 */
	public void setFinalPrize(float finalPrize) {
		this.finalPrize = finalPrize;
	}
	
	/**
	 * @return the coupanProvider
	 */
	public String getCoupanProvider() {
		return coupanProvider;
	}
	/**
	 * @param coupanProvider the coupanProvider to set
	 */
	public void setCoupanProvider(String coupanProvider) {
		this.coupanProvider = coupanProvider;
	}
	/**
	 * @return the coupanCatagory
	 */
	public String getCoupanCatagory() {
		return coupanCatagory;
	}
	/**
	 * @param coupanCatagory the coupanCatagory to set
	 */
	public void setCoupanCatagory(String coupanCatagory) {
		this.coupanCatagory = coupanCatagory;
	}
	/**
	 * @return the coupanAccesoryName
	 */
	public String getCoupanAccesoryName() {
		return coupanAccesoryName;
	}
	/**
	 * @param coupanAccesoryName the coupanAccesoryName to set
	 */
	public void setCoupanAccesoryName(String coupanAccesoryName) {
		this.coupanAccesoryName = coupanAccesoryName;
	}
	/**
	 * @return the coupanName
	 */
	public String getCoupanName() {
		return coupanName;
	}
	/**
	 * @param coupanName the coupanName to set
	 */
	public void setCoupanName(String coupanName) {
		this.coupanName = coupanName;
	}
	/**
	 * @return the itemPrize
	 */
	public int getItemPrize() {
		return itemPrize;
	}
	/**
	 * @param itemPrize the itemPrize to set
	 */
	public void setItemPrize(int itemPrize) {
		this.itemPrize = itemPrize;
	}
	/**
	 * @return the discountRate
	 */
	public int getDiscountRate() {
		return discountRate;
	}
	/**
	 * @param discountRate the discountRate to set
	 */
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
	/**
	 * @return the numberOfDaysForCoupanValidity
	 */
	public int getNumberOfDaysForCoupanValidity() {
		return numberOfDaysForCoupanValidity;
	}
	/**
	 * @param numberOfDaysForCoupanValidity the numberOfDaysForCoupanValidity to set
	 */
	public void setNumberOfDaysForCoupanValidity(int numberOfDaysForCoupanValidity) {
		this.numberOfDaysForCoupanValidity = numberOfDaysForCoupanValidity;
	}
	/**
	 * @return the coupanStatus
	 */
	public String getCoupanStatus() {
		return coupanStatus;
	}
	/**
	 * @param coupanStatus the coupanStatus to set
	 */
	public void setCoupanStatus(String coupanStatus) {
		this.coupanStatus = coupanStatus;
	}
	/**
	 * @param coupanProvider
	 * @param coupanCatagory
	 * @param coupanAccesoryName
	 * @param coupanName
	 * @param itemPrize
	 * @param discountRate
	 * @param numberOfDaysForCoupanValidity
	 * @param coupanStatus
	 */
	public CoupanDetails(String coupanProvider, String coupanCatagory, String coupanAccesoryName, String coupanName,
			int itemPrize, int discountRate, int numberOfDaysForCoupanValidity, String coupanStatus) {
		super();
		this.coupanProvider = coupanProvider;
		this.coupanCatagory = coupanCatagory;
		this.coupanAccesoryName = coupanAccesoryName;
		this.coupanName = coupanName;
		this.itemPrize = itemPrize;
		this.discountRate = discountRate;
		this.numberOfDaysForCoupanValidity = numberOfDaysForCoupanValidity;
		this.coupanStatus = coupanStatus;
	}
	/**
	 * 
	 */
	public CoupanDetails() {
		super();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CoupanDetails [coupanProvider=" + coupanProvider + ", coupanCatagory=" + coupanCatagory
				+ ", coupanAccesoryName=" + coupanAccesoryName + ", coupanName=" + coupanName + ", itemPrize="
				+ itemPrize + ", discountRate=" + discountRate + ", numberOfDaysForCoupanValidity="
				+ numberOfDaysForCoupanValidity + ", coupanStatus=" + coupanStatus + "]";
	}
	
	
}
