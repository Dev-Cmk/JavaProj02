package project2.ver02;

public class Account {

	public String accountNumber, name;
	public int allMoney;
	double h_rate;
	int rate;

	public Account(String number, String name, int balance) {
		this.accountNumber = number;
		this.name = name;
		this.allMoney = balance;
	}

	public void showAccInfo() {
	}
}// end of class
