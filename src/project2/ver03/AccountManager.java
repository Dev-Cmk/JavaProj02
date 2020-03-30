package project2.ver03;

import java.util.InputMismatchException;
import java.util.Scanner;

import project2.ver03.Account;
import project2.ver03.MenuChoice;
import project2.ver03.MenuSelectException;

public class AccountManager implements MenuChoice {
	private Account[] data = new Account[50]; ;
	int numOfaccount;
	int selection;
	String credit;
	
	public AccountManager() {
		numOfaccount = 0;
	}
	//메뉴출력
		public void showMenu() {
			while(true) {
			System.out.println("-----Menu-----");
			System.out.println("1.계좌개설");
			System.out.println("2.입   금");
			System.out.println("3.출   금");
			System.out.println("4.계좌정보출력");
			System.out.println("5.프로그램종료");
			System.out.print("선택:");
			
			try {
				Scanner sc = new Scanner(System.in);
				int opt = sc.nextInt();
				
				if(!(opt>=1 && opt<=5)) {
					MenuSelectException err = new MenuSelectException();
					throw err;
				}

				switch(opt) {
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
			}
			catch(MenuSelectException e) {
				System.out.println(e.getMessage());
			}
			catch(InputMismatchException e) {
				System.out.println("에러,정수를입력하세요");
			}
			
			
			
			}//end of while
		}//end of showMenu
		
		//계좌개설을 위한 함수
		void makeAccount() {
			Scanner sc2 = new Scanner(System.in);
			System.out.println("***신규계좌개설***");
			System.out.println("-----계좌선택-----");
			System.out.println("1.보통계좌");
			System.out.println("2.신용신뢰계좌");
			System.out.print("선택:");
			selection = sc2.nextInt();
			
			System.out.print("계좌번호:");
			String account = sc2.next();
			System.out.print("고객이름:");
			String name = sc2.next();
			System.out.print("잔고:");
			int balance = sc2.nextInt();
			System.out.print("기본이자%(정수형태로입력):");
			int interest = sc2.nextInt();
	
			if(selection==2) {
				System.out.print("신용등급(A,B,C등급):");
				credit = sc2.next();
			}
			switch(selection) {
				case 1:
				data[numOfaccount++] = 
						new NormalAccount(account, name, balance, interest);
				break;
				case 2:
				data[numOfaccount++] =
						new HighCreditAccount(account, name, balance, interest, credit);
			}
			System.out.println("계좌개설이 완료되었습니다.");
		}//end of makeAccount
		
		//입금
		void depositMoney() {
			try {
				System.out.println("***입  금***");
				System.out.println("계좌번호와 입금할 금액을 입력하세요 ");
				Scanner sc3 = new Scanner(System.in);
				System.out.print("계좌번호:");
				String inputNum = sc3.next();
				System.out.print("입금액:");
				int inputMoney = sc3.nextInt();
			
				try {		
					if(inputMoney<0) {
						Exception err = new Exception();
						throw err;
					}
				}
				catch(Exception e) {
					System.out.println("음수 입력불가능..!!");
					depositMoney();
				}
				
				try {		
					if(inputMoney%500!=0) {
						Exception err = new Exception();
						throw err;
					}
				}
				catch(Exception e) {
					System.out.println("500원 단위로만입력가능..!!");
					depositMoney();
				}
					
					
					
				
				switch(selection) {
				case 1:
					for(int i=0 ; i<data.length ; i++) {
						if(data[i]!=null && data[i].accountNumber.equals(inputNum)) {
								data[i].allMoney += (data[i].allMoney * (data[i].rate * 0.01))+inputMoney;
						}
					}
					break;
				case 2:
					for(int i=0 ; i<data.length ; i++) {
						if(data[i]!=null && data[i].accountNumber.equals(inputNum)) {
								data[i].allMoney += ((data[i].allMoney)*(data[i].rate * 0.01))
											+(data[i].allMoney * data[i].h_rate)+inputMoney;
						}
					}
				}
				System.out.println("입금이 완료되었습니다.");
				
			}
			catch(InputMismatchException e) {
				System.out.println("에러,정수를입력하세요");
				depositMoney();
			}
		}
		
		//출금
		void withdrawMoney() {
			int i = 0;
			System.out.println("***출  금***");
			System.out.println("계좌번호와 출금할 금액을 입력하세요 ");
			Scanner sc4 = new Scanner(System.in);
			System.out.print("계좌번호:");
			String actNum = sc4.next();
			System.out.print("출금액:");
			int outputMoney = sc4.nextInt();
			
			try {
				if(outputMoney<0) {
					Exception err = new Exception();
					throw err;
				}
			}
			catch(Exception e) {
				System.out.println("음수액 출금 불가능..!!");
				withdrawMoney();
			}

			try {
				if(outputMoney%1000!=0) {
					Exception err = new Exception();
					throw err;
				}
			}
			catch(Exception e) {
				System.out.println("1000원 단위로만 출금가능..!!");
				withdrawMoney();
			}
			
			try {
				for(i=0 ; i<data.length ; i++) {
					if(data[i].allMoney<outputMoney) {
						Exception err = new Exception();
						throw err;
					}
					if(data[i]!=null && data[i].accountNumber.equals(actNum)) 
							data[i].allMoney -= outputMoney;
						
				}
			}
			catch(Exception e) {
				System.out.println("잔고보다 많은 금액 출금 불가능..!!");
				System.out.println("잔고부족합니다.. 금액전체를 출금할까요?");
				System.out.println("   - YES:금액전체 출금처리");
				System.out.println("   - NO:출금요청취소");
				Scanner sc = new Scanner(System.in);
				String opt = sc.nextLine();
				switch(opt) {
				case "YES":
					data[i].allMoney -= data[i].allMoney;
					break;
				case "NO":
					showMenu();
				}
			}
			System.out.println("출금이 완료되었습니다.");
		}
		
		//전체계좌정보출력
		void showAccInfo() {
			System.out.println("***계좌정보출력***");
			for(int i=0 ; i<data.length ; i++) {
				if(data[i]!=null) {
					data[i].showAccInfo();
				}
			}
			System.out.println("전체계좌정보 출력이 완료되었습니다.");
		}
}
