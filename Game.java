package polartictactoe;

public class Game {

	// circles, lines gotten through command-line input
	// number of circles
	private int numX;
	// number of radial lines
	private int numY;
	// players; types of which gotten through command line
	Player pX;
	Player pO;
	boolean firstMove;
<<<<<<< HEAD
	GameState gameState;
=======
	GameState nodes;
>>>>>>> 1fef8a5b609010e7aed065e5d0de9d130e3e840e

	public Game(int numX, int numY, Player pX, Player pO) {
		this.numX = numX;
		this.numY = numY;
		this.pX = pX;
		this.pO = pO;

		firstMove = true;
<<<<<<< HEAD
		gameState = new GameState(numX, numY);
=======
		nodes = new GameState(numX, numY);
>>>>>>> 1fef8a5b609010e7aed065e5d0de9d130e3e840e

	}

	/**
	 * Checks whether or not a move is legal (i.e. next to a previously played
	 * node) call this before move() using user/AI input
	 */
	public boolean isValidMove(Node chosen) {
		if (firstMove == true) {
			System.out.println("It's the first move");
			return true;
		}

		for (Node i : chosen.getNeighbors()) {
			if (i.getPlayer() != 0) {
				// returns true if the node has a neighbor that is played
				return true;
			}
		}
		// all neighbors are blank/unplayed
		return false;
	}

	/**
	 * Modifies the board state with a specific move, which MUST be evaluated to
	 * be valid before being passed in as an argument
	 */
	public void move(Node changed, Player currentPlayer) {
		if (firstMove == true) {
			firstMove = false;
		}
		// sets the Node to belong to the current player
		changed.setPlayer(currentPlayer.getPlayerNum());

		// create and add any new edges as applicable
		for (Node i : changed.getNeighbors()) {
			if (i.getPlayer() == currentPlayer.getPlayerNum()) {
				Edge possibleNewEdge = new Edge(changed, i, changed.getPlayer());
				if (!currentPlayer.hasEdge(possibleNewEdge)) {
					currentPlayer.addEdge(possibleNewEdge);
<<<<<<< HEAD
					// add to knowledge base
					gameState.addToKB(possibleNewEdge.getEndpointsAxiom());
					gameState.addToKB(possibleNewEdge.getTypeAxiom());
				}
			}
		}
		
		gameState.checkIfWin(pX);
		gameState.checkIfWin(pO);

	}

=======
				}
			}
		}

	}

	/** Reports output of win-checker. Should be called after each successful move */
	public boolean checkIfWin() {
		// TODO: calls winChecker object (yet to be implemented)
		return false;
	}

>>>>>>> 1fef8a5b609010e7aed065e5d0de9d130e3e840e
	public void draw() {
		// does nothing I'm involved with
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * Below here be getters and setters
	 * 
	 * 
	 * 
	 * 
	 * */

	public int getNumX() {
		return numX;
	}

	public void setNumX(int numX) {
		this.numX = numX;
	}

	public int getNumY() {
		return numY;
	}

	public void setNumY(int numY) {
		this.numY = numY;
	}

	public Node[][] getGameState() {
<<<<<<< HEAD
		return gameState.getNodes();
=======
		return nodes.getNodes();
>>>>>>> 1fef8a5b609010e7aed065e5d0de9d130e3e840e
	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public Diagonal[][] getDiagonals() {
<<<<<<< HEAD
		return gameState.getDiagonals();
	}
	
	public WinChecker getWinChecker(){
		return gameState.getWinChecker();
=======
		return nodes.getDiagonals();
>>>>>>> 1fef8a5b609010e7aed065e5d0de9d130e3e840e
	}

}
