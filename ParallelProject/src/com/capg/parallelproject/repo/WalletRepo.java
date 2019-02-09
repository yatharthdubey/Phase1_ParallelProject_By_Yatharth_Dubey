package com.capg.parallelproject.repo;

import com.capg.parallelproject.beans.Customer;
import com.capg.parallelproject.beans.Transactions;
import com.capg.parallelproject.exceptiions.DuplicateMobileNumberException;
import com.capg.parallelproject.exceptiions.MobileNumberNotFoundException;

public interface WalletRepo {

	boolean save(Customer customer) throws DuplicateMobileNumberException;

	boolean saveTransaction(String mobileNo, Transactions transaction) throws MobileNumberNotFoundException;
	
	Customer findOne(String mobileNo) throws MobileNumberNotFoundException;
	 
}