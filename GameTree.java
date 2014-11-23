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

	public GameTree(Node[][] currentGameState, Node moveToEvaluate, int firstPlayer,
			int secondPlayer, int evaluationDepth) {

		// root has a null parent
		root = new TreeNode(currentGameState, moveToEvaluate, firstPlayer, secondPlayer, null,
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

			// TODO: pruning, iterating values up
			if (current.getParent().isMaxNode()) {
				// change alpha
				if (current.getValue() > current.getParent().getAlpha()) {
					System.out.println("Parent's alpha changed to "
							+ current.getValue());
					current.getParent().setAlpha(current.getValue());
				}
			} else {
				// change beta
				if (current.getValue() < current.getParent().getBeta()) {
					System.out.println("Parent's beta changed to "
							+ current.getValue());
					current.getParent().setBeta(current.getValue());
				}
			}

			// TODO: method calls or something in here? update grandparent when
			// you finish a branch!!!

		} else {
			System.out.println("-------------------------------------");
			while (current.hasNextChild()) {
				TreeNode next = current.createNextChild();
				minimaxBuildTree(next, maxDepth);
			}
			System.out.println("Update grandparent?!?!?!?!?!");
			System.out.println("\n" + current.toString());
			// TODO: include an if-else in case this is the root
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
