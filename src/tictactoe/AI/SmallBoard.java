package tictactoe.AI;

import java.util.ArrayList;

public class SmallBoard implements Board{
	private static final int X = 1;
	private static final int O = -1;
	
	private int[] spaces = new int[9];
	
	public SmallBoard()
	{
		for(int i=0; i<spaces.length; i++) {
			spaces[i] = 0;
		}
	}
	
	public boolean makeMove(int player, int space)
	{
		if(!getLegalMoves().contains(space)) {return false;}
		spaces[space] = player;
		return true;
	}
	
	public ArrayList<Integer> getLegalMoves()
	{
		ArrayList<Integer> moveList = new ArrayList<Integer>();
		if(checkWin() != 0) {return moveList;}
		for(int space = 0; space<9; space++){
			if(spaces[space] ==  0) {moveList.add(space);}
		}
		return moveList;
	}
	
	//Small Text Board
	public void printBoard()
	{
		for(int i=0; i<3; i++)
		{
			for(int y=0; y<3; y++)
			{
				int spaceVal = spaces[i*3+y];
				
				switch(spaceVal) {
				case 0: 
					System.out.print(" ");
					break;
				case -1:
					System.out.print("O");
					break;
				case 1:
					System.out.print("X");
					break;
				}
				
				if(y!=2) {System.out.print(" | ");}
					
			}
			System.out.println();
			if(i<2) {System.out.println("---------");}
		}
	}

	public void printRow(int row)
	{
		for(int y=0; y<3; y++)
		{
			int spaceVal = spaces[row*3+y];
			
			switch(spaceVal) {
			case 0: 
				System.out.print(" ");
				break;
			case -1:
				System.out.print("O");
				break;
			case 1:
				System.out.print("X");
				break;
			}
			
			if(y!=2) {System.out.print(" | ");}
				
		}
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
			sum += spaces[row*3+i];
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
			sum += spaces[i*3+col];
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
			sum1 += spaces[i*3+i];
			sum2 += spaces[(i+1)*3-i-1];
		}
		
		if(sum1 == 3 || sum2 == 3) {return 1;}
		else if(sum1 == -3 || sum2 == -3) {return -1;}
		return 0;
	}
}
