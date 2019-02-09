package com.capg.parallelproject.repo;

import java.util.HashMap;
import java.util.Map;

import com.capg.parallelproject.beans.Customer;
import com.capg.parallelproject.beans.Transactions;
import com.capg.parallelproject.exceptiions.DuplicateMobileNumberException;
import com.capg.parallelproject.exceptiions.MobileNumberNotFoundException;


public class WalletRepoImpl implements WalletRepo {

	
	private Map<String, Customer> map = new HashMap<>();
	
	/* (non-Javadoc)
	 * @see com.capg.parallelproject.repo.WalletRepo#save(com.capg.parallelproject.beans.Customer)
	 */
	@Override
	public boolean save(Customer customer) throws DuplicateMobileNumberException {
		
		if(map.containsKey(customer.getMobileNo())) {
			throw new DuplicateMobileNumberException();
		}
		else {
			map.put(customer.getMobileNo(), customer);
			return true;
		}
	
	}
	
	/* (non-Javadoc)
	 * @see com.capg.parallelproject.repo.WalletRepo#findOne(java.lang.String)
	 */
	@Override
	public Customer findOne(String mobileNo) throws MobileNumberNotFoundException {
		
		if(map.containsKey(mobileNo)) {
			return map.get(mobileNo);
		}
		else {
			throw new MobileNumberNotFoundException();
		}
	}

	@Override
	public boolean saveTransaction(String mobileNo, Transactions transactions) throws MobileNumberNotFoundException {
		
		if(map.containsKey(transactions.getMobileNo())) {
			map.get(mobileNo).getListOfTransactions().add(transactions);
			return true;
		}
		return false;
		
	}
	
}
