
// Observable

import java.util.ArrayList;
import java.util.Observable;


public class Account extends Observable{
	
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", accountMoney="
				+ accountMoney + "]\n";
	}

	public int accountID;
	public double accountMoney;
	
	public Account(int accountID,double accountMoney)		// Constructor
	{
		this.accountID=accountID;
		this.accountMoney=accountMoney;
	}
	
	public void setAccount(int accountID,double accountMoney)		// we have setChanged and notifyObservers
	{																// in order to notify person if a change is made here
		this.accountID=accountID;
		this.accountMoney=accountMoney;
		setChanged();
		notifyObservers();
	}

	public void addMoney(double Sum)
	{
		this.accountMoney=this.accountMoney+Sum;
		setChanged();
		notifyObservers();
		
	}
	
	public void substractMoney(double Sum)
	{
		this.accountMoney=this.accountMoney-Sum;
		setChanged();
		notifyObservers();
		
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public double getAccountMoney() {
		return accountMoney;
	}

	public void setAccountMoney(double accountMoney) {
		this.accountMoney = accountMoney;
	}
	
	
}
