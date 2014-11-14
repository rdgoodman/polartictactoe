package polartictactoe;

public class GameState {
	// nodes in the current game state
	Node[][] nodes;
	int numX;
	int numY;
	
	public Node[][] getNodes() {
		return nodes;
	}

	public int getNumX() {
		return numX;
	}

	public int getNumY() {
		return numY;
	}

	public GameState(int circles, int lines){
		this.numX = circles;
		this.numY = lines;
		
		nodes = new Node[circles][lines];
	}
	
	/** Creates Nodes for the graph, all initialized to empty */
	public void createNodesAndNeighbors() {
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
		for (int i = 0; i < numX; i++){
			for(int j = 0; j < numY; j++){
				setNeighbors(nodes[i][j]);
			}
		}
	}

	/** Sets neighbors for a node in the graph */
	public void setNeighbors(Node a) {
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
	

}
