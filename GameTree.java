package polartictactoe;

public class GameTree {

	int evaluationDepth;
	TreeNode root;
	int depthReached;
	int nodesEvaluated;
	TreeNode maximin;
	int maxEvalDepth;
	long time;
	
	private long startWall; //what time the search starts
	private long endWall; //what time the search ends

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
	 * 
	 * @param player1
	 *            the player who moved first in this game - important for KB
	 *            building
	 * 
	 * @param evaluationDepth
	 *            the depth at which the tree will be cut off for heuristic
	 *            evaluation.
	 * @param AB
	 *            boolean - true if the tree will be using alpha-beta pruning
	 * @param heuristic
	 * 			boolean - true if the tree will be evaluating using a heuristic - otherwise, the classifier is the default
	 */
	public GameTree(GameState currentGameState, int MAXPlayer, int MINPlayer,
			int player1, int evaluationDepth, boolean AB, boolean heuristic) {

		// root has a null parent
		root = new TreeNode(currentGameState, MAXPlayer, MINPlayer, player1,
				null, 1);
		depthReached = 0;
		nodesEvaluated = 0;
		maxEvalDepth = evaluationDepth;
		
		startWall = System.currentTimeMillis();

		if (AB) {
			buildABTree(root, evaluationDepth);
		} else {
			buildMinimaxTree(root, evaluationDepth);
		}
		
		endWall = System.currentTimeMillis();
		time = endWall - startWall;
		reportDepthAndNodes();
	}

	/** Builds a tree without performing alpha-beta pruning */
	private void buildMinimaxTree(TreeNode current, int maxDepth) {
		// base case
		if (current.getDepth() == maxDepth) {
			current.heuristicEvaluate();
			depthReached = maxDepth;

			updateFromHeuristicEvaluatedLevel(current);

		} else {
			//System.out.println("-------------------------------------");
			while (current.hasNextChild()) {
				depthReached = current.getDepth();
				
				TreeNode next = current.createNextChild();
				nodesEvaluated++;
				buildMinimaxTree(next, maxDepth);
			}
			updateFromHeuristicEvaluatedLevel(current);
		}

	}

	/** Takes values from max-depth level and populates them back up the tree */
	private void updateFromHeuristicEvaluatedLevel(TreeNode current) {
		if (current.getDepth() != 1) {
			// changes value of parent if still at default (i.e. this is
			// current's
			// first child)
			if (current.getParent().getValue() == -Integer.MIN_VALUE) {
				current.getParent().setValue(current.getValue());
//				System.out.println("Parent's value changed to "
//						+ current.getValue());
			}

			if (current.getParent().isMaxNode()) {
				// change value of max parent
				if (current.getValue() > current.getParent().getValue()) {
//					System.out.println("Parent's value changed to "
//							+ current.getValue());
					current.getParent().setValue(current.getValue());

				}
			} else {
				// change value of min parent
				if (current.getValue() < current.getParent().getValue()) {
//					System.out.println("Parent's value changed to "
//							+ current.getValue());
					current.getParent().setValue(current.getValue());

				}
			}
		} else {
			// at root - do nothing
//			System.out.println("ROOT");
//			System.out.println("Value is " + current.getValue());
//			System.out.println("CHILDREN: " + current.getChildren().size());

			// after iterating values up tree, chooses move with greatest payoff
			// for max player
			maximin = current.getChildren().get(0);

			for (TreeNode t : current.getChildren()) {
				if (t.getValue() > maximin.getValue()) {
					maximin = t;
				}
			}

//			System.out.println("Maximin is "
//					+ maximin.getHypotheticalMove().toString());
		}

	}

