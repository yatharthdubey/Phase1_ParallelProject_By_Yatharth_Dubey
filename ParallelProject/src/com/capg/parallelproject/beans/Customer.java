package com.capg.parallelproject.beans;

import java.util.LinkedList;

public class Customer {

	private String name;
	private String mobileNo;
	private Wallet wallet;
	private LinkedList<Transactions> listOfTransactions = new LinkedList<>();
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listOfTransactions == null) ? 0 : listOfTransactions.hashCode());
		result = prime * result + ((mobileNo == null) ? 0 : mobileNo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((wallet == null) ? 0 : wallet.hashCode());
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
		Customer other = (Customer) obj;
		if (listOfTransactions == null) {
			if (other.listOfTransactions != null)
				return false;
		} else if (!listOfTransactions.equals(other.listOfTransactions))
			return false;
		if (mobileNo == null) {
			if (other.mobileNo != null)
				return false;
		} else if (!mobileNo.equals(other.mobileNo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (wallet == null) {
			if (other.wallet != null)
				return false;
		} else if (!wallet.equals(other.wallet))
			return false;
		return true;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	public LinkedList<Transactions> getListOfTransactions() {
		return listOfTransactions;
	}
	public void setListOfTransactions(LinkedList<Transactions> listOfTransactions) {
		this.listOfTransactions = listOfTransactions;
	}
	
	
	
	
}
