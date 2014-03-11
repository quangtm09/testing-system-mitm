package test;

import java.util.List;

import model.Account;
import business.ManageAccount;

public class AccountTest {
	private ManageAccount manageAccount;

	public ManageAccount getManageAccount() {
		return manageAccount;
	}

	public void setManageAccount(ManageAccount manageAccount) {
		this.manageAccount = manageAccount;
	}
	
	public void printAllAccount(){
		List<Account> accountList=manageAccount.getAccounts();
		for(Account account:accountList){
			System.out.println(account.getAccountName());
		}
	}
	
	public void getAccountByID(long accountId){
		System.out.println(manageAccount.getAccount(accountId).getAccountName());
	}
}
