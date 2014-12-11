package polartictactoe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;

public class AIPlayerMinimax implements Player {

	int maxSearchDepth;
	int numNodesEvaluated;
	long timeToSelect;
	int playerNum;
	LinkedList<Edge> edges;
	Game game;
	File timeOutput;
	File nodeOutput;
	FileWriter timeWriter;
	FileWriter nodeWriter;
	Player opponent;

	public AIPlayerMinimax(int playerNum) {
		this.playerNum = playerNum;
		edges = new LinkedList<Edge>();
		
		// creates output files
		timeOutput = new File("src"+ File.separator + "polartictactoe" + File.separator + "mmtimeOutput.txt");
		if (!timeOutput.exists()){
			try {
				timeOutput.createNewFile();
			} catch (IOException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		nodeOutput = new File("src"+ File.separator + "polartictactoe" + File.separator + "mmnodeOutput.txt");
		if (!nodeOutput.exists()){
			try {
				nodeOutput.createNewFile();
			} catch (IOException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		}
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
			// makes a random move
			Random randomMoveGenerator = new Random();
			int x = randomMoveGenerator.nextInt(game.getNumX());
			int y = randomMoveGenerator.nextInt(game.getNumY());

			reportFirstMove(game.getGameNodes()[x][y]);

		} else {

			if (playerNum == 0) {
				opponent = game.getPO();
			} else {
				opponent = game.getPX();
			}

			// creates a game tree that uses the heuristic but NOT alpha-beta
			GameTree tree = new GameTree(game.getGameState(), this,
					opponent, game.getPX(), game.getMaxSearchDepth(), false, true);
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

		// 2) print results
		timeToSelect = results[2];
		numNodesEvaluated = (int) results[3];
		maxSearchDepth = (int) results[4];
		System.out
				.println("Result produced in " + timeToSelect + " milliseconds");
		System.out.println("There were " + numNodesEvaluated + " nodes evaluated");
		System.out.println("Ply " + maxSearchDepth + " was reached in the game tree");
		
		// TODO: send stuff to file
		try {
			timeWriter = new FileWriter(timeOutput, true);
			nodeWriter = new FileWriter(nodeOutput, true);
			
			timeWriter.write(String.valueOf(timeToSelect) + "   ");
			nodeWriter.write(String.valueOf(numNodesEvaluated) + "   ");
			
			timeWriter.close();
			nodeWriter.close();
			
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

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
