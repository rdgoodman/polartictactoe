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
		root = new TreeNode(currentGameState, firstPlayer, secondPlayer, null);
		depthReached = 0;
		nodesEvaluated = 0;

		root.createNextBranch();
		// TODO: evaluationDepth should be some sort of command line parameter,
		// I think

		// root is ply 1 - starts at ply 2
		createPlies(2, evaluationDepth, root);

	}

	/**
	 * Creates all the plies in a tree, up until the specified maximum depth
	 * TODO: This builds in level-order, which may need to change...
	 */
	protected void createPlies(int currentDepth, int maxDepth, TreeNode current) {
		// TODO: testing, remove
		System.out.println("\n\n\n************************ Branch In Ply "
				+ currentDepth + " ************************\n\n");

//		if (currentDepth == maxDepth) {
//			// base case
//			System.out.println("Heuristic evaluation");
//		} else {
//			for (TreeNode i : current.getChildren()) {
//				// create children
//				i.createNextBranch();
//				createPlies(currentDepth++, maxDepth, i);
//			}
//			// currentDepth++;
//		}

//		// base case
//		if (currentDepth == maxDepth) {
//			System.out
//					.println("We're at the maximum depth and should be using our heuristic");
//			current.evaluate();
//			// TODO: is current the right thing? I think we're going a step too
//			return;
//		} else {
//			// creates the branches for all of the current node's children
//			for (TreeNode i : current.getChildren()) {
//				System.out.println("\n-----NEW BRANCH-----");
//				i.createNextBranch();
//			}
//			currentDepth++;
//			// TODO: Is this method call even the right thing to do here?
//			for (TreeNode i : current.getChildren()) {
//				createPlies(currentDepth, maxDepth, i);
//			}
//		}
		
		if (currentDepth < (maxDepth - 1)){
			// creates the branches for all of the current node's children
			for (TreeNode i : current.getChildren()) {
				System.out.println("\n-----NEW BRANCH-----");
				i.createNextBranch();
			}
			currentDepth++;
			// recursively creates next branch
			for (TreeNode i : current.getChildren()) {
				createPlies(currentDepth, maxDepth, i);
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
