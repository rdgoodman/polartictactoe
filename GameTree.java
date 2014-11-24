package polartictactoe;

public class GameTree {

	int evaluationDepth;
	TreeNode root;
	int depthReached;
	int nodesEvaluated;

	// TODO: get heuristic

	public GameTree(Node[][] currentGameState, Node moveToEvaluate,
			int firstPlayer, int secondPlayer, int evaluationDepth) {

		// root has a null parent
		root = new TreeNode(currentGameState, moveToEvaluate, firstPlayer,
				secondPlayer, null, 1);
		depthReached = 0;
		nodesEvaluated = 0;

		buildABTree(root, evaluationDepth);
		
		reportDepthAndNodes();
	}

	/** TODO: maxDepth probably won't be a thing later on */
	protected void buildABTree(TreeNode current, int maxDepth) {
		// base case
		if (current.getDepth() == maxDepth) {
			current.evaluate();
			updateABFromHeuristicEvaluatedLevel(current);

		} else {
			System.out.println("-------------------------------------");
			while (current.hasNextChild()) {
				TreeNode next = current.createNextChild();
				nodesEvaluated++;
				buildABTree(next, maxDepth);
			}
			updateABToRoot(current);
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
	
	private void updateABToRoot(TreeNode current){
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

	private void reportDepthAndNodes() {
		System.out.println("\n\n\n\n\nNot sure of depth yet [given maxdepth], but that'll be easy to check");
		System.out.println("Nodes evaluated: " + nodesEvaluated);
		
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
