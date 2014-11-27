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
	public void testAxiomEquality(){
		Endpoints e1 = new Endpoints("10", "11", "10-11");
		Endpoints e2 = new Endpoints("10", "11", "10-11");
		Type t1 = new Type("A", "10-11");
		Type t2 = new Type("A", "10-11");
		Type t3 = new Type("S", "10-11");
		
		Axiom a1 = new Axiom();
		a1.add(e1);
		a1.add(t1);
		
		Axiom a2 = new Axiom();
		a2.add(t2);
		a2.add(e2);
		
		Axiom a3 = new Axiom();
		a3.add(e1);
		a3.add(t3);
		
		System.out.println(a1.toString());
		System.out.println(a2.toString());
		System.out.println(a3.toString());
		
		assertEquals(false, a3.equals(a1));
		assertEquals(false, a2.equals(a3));
		assertEquals(true, a2.equals(a1));
		assertEquals(true, a1.equals(a2));
	}
	
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
