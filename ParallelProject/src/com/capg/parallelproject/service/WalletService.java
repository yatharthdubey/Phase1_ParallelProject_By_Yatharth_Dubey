package com.capg.parallelproject.service;

import java.math.BigDecimal;
import java.util.LinkedList;

import com.capg.parallelproject.beans.Customer;
import com.capg.parallelproject.beans.Transactions;
import com.capg.parallelproject.exceptiions.DuplicateMobileNumberException;
import com.capg.parallelproject.exceptiions.InsufficientWalletBalanceException;
import com.capg.parallelproject.exceptiions.MobileNumberNotFoundException;

public interface WalletService {

	Customer createAccount(String name, String mobileNo, BigDecimal balance) throws DuplicateMobileNumberException;

	Customer showBalance(String mobileNo) throws MobileNumberNotFoundException;

	LinkedList<Transactions> printLastTenTranscations(String mobileNo)throws MobileNumberNotFoundException;
	
	Customer[] fundTransfer(String sourceMobileNo, String targetMobileno, BigDecimal amount) throws InsufficientWalletBalanceException, MobileNumberNotFoundException;

	Customer depositAmount(String mobileNo, BigDecimal amount) throws InsufficientWalletBalanceException, MobileNumberNotFoundException;

	Customer withdrawAmount(String mobileNo, BigDecimal amount) throws MobileNumberNotFoundException, InsufficientWalletBalanceException;

}