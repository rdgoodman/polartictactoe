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

	public GameTree(Node[][] currentGameState, Node moveToEvaluate,
			int firstPlayer, int secondPlayer, int evaluationDepth) {

		// root has a null parent
		root = new TreeNode(currentGameState, moveToEvaluate, firstPlayer,
				secondPlayer, null, 1);
		depthReached = 0;
		nodesEvaluated = 0;

		minimaxBuildTree(root, evaluationDepth);

	}

	/** TODO: maxDepth probably won't be a thing later on */
	protected void minimaxBuildTree(TreeNode current, int maxDepth) {
		// base case
		if (current.getDepth() == maxDepth) {
			current.evaluate();
			
			updateABFromHeuristicEvaluatedLevel(current);

		} else {
			System.out.println("-------------------------------------");
			while (current.hasNextChild()) {
				TreeNode next = current.createNextChild();
				minimaxBuildTree(next, maxDepth);
			}
			updateABFromOtherAB(current);
		}
	}
	
	private void updateABFromHeuristicEvaluatedLevel(TreeNode current){
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
	}
	
	private void updateABFromOtherAB(TreeNode current){
		if (current.getDepth() != 1){
			System.out.println("grandparent: " + current.getParent().toString());
			if (current.getParent().isMaxNode()) {
				// change alpha
				if (current.getBeta() > current.getParent().getAlpha()) {
					System.out.println("Grandparent's alpha changed to "
							+ current.getBeta());
					current.getParent().setAlpha(current.getBeta());
				}
			} else {
				// change beta
				if (current.getAlpha() < current.getParent().getBeta()) {
					System.out.println("Grandparent's beta changed to "
							+ current.getAlpha());
					current.getParent().setBeta(current.getAlpha());
				}
			}
			System.out.println();
		} else {
			// at root - do nothing
			System.out.println("ROOT");
			System.out.println("Alpha is " + current.getAlpha());
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
