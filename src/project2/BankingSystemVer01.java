package project2;

import java.util.Scanner;

import project2.ver01.Account;
import project2.ver01.MenuChoice;

public class BankingSystemVer01 implements MenuChoice {
	private Account[] data = new Account[50];;
	int numOfaccount = 0;

	// 메뉴출력
	public void showMenu() {
		while (true) {
			System.out.println("-----Menu-----");
			System.out.println("1.계좌개설");
			System.out.println("2.입   금");
			System.out.println("3.출   금");
			System.out.println("4.계좌정보출력");
			System.out.println("5.프로그램종료");

			System.out.print("선택:");
			Scanner sc = new Scanner(System.in);
			int opt = sc.nextInt();
			switch (opt) {
			case MAKE:
				makeAccount();
				break;
			case DEPOSIT:
				depositMoney();
				break;
			case WITHDRAW:
				withdrawMoney();
				break;
			case INQUIRE:
				showAccInfo();
				break;
			case EXIT:
				System.out.println("프로그램 종료..!!");
				return;
			}

		} // end of while
	}// end of showMenu

	// 계좌개설을 위한 함수
	void makeAccount() {
		Scanner sc2 = new Scanner(System.in);
		System.out.println("***신규계좌개설***");
		System.out.print("계좌번호:");
		String account = sc2.nextLine();
		System.out.print("고객이름:");
		String name = sc2.nextLine();
		System.out.print("잔고:");
		int balance = sc2.nextInt();

		data[numOfaccount++] = new Account(account, name, balance);
		System.out.println("계좌개설이 완료되었습니다.");
	}// end of makeAccount

	// 입금
	void depositMoney() {
		System.out.println("***입  금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요 ");
		Scanner sc3 = new Scanner(System.in);
		System.out.print("계좌번호:");
		String inputNum = sc3.nextLine();
		System.out.print("입금액:");
		int inputMoney = sc3.nextInt();

		for (int i = 0; i < data.length; i++) {
			if (data[i] != null && data[i].accountNumber.equals(inputNum)) {
				data[i].allMoney += inputMoney;
			}
		}

		System.out.println("입금이 완료되었습니다.");
	}

	// 출금
	void withdrawMoney() {
		System.out.println("***출  금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요 ");
		Scanner sc4 = new Scanner(System.in);
		System.out.print("계좌번호:");
		String actNum = sc4.nextLine();
		System.out.print("출금액:");
		int outputMoney = sc4.nextInt();

		for (int i = 0; i < data.length; i++) {
			if (data[i] != null && data[i].accountNumber.equals(actNum)) {
				data[i].allMoney -= outputMoney;
			}
		}

		System.out.println("출금이 완료되었습니다.");
	}

	// 전체계좌정보출력
	void showAccInfo() {
		System.out.println("***계좌정보출력***");
		System.out.println("-------");
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null) {
				System.out.println("계좌번호:" + data[i].accountNumber);
				System.out.println("고객이름:" + data[i].name);
				System.out.println("잔고:" + data[i].allMoney);
			}
		}
		System.out.println("-------");
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}

	public static void main(String[] args) {
		BankingSystemVer01 act = new BankingSystemVer01();
		act.showMenu();
	}

}
