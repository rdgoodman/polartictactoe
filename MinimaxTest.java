package polartictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinimaxTest {

	Player player1 = new HumanPlayer(1);
	Player player2 = new HumanPlayer(2);
	Player player3 = new HumanPlayer(3);
	Player player4 = new HumanPlayer(4);

	Game game = new Game(3, 4, player1, player2);
	Game game2 = new Game(3, 4, player3, player4);
	Node[][] gameNodes = game.getGameState();
	Node[][] gameNodes2 = game2.getGameState();

	@Test
	public void testMinimax() {

		// TODO: turn win checker back on

		game.move(gameNodes[1][3], player1);
		game.move(gameNodes[2][3], player2);
		game.move(gameNodes[2][2], player1);
		game.move(gameNodes[2][1], player2);
		game.move(gameNodes[1][2], player1);
		game.move(gameNodes[1][0], player2);
		game.move(gameNodes[2][0], player1);
		game.move(gameNodes[0][0], player2);

		Node[][] gameState1 = game.getGameState();
		System.out.println("Game State:");
		for (int i = 0; i < game.getNumX(); i++) {
			for (int j = 0; j < game.getNumY(); j++) {
				System.out.print(gameState1[i][j].toString() + "   ");
			}
			System.out.println("");
		}
		System.out.println("\n\n\n\n");

		// TODO: does NOT take hypothetical moves - takes game state
		// Question is: what is 1's best move?

		// new tree with max depth of three
		GameTree tree = new GameTree(gameNodes, player1.getPlayerNum(), player2.getPlayerNum(), 3, false);

		// checking that nothing untoward happened
		// the gamestate should NOT have been actually changed.
		Node[][] gameState2 = game.getGameState();
		System.out.println("\n\n\n\nGame State:");
		for (int i = 0; i < game.getNumX(); i++) {
			for (int j = 0; j < game.getNumY(); j++) {
				System.out.print(gameState2[i][j].toString()
						+ gameState2[i][j].getNeighbors().size() + "   ");
			}
			System.out.println();
		}

	}

	// @Test
	// public void testAlphaBeta() {
	//
	// game.move(gameNodes[1][3], player1);
	// game.move(gameNodes[2][3], player2);
	// game.move(gameNodes[2][2], player1);
	// game.move(gameNodes[2][1], player2);
	// game.move(gameNodes[1][2], player1);
	// game.move(gameNodes[1][0], player2);
	// game.move(gameNodes[2][0], player1);
	//
	// Node[][] gameState1 = game.getGameState();
	// System.out.println("Game State:");
	// for (int i = 0; i < game.getNumX(); i++) {
	// for (int j = 0; j < game.getNumY(); j++) {
	// System.out.print(gameState1[i][j].toString() + "   ");
	// }
	// System.out.println("");
	// }
	// System.out.println("\n\n\n\n");
	//
	// // TODO: changed input for hypothetical moves
	// Node hypotheticalMove = gameNodes[0][0].cloneNodeButNotNeighbors();
	// hypotheticalMove.setPlayer(player2.getPlayerNum());
	//
	// // new tree with max depth of three
	// @SuppressWarnings("unused")
	// GameTree tree = new GameTree(gameNodes, hypotheticalMove,
	// player1.getPlayerNum(), player2.getPlayerNum(), 3, true);
	//
	// // checking that nothing untoward happened
	// // the gamestate should NOT have been actually changed.
	// Node[][] gameState2 = game.getGameState();
	// System.out.println("\n\n\n\nGame State:");
	// for (int i = 0; i < game.getNumX(); i++) {
	// for (int j = 0; j < game.getNumY(); j++) {
	// System.out.print(gameState2[i][j].toString()
	// + gameState2[i][j].getNeighbors().size() + "   ");
	// }
	// System.out.println();
	// }
	// }

}