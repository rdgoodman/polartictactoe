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

	/**
	 * Creates all the plies in a tree, up until the specified maximum depth
	 * TODO: This builds in level-order, which may need to change...
	 */
	protected void levelOrderCreatePlies(int currentDepth, int maxDepth,
			TreeNode current) {
		// TODO: testing, remove
		System.out.println("\n\n\n************************ Branch In Ply "
				+ currentDepth + " ************************\n\n");

		if (currentDepth < (maxDepth - 1)) {
			// creates the branches for all of the current node's children
			for (TreeNode i : current.getChildren()) {
				System.out.println("\n-----NEW BRANCH-----");
				i.createNextBranch();
			}
			currentDepth++;
			// recursively creates next branch
			for (TreeNode i : current.getChildren()) {
				levelOrderCreatePlies(currentDepth, maxDepth, i);
			}
		} else {
			// creates AND EVALUATES children

			// creates the branches for all of the current node's children
			for (TreeNode i : current.getChildren()) {
				System.out.println("\n-----NEW BRANCH-----");
				i.createNextBranch();
				i.evaluateChildren();
			}

		}

	}

	/** TODO: maxDepth probably won't be a thing later on */
	protected void minimaxBuildTree(TreeNode current, int maxDepth) {
		// base case
		if (current.getDepth() == maxDepth) {
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
