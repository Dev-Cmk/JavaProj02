package project2.ver04;

import java.io.Serializable;

abstract public class Account implements Serializable {
	public String accountNumber, name;
	public double allMoney;
	double h_rate;
	int rate;

	public Account(String number, String name, double balance) {
		this.accountNumber = number;
		this.name = name;
		this.allMoney = balance;
	}

	public void showAccInfo() {
	}
}// end of class