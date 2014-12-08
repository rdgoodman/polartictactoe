package polartictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinimaxTest {

	Player player1 = new HumanPlayer(1);
	Player player2 = new HumanPlayer(2);
	Player player3 = new HumanPlayer(3);
	Player player4 = new HumanPlayer(4);

//	Game game = new Game(3, 4, player1, player2);
//	Game game2 = new Game(3, 4, player3, player4);
//	Node[][] gameNodes = game.getGameNodes();
//	Node[][] gameNodes2 = game2.getGameNodes();

//	@Test
//	public void testMinimax1() {
//
//		// TODO: turn win checker back on
//
//		game.move(gameNodes[1][3], player1);
//		game.move(gameNodes[2][3], player2);
//		game.move(gameNodes[2][2], player1);
//		game.move(gameNodes[2][1], player2);
//		game.move(gameNodes[1][2], player1);
//		game.move(gameNodes[1][0], player2);
//		game.move(gameNodes[2][0], player1);
//		game.move(gameNodes[0][0], player2);
//
//		Node[][] gameState1 = game.getGameNodes();
//		System.out.println("Game State:");
//		for (int i = 0; i < game.getNumX(); i++) {
//			for (int j = 0; j < game.getNumY(); j++) {
//				System.out.print(gameState1[i][j].toString() + "   ");
//			}
//			System.out.println("");
//		}
//		System.out.println("\n\n\n\n");
//
//		// does NOT take hypothetical moves - takes game state
//		// Question is: what is 1's best move?
//
//		// new tree with max depth of three
//		GameTree tree = new GameTree(game.getGameState(),
//				player1.getPlayerNum(), player2.getPlayerNum(),
//				player1.getPlayerNum(), 4, false);
//
//		// checking that nothing untoward happened
//		// the gamestate should NOT have been actually changed.
//		Node[][] gameState2 = game.getGameNodes();
//		System.out.println("\n\n\n\nGame State:");
//		for (int i = 0; i < game.getNumX(); i++) {
//			for (int j = 0; j < game.getNumY(); j++) {
//				System.out.print(gameState2[i][j].toString()
//						+ gameState2[i][j].getNeighbors().size() + "   ");
//			}
//			System.out.println();
//		}
//		game.getGameState().getWinChecker().printp1KB();
//		game.getGameState().getWinChecker().printp2KB();
//
//	}

//	 @Test
//	 public void testAlphaBeta1() {
//	 // TODO: turn win checker back on
//	
//	 game.move(gameNodes[1][3], player1);
//	 game.move(gameNodes[2][3], player2);
//	 game.move(gameNodes[2][2], player1);
//	 game.move(gameNodes[2][1], player2);
//	 game.move(gameNodes[1][2], player1);
//	 game.move(gameNodes[1][0], player2);
//	 game.move(gameNodes[2][0], player1);
//	 game.move(gameNodes[0][0], player2);
//	
//	 Node[][] gameState1 = game.getGameNodes();
//	 System.out.println("Game State:");
//	 for (int i = 0; i < game.getNumX(); i++) {
//	 for (int j = 0; j < game.getNumY(); j++) {
//	 System.out.print(gameState1[i][j].toString() + "   ");
//	 }
//	 System.out.println("");
//	 }
//	 System.out.println("\n\n\n\n");
//	
//	 // does NOT take hypothetical moves - takes game state
//	 // Question is: what is 1's best move?
//	
//	 // new tree with max depth of three
//	 @SuppressWarnings("unused")
//	 GameTree tree = new GameTree(game.getGameState(), player1.getPlayerNum(),
//	 player2.getPlayerNum(), player1.getPlayerNum(), 4, true, true);
//	
//	 // checking that nothing untoward happened
//	 // the gamestate should NOT have been actually changed.
//	 Node[][] gameState2 = game.getGameNodes();
//	 System.out.println("\n\n\n\nGame State:");
//	 for (int i = 0; i < game.getNumX(); i++) {
//	 for (int j = 0; j < game.getNumY(); j++) {
//	 System.out.print(gameState2[i][j].toString()
//	 + gameState2[i][j].getNeighbors().size() + "   ");
//	 }
//	 System.out.println();
//	 }
//	
//	 game.getGameState().getWinChecker().printp1KB();
//	 game.getGameState().getWinChecker().printp2KB();
//	 }

//	@Test
//	public void testMinimaxWithWinForMax() {
//		Player player1 = new HumanPlayer(1);
//		Player player2 = new HumanPlayer(2);
//
//		Game game = new Game(4, 4, player1, player2);
//		 Node[][] gameNodes = game.getGameNodes();
//
//		game.move(gameNodes[3][1], player1);
//		game.move(gameNodes[2][1], player2);
//		game.move(gameNodes[2][2], player1);
//		game.move(gameNodes[1][2], player2);
//
//		game.move(gameNodes[0][3], player1);
//		game.move(gameNodes[1][3], player2);
//		game.move(gameNodes[0][0], player1);
//		game.move(gameNodes[3][2], player2);
//
//		game.move(gameNodes[2][0], player1);
//		game.move(gameNodes[0][2], player2);
//		game.move(gameNodes[3][0], player1);
//		game.move(gameNodes[3][3], player2);
//
//		GameTree tree = new GameTree(game.getGameState(),
//				player1.getPlayerNum(), player2.getPlayerNum(),
//				player1.getPlayerNum(), 3, false);
//
//	}
	
//	@Test
//	public void testMinimaxKB1(){
//		Player player1 = new HumanPlayer(1);
//		Player player2 = new HumanPlayer(2);
//
//		Game game = new Game(3, 3, player1, player2);
//		 Node[][] gameNodes = game.getGameNodes();
//
//		game.move(gameNodes[2][0], player1);
//		game.move(gameNodes[1][0], player2);
//		
//		// maximizing for p1
//		GameTree tree = new GameTree(game.getGameState(),
//		player1.getPlayerNum(), player2.getPlayerNum(),
//		player1.getPlayerNum(), 3, false);
//	}
//	
//	@Test
//	public void testMinimaxKB2(){
//		
//	}

//	 @Test
//	 public void testMinimaxWithWinForMax(){
//	 Player player1 = new HumanPlayer(1);
//	 Player player2 = new HumanPlayer(2);
//	
//	 Game game = new Game(4, 4, player1, player2);
//	 Node[][] gameNodes = game.getGameNodes();
//	 
//	 game.move(gameNodes[3][1], player1);
//	 game.move(gameNodes[3][3], player2);
//	 game.move(gameNodes[2][2], player1);
//	 game.move(gameNodes[2][1], player2);
//	
//	 game.move(gameNodes[0][0], player1);
//	 game.move(gameNodes[1][2], player2);
//	 game.move(gameNodes[0][3], player1);
//	 game.move(gameNodes[3][2], player2);
//	
//	 game.move(gameNodes[3][0], player1);
//	 game.move(gameNodes[1][3], player2);
//	 game.move(gameNodes[2][0], player1);
//	 game.move(gameNodes[0][2], player2);
//	 
//		// maximizing for p1
//		GameTree tree = new GameTree(game.getGameState(),
//		player1.getPlayerNum(), player2.getPlayerNum(),
//		player1.getPlayerNum(), 3, false);
//	
//	 }
	
//	 @Test
//	 public void testABWithWinForMax(){
//	 Player player1 = new HumanPlayer(1);
//	 Player player2 = new HumanPlayer(2);
//	
//	 Game game = new Game(4, 4, player1, player2);
//	 Node[][] gameNodes = game.getGameNodes();
//	 
//	 game.move(gameNodes[3][1], player1);
//	 game.move(gameNodes[3][3], player2);
//	 game.move(gameNodes[2][2], player1);
//	 game.move(gameNodes[2][1], player2);
//	
//	 game.move(gameNodes[0][0], player1);
//	 game.move(gameNodes[1][2], player2);
//	 game.move(gameNodes[0][3], player1);
//	 game.move(gameNodes[3][2], player2);
//	
//	 game.move(gameNodes[3][0], player1);
//	 game.move(gameNodes[1][3], player2);
//	 game.move(gameNodes[2][0], player1);
//	 game.move(gameNodes[0][2], player2);
//	 
//		// maximizing for p1
//		GameTree tree = new GameTree(game.getGameState(),
//		player1.getPlayerNum(), player2.getPlayerNum(),
//		player1.getPlayerNum(), 4, true);
//	
//	 }

	// @Test
	// public void testAlphaBeta3(){
	// Player player1 = new HumanPlayer(1);
	// Player player2 = new HumanPlayer(2);
	//
	// Game game = new Game(4, 6, player1, player2);
	// //Node[][] gameNodes = game.getGameNodes();
	//
	// game.move(gameNodes[1][3], player1);
	// game.move(gameNodes[2][3], player2);
	// game.move(gameNodes[2][2], player1);
	// game.move(gameNodes[2][1], player2);
	//
	// game.move(gameNodes[1][2], player1);
	// game.move(gameNodes[1][0], player2);
	// game.move(gameNodes[2][0], player1);
	// game.move(gameNodes[0][0], player2);
	//
	// }

}