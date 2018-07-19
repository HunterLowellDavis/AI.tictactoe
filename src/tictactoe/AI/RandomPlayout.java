package tictactoe.AI;

public class RandomPlayout {
	private Board board;
	private double eval;
	private int playNumber;
	private RandomPlayer aiX, aiO;
	private int turn;
	
	public RandomPlayout(Board board, int playNumber, int turn)
	{
		this.board = board;
		this.playNumber = playNumber;
		aiX = new RandomPlayer();
		evaluate();
	}
	
	public RandomPlayout(Board board, int playNumber)
	{
		this.board = board;
		this.playNumber = playNumber;
		aiX = new RandomPlayer();
		evaluate();
	}
	
	public RandomPlayout(int playNumber)
	{
		this.board = new BigBoard();
		this.playNumber = playNumber;
		aiX = new RandomPlayer();
		evaluate();
	}
	
	private void evaluate()
	{
		int score = 0;
		for(int i=0; i<playNumber; i++)
		{
			Board testBoard = board.getCopy();
			aiX.setBoard(testBoard);
			while(!testBoard.checkDone())
			{
				aiX.makeMove();
			}
			score += testBoard.checkWin();
		}
		eval = (double)score/(double)playNumber;
	}
	
	public double getEvaluation()
	{
		return eval;
	}
}
