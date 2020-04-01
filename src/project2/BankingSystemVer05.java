package project2;

import java.sql.SQLException;
import java.util.Scanner;

import project2.ver05.IConnectImpl;
import project2.ver05.MenuChoice;

public class BankingSystemVer05 extends IConnectImpl implements MenuChoice {

	public BankingSystemVer05() {
		super("study", "1234");
		makeTable();
		makeSequence();
	}

	// 메뉴출력
	public void showMenu() {
		while (true) {
			System.out.println("-----Menu-----");
			System.out.println("1.계좌개설");
			System.out.println("2.입   금");
			System.out.println("3.출   금");
			System.out.println("4.계좌정보출력");
			System.out.println("5.--3X3게임-- ");
			System.out.println("6.프로그램종료");

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
			case GAME:
				game3by3.gamestart();
				break;
			case EXIT:
				System.out.println("프로그램 종료..!!");
				close();
				return;
			}
		} // end of while
	}// end of showMenu

	public void makeTable() {
		try {
			System.out.println("테이블 생성");
			String query = "create table banking_tb( " + " account varchar2(100) primary key, "
					+ " name varchar2(100), " + " balance int" + ")";

			stmt = con.prepareStatement(query);
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("쿼리오류발생");
			e.printStackTrace();
		}
		System.out.println("테이블 생성완료");
	}

	public void makeSequence() {
		try {
			System.out.println("시퀀스 생성");
			String query = "create sequence seq_banking " + " increment by 1 " + " start with 1 " + " nomaxvalue "
					+ " minvalue 1 " + " nocycle " + " nocache ";

			stmt = con.prepareStatement(query);
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("쿼리오류발생");
			e.printStackTrace();
		}
		System.out.println("시퀀스 생성완료");
	}

	// 계좌개설을 위한 함수
	void makeAccount() {
		try {
			System.out.println("***신규계좌개설***");
			// 1.쿼리문준비 : 값의 세팅이 필요한 부분을 ?로 대체한다.
			String query = "INSERT INTO banking_tb VALUES (?, ?, ?)";
			// 2.prepared객체 생성 : 생성시 준비한 쿼리문을 인자로 전달한다.
			psmt = con.prepareStatement(query);

			Scanner sc = new Scanner(System.in);
			System.out.print("계좌번호:");
			String account = sc.nextLine();
			System.out.print("고객이름:");
			String name = sc.nextLine();
			System.out.print("잔고:");
			int balance = sc.nextInt();

			// 4.인파라미터 설정하기 : ?의 순서대로 설정하고 DB이므로 인덱스는 1부터 시작.
			psmt.setString(1, account);
			psmt.setString(2, name);

			psmt.setInt(3, balance);

			int affected = psmt.executeUpdate();
			System.out.println(affected + "행이 입력되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end of makeAccount

	// 입금
	void depositMoney() {
		String sql = "UPDATE banking_tb " + "SET balance=balance+? " + "WHERE account=?";

		try {
			System.out.println("계좌번호와 입금할 금액을 입력하세요 ");
			psmt = con.prepareStatement(sql);

			Scanner sc3 = new Scanner(System.in);
			System.out.print("계좌번호:");
			String inputNum = sc3.nextLine();
			System.out.print("입금액:");
			int inputMoney = sc3.nextInt();
			// 인파라미터 설정시 해당 인덱스만 맞으면 순서는 상관없다.
			psmt.setInt(1, inputMoney);
			psmt.setString(2, inputNum);

			int affected = psmt.executeUpdate();
			System.out.println(affected + "행이 업데이트 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("입금이 완료되었습니다.");
	}

	// 출금
	void withdrawMoney() {
		System.out.println("***출  금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요 ");
		String sql = "UPDATE banking_tb " + "SET balance=balance-? " + "WHERE account=?";
		try {
			psmt = con.prepareStatement(sql);
			Scanner sc4 = new Scanner(System.in);
			System.out.print("계좌번호:");
			String inputNum = sc4.nextLine();
			System.out.print("출금액:");
			int outputMoney = sc4.nextInt();
			// 인파라미터 설정시 해당 인덱스만 맞으면 순서는 상관없다.
			psmt.setInt(1, outputMoney);
			psmt.setString(2, inputNum);

			int affected = psmt.executeUpdate();
			System.out.println(affected + "행이 업데이트 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("출금이 완료되었습니다.");
	}

	// 전체계좌정보출력
	void showAccInfo() {
		System.out.println("***계좌정보출력***");
		System.out.println("-------");
		System.out.println("account     name     balance");
		try {
			String query = "SELECT account,name,balance FROM banking_tb ";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String account = rs.getString("account");
				String name = rs.getString("name");
				String balance = rs.getString("balance");
				System.out.printf("%-10s %-10s %-13s \n", account, name, balance);
				System.out.println("계좌목록 출력이 완료되었습니다.");
			}
		} catch (SQLException e) {
			System.out.println("쿼리오류발생");
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (con != null)
				con.close();
			if (psmt != null)
				psmt.close();
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			System.out.println("자원 반납 완료");
		} catch (Exception e) {
			System.out.println("자원 반납시 오류 발생");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BankingSystemVer05 act = new BankingSystemVer05();
		act.showMenu();
	}
}