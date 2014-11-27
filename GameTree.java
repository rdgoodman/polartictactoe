package polartictactoe;

import java.util.Vector;

public class GameTree {

	int evaluationDepth;
	TreeNode root;
	int depthReached;
	int nodesEvaluated;
	Vector<Node> moveSequence;

	// TODO: get heuristic
	// TODO: return sequence of moves

	/**
	 * Builds game tree given a hypothetical move to evaluate
	 * 
	 * @param currentGameState
	 *            the current game state with only concrete moves made
	 * @param moveToEvaluate
	 *            the hypothetical move to evaluate
	 * @param firstPlayer
	 *            the player who would be playing the hypothetical move
	 * @param secondPlayer
	 *            the opposing player
	 * @param evaluationDepth
	 *            max depth for evaluation
	 * @param AB
	 *            will this tree be using alpha-beta pruning?
	 */
	public GameTree(Node[][] currentGameState, Node moveToEvaluate,
			int firstPlayer, int secondPlayer, int evaluationDepth, boolean AB) {

		// root has a null parent
		root = new TreeNode(currentGameState, moveToEvaluate, firstPlayer,
				secondPlayer, null, 1);
		depthReached = 0;
		nodesEvaluated = 0;
		moveSequence = new Vector<Node>(2, 1);

		if (AB) {
			buildABTree(root, evaluationDepth);
		} else {
			buildMinimaxTree(root, evaluationDepth);
		}

		reportDepthAndNodes();

		System.out.println("\n\nVector of moves is of size "
				+ moveSequence.size());
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
			updateToRoot(current);
		}

	}

	/** Takes values from max-depth level and populates them back up the tree */
	private void updateFromHeuristicEvaluatedLevel(TreeNode current) {
		// changes value of parent if still at default (i.e. this is current's first child)
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

				// TODO: add to vector
				if (moveSequence.size() < current.getDepth()) {
					moveSequence.add(current.getHypotheticalMove());
					System.out.println("ADDED");
					System.out.println(this.toString());
				} else {
					moveSequence.add(current.getDepth(),
							current.getHypotheticalMove());
					System.out.println("ADDED - ELSE");
					System.out.println(this.toString());
				}
			}
		} else {
			// change value of min parent
			if (current.getValue() < current.getParent().getValue()) {
				System.out.println("Parent's value changed to "
						+ current.getValue());
				current.getParent().setValue(current.getValue());

				// TODO: add to vector
				if (moveSequence.size() < current.getDepth()) {
					moveSequence.add(current.getHypotheticalMove());
					System.out.println("ADDED");
					System.out.println(this.toString());
				} else {
					moveSequence.add(current.getDepth(),
							current.getHypotheticalMove());
					System.out.println("ADDED - ELSE");
					System.out.println(this.toString());
				}
			}
		}

	}

	/** Populates value up to root (hypothetical move) */
	private void updateToRoot(TreeNode current) {
		if (current.getDepth() != 1) {
			System.out
					.println("grandparent: " + current.getParent().toString());
			if (current.getParent().isMaxNode()) {
				// change value of max parent
				if (current.getValue() > current.getParent().getValue()) {
					System.out.println("Grandparent's value changed to "
							+ current.getValue());
					current.getParent().setValue(current.getValue());

					// TODO: add to vector
					if (moveSequence.size() < current.getDepth()) {
						moveSequence.add(current.getHypotheticalMove());
					} else {
						moveSequence.add(current.getDepth(),
								current.getHypotheticalMove());
					}
				}
			} else {
				// change value of min parent
				if (current.getValue() < current.getParent().getValue()) {
					System.out.println("Grandparent's value changed to "
							+ current.getValue());
					current.getParent().setValue(current.getValue());

					// TODO: add to vector
					if (moveSequence.size() < current.getDepth()) {
						moveSequence.add(current.getHypotheticalMove());
					} else {
						moveSequence.add(current.getDepth(),
								current.getHypotheticalMove());
					}
				}
			}
			System.out.println();
		} else {
			// at root - do nothing
			System.out.println("ROOT");
			System.out.println("Value is " + current.getValue());
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
				nodesEvaluated++;
				buildABTree(next, maxDepth);
			}
			updateABToRoot(current);
		}
	}

	/** Takes values from max-depth level and populates them back up the tree */
	private void updateABFromHeuristicEvaluatedLevel(TreeNode current) {
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

	/** Populates alpha-beta values up to root (hypothetical move) */
	private void updateABToRoot(TreeNode current) {
		if (current.getDepth() != 1) {
			System.out
					.println("grandparent: " + current.getParent().toString());
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
	public String toString(){
		return moveSequence.toString();
	}

}
