
package polartictactoe;

public class GameState {
	// nodes in the current game state
	Node[][] nodes;
	// win-checker for this game
	WinChecker winchecker;
	boolean win;

	int numX;
	int numY;

	/** Default constructor for game */
	public GameState(int circles, int lines) {
		this.numX = circles;
		this.numY = lines;

		nodes = new Node[numX][numY];

		createNodesAndNeighbors();
		winchecker = new WinChecker(this);

	}

	/** Constructor to be used with an existing game state */
	public GameState(Node[][] currentState) {
		numX = currentState.length;
		numY = currentState[0].length;

		nodes = new Node[numX][numY];

		// clones the existing state
		for (int circles = 0; circles < numX; circles++) {
			for (int lines = 0; lines < numY; lines++) {
				Node entry = new Node(currentState[circles][lines].getX(),
						currentState[circles][lines].getY());
				entry.setPlayer(currentState[circles][lines].getPlayer());
				nodes[circles][lines] = entry;
			}
		}
		
		// sets all neighbors
		for (int i = 0; i < numX; i++) {
			for (int j = 0; j < numY; j++) {
				setNeighbors(nodes[i][j]);
			}
		}
		winchecker = new WinChecker(this);
		

	}

	public void addToP1KB(EdgeAxiom a) {
		winchecker.addToP1KnowledgeBase(a);
	}
	
	public void addToP2KB(EdgeAxiom a) {
		winchecker.addToP2KnowledgeBase(a);
	}

	
	/** Creates Nodes for the game, all initialized to empty */
	private void createNodesAndNeighbors() {
		// create edge nodes
		for (int y = 0; y < numY; y++) {
			nodes[numX - 1][y] = new Node(numX - 1, y);
			nodes[0][y] = new Node(0, y);
		}
		// create inner nodes
		for (int y = 0; y < numY; y++) {
			for (int x = 1; x < numX - 1; x++) {
				nodes[x][y] = new Node(x, y);
			}
		}

		// set all neighbors
		for (int i = 0; i < numX; i++) {
			for (int j = 0; j < numY; j++) {
				setNeighbors(nodes[i][j]);
			}
		}
	}

	/** Sets neighbors for a node in the graph */
	private void setNeighbors(Node a) {
		int counterclockwise = a.getY() - 1;
		int clockwise = a.getY() + 1;

		// special case: on "nth" radial line of grid
		if (a.getY() == (numY - 1)) {
			clockwise = 0;
		}

		// special case: on "zeroth" radial line of grid
		if (a.getY() == 0) {
			counterclockwise = numY - 1;
		}

		// for all
		a.addNeighbor(nodes[a.getX()][clockwise]);
		a.addNeighbor(nodes[a.getX()][counterclockwise]);
		// for those not on outermost arc
		if (a.getX() != (numX - 1)) {
			a.addNeighbor(nodes[a.getX() + 1][a.getY()]);
			a.addNeighbor(nodes[a.getX() + 1][counterclockwise]);
			a.addNeighbor(nodes[a.getX() + 1][clockwise]);
		}
		// for those not on innermost arc
		if (a.getX() != 0) {
			a.addNeighbor(nodes[a.getX() - 1][a.getY()]);
			a.addNeighbor(nodes[a.getX() - 1][counterclockwise]);
			a.addNeighbor(nodes[a.getX() - 1][clockwise]);
		}

	}

	public Node[][] getNodes() {
		return nodes;
	}

	public int getNumX() {
		return numX;
	}

	public int getNumY() {
		return numY;
	}

	
	public WinChecker getWinChecker(){
		return winchecker;
	}
	
	public void setToHaveWin(){
		this.win = true;
	}
	
	public boolean hasWin(){
		return win;
	}
	
	public void printGameState(){
		System.out.println("Current Game State:");
		for (int i = 0; i < numX; i++) {
			for (int j = 0; j < numY; j++) {
				System.out.print(nodes[i][j].toString() + "   ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

}