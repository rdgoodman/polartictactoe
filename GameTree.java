package polartictactoe;

public class GameTree {
	
	TreeNode root;
	int depthReached;
	int nodesEvaluated;
	// TODO: maybe heuristic should be passed in here,
	// and evaluation should happen in this class instead of TreeNode?
	
	public GameTree(Node[][] currentGameState, int currentPlayer){
		Node[][] passThisIn = new Node[currentGameState.length][currentGameState[0].length];
		
		// creates a copy of the game state passed in, without pointer issues
		for (int circles = 0; circles < currentGameState.length; circles++) {
			for (int lines = 0; lines < currentGameState[0].length; lines++) {
				Node entry = new Node(currentGameState[circles][lines].getX(),currentGameState[circles][lines].getY());
				entry.setPlayer(currentGameState[circles][lines].getPlayer());
				passThisIn[circles][lines] = entry;
			}
		}
		
		
		
		// root has a null parent
		root = new TreeNode(passThisIn, currentPlayer, null);
		depthReached = 0;
		nodesEvaluated = 0;
		
		root.createNextPly();
	}
	
	// TODO: need a method to continue building the tree (calling createNextPly)

	public TreeNode getRoot() {
		return root;
	}

	public int getDepthReached() {
		return depthReached;
	}

	public int getNodesEvaluated() {
		return nodesEvaluated;
	}
	
}
