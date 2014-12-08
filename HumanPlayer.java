package polartictactoe;

import java.util.LinkedList;
import java.util.Scanner;

public class HumanPlayer implements Player {

	int playerNum;
	LinkedList<Edge> edges;
	Game game;

	public HumanPlayer(int playerNum){
		this.playerNum = playerNum;
		edges = new LinkedList<Edge>();
	}
	

	@Override
	public void chooseMove() {
		// user is prompted to give a move by the UI
		
		Scanner getMoveScanner = new Scanner(System.in);
		System.out.println("> Please input the x (circle) coordinate of the intersection you wish to play");
		int x = getMoveScanner.nextInt();
		System.out.println("> Please input the y (line) coordinate of the intersection you wish to play");
		int y = getMoveScanner.nextInt();
		
		
		if ((x > game.getNumX() - 1) || (y > game.getNumY() - 1)){
			System.out.println("> ERROR: not a valid move, please try again");
			chooseMove();
		}
		
		
		Node tryMove = game.getGameNodes()[x][y];
		
		// chosen move is run through isValidMove
		if(!game.isValidMove(tryMove)){
			// if not a valid move, user is prompted to try again
			System.out.println("> ERROR: not a valid move, please try again");
			chooseMove();
		} else {
			// if a valid move, the move is sent to the Game, which updated the GameState
			game.move(tryMove, this);
			reportMove(null);
		}

	}

	@Override
	public void reportMove(long[] results) {
		System.out.println("> Your move was recorded ");
		
	}

	
	@Override
	public int getPlayerNum() {
		return playerNum;
	}


	@Override
	public LinkedList<Edge> getEdges() {
		return edges;
	}


	@Override
	public void addEdge(Edge newEdge) {
		edges.add(newEdge);
		
	}


	@Override
	public boolean hasEdge(Edge seeking) {
		if(edges.isEmpty()){
			return false;
		} else if (edges.contains(seeking)){
			return true;
		}
		return false;
	}


	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}
}
