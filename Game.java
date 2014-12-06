package rolliepolartictactoe;

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
	GameState gameState;

	public Game(int numX, int numY, Player pX, Player pO) {
		this.numX = numX;
		this.numY = numY;
		this.pX = pX;
		this.pO = pO;

		firstMove = true;
		gameState = new GameState(numX, numY);

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

		// /see if node already played
		if (chosen.player == 0) {
			for (Node i : chosen.getNeighbors()) {
				if (i.player != 0) {
					// returns true if the node has a neighbor that is played
					return true;
				}
			}
		}
		// all neighbors are blank/unplayed
		// TODO: Kevin's, uncomment once you integrate
		// else if (allMovesNodes.size() >= numX * numY) {
		// // for testing until a winchecker is implemented
		// isActive = false;
		// }
		// checkIfWin()
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
				Edge possibleNewEdge = new Edge(changed, i, changed.getPlayer(), numY-1);
				if (!currentPlayer.hasEdge(possibleNewEdge)) {
					currentPlayer.addEdge(possibleNewEdge);
					// add to knowledge base
					if (possibleNewEdge.getColor() == pX.getPlayerNum()) {
						gameState.addToP1KB(possibleNewEdge.getEdgeAxiom());
					} else {
						gameState.addToP2KB(possibleNewEdge.getEdgeAxiom());
					}
				}
			}
		}
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

	public Node[][] getGameNodes() {
		return gameState.getNodes();
	}
	
	public GameState getGameState(){
		return gameState;
	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public Diagonal[][] getDiagonals() {
		return gameState.getDiagonals();
	}

	public WinChecker getWinChecker() {
		return gameState.getWinChecker();

	}

}
