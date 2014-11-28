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

		
		assertEquals(false, a3.equals(a1));
		assertEquals(false, a2.equals(a3));
		assertEquals(true, a2.equals(a1));
		assertEquals(true, a1.equals(a2));
	}
	
	@Test
	public void testLogicalFunctionNegationEquality(){
		Endpoints e1 = new Endpoints("10", "11", "10-11");
		Endpoints e2 = new Endpoints("10", "11", "10-11");
		Type t1 = new Type("A", "10-11");
		Type t2 = new Type("A", "10-11");
		Type t3 = new Type("S", "10-11");
		
		assertEquals(false, e1.equalsNegated(e2));
		e2.negate();
		assertEquals(true, e1.equalsNegated(e2));
		assertEquals(true, e2.equalsNegated(e1));
		
	}
	
	@Test
	public void testOccursCheck(){
		Endpoints e1 = new Endpoints("10", "11", "10-11");
		Endpoints e2 = new Endpoints("10", "11", "10-11");
		Endpoints e3 = new Endpoints("23", "13", "23-13");
		Endpoints e4 = new Endpoints("11", "12", "11-12");
		Type t1 = new Type("A", "10-11");
		Type t3 = new Type("S", "10-11");
		
		assertEquals(true, e1.passesOccursCheck(e4.getEndNodeName()));
		assertEquals(false, e2.passesOccursCheck(t3.getEdgeName()));
		assertEquals(false, e1.passesOccursCheck(e4.getStartNodeName()));
		assertEquals(true, t1.passesOccursCheck(e3.getEdgeName()));
	}
	
//	@Test
//	public void testResolutionWithoutWin(){
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
//		game.move(gameNodes[1][0], player1);
//		
//		
//	}
}
