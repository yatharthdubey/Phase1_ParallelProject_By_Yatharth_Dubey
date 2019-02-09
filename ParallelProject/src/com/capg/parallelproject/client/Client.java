package com.capg.parallelproject.client;

import java.math.BigDecimal;
import java.util.Scanner;

import com.capg.parallelproject.beans.Customer;
import com.capg.parallelproject.exceptiions.DuplicateMobileNumberException;
import com.capg.parallelproject.exceptiions.InsufficientWalletBalanceException;
import com.capg.parallelproject.exceptiions.MobileNumberNotFoundException;
import com.capg.parallelproject.repo.WalletRepo;
import com.capg.parallelproject.repo.WalletRepoImpl;
import com.capg.parallelproject.service.WalletService;
import com.capg.parallelproject.service.WalletServiceImpl;

public class Client {

	public static void main(String[] args)  {

		WalletRepo wr  = new WalletRepoImpl();
		WalletService ws = new WalletServiceImpl(wr);
		
		Scanner sc = new Scanner(System.in);
		
		for(;;) {
			System.out.println("Enter your choice \n"
					+ "1.Create Account \n"
					+ "2.Show Balance \n"
					+ "3.Depostie Amount \n"
					+ "4.WithDraw Amount \n"
					+ "5.Fund Transfer \n"
					+ "6.Print Last 10 Transactions \n"
					+ "7.Exit ");
			int ch = sc.nextInt();
			sc.nextLine();
			switch(ch) {
				case 1: System.out.println("Enter Mobile Number: ");
						String mobileNo  = sc.nextLine();
						if(validateMobileNumber(mobileNo)) {
							System.out.println("Enter your Name:");
							String name = sc.nextLine();
							if(validateName(name)){
								System.out.println("Enter Initial Wallet Balance: ");
								String balanceString = sc.nextLine();
								if(validateBalance(balanceString)) {
									BigDecimal balance = new BigDecimal(balanceString);
									Customer customer;
									try {
										customer = ws.createAccount(name, mobileNo, balance);
										if(customer != null) {
											System.out.println("Account created successfully \n"
															+ "Mobile Number: "+customer.getMobileNo()+"\n"
															+ "Account Holder's Name: "+customer.getName()+"\n"
															+ "Wallet Balance: "+customer.getWallet().getBalance());
											
										}
									} catch (DuplicateMobileNumberException e) {
										System.out.println("Mobile Number cannot be duplicate");
									}
									
								}
								else {
									System.err.println("Entered Amount is Invalid");
									break;
								}
							}
							else {
								System.err.println("Entered Name is Invalid");
								break;
							}
							
						}
						else {
							System.err.println("Entered Mobile Number is Invalid");
							break;
						}
						break;

				case 2: System.out.println("Enter Mobile Number: ");
						
						String mobileNo2  = sc.nextLine();
						if(validateMobileNumber(mobileNo2)) {
							Customer customer2;
							try {
								customer2 = ws.showBalance(mobileNo2);
								System.out.println("Mobile Number: "+customer2.getMobileNo()+"\n"
										+ "Account Holder's Name: "+customer2.getName()+"\n"
										+ "Wallet Balance: "+customer2.getWallet().getBalance());
								
							} catch (MobileNumberNotFoundException e) {
								System.out.println("Mobile Number not found");

							}
							
						}
						else {
							System.err.println("Entered Mobile Number is Invalid");
							break;
						}
						break;
				case 3: System.out.println("Enter Mobile Number: ");
						String mobileNo3  = sc.nextLine();
						if(validateMobileNumber(mobileNo3)) {
							System.out.println("Enter Amount: ");
							String amountString3 = sc.nextLine();
							if(validateBalance(amountString3)) {
								BigDecimal amount3 = new BigDecimal(amountString3);
								Customer customer3;
								try {
									customer3 = ws.depositAmount(mobileNo3, amount3);
									System.out.println("Amount deposited successfully \n"
											+ "Mobile Number: "+customer3.getMobileNo()+"\n"
											+ "Account Holder's Name: "+customer3.getName()+"\n"
											+ "Wallet Balance: "+customer3.getWallet().getBalance());
									
								} catch (InsufficientWalletBalanceException e) {
									System.out.println("Your Wallet Balance is Insufficient");

								} catch (MobileNumberNotFoundException e) {
									System.out.println("Mobile Number not found");

								}
								

							}
							else {
								System.err.println("Entered Amount is Invalid");
								break;
							}
						}
						else{
							System.err.println("Entered Mobile Number is Invalid");
							break;
						}
						break;
				case 4: System.out.println("Enter Mobile Number: ");
						String mobileNo4  = sc.nextLine();
						if(validateMobileNumber(mobileNo4)) {
							System.out.println("Enter Amount: ");
							String amountString4 = sc.nextLine();
							if(validateBalance(amountString4)) {
								BigDecimal amount4 = new BigDecimal(amountString4);
								Customer customer4;
								try {
									customer4 = ws.withdrawAmount(mobileNo4, amount4);
									System.out.println("Amount withdrawled successfully \n"
											+ "Mobile Number: "+customer4.getMobileNo()+"\n"
											+ "Account Holder's Name: "+customer4.getName()+"\n"
											+ "Wallet Balance: "+customer4.getWallet().getBalance());
									
								} catch (MobileNumberNotFoundException me) {
									System.out.println("Mobile Number not found");

								}catch( InsufficientWalletBalanceException e) {
									System.out.println("Your Wallet Balance is Insufficient");

								}
								
		
							}
							else {
								System.err.println("Entered Amount is Invalid");
								break;
							}
						}
						else{
							System.err.println("Entered Mobile Number is Invalid");
							break;
						}							
						break;
				case 5:	System.out.println("Enter Source Mobile Number: ");
						String sourceMobileNo5  = sc.nextLine();
						if(validateMobileNumber(sourceMobileNo5)) {
							System.out.println("Enter Target Mobile Number: ");
							String targetMobileNo5  = sc.nextLine();
							if(validateMobileNumber(targetMobileNo5)) {
								System.out.println("Enter Amount: ");
								String amountString5 = sc.nextLine();
								if(validateBalance(amountString5)) {
									BigDecimal amount5 = new BigDecimal(amountString5);
									Customer[] customer5;
									try {
										customer5 = ws.fundTransfer(sourceMobileNo5, targetMobileNo5, amount5);
										System.out.println("Amount transferred successfully \n"
												+ "Sender's Account Details \n"
												+ "Mobile Number: "+customer5[0].getMobileNo()+"\n"
												+ "Account Holder's Name: "+customer5[0].getName()+"\n"
												+ "Wallet Balance: "+customer5[0].getWallet().getBalance());
										System.out.println( "Receiver's Account Details \n"
												+ "Mobile Number: "+customer5[1].getMobileNo()+"\n"
												+ "Account Holder's Name: "+customer5[1].getName()+"\n"
												+ "Wallet Balance: "+customer5[1].getWallet().getBalance());
								
									} catch (InsufficientWalletBalanceException e)  {
										System.out.println("Your Wallet Balance is Insufficient");

									} catch(MobileNumberNotFoundException me) {
										System.out.println("Mobile Number not found");

									}
		
										}
								else {
									System.err.println("Entered Amount is Invalid");
									break;
								}
							}
							else {
								System.err.println("Entered Amount is Invalid");
								break;
							}
						}
						else {
							System.err.println("Entered Amount is Invalid");
							break;
						}
						break;
						
				case 6: System.out.println("Enter Source Mobile Number: ");
						String sourceMobileNo6  = sc.nextLine();
				try {
					System.out.println(ws.printLastTenTranscations(sourceMobileNo6));
				} catch (MobileNumberNotFoundException e) {
					System.out.println("Mobile Number not found");

				}
						break;
				case 7: sc.close();
						System.exit(0);
				default: System.out.println("Sorry! You Entered a Wrong Choice");
						 break;
						}
			}
		
	}

	private static boolean validateBalance(String balanceString) {
		
		if(balanceString.charAt(0)=='.' && balanceString.charAt(balanceString.length())=='.')
			return false;
		String[] c = balanceString.split(".");
		if(c.length>2)
			return false;
		int flag=0;
		for(int i=0;i<balanceString.length();i++)
			if(balanceString.charAt(i) < 48 && balanceString.charAt(i) > 57)
				flag=1;
		if(flag==1)
			return false;
		return true;
				
			
	}

	private static boolean validateName(String name) {
		
		int flag=0;
		if(name == null || name == "")
			return false;
		for(int i=0;i<name.length();i++)
			if((name.toLowerCase().charAt(i) >= 97 && name.toLowerCase().charAt(i) <= 122) || name.charAt(i)==' ');
			else {
				flag=1;
			}
		if(flag ==1)
			return false;
		return true;
		
	}

	private static boolean validateMobileNumber(String mobileNo) {

		if(mobileNo.length()!=10) 
			return false;
		
		for(int i=0;i<mobileNo.length();i++)
			if(mobileNo.charAt(i) < 48 || mobileNo.charAt(i) > 57)
				return false;
		return true;
	}

}
