package project2;

import java.util.Random;
import java.util.Scanner;

public class game3by3 {

	private static int[][] puzzle = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
	static int row = 2;
	static int col = 2;

	static void shuffle(int count) {
		int opt;
		while (count > 0) {
			Random r = new Random();
			opt = r.nextInt(4);
			try {
				switch (opt) {
				case 0:
					puzzle[col][row] = puzzle[col - 1][row];
					puzzle[col - 1][row] = 0;
					col--;
					break;
				case 1:
					puzzle[col][row] = puzzle[col + 1][row];
					puzzle[col + 1][row] = 0;
					col++;
					break;
				case 2:
					puzzle[col][row] = puzzle[col][row + 1];
					puzzle[col][row + 1] = 0;
					row++;
					break;
				case 3:
					puzzle[col][row] = puzzle[col][row - 1];
					puzzle[col][row - 1] = 0;
					row--;
					break;
				}// end of switch
			} catch (Exception e) {
				continue;
			}
			count--;
		}
	}
//
	static void print_puzzle(int puzzle[][]) {
		int r, c;
		for (c = 0; c < 3; c++)// 열 반복
		{
			for (r = 0; r < 3; r++)// 행 반복
			{
				if (puzzle[c][r] > 0)// 참이면(0이 아니면)
				{
					if ((c == 0 && r == 0) || (c == 1 && r == 0) || (c == 2 && r == 0)) {
						System.out.printf("%d", puzzle[c][r]);
					} else {
						System.out.printf("%3d", puzzle[c][r]);// 퍼즐의 수를 출력
					}
				} else// 거짓(0)이면
				{
					if ((c == 0 && r == 0) || (c == 1 && r == 0) || (c == 2 && r == 0)) {
						System.out.printf("x");// 공백 미출력
					} else
						System.out.printf("  x");// 공백 출력
				}

			}
			System.out.printf("\n");// 개행
		}
	}

	static boolean answer(int puzzle[][]) {
		int[][] answerpuzzle = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		for (int i = 0; i < answerpuzzle.length; i++) {
			for (int j = 0; j < answerpuzzle.length; j++) {
				if (answerpuzzle[i][j] == puzzle[i][j]) {
				} else if (answerpuzzle[i][j] != puzzle[i][j])
					return true;
			}
		}
		return false;
	}

	public static void gamestart() {
		Scanner sh = new Scanner(System.in);
		System.out.print("셔플횟수입력:");
		int shuffle_num = sh.nextInt();
		shuffle(shuffle_num);
		boolean res = true;
		while (res) {
			System.out.println("=======");
			print_puzzle(puzzle);
			System.out.println("=======");
			System.out.println("[ 이동 ] a:Left d:Right w:Up s:Down");
			System.out.println("[ 종료 ] x:Exit");
			System.out.printf(">> 방향키 선택해주세요 :");
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			try {
				switch (input.toUpperCase()) {
				//위쪽이동
				case "S":
					try {
						puzzle[col][row] = puzzle[col - 1][row];
						puzzle[col - 1][row] = 0;
						col--;
					} catch (Exception e) {
						System.out.println("xxxxxxxxxxxxxxx");
						System.out.println("xxxxx이동불가xxxxx");
						System.out.println("xxxxxxxxxxxxxxx");
						continue;
					}
					break;
				//아래쪽이동
				case "W":
					try {
						puzzle[col][row] = puzzle[col + 1][row];
						puzzle[col + 1][row] = 0;
						col++;
					} catch (Exception e) {
						System.out.println("xxxxxxxxxxxxxxx");
						System.out.println("xxxxx이동불가xxxxx");
						System.out.println("xxxxxxxxxxxxxxx");
						continue;
					}
					break;
				//오른쪽이동
				case "A":
					try {
						puzzle[col][row] = puzzle[col][row + 1];
						puzzle[col][row + 1] = 0;
						row++;
					} catch (Exception e) {
						System.out.println("xxxxxxxxxxxxxxx");
						System.out.println("xxxxx이동불가xxxxx");
						System.out.println("xxxxxxxxxxxxxxx");
						continue;
					}
					break;
				//왼쪽이동
				case "D":
					try {
						puzzle[col][row] = puzzle[col][row - 1];
						puzzle[col][row - 1] = 0;
						row--;
					} catch (Exception e) {
						System.out.println("xxxxxxxxxxxxxxx");
						System.out.println("xxxxx이동불가xxxxx");
						System.out.println("xxxxxxxxxxxxxxx");
						continue;
					}
					break;
				//종료
				case "X":
					return;
				}// end of try
				res = answer(puzzle);
				if (res == false) {
					System.out.println("==^^정답입니다^^==");
					print_puzzle(puzzle);
					System.out.println("재시작하시겠습니까?(y:재시작,나머지:종료)");
					Scanner sc2 = new Scanner(System.in);
					String sc3 = sc2.next();
					if (sc3.equals("y")) {
						gamestart();
					} else {
					}
				} // end of if
			} catch (Exception e) {
				continue;
			}
		} // end of while
	}

	public static void main(String[] args) {
		gamestart();
	}// end of while
}
