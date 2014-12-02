package polartictactoe;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class WinCheckerTest {

	WinChecker testwc1 = new WinChecker();

	// @Test
	// public void testNegatedGoalCreation() {
	// System.out.println("Negated goal: " );
	// System.out.println(testwc1.getNegatedGoal().toString() + "\n");
	// }

	// @Test
	// public void testKBBuilding(){
	// Player player1 = new HumanPlayer(1);
	// Player player2 = new HumanPlayer(2);
	// Game game = new Game(5, 5, player1, player2);
	//
	// Node[][] gameNodes = game.getGameState();
	//
	// game.move(gameNodes[0][0], player1);
	// game.move(gameNodes[0][1], player2);
	// game.move(gameNodes[1][1], player1);
	// game.move(gameNodes[1][2], player2);
	// game.move(gameNodes[1][3], player1);
	// game.move(gameNodes[2][3], player2);
	//
	// game.getWinChecker().printp1KB();
	// game.getWinChecker().printp2KB();
	//
	// }

	// @Test
	// public void testKBRotation(){
	// Player player1 = new HumanPlayer(1);
	// Player player2 = new HumanPlayer(2);
	// Game game = new Game(5, 5, player1, player2);
	//
	// Node[][] gameNodes = game.getGameState();
	//
	// game.move(gameNodes[0][0], player1);
	// game.move(gameNodes[0][1], player1);
	// game.move(gameNodes[1][1], player1);
	// game.move(gameNodes[1][2], player1);
	//
	// game.getWinChecker().printp1KB();
	//
	// game.getWinChecker().rotateKB(game.getWinChecker().getP1KB());
	//
	// game.getWinChecker().printp1KB();
	//
	//
	//
	// }

	// @Test
	// public void testCanBeUnified(){
	// EdgeAxiom allVars = new EdgeAxiom("n1", "n2", "a", "e1");
	// EdgeAxiom allConst = new EdgeAxiom("11", "12", "A", "11-12");
	// EdgeAxiom oneConst1 = new EdgeAxiom("n1", "12", "a", "e1");
	// EdgeAxiom oneConst2 = new EdgeAxiom("11", "23", "a", "e1");
	//
	//
	// assertEquals(true, allConst.canBeUnified(allVars));
	// assertEquals(true, allConst.canBeUnified(oneConst1));
	// assertEquals(false, allConst.canBeUnified(oneConst2));
	//
	// assertEquals(true, allConst.isAllConstants());
	// assertEquals(false, allVars.isAllConstants());
	// assertEquals(false, oneConst2.isAllConstants());
	//
	// }

	//
	// @Test
	// public void testUnification(){
	// LinkedList<EdgeAxiom> goal = new LinkedList<EdgeAxiom>();
	//
	// EdgeAxiom edge1 = new EdgeAxiom("n1", "n2", "a", "e1");
	// edge1.negate();
	// EdgeAxiom edge2 = new EdgeAxiom("n2", "n3", "a", "e2");
	// edge2.negate();
	// EdgeAxiom edge3 = new EdgeAxiom("n3", "n4", "a", "e3");
	// edge3.negate();
	// EdgeAxiom allConst = new EdgeAxiom("11", "12", "A", "11-12");
	// EdgeAxiom oneConst1 = new EdgeAxiom("n1", "12", "a", "e1");
	// EdgeAxiom oneConst2 = new EdgeAxiom("12", "13", "A", "12-13");
	// EdgeAxiom oneConst3 = new EdgeAxiom("13", "14", "A", "13-14");
	//
	// goal.add(edge1);
	// goal.add(edge2);
	// goal.add(edge3);
	//
	// allConst.unify(goal);
	// oneConst2.unify(goal);
	// oneConst1.unify(goal);
	// oneConst3.unify(goal);
	//
	// System.out.println("\n AFTER ALL UNIFICATIONS:" + goal.toString());
	//
	//
	// }

	@Test
	public void testResolutionWithWin1() {
		Player player1 = new HumanPlayer(1);
		Player player2 = new HumanPlayer(2);
		Game game = new Game(5, 5, player1, player2);

		Node[][] gameNodes = game.getGameNodes();

		game.move(gameNodes[0][0], player1);
		game.move(gameNodes[2][0], player1);
		game.move(gameNodes[1][0], player1);
		game.move(gameNodes[3][0], player1);

		game.getWinChecker().printp1KB();

		System.out.println("SHOULD BE A WIN FOR PLAYER 1 ONLY");
		assertEquals(true, game.getWinChecker().getWinForPlayer1());
		assertEquals(false, game.getWinChecker().getWinForPlayer2());

	}

	@Test
	public void testResolutionWithoutWin1() {
		Player player1 = new HumanPlayer(1);
		Player player2 = new HumanPlayer(2);
		Game game = new Game(5, 5, player1, player2);

		Node[][] gameNodes = game.getGameNodes();

		game.move(gameNodes[0][0], player1);
		game.move(gameNodes[0][1], player2);
		game.move(gameNodes[1][1], player1);
		game.move(gameNodes[1][2], player2);
		game.move(gameNodes[1][3], player1);
		game.move(gameNodes[2][3], player2);
		game.move(gameNodes[1][0], player1);

		assertEquals(false, game.getWinChecker().getWinForPlayer1());
		assertEquals(false, game.getWinChecker().getWinForPlayer2());

	}

	@Test
	public void testResolutionWithWin2() {
		Player player1 = new HumanPlayer(1);
		Player player2 = new HumanPlayer(2);
		Game game = new Game(5, 5, player1, player2);

		Node[][] gameNodes = game.getGameNodes();

		game.move(gameNodes[0][0], player1);
		game.move(gameNodes[0][1], player2);

		game.move(gameNodes[1][1], player1);
		game.move(gameNodes[1][2], player2);

		game.move(gameNodes[1][3], player1);
		game.move(gameNodes[2][3], player2);

		game.move(gameNodes[1][0], player1);
		game.move(gameNodes[3][4], player2);

		System.out.println("SHOULD BE A WIN FOR PLAYER 2 ONLY");
		assertEquals(false, game.getWinChecker().getWinForPlayer1());
		assertEquals(true, game.getWinChecker().getWinForPlayer2());

	}

	@Test
	public void testResolutionWithWin3() {
		Player player1 = new HumanPlayer(1);
		Player player2 = new HumanPlayer(2);
		Game game = new Game(5, 5, player1, player2);

		Node[][] gameNodes = game.getGameNodes();

		game.move(gameNodes[0][0], player1);
		game.move(gameNodes[0][1], player2);

		game.move(gameNodes[1][1], player1);
		game.move(gameNodes[1][2], player2);

		game.move(gameNodes[1][3], player1);
		game.move(gameNodes[2][3], player2);

		game.move(gameNodes[1][0], player1);
		game.move(gameNodes[2][1], player2);

		game.move(gameNodes[3][1], player1);
		game.move(gameNodes[3][4], player2);

		System.out.println("SHOULD BE A WIN FOR PLAYER 2 ONLY");
		assertEquals(false, game.getWinChecker().getWinForPlayer1());
		assertEquals(true, game.getWinChecker().getWinForPlayer2());
	}

	@Test
	public void testResolutionWithWin4() {
		Player player1 = new HumanPlayer(1);
		Player player2 = new HumanPlayer(2);
		Game game = new Game(5, 5, player1, player2);

		Node[][] gameNodes = game.getGameNodes();

		game.move(gameNodes[0][0], player1);
		game.move(gameNodes[0][1], player2);

		game.move(gameNodes[1][1], player1);
		game.move(gameNodes[1][2], player2);

		game.move(gameNodes[1][3], player1);
		game.move(gameNodes[2][3], player2);

		game.move(gameNodes[1][0], player1);
		game.move(gameNodes[2][1], player2);

		game.move(gameNodes[1][4], player1);
		game.move(gameNodes[2][0], player2);

		System.out.println("SHOULD BE A WIN FOR PLAYER 1 ONLY");
		assertEquals(true, game.getWinChecker().getWinForPlayer1());
		assertEquals(false, game.getWinChecker().getWinForPlayer2());

		game.getWinChecker().printp1KB();

	}

	@Test
	public void testResolutionWithoutWin3() {
		Player player1 = new HumanPlayer(1);
		Player player2 = new HumanPlayer(2);
		Game game = new Game(5, 5, player1, player2);

		Node[][] gameNodes = game.getGameNodes();

		game.move(gameNodes[0][0], player1);
		game.move(gameNodes[0][1], player2);

		game.move(gameNodes[1][1], player1);
		game.move(gameNodes[1][2], player2);

		game.move(gameNodes[1][3], player1);
		game.move(gameNodes[2][3], player2);

		game.move(gameNodes[1][0], player1);
		game.move(gameNodes[2][1], player2);

		game.move(gameNodes[3][3], player1);
		game.move(gameNodes[2][0], player2);

		System.out.println("SHOULD NOT BE ANY WINS");

		assertEquals(false, game.getWinChecker().getWinForPlayer1());
		assertEquals(false, game.getWinChecker().getWinForPlayer2());

		game.getWinChecker().printp1KB();

	}
	
	@Test
	public void testProblemResolution1() {
		Player player1 = new HumanPlayer(1);
		Player player2 = new HumanPlayer(2);
		Game game = new Game(4, 12, player1, player2);

		Node[][] gameNodes = game.getGameNodes();


		game.move(gameNodes[1][9], player1);
		game.move(gameNodes[2][10], player1);

		game.move(gameNodes[2][8], player1);
		game.move(gameNodes[2][9], player1);
		
		game.move(gameNodes[3][11], player1);
		game.move(gameNodes[3][10], player1);



		System.out.println("SHOULD NOT BE ANY WINS");
		
		game.getWinChecker().printp1KB();

		assertEquals(false, game.getWinChecker().getWinForPlayer1());
		assertEquals(false, game.getWinChecker().getWinForPlayer2());

		game.getWinChecker().printp1KB();

	}
	
	@Test
	public void testProblemResolution2() {
		Player player1 = new HumanPlayer(1);
		Player player2 = new HumanPlayer(2);
		Game game = new Game(4, 12, player1, player2);

		Node[][] gameNodes = game.getGameNodes();


		game.move(gameNodes[1][6], player1);

		game.move(gameNodes[0][0], player1);
		game.move(gameNodes[0][3], player1);
		game.move(gameNodes[0][8], player1);
		
		game.move(gameNodes[1][9], player1);
		game.move(gameNodes[0][10], player1);
		
		game.move(gameNodes[0][11], player1);



		System.out.println("SHOULD NOT BE ANY WINS");
		
		game.getWinChecker().printp1KB();

		assertEquals(false, game.getWinChecker().getWinForPlayer1());
		assertEquals(false, game.getWinChecker().getWinForPlayer2());

		game.getWinChecker().printp1KB();

	}
	
//	@Test
//	public void testProblemResolution3(){
//		
//	}
	
}
