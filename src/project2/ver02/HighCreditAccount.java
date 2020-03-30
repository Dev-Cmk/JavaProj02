package project2.ver02;

public class HighCreditAccount extends Account implements CustomSpecialRate{

	String credit_level;
	
	public HighCreditAccount(String number, String name, int balance,int rate,String credit) {
		super(number, name, balance);
		this.rate=rate;
		this.credit_level=credit;
		switch(credit_level) {
		case first:
			this.h_rate = 0.07;
			break;
		case second:
			this.h_rate = 0.04;
			break;
		case third:
			this.h_rate = 0.02;
		}
	}
	
	public void showAccInfo() {
		System.out.println("-------");
		System.out.println("계좌번호>"+ accountNumber);
		System.out.println("고객이름>"+ name);
		System.out.println("잔고>" + allMoney);
		System.out.println("기본이자>" + rate+"%");
		System.out.println("신용등급>" + credit_level);
		System.out.println("-------");
	}
}
