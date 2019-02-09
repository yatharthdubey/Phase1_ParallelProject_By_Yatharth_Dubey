package com.capg.parallelproject.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;

import com.capg.parallelproject.beans.Customer;
import com.capg.parallelproject.beans.Transactions;
import com.capg.parallelproject.beans.Transactions.whichTransaction;
import com.capg.parallelproject.beans.Wallet;
import com.capg.parallelproject.exceptiions.DuplicateMobileNumberException;
import com.capg.parallelproject.exceptiions.InsufficientWalletBalanceException;
import com.capg.parallelproject.exceptiions.MobileNumberNotFoundException;
import com.capg.parallelproject.repo.WalletRepo;

public class WalletServiceImpl implements WalletService {

	
	WalletRepo walletRepo;
	
	public WalletServiceImpl(WalletRepo walletRepo) {
		super();
		this.walletRepo = walletRepo;
	}

	/* (non-Javadoc)
	 * @see com.capg.parallelproject.service.WalletService#createAccount(java.lang.String, java.lang.String, java.math.BigDecimal)
	 */
	@Override
	public Customer createAccount(String name, String mobileNo, BigDecimal balance) throws DuplicateMobileNumberException {
		
		Wallet wallet =new Wallet();
		wallet.setBalance(balance);
		Customer customer = new Customer();
		customer.setName(name);
		customer.setMobileNo(mobileNo);
		customer.setWallet(wallet);
		if(walletRepo.save(customer)) {
			return customer;
		}
		return null;
			
	}
	
	/* (non-Javadoc)
	 * @see com.capg.parallelproject.service.WalletService#showBalance(java.lang.String)
	 */
	@Override
	public Customer showBalance(String mobileNo) throws MobileNumberNotFoundException {

		Customer customer = new Customer();
		customer = walletRepo.findOne(mobileNo);
		return customer;
	}
	
	/* (non-Javadoc)
	 * @see com.capg.parallelproject.service.WalletService#fundTransfer(java.lang.String, java.lang.String, java.math.BigDecimal)
	 */
	@Override
	public Customer[] fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws InsufficientWalletBalanceException, MobileNumberNotFoundException {
	
		Customer[] customerArray = new Customer[2];
		Customer customerSource = new Customer();
		customerSource = walletRepo.findOne(sourceMobileNo);
		Customer customerTarget = new Customer();
		customerTarget = walletRepo.findOne(targetMobileNo);
//		System.out.println(amount.compareTo(customerSource.getWallet().getBalance()));
		if(amount.compareTo(customerSource.getWallet().getBalance()) == 1) {
			throw new InsufficientWalletBalanceException();
		}
		else {
//			withdrawAmount(customerSource.getMobileNo(), amount);
			customerSource.getWallet().setBalance(customerSource.getWallet().getBalance().subtract(amount));
			customerTarget.getWallet().setBalance(customerTarget.getWallet().getBalance().add(amount));
			Transactions transaction = new Transactions();
			transaction.setId();
			transaction.setMobileNo(customerSource.getMobileNo());
			transaction.setWt(whichTransaction.FundTransfer_From);
			transaction.setAmount(customerSource.getWallet().getBalance());
			walletRepo.saveTransaction(customerSource.getMobileNo(),transaction);
			Transactions transaction1 = new Transactions();
			transaction1.setId();
			transaction1.setMobileNo(customerTarget.getMobileNo());
			transaction1.setWt(whichTransaction.FundTransfer_To);
			transaction1.setAmount(customerTarget.getWallet().getBalance());
			walletRepo.saveTransaction(customerSource.getMobileNo(),transaction1);
//			depositAmount(customerTarget.getMobileNo(), amount);
			Transactions transaction2 = new Transactions();
			transaction2.setId();
			transaction2.setMobileNo(customerSource.getMobileNo());
			transaction2.setWt(whichTransaction.FundTransfer_To);
			transaction2.setAmount(customerTarget.getWallet().getBalance());
			walletRepo.saveTransaction(customerTarget.getMobileNo(),transaction2);
			Transactions transaction3 = new Transactions();
			transaction3.setId();
			transaction3.setMobileNo(customerTarget.getMobileNo());
			transaction3.setWt(whichTransaction.FundTransfer_From);
			transaction3.setAmount(customerSource.getWallet().getBalance());
			walletRepo.saveTransaction(customerTarget.getMobileNo(),transaction3);
			customerArray[0] =  customerSource;
			customerArray[1] = customerTarget;
		}
		
		return customerArray;
}
	/* (non-Javadoc)
	 * @see com.capg.parallelproject.service.WalletService#depositAmount(java.lang.String, java.math.BigDecimal)
	 */
	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws InsufficientWalletBalanceException, MobileNumberNotFoundException {
		
		Customer customer = new Customer();
		customer = walletRepo.findOne(mobileNo);
		
			customer.getWallet().setBalance(customer.getWallet().getBalance().add(amount));
			Transactions transaction = new Transactions();
			transaction.setId();
			transaction.setMobileNo(mobileNo);
			transaction.setWt(whichTransaction.Deposite);
			transaction.setAmount(customer.getWallet().getBalance());
			walletRepo.saveTransaction(customer.getMobileNo(),transaction);
			return customer;
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.capg.parallelproject.service.WalletService#withdrawAmount(java.lang.String, java.math.BigDecimal)
	 */
	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws MobileNumberNotFoundException, InsufficientWalletBalanceException {
		
		Customer customer = new Customer();
		customer = walletRepo.findOne(mobileNo);
		if(amount.compareTo(customer.getWallet().getBalance()) == 1) {
			throw new InsufficientWalletBalanceException();
		}
		else {
		
		customer.getWallet().setBalance(customer.getWallet().getBalance().subtract(amount));
		Transactions transaction = new Transactions();
		transaction.setId();
		transaction.setMobileNo(mobileNo);
		transaction.setWt(whichTransaction.Withdraw);
		transaction.setAmount(customer.getWallet().getBalance());
		walletRepo.saveTransaction(customer.getMobileNo(),transaction);
		return customer;
		
		}
	}

	@Override
	public LinkedList<Transactions> printLastTenTranscations(String mobileNo) throws MobileNumberNotFoundException {

		
		Customer customer = new Customer();
		customer = walletRepo.findOne(mobileNo);
		LinkedList<Transactions> l = new LinkedList<>();
		Collections.reverse(customer.getListOfTransactions());
		if(customer.getListOfTransactions().size()>10) {
			for(int i=0;i<10;i++)
				l.add(customer.getListOfTransactions().get(i));
			Collections.reverse(l);
			return l;
		}
		else {
			return customer.getListOfTransactions();
		}
	}
}
