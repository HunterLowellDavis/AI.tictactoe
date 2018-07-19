package tictactoe.AI;
import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer {
	private Board board;
	private int player;
	private Random rand = new Random();
	private int lastMove;
	
	public RandomPlayer()
	{
		this.lastMove = -1;
	}
	
	public RandomPlayer(Board board, int player)
	{
		this.board = board;
		this.lastMove = -1;
		this.player = player;
	}
	
	public void makeMove()
	{
		ArrayList<Integer> moveList = board.getLegalMoves();
		int choice = rand.nextInt(moveList.size());
		board.makeMove(moveList.get(choice));
		lastMove = moveList.get(choice);
	}
	
	public void setBoard(Board board)
	{
		this.board = board;
	}
	
	public void printLastMove()
	{
		System.out.println("Last move was on board "+lastMove/9+" in space "+lastMove%9);
	}
	
	
}
