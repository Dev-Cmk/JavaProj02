package project2.ver04;

public class NormalAccount extends Account {

	public NormalAccount(String number, String name, double balance, int rate) {
		super(number, name, balance);

		this.rate = rate;
	}

	public void showAccInfo() {
		System.out.println("-------");
		System.out.println("계좌번호>" + accountNumber);
		System.out.println("고객이름>" + name);
		System.out.println("잔고>" + allMoney);
		System.out.println("기본이자>" + rate + "%");
		System.out.println("-------");
	}
}