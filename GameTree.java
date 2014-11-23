package polartictactoe;

public class GameTree {

	// TODO: do we want this maxdepth?
	int evaluationDepth;
	TreeNode root;
	int depthReached;
	int nodesEvaluated;

	// TODO: maybe heuristic should be passed in here,
	// and evaluation should happen in this class instead of TreeNode?
	// also, will need a separate method to iterate back up the tree
	// to assign values

	public GameTree(Node[][] currentGameState, int firstPlayer,
			int secondPlayer, int evaluationDepth) {

		// root has a null parent
		root = new TreeNode(currentGameState, firstPlayer, secondPlayer, null,
				1);
		depthReached = 0;
		nodesEvaluated = 0;

		minimaxBuildTree(root, 3);

	}

	/** TODO: maxDepth probably won't be a thing later on */
	protected void minimaxBuildTree(TreeNode current, int maxDepth) {
		// base case
		if (current.getDepth() == maxDepth) {
			current.evaluate();
			// do nothing
		} else {
			while (current.hasNextChild()) {
				TreeNode next = current.createNextChild();
				minimaxBuildTree(next, maxDepth);
			}
		}
	}

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
