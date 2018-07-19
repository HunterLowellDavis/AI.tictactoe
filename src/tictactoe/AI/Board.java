package tictactoe.AI;

import java.util.ArrayList;

public interface Board {
	public boolean makeMove(int player, int move);
	public void printBoard();
	public int checkWin();
	public ArrayList<Integer> getLegalMoves();
}
