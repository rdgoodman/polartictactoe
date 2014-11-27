package polartictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

public class WinCheckerTest {
	
	WinChecker testwc1 = new WinChecker();

	@Test
	public void testNegatedGoalCreation() {
		System.out.println("Negated goal: " );
		System.out.println(testwc1.getNegatedGoal().toString() + "\n");
	}
	
//	@Test
//	public void testKBBuilding(){
//		Player player1 = new HumanPlayer(1);
//		Player player2 = new HumanPlayer(2);
//		Game game = new Game(5, 5, player1, player2);
//		
//		Node[][] gameNodes = game.getGameState();
//		
//		game.move(gameNodes[0][0], player1);
//		game.move(gameNodes[0][1], player2);
//		game.move(gameNodes[1][1], player1);
//		game.move(gameNodes[1][2], player2);
//		game.move(gameNodes[1][3], player1);
//		game.move(gameNodes[2][3], player2);
//
//		game.getWinChecker().printp1KB();
//		game.getWinChecker().printp2KB();
//	}

	@Test
	public void testResolutionWithoutWin(){
		Player player1 = new HumanPlayer(1);
		Player player2 = new HumanPlayer(2);
		Game game = new Game(5, 5, player1, player2);
		
		Node[][] gameNodes = game.getGameState();
		
		game.move(gameNodes[0][0], player1);
		game.move(gameNodes[0][1], player2);
		game.move(gameNodes[1][1], player1);
		game.move(gameNodes[1][2], player2);
		game.move(gameNodes[1][3], player1);
		game.move(gameNodes[2][3], player2);
		game.move(gameNodes[1][0], player1);
		
		
	}
}
