package com.capg.parallelproject.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.LinkedList;

import org.junit.Test;

import com.capg.parallelproject.beans.Customer;
import com.capg.parallelproject.beans.Transactions;
import com.capg.parallelproject.beans.Wallet;
import com.capg.parallelproject.exceptiions.DuplicateMobileNumberException;
import com.capg.parallelproject.exceptiions.InsufficientWalletBalanceException;
import com.capg.parallelproject.exceptiions.MobileNumberNotFoundException;
import com.capg.parallelproject.repo.WalletRepo;
import com.capg.parallelproject.repo.WalletRepoImpl;
import com.capg.parallelproject.service.WalletService;
import com.capg.parallelproject.service.WalletServiceImpl;

public class TestCase {
	
	WalletRepo wr = new WalletRepoImpl();
	WalletService ws = new WalletServiceImpl(wr);

	@Test
	public void whenCustomerAccountIsCreatedSuccessfully() throws DuplicateMobileNumberException {
		
		Wallet wallet = new Wallet();
		wallet.setBalance(new BigDecimal("200.0"));
		Customer customer = new Customer();
		customer.setMobileNo("9412164694");
		customer.setName("Yatharth");
		customer.setWallet(wallet);
		customer.setListOfTransactions(new LinkedList<Transactions>());
		assertEquals(customer, ws.createAccount("Yatharth", "9412164694", new BigDecimal("200.0")));
		
	}
	
	@Test(expected = DuplicateMobileNumberException.class)
	public void whenYouEnteredADuplicateNumberThenDuplicateNumberExceptionOccurs() throws DuplicateMobileNumberException {
		
		ws.createAccount("Yatharth", "9412164694", new BigDecimal("200.0"));
		ws.createAccount("Dubey", "9412164694", new BigDecimal("300.0"));
		
	}
	
	@Test(expected = MobileNumberNotFoundException.class)
	public void whenMobileNumberIsNotPresentWhileCallingShowBalaceMethodThenMobileNumberNotFoundExceptionOccurs() throws DuplicateMobileNumberException, MobileNumberNotFoundException {
		
		ws.createAccount("Yatharth", "9412164694", new BigDecimal("200.0"));
		ws.showBalance("9412164695");
		
	}
	@Test
	public void whenShowBalanceIsSuccessfullyCompleted() throws DuplicateMobileNumberException, MobileNumberNotFoundException {
		
		assertEquals(ws.createAccount("Yatharth", "9412164694", new BigDecimal("200.0")),ws.showBalance("9412164694"));
		
	}
	@Test
	public void whenWithdrawAmountIsSuccessfullyCompleted() throws DuplicateMobileNumberException, MobileNumberNotFoundException, InsufficientWalletBalanceException {
		ws.createAccount("Yatharth", "9412164694", new BigDecimal("200.0"));
		ws.withdrawAmount("9412164694", new BigDecimal("100.0"));

	}
	
	@Test
	public void whenDepositeAmountIsSuccessfullyCompleted() throws DuplicateMobileNumberException, MobileNumberNotFoundException, InsufficientWalletBalanceException {
		ws.createAccount("Yatharth", "9412164694", new BigDecimal("200.0"));
		ws.depositAmount("9412164694", new BigDecimal("100.0"));

	}
	@Test
	public void whenFundTransferIsSuccessfullyCompleted() throws DuplicateMobileNumberException, MobileNumberNotFoundException, InsufficientWalletBalanceException {
		ws.createAccount("Yatharth", "9412164694", new BigDecimal("200.0"));
		ws.createAccount("Yatharth", "9808180238", new BigDecimal("100.0"));
		ws.fundTransfer("9412164694", "9808180238", new BigDecimal("50.0"));
		

	}
	@Test(expected = MobileNumberNotFoundException.class)
	public void whenMobileNumberIsNotPresentWhileCallingWithdrawAmountMethodThenMobileNumberNotFoundExceptionOccurs() throws DuplicateMobileNumberException, MobileNumberNotFoundException, InsufficientWalletBalanceException {
		
		ws.createAccount("Yatharth", "9412164694", new BigDecimal("200.0"));
		ws.withdrawAmount("9412164695", new BigDecimal("100.0"));
		
	}
	
	@Test(expected = InsufficientWalletBalanceException.class)
	public void whenBalanceIsLessThanAmountThatYouWantToWithdrawThenInsufficientWalletBalanceExceptionOccurs() throws DuplicateMobileNumberException, MobileNumberNotFoundException, InsufficientWalletBalanceException {
		
		ws.createAccount("Yatharth", "9412164694", new BigDecimal("200.0"));
		ws.withdrawAmount("9412164694", new BigDecimal("300.0"));
		
	}
	@Test(expected = MobileNumberNotFoundException.class)
	public void whenMobileNumberIsNotPresentWhileCallingDepositeAmountMethodThenMobileNumberNotFoundExceptionOccurs() throws DuplicateMobileNumberException, MobileNumberNotFoundException, InsufficientWalletBalanceException {
		
		ws.createAccount("Yatharth", "9412164694", new BigDecimal("200.0"));
		ws.depositAmount("9412164695", new BigDecimal("100.0"));
		
	}
	@Test(expected = MobileNumberNotFoundException.class)
	public void whenSourceMobileNumberIsNotPresentWhileCallingFundTranferMethodThenMobileNumberNotFoundExceptionOccurs() throws DuplicateMobileNumberException, MobileNumberNotFoundException, InsufficientWalletBalanceException {
		
		ws.createAccount("Yatharth", "9412164694", new BigDecimal("200.0"));
		ws.createAccount("Yatharth", "9808180238", new BigDecimal("100.0"));
		ws.fundTransfer("9412164695", "9808180238", new BigDecimal("50.0"));
		
	}
	@Test(expected = MobileNumberNotFoundException.class)
	public void whenTargetMobileNumberIsNotPresentWhileCallingDepositeAmountMethodThenMobileNumberNotFoundExceptionOccurs() throws DuplicateMobileNumberException, MobileNumberNotFoundException, InsufficientWalletBalanceException {
		
		ws.createAccount("Yatharth", "9412164694", new BigDecimal("200.0"));
		ws.createAccount("Yatharth", "9808180238", new BigDecimal("100.0"));
		ws.fundTransfer("9412164694", "9808180239", new BigDecimal("50.0"));
		
	}
	
	@Test(expected = InsufficientWalletBalanceException.class)
	public void whenBalanceInSourceAccountIsLessThanAmountThatYouWantToTransferThenInsufficientWalletBalanceExceptionOccurs() throws DuplicateMobileNumberException, MobileNumberNotFoundException, InsufficientWalletBalanceException {
		
		ws.createAccount("Yatharth", "9412164694", new BigDecimal("100.0"));
		ws.createAccount("Yatharth", "9808180238", new BigDecimal("200.0"));
		ws.fundTransfer("9412164694", "9808180238", new BigDecimal("150.0"));
		
	}
	
	@Test(expected = MobileNumberNotFoundException.class)
	public void whenMobileNumberIsNotPresentWhileCallingPrintLastTenTransactionsMethodThenMobileNumberNotFoundExceptionOccurs() throws DuplicateMobileNumberException, MobileNumberNotFoundException, InsufficientWalletBalanceException {
		
		ws.createAccount("Yatharth", "9412164694", new BigDecimal("200.0"));
		ws.printLastTenTranscations("9412164695");
		
	}
}
