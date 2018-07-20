package tictactoe.AI;

import java.util.ArrayList;

public class MonteCarloEvaluator {
	private Board boardMain;
	private int maxPlayouts;
	private int plyDepth;
	private int bestMove;
	private int moveCount;
	
	public MonteCarloEvaluator(Board boardMain, int maxPlayouts, int plyDepth)
	{
		this.boardMain = boardMain;
		this.maxPlayouts = maxPlayouts;
		this.plyDepth = plyDepth;
		moveCount = 0;
	}
	
	public int getBestMove()
	{
		return bestMove;
	}
	
	public void makeMove()
	{
		int plays = maxPlayouts;
		int plys = plyDepth;
		
		if(moveCount > 8)
		{
			plays = 2000000;
			plyDepth = 4;
		}
		
		if(moveCount > 22) {
			plays = 4000000;
			plys = 6;
		}
		
		boardMain.makeMove(chooseBestMove(boardMain.getCopy(), plays, plys));
		moveCount++;
	}
	
	private int chooseBestMove(Board board, int playouts, int depth)
	{
		int best = -1;
		ArrayList<Integer> moveList = board.getLegalMoves();
		int playoutPerBranch = playouts/moveList.size();
		
		Board[] possibleStates = new Board[moveList.size()];
		for(int move : moveList)
		{
			Board newState = board.getCopy();
			newState.makeMove(move);
			possibleStates[moveList.indexOf(move)] = newState;
		}
		
		double bestEval = 2;
		int mult;
		if(depth%2 == 0) {mult = -1;}
		else {mult = 1;}
		for(int i=0; i < possibleStates.length; i++) {
			Board evalBoard = possibleStates[i];
			evalBoard.printBoard();
			double eval = evalMove(evalBoard, playoutPerBranch, depth-1);
			System.out.println(eval);
			if(eval<bestEval) {
				bestEval = eval;
				best = moveList.get(i);
				System.out.println("best eval: "+bestEval);
			}
		}
		System.out.println("Best move is board "+best/9+" space "+best%9);
		return best;
	}
	
	private double evalMove(Board board, int playouts, int depth)
	{
		int mult;
		if(depth%2 == 0) {mult = -1;}
		else {mult = 1;}
		
		if(depth == 0)
		{
			RandomPlayout playout = new RandomPlayout(board, playouts);
			return playout.getEvaluation();
		}
		
		
		ArrayList<Integer> moveList = board.getLegalMoves();
		if(board.checkDone()){return (double)board.checkWin();}
		int playoutPerBranch = playouts/moveList.size();
		
		Board[] possibleStates = new Board[moveList.size()];
		for(int move : moveList)
		{
			Board newState = board.getCopy();
			newState.makeMove(move);
			possibleStates[moveList.indexOf(move)] = newState;
		}
		
		
		
		double bestEval = -2;
		double worstEval = 2;
		for(int i=0; i < possibleStates.length; i++) {
			Board evalBoard = possibleStates[i];
			//evalBoard.printBoard();
			double eval = evalMove(evalBoard, playoutPerBranch, depth-1);
			//System.out.println(eval);
			if(depth%2 == 1) {
				if(eval>bestEval) {
					bestEval = eval;
				}
			}else if(depth%2 == 0) {
				if(eval<worstEval)
				{
					worstEval = eval;
				}
			}
			//System.out.println("worst eval "+bestEval);
		}
		if(depth%2 == 1) {return bestEval;}
		else{return worstEval;}
	}
	
	
}
