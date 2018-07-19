package tictactoe.AI;

import java.util.Scanner;

public class BigBoardTest {
	
	private static Scanner scan = new Scanner(System.in);
	private static int mover = 1;
	
	public static void main(String[] args)
	{
		BigBoard board = new BigBoard();
		boolean alive = true;
		while(alive)
		{
			System.out.println(board.getLegalMoves());
			System.out.println("Make a move");
			board.makeMove(mover, scan.nextInt());
			mover *= -1;
			board.printBoard();
			if(board.checkWin() != 0) {alive = false;}
		}
		System.out.println("Thanks for playing!");
	}
}
