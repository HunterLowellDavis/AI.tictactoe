package tictactoe.AI;

import java.util.ArrayList;

public class BigBoard implements Board{
	private static final int X = 1;
	private static final int O = -1;
	
	private Board[] spaces = new Board[9];
	private int currentBoard = -1;
	private int lastTurn = -1;
	
	public BigBoard()
	{
		for(int i=0; i<spaces.length; i++)
		{
			spaces[i] = new SmallBoard();
		}
	}
	
	public BigBoard(Board[] spaces, int currentBoard)
	{
		this.spaces = spaces;
		this.currentBoard = currentBoard;
	}
	
	public void setTurn(int lastTurn) 
	{
		this.lastTurn = lastTurn;
	}
	
	public boolean makeMove(int player, int move) {
		if(getLegalMoves().contains(move)) {
			int board = move/9;
			int space = move%9;
			spaces[board].makeMove(player, space);
			updateCurrentBoard(move);
			lastTurn = player;
			return true;
		}
		return false;
	}
	
	public boolean makeMove(int move)
	{
		int player = -lastTurn;
		if(getLegalMoves().contains(move)) {
			int board = move/9;
			int space = move%9;
			spaces[board].makeMove(player, space);
			updateCurrentBoard(move);
			lastTurn = player;
			return true;
		}
		return false;
	}

	
	public void printBoard() {
		for(int i = 0; i<9; i++)
		{
			int boardRow = i/3;
			for(int j=0; j<3; j++)
			{
				spaces[boardRow*3+j].printRow(i%3);
				if(j!=2) {System.out.print("  ||  ");}
			}
			System.out.println();
			if(i%3 != 2) {System.out.println("---------"+"      "+"---------"+"      "+"---------");}
			else {
				System.out.println("______________________________________");
				System.out.println("______________________________________");
				System.out.println();
			}
		}
	}
	
	public void printRow(int row) {}

	public ArrayList<Integer> getLegalMoves() {
		ArrayList<Integer> moveList = new ArrayList<Integer>();
		if(currentBoard == -1)
		{
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
			for(Integer x : board.getLegalMoves()) {
				moveList.add(x+(currentBoard*9));
			}
		}
		
		
		return moveList;
	}

	public boolean checkDone()
	{
		if(checkWin() != 0 || getLegalMoves().size() == 0) {return true;}
		return false;
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
		if(spaces[newBoard].checkDone()) {newBoard = -1;}
		currentBoard = newBoard;
	}
	
	public BigBoard getCopy()
	{
		Board[] newSpaces = new Board[spaces.length];
		for(int i=0; i<spaces.length; i++)
		{
			newSpaces[i] = spaces[i].getCopy();
		}
		BigBoard newBoard = new BigBoard(newSpaces, currentBoard);
		newBoard.setTurn(lastTurn);
		return newBoard;
	}
	
}
