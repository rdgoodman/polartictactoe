package polartictactoe;

public class GameState {
	// nodes in the current game state
	Node[][] nodes;
	// diagonals for this game
	Diagonal[][] diagonals;

	int numX;
	int numY;

	/** Default constructor for game */
	public GameState(int circles, int lines) {
		this.numX = circles;
		this.numY = lines;
		
		nodes = new Node[numX][numY];
		
		createNodesAndNeighbors();
		createDiagonals();
		setDiagonals();

	}

	/** Constructor to be used with an existing game state */
	public GameState(Node[][] currentState) {
		numX = currentState.length;
		numY = currentState[0].length;

		nodes = new Node[numX][numY];

		createDiagonals();

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
	}
	
	private void createDiagonals(){
		// There are two diagonals originating from each line: one clockwise,
		// one counterclockwise
		// Diagonal [0][x] refers to the counterclockwise diagonal from line x
		diagonals = new Diagonal[2][numY];
		// create counterclockwise diagonals
		for (int j = 0; j < numY; j++) {
			diagonals[0][j] = new Diagonal(j, false);
		}
		// create clockwise diagonals
		for (int j = 0; j < numY; j++) {
			diagonals[1][j] = new Diagonal(j, true);
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

	/** Creates Nodes for the graph, all initialized to empty */
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

		// sets all neighbors
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
			// TODO: diagonals
			//I think the origins (the Ys) are the problem
//			diagonals[0][a.getY()].add(a);
//			diagonals[0][a.getY()].add(nodes[a.getX() + 1][counterclockwise]);
//			diagonals[1][a.getY()].add(a);
//			diagonals[1][a.getY()].add(nodes[a.getX() + 1][clockwise]);

		}
		// for those not on innermost arc
		if (a.getX() != 0) {
			a.addNeighbor(nodes[a.getX() - 1][a.getY()]);
			a.addNeighbor(nodes[a.getX() - 1][counterclockwise]);
			a.addNeighbor(nodes[a.getX() - 1][clockwise]);
			// TODO: diagonals
//			diagonals[0][a.getY()].add(a);
//			diagonals[0][a.getY()].add(nodes[a.getX() - 1][counterclockwise]);
//			diagonals[1][a.getY()].add(a);
//			diagonals[1][a.getY()].add(nodes[a.getX() - 1][clockwise]);
		}

	}
	
	private void setDiagonals(){
		
	}
	
	
	public Diagonal[][] getDiagonals(){
		return diagonals;
	}

}
