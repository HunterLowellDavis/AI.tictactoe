package tictactoe.AI;

import java.util.ArrayList;

public class MonteCarloEvaluator {
	private Board boardMain;
	private int maxPlayouts;
	private int plyDepth;
	private int bestMove;
	
	public MonteCarloEvaluator(Board boardMain, int maxPlayouts, int plyDepth)
	{
		this.boardMain = boardMain;
		this.maxPlayouts = maxPlayouts;
		this.plyDepth = plyDepth;
	}
	
	public int getBestMove()
	{
		return bestMove;
	}
	
	public void makeMove()
	{
		boardMain.makeMove(chooseBestMove(boardMain.getCopy(), maxPlayouts, plyDepth));
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
		int playoutPerBranch = playouts/moveList.size();
		
		Board[] possibleStates = new Board[moveList.size()];
		for(int move : moveList)
		{
			Board newState = board.getCopy();
			newState.makeMove(move);
			possibleStates[moveList.indexOf(move)] = newState;
		}
		
		
		
		double bestEval = -2;
		for(int i=0; i < possibleStates.length; i++) {
			Board evalBoard = possibleStates[i];
			//evalBoard.printBoard();
			double eval = evalMove(evalBoard, playoutPerBranch, depth-1);
			//System.out.println(eval);
			if((eval)>bestEval) {
				bestEval = eval;
			}
			//System.out.println("worst eval "+bestEval);
		}
		return bestEval;
	}
	
	
}
