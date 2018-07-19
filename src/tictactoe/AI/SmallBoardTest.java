package tictactoe.AI;

import java.util.Scanner;

public class SmallBoardTest {
	
	private static Scanner scan = new Scanner(System.in);
	private static SmallBoard board = new SmallBoard();
	private static int mover = 1;
	
	public static void main(String[] args)
	{
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