	/** Builds a tree while performing alpha-beta pruning */
	private void buildABTree(TreeNode current, int maxDepth) {
		// base case
		if (current.getDepth() == maxDepth) {
			current.heuristicEvaluate();
			depthReached = maxDepth;
			
			updateABFromHeuristicEvaluatedLevel(current);

		} else {
			//System.out.println("-------------------------------------");
			while (current.hasNextChild()) {
				depthReached = current.getDepth();
				TreeNode next = current.createNextChild();

				// pass on values
				if (current.isMaxNode()) {
					next.setAlpha(current.getAlpha());
					// System.out.println("     Set alpha");
					next.printAB();
				} else {
					next.setBeta(current.getBeta());
					// System.out.println("     Set beta");
					next.printAB();
				}

				nodesEvaluated++;
				buildABTree(next, maxDepth);
			}
			updateABFromHeuristicEvaluatedLevel(current);
			//System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}

	/** Takes values from max-depth level and populates them back up the tree */
	private void updateABFromHeuristicEvaluatedLevel(TreeNode current) {

		if (current.getDepth() == maxEvalDepth || current.isTerminal()) {
			if (current.getParent().isMaxNode()) {
				// change alpha
				if (current.getValue() > current.getParent().getAlpha()) {
//					System.out.println("Parent's alpha changed to "
//							+ current.getValue());
					current.getParent().setAlpha(current.getValue());
				}
				checkIfPrune(current);
			} else {
				// change beta
				if (current.getValue() < current.getParent().getBeta()) {
//					System.out.println("Parent's beta changed to "
//							+ current.getValue());
					current.getParent().setBeta(current.getValue());
				}
				checkIfPrune(current);
			}

		} else if (current.getDepth() != 1) {
			if (current.getParent().isMaxNode()) {
				// change alpha
				if (current.getBeta() > current.getParent().getAlpha()) {
//					System.out.println("Parent's alpha changed to "
//							+ current.getBeta());
					current.getParent().setAlpha(current.getBeta());
				}
				checkIfPrune(current);
			} else {
				// change beta
				if (current.getAlpha() < current.getParent().getBeta()) {
//					System.out.println("Parent's beta changed to "
//							+ current.getAlpha());
					current.getParent().setBeta(current.getAlpha());
				}
				checkIfPrune(current);
			}
		} else {
			// at root - do nothing
//			System.out.println("ROOT");
//			System.out.println("Alpha is " + current.getAlpha());

			// after iterating values up tree, chooses move with greatest payoff
			// for max player
			maximin = current.getChildren().get(0);

			for (TreeNode t : current.getChildren()) {
				if ((t.getBeta() > maximin.getBeta()) && (t.getBeta() != Integer.MAX_VALUE)) {
					maximin = t;
				}
			}
//
//			System.out.println("Maximin is "
//					+ maximin.getHypotheticalMove().toString());

		}

		System.out.println();
	}

	/** Checks if we should prune below this node */
	public void checkIfPrune(TreeNode current) {
		if (current.getParent().getBeta() < current.getParent().getAlpha()) {
//			System.out.println("########PRUNE HERE: ########");
//			System.out.println("THE NODE TO PRUNE BELOW IS "
//					+ current.getParent().toString());

			// pruning removes the rest of this node's potential children
			current.prune();

		}
	}

	/** Reports depth reached and number of nodes evaluated */
	private void reportDepthAndNodes() {
//		System.out
//				.println("\n\n\n\n\nNot sure of depth yet [given maxdepth], but that'll be easy to check");
//		System.out.println("Nodes evaluated: " + nodesEvaluated);

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
	
	/**
	 *
	 * @return returns an array of (0: maximin x), (1: maximin y), (2: time), (3: nodes evaluated), and (4: depth reached)
	 */
	public long[]getOutputs(){
		long[] results = new long[5];
		results[0] = maximin.getHypotheticalMove().getX();
		results[1] = maximin.getHypotheticalMove().getY();
		results[2] = time;
		results[3] = nodesEvaluated;
		results[4] = depthReached;
		
		
		
		return results;
		
	}

	/** Returns the plan generated by this tree */
	public String toString() {
		return maximin.toString();
	}
	
	public void startTimeSet(long start){
		this.startWall = start;
	}
	public void endTimeSet(long end){
		this.endWall = end;
	}

}
