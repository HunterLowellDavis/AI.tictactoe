package tictactoe.AI;

import java.util.Scanner;

public class RandomPlayerTest {
	
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		System.out.println("Hit start");
		scan.next();
		RandomPlayout rand = new RandomPlayout(100000);
		System.out.println(rand.getEvaluation());
	}
}
