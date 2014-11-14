package polartictactoe;

public class Game {

	// TODO: both should be gotten through command-line input
	// number of circles
	private int numX;
	// number of radial lines
	private int numY;
	// players; types of which gotten through command line
	// TODO: AI player should be assigned to p1 or p2 randomly?
	Player pX;
	Player pO;
	boolean firstMove;
	GameState nodes;

	
	public Game(int numX, int numY, Player pX, Player pO) {
		this.numX = numX;
		this.numY = numY;
		this.pX = pX;
		this.pO = pO;

		firstMove = true;
		nodes = new GameState(numX, numY);
		
		nodes.createNodesAndNeighbors();
		
	}


	/** call this before move() using user/AI input */
	public boolean isValidMove(Node chosen) {
		if (firstMove == true){
			System.out.println("It's the first move");
			return true;
		}
		
		for (Node i: chosen.getNeighbors()){
			if (i.getPlayer() != 0){
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
				}
			}
		}

	}

	public boolean checkIfWin() {
		// TODO: all this
		return false;
	}

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

	public Node[][] getNodes() {
		return nodes.getNodes();
	}
	
	public boolean isFirstMove(){
		return firstMove;
	}

}
