package tictactoe.AI;

import java.util.ArrayList;

public interface Board {
	public boolean makeMove(int player, int move);
	public void printBoard();
	public int checkWin();
	public ArrayList<Integer> getLegalMoves();
	public void printRow(int row);
	public Board getCopy();
	public boolean checkDone();
	public boolean makeMove(int move);
}
