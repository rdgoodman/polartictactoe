package polartictactoe;

import java.util.Vector;

public class GameTree {

	int evaluationDepth;
	TreeNode root;
	int depthReached;
	int nodesEvaluated;
	TreeNode maximin;
	int maxEvalDepth;

	// TODO: get heuristic

	/**
	 * Builds a game tree to evaluate max player's best move
	 * 
	 * @param currentGameState
	 *            the state of the game - MIN player has just moved, MAX player
	 *            is deciding on a move
	 * @param MAXPlayer
	 *            the player deciding on a move. Tree is built using MAXplayer's
	 *            rewards.
	 * @param MINPlayer
	 *            the opposing player.
	 * @param evaluationDepth
	 *            the depth at which the tree will be cut off for heuristic
	 *            evaluation.
	 * @param AB
	 *            boolean - true if the tree will be using alpha-beta pruning
	 */
	public GameTree(Node[][] currentGameState, int MAXPlayer, int MINPlayer,
			int evaluationDepth, boolean AB) {

		// root has a null parent
		root = new TreeNode(currentGameState, MAXPlayer, MINPlayer, null, 1);
		// TODO: set depthReached
		depthReached = 0;
		nodesEvaluated = 0;
		maxEvalDepth = evaluationDepth;

		if (AB) {
			buildABTree(root, evaluationDepth);
		} else {
			buildMinimaxTree(root, evaluationDepth);
		}

		reportDepthAndNodes();
	}

	/** Builds a tree without performing alpha-beta pruning */
	private void buildMinimaxTree(TreeNode current, int maxDepth) {
		// base case
		if (current.getDepth() == maxDepth) {
			current.evaluate();

			updateFromHeuristicEvaluatedLevel(current);

		} else {
			System.out.println("-------------------------------------");
			while (current.hasNextChild()) {
				TreeNode next = current.createNextChild();
				nodesEvaluated++;
				buildMinimaxTree(next, maxDepth);
			}
			// updateToRoot(current);
			updateFromHeuristicEvaluatedLevel(current);
		}

	}

	/** Takes values from max-depth level and populates them back up the tree */
	private void updateFromHeuristicEvaluatedLevel(TreeNode current) {
		if (current.getDepth() != 1) {
			// changes value of parent if still at default (i.e. this is
			// current's
			// first child)
			if (current.getParent().getValue() == -5) {
				current.getParent().setValue(current.getValue());
				System.out.println("Parent's value changed to "
						+ current.getValue());
			}

			if (current.getParent().isMaxNode()) {
				// change value of max parent
				if (current.getValue() > current.getParent().getValue()) {
					System.out.println("Parent's value changed to "
							+ current.getValue());
					current.getParent().setValue(current.getValue());

				}
			} else {
				// change value of min parent
				if (current.getValue() < current.getParent().getValue()) {
					System.out.println("Parent's value changed to "
							+ current.getValue());
					current.getParent().setValue(current.getValue());

				}
			}
		} else {
			// at root - do nothing
			System.out.println("ROOT");
			System.out.println("Value is " + current.getValue());
			System.out.println("CHILDREN: " + current.getChildren().size());

			// after iterating values up tree, chooses move with greatest payoff
			// for max player
			maximin = current.getChildren().get(0);

			for (TreeNode t : current.getChildren()) {
				if (t.getValue() > maximin.getValue()) {
					maximin = t;
				}
			}

			System.out.println("Maximin is "
					+ maximin.getHypotheticalMove().toString());
		}

	}

	/** Builds a tree while performing alpha-beta pruning */
	private void buildABTree(TreeNode current, int maxDepth) {
		// base case
		if (current.getDepth() == maxDepth) {
			current.evaluate();
			updateABFromHeuristicEvaluatedLevel(current);

		} else {
			System.out.println("-------------------------------------");
			while (current.hasNextChild()) {
				TreeNode next = current.createNextChild();

				//pass on values
				if (current.isMaxNode()) {
					next.setAlpha(current.getAlpha());
					//System.out.println("     Set alpha");
					next.printAB();
				} else {
					next.setBeta(current.getBeta());
					//System.out.println("     Set beta");
					next.printAB();
				}

				nodesEvaluated++;
				buildABTree(next, maxDepth);
			}
			// updateABToRoot(current);
			updateABFromHeuristicEvaluatedLevel(current);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}

	/** Takes values from max-depth level and populates them back up the tree */
	private void updateABFromHeuristicEvaluatedLevel(TreeNode current) {
//		
//		if (current.getBeta() < current.getAlpha()){
//			System.out.println("########PRUNE HERE: ########");
//			System.out.println("THE NODE TO PRUNE BELOW IS " + current.toString());
//			
//			// TODO: when pruning, remove the rest of the node's children
//			current.prune();
//			
//		}
		
		
		
		
		if (current.getDepth() == maxEvalDepth) { // TODO: this "if" should also
													// cover wins, etc
			if (current.getParent().isMaxNode()) {
				// change alpha
				if (current.getValue() > current.getParent().getAlpha()) {
					System.out.println("Parent's alpha changed to "
							+ current.getValue());
					current.getParent().setAlpha(current.getValue());
				}
				checkIfPrune(current);
			} else {
				// change beta
				if (current.getValue() < current.getParent().getBeta()) {
					System.out.println("Parent's beta changed to "
							+ current.getValue());
					current.getParent().setBeta(current.getValue());
				}
				checkIfPrune(current);
			}

		} else if (current.getDepth() != 1) {
			if (current.getParent().isMaxNode()) {
				// change alpha
				if (current.getBeta() > current.getParent().getAlpha()) {
					System.out.println("Parent's alpha changed to "
							+ current.getValue());
					current.getParent().setAlpha(current.getBeta());
				}
				checkIfPrune(current);
			} else {
				// change beta
				if (current.getAlpha() < current.getParent().getBeta()) {
					System.out.println("Parent's beta changed to "
							+ current.getValue());
					current.getParent().setBeta(current.getAlpha());
				}
				checkIfPrune(current);
			}
		} else {
			// at root - do nothing
			System.out.println("ROOT");
			System.out.println("Alpha is " + current.getAlpha());

			// after iterating values up tree, chooses move with greatest payoff
			// for max player
			maximin = current.getChildren().get(0);

			for (TreeNode t : current.getChildren()) {
				if (t.getBeta() > maximin.getBeta()) {
					maximin = t;
				}
			}

			System.out.println("Maximin is "
					+ maximin.getHypotheticalMove().toString());

		}
		
		System.out.println();
	}
	
	/** Checks if we should prune below this node */
	public boolean checkIfPrune(TreeNode current){
		if (current.getParent().getBeta() < current.getParent().getAlpha()){
			System.out.println("########PRUNE HERE: ########");
			System.out.println("THE NODE TO PRUNE BELOW IS " + current.toString());
			
			// TODO: when pruning, remove the rest of the node's children
			current.prune();
			return true;
			
		}
		return false;
	}

	/** Reports depth reached and number of nodes evaluated */
	private void reportDepthAndNodes() {
		System.out
				.println("\n\n\n\n\nNot sure of depth yet [given maxdepth], but that'll be easy to check");
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

	/** Returns the plan generated by this tree */
	public String toString() {
		return maximin.toString();
	}

}
