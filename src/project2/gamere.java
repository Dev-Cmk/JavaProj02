package project2;

import java.util.Scanner;

public class gamere {

	static int row=0;
	static int col=0;
	
	static int[][] puzzle = {{0,1,2},{3,4,5},{6,7,8}}; 
	

	static void print_puzzle(int puzzle[][])
	{
		int r, c;
		for (r = 0; r<3; r++)//열 반복
		{
			for (c = 0; c<3; c++)//행 반복
			{
				if (puzzle[r][c]>0)//참이면(0이 아니면)
				{
					System.out.printf("%3d", puzzle[r][c]);//퍼즐의 수를 출력
				}
				else//거짓(0)이면
				{
					System.out.printf("  x");//공백 출력
				}

			}
			System.out.printf("\n");//개행
		}
	}

	static boolean is_ending(int puzzle[][])
	{
		int r, c;

		for (r = 0; r<3; r++)//열 반복
		{
			for (c = 0; c<3; c++)//행 반복
			{
				if (puzzle[r][c] != r * 3 + c + 1)
				{
					return (r == 2) && (c == 2);
				}
			}
		}
		return false;
	}

	public static void shuffle(int[] array, int count) {
		
		
		
	}
	public static void main(String[] args)
	{

		while (!is_ending(puzzle))
		{
			print_puzzle(puzzle);
			System.out.printf(">> 방향키 선택 \n");
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();

			switch (input)
			{
			case "w":
				if (col>0)
				{
					puzzle[row][col] = puzzle[row][col - 1];
					puzzle[row][col - 1] = 0;
					col--;
				}

				break;
			case "s":
				if (col<3 - 1)
				{
					puzzle[row][col] = puzzle[row][col + 1];
					puzzle[row][col + 1] = 0;
					col++;
				}
				break;
			case "a":
				if (row<3 - 1)
				{
					puzzle[row][col] = puzzle[row + 1][col];
					puzzle[row + 1][col] = 0;
					row++;
				}
				break;
			case "d":
				if (row>0)
				{
					puzzle[row][col] = puzzle[row - 1][col];
					puzzle[row - 1][col] = 0;
					row--;
				}
				break;
			case "x":
				return;
			}
		}
	}
}

