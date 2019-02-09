package com.capg.parallelproject.beans;

import java.math.BigDecimal;

public class Transactions {

	private static int count=1;
	private int id;
	private String mobileNo;
	public enum whichTransaction{Deposite,Withdraw,FundTransfer_From,FundTransfer_To}
	private BigDecimal amount;
	private whichTransaction wt;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + id;
		result = prime * result + ((mobileNo == null) ? 0 : mobileNo.hashCode());
		result = prime * result + ((wt == null) ? 0 : wt.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transactions other = (Transactions) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (id != other.id)
			return false;
		if (mobileNo == null) {
			if (other.mobileNo != null)
				return false;
		} else if (!mobileNo.equals(other.mobileNo))
			return false;
		if (wt != other.wt)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transactions [ id=" + id + ", mobileNo=" + mobileNo + ", amount=" + amount + ", wt="
				+ wt + "]\n";
	}
	public int getId() {
		return id;
	}
	public void setId() {
		this.id = count++;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public whichTransaction getWt() {
		return wt;
	}
	public void setWt(whichTransaction wt) {
		this.wt = wt;
	}
	}
	

