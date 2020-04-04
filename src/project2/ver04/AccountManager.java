package project2.ver04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import project2.ver04.Account;
import project2.ver04.MenuChoice;
import project2.ver04.MenuSelectException;

public class AccountManager implements MenuChoice {
	HashSet<Account> set = new HashSet<Account>();
	int numOfaccount;
	int selection;
	String credit;

	public AccountManager() {
		numOfaccount = 0;
	}

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

			try {
				Scanner sc = new Scanner(System.in);
				int opt = sc.nextInt();

				if (!(opt >= 1 && opt <= 5)) {
					MenuSelectException err = new MenuSelectException();
					throw err;
				}

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
			} catch (MenuSelectException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("에러,정수를입력하세요");
			}

		} // end of while
	}// end of showMenu

	// 계좌개설을 위한 함수
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

		if (selection == 2) {
			System.out.print("신용등급(A,B,C등급):");
			credit = sc2.next();
		}

		Account ac = null;
		Account ac2 = null;

		switch (selection) {
		case 1:
			ac = new NormalAccount(account, name, balance, interest);
			break;
		case 2:
			ac2 = new HighCreditAccount(account, name, balance, interest, credit);
		}

		int opt = 1;
		Iterator<Account> itr = set.iterator();
		while (itr.hasNext()) {
			Account data = (Account) itr.next();
			if (data.accountNumber.equals(account)) {
				System.out.println("동일한계좌입력됨,덮어쓰기하시겠습니까?(예:0,아니오:1)");
				opt = sc2.nextInt();
				if (opt == 0) {
					set.remove(data);
					if (selection == 1)
						set.add(ac);
					else if (selection == 2)
						set.add(ac2);
				} else if (opt == 1) {
					showMenu();
				}
			}
		}

		switch (selection) {
		case 1:
			set.add(ac);
			break;
		case 2:
			set.add(ac2);
		}
		System.out.println("계좌개설이 완료되었습니다.");
	}// end of makeAccount

	// 입금
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
				if (inputMoney < 0) {
					Exception err = new Exception();
					throw err;
				}
			} catch (Exception e) {
				System.out.println("음수 입력불가능..!!");
				depositMoney();
			}

			try {
				if (inputMoney % 500 != 0) {
					Exception err = new Exception();
					throw err;
				}
			} catch (Exception e) {
				System.out.println("500원 단위로만입력가능..!!");
				depositMoney();
			}

			switch (selection) {
			case 1:
				Iterator<Account> itr = set.iterator();
				while (itr.hasNext()) {
					Account data = (Account) itr.next();
					if (inputNum.equals(data.accountNumber)) {
						data.allMoney += (data.allMoney * (data.rate * 0.01)) + inputMoney;
					}
				}
				break;
			case 2:
				Iterator<Account> itr2 = set.iterator();
				while (itr2.hasNext()) {
					Account data2 = (Account) itr2.next();
					if (inputNum.equals(data2.accountNumber)) {
						data2.allMoney += (data2.allMoney * (data2.rate * 0.01)) + (data2.allMoney * (data2.h_rate))
								+ inputMoney;
					}
				}
				System.out.println("입금이 완료되었습니다.");
			}
		} catch (InputMismatchException e) {
			System.out.println("에러,정수를입력하세요");
			depositMoney();
		}
	}

	// 출금
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
			if (outputMoney < 0) {
				Exception err = new Exception();
				throw err;
			}
		} catch (Exception e) {
			System.out.println("음수액 출금 불가능..!!");
			withdrawMoney();
		}

		try {
			if (outputMoney % 1000 != 0) {
				Exception err = new Exception();
				throw err;
			}
		} catch (Exception e) {
			System.out.println("1000원 단위로만 출금가능..!!");
			withdrawMoney();
		}

		Account data3 = null;
		try {
			Iterator<Account> itr3 = set.iterator();
			while (itr3.hasNext()) {
				data3 = (Account) itr3.next();
				if (data3 != null && data3.accountNumber.equals(actNum))
					data3.allMoney -= outputMoney;
				if (data3.allMoney < outputMoney) {
					Exception err = new Exception();
					throw err;
				}
			} // end of while
		} catch (Exception e) {
			System.out.println("잔고보다 많은 금액 출금 불가능..!!");
			System.out.println("잔고부족합니다.. 금액전체를 출금할까요?");
			System.out.println("   - YES:금액전체 출금처리");
			System.out.println("   - NO:출금요청취소");
			Scanner sc = new Scanner(System.in);
			String opt = sc.nextLine();
			switch (opt) {
			case "YES":
				data3.allMoney -= data3.allMoney;
				break;
			case "NO":
				showMenu();
			}
		}
		System.out.println("출금이 완료되었습니다.");
	}

	// 전체계좌정보출력
	void showAccInfo() {
		System.out.println("***계좌정보출력***");
		Iterator<Account> itr = set.iterator();
		while (itr.hasNext()) {
			Account object = (Account) itr.next();
			object.showAccInfo();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}

	public void SaveData() {
		try {
			// 인스턴스를 파일에 저장하기 위한 스트림을 생성한다.
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/project2/ver04/Account.obj"));

			// 인스턴스를 파일에 저장한다.
			out.writeObject(set);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("파일없음");
		} catch (IOException e) {
			System.out.println("뭔가없음");
			e.printStackTrace();
		}
	}//

	public void PullData() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/project2/ver04/Account.obj"));

			HashSet<Account> data = (HashSet<Account>) in.readObject();
			Iterator<Account> object = data.iterator();
			while (object.hasNext()) {
				object.next().showAccInfo();
			}
			in.close();
		} catch (Exception e) {
			System.out.println("파일없음");
		}
	}// end of PullData
}