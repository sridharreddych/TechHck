package com.hcl.loan.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
/**
 * 
 * @author shashank.sr
 * It will hold the user Input for calculation
 *
 */
public class EMI implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -138520121019175499L;
	@NotNull
	private String loanType;
	@NotNull
	@Min(1)
	@Max(1000000000)
	private long amount;
	@NotNull
	@Min(1)
	@Max(15)
	private int year;
	@NotNull
	private boolean seniorCitizen;
	private double rateOfInterest;
	private long monthlyEMI;

	

	public long getMonthlyEMI() {
		return monthlyEMI;
	}

	public void setMonthlyEMI(long monthlyEMI) {
		this.monthlyEMI = monthlyEMI;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public boolean isSeniorCitizen() {
		return seniorCitizen;
	}

	public void setSeniorCitizen(boolean seniorCitizen) {
		this.seniorCitizen = seniorCitizen;
	}
	@Override
	public String toString() {
		return "loanType:"+loanType+" amount:"+amount+" year:"+year+" seniorCitizen:"+seniorCitizen+" rateOfInterest:"+rateOfInterest+" monthlyEMI:"+monthlyEMI;
	}

}
