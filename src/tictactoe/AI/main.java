package tictactoe.AI;

import java.util.Scanner;

public class main {
	private static Scanner scan = new Scanner(System.in);
	
	
	public static void main(String[] args)
	{
		Board board = new BigBoard();
		MonteCarloEvaluator monte = new MonteCarloEvaluator(board, 1000000, 2);
		boolean alive = true;
		while(!board.checkDone())
		{
			System.out.println(board.getLegalMoves());
			System.out.println("Input board number");
			int boardNum = scan.nextInt();
			System.out.println("Enter space number");
			board.makeMove(scan.nextInt()+(boardNum*9));
			board.printBoard();
			
			monte.makeMove();
			board.printBoard();
		}
		System.out.println("Thanks for playing!");
	}
}
