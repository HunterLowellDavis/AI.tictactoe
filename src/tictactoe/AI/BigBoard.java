package tictactoe.AI;

import java.util.ArrayList;

public class BigBoard implements Board{
	private static final int X = 1;
	private static final int O = -1;
	
	private Board[] spaces = new Board[9];
	private int currentBoard = -1;
	
	public BigBoard()
	{
		for(int i=0; i<spaces.length; i++)
		{
			spaces[i] = new SmallBoard();
		}
	}
	
	public boolean makeMove(int player, int move) {
		if(getLegalMoves().contains(move)) {
			int board = move/9;
			int space = move%9;
			spaces[board].makeMove(player, space);
			updateCurrentBoard(move);
			return true;
		}
		return false;
	}

	
	//#TODO
	public void printBoard() {
		
	}

	public ArrayList<Integer> getLegalMoves() {
		ArrayList<Integer> moveList = new ArrayList<Integer>();
		if(currentBoard == -1)
		{
			System.out.println();
			for(int i=0; i<spaces.length; i++)
			{
				Board board = spaces[i];
				ArrayList<Integer> smallList = board.getLegalMoves();
			
				for(Integer x : smallList)
				{
					moveList.add(x+(i*9));
				}
			}
		}else {
			Board board = spaces[currentBoard];
			moveList = board.getLegalMoves();
		}
		
		
		return moveList;
	}

	public int checkWin()
	{
		for(int i=0; i<3; i++) {
			if(checkRow(i) != 0) {return checkRow(i);}
			if(checkCol(i) != 0) {return checkCol(i);}
		}
		return checkDia();
	}
	
	private int checkRow(int row)
	{
		int sum = 0;
		for(int i=0; i<3; i++)
		{
			sum += spaces[row*3+i].checkWin();
		}
		if(sum == 3) {return 1;}
		else if(sum == -3) {return -1;}
		return 0;
	}
	
	private int checkCol(int col)
	{
		int sum = 0;
		for(int i=0; i<3; i++)
		{
			sum += spaces[i*3+col].checkWin();
		}
		
		if(sum == 3) {return 1;}
		else if(sum == -3) {return -1;}
		return 0;
	}
	
	private int checkDia()
	{
		int sum1 = 0, sum2 = 0;
		for(int i=0; i<3; i++)
		{
			sum1 += spaces[i*3+i].checkWin();
			sum2 += spaces[(i+1)*3-i-1].checkWin();
		}
		
		if(sum1 == 3 || sum2 == 3) {return 1;}
		else if(sum1 == -3 || sum2 == -3) {return -1;}
		return 0;
	}

	private void updateCurrentBoard(int space)
	{
		int newBoard = space%9;
		if(spaces[newBoard].checkWin() != 0) {newBoard = -1;}
		currentBoard = newBoard;
	}
}
