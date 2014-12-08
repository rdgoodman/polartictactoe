package polartictactoe;

import java.util.LinkedList;
import java.util.Random;

public class AIPlayerMinimax implements Player {

	int maxSearchDepth;
	// TODO:the next two should be parameters reported by reportMove() and its
	// delegates (minimax, etc)?
	int numNodesEvaluated;
	int timeToSelect;
	int playerNum;
	LinkedList<Edge> edges;
	Game game;

	public AIPlayerMinimax(int playerNum) {
		this.playerNum = playerNum;
		edges = new LinkedList<Edge>();
	}

	@Override
	public LinkedList<Edge> getEdges() {
		return edges;
	}

	@Override
	public int getPlayerNum() {
		return playerNum;
	}

	@Override
	public void chooseMove() {
		if (game.isFirstMove()) {
			// TODO: makes a random move
			Random randomMoveGenerator = new Random();
			int x = randomMoveGenerator.nextInt(game.getNumX());
			int y = randomMoveGenerator.nextInt(game.getNumY());

			reportFirstMove(game.getGameNodes()[x][y]);

		} else {

			int opponentPlayerNum = 0;
			if (playerNum == 0) {
				opponentPlayerNum = 1;
			}

			// creates a game tree that uses the heuristic but NOT alpha-beta
			GameTree tree = new GameTree(game.getGameState(), playerNum,
					opponentPlayerNum, 0, game.getMaxSearchDepth(), false, true);
			reportMove(tree.getOutputs());
		}

	}

	@Override
	public void reportMove(long[] results) {
		// 1) send move to game
		int x = (int) results[0];
		int y = (int) results[1];
		Node move = game.getGameNodes()[x][y];
		game.move(move, this);

		// TODO: send stuff to file

		// 2) print results
		int nodes = (int) results[3];
		int depth = (int) results[4];
		System.out
				.println("Result produced in " + results[2] + " milliseconds");
		System.out.println("There were " + nodes + " nodes evaluated");
		System.out.println("Ply " + depth + " was reached in the game tree");

	}

	public void reportFirstMove(Node move) {
		System.out.println("The player chose to move at node "
				+ move.toString());
		game.move(move, this);
	}

	@Override
	public void addEdge(Edge newEdge) {
		edges.add(newEdge);

	}

	@Override
	public boolean hasEdge(Edge seeking) {
		if (edges.isEmpty()) {
			return false;
		} else if (edges.contains(seeking)) {
			return true;
		}
		return false;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;

	}

}
