package polartictactoe;

import java.util.ArrayList;
import java.util.LinkedList;

public class TreeNode {

	GameState gameState;
	boolean maxNode;
	// note: these refer to whose turn it is in the game,
	// NOT whose move generated the current ply
	Player currentPlayer;
	Player nextPlayer;
	// this is the player who played first in this game
	Player player1;
	// used to generate children - 1:1 relationship
	LinkedList<Node> potentialMoves;
	int numChildren;
	ArrayList<TreeNode> children = new ArrayList<TreeNode>();
	// doubly linked
	TreeNode parent;
	// comes from heuristic - initialized to -infinity
	double value = Integer.MIN_VALUE;
	// initialized to values larger/smaller than the heuristic will ever return
	double alpha = Integer.MIN_VALUE;
	double beta = Integer.MAX_VALUE;
	int depth;
	Node hypotheticalMoveAttribute;
	boolean isTerminal = false;
	boolean print = false;

	/**
	 * For root only
	 * 
	 * @param currentState
	 * @param maxPlayer
	 * @param minPlayer
	 * @param parent
	 * @param depth
	 */
	public TreeNode(GameState currentState, Player maxPlayer, Player minPlayer,
			Player player1, TreeNode parent, int depth, boolean print) {

		// sets game state nodes to a copy of current game state's nodes
		gameState = new GameState(currentState.getNodes());

		// clones both KBs from the game state - for other nodes, separate
		// method for this
		LinkedList<EdgeAxiom> newP1KB = new LinkedList<EdgeAxiom>();
		LinkedList<EdgeAxiom> newP2KB = new LinkedList<EdgeAxiom>();

		for (EdgeAxiom e : currentState.getWinChecker().getP1KB()) {
			newP1KB.add(e);
		}

		for (EdgeAxiom e : currentState.getWinChecker().getP2KB()) {
			newP2KB.add(e);
		}

		gameState.getWinChecker().setP1KB(newP1KB);
		gameState.getWinChecker().setP2KB(newP2KB);

		// System.out.println("Starting KB: ");
		// gameState.getWinChecker().printp1KB();
		// gameState.getWinChecker().printp2KB();

		this.currentPlayer = maxPlayer;
		this.nextPlayer = minPlayer;
		this.player1 = player1;
		potentialMoves = new LinkedList<Node>();
		this.parent = parent;
		this.depth = depth;

		// root is always a max node
		maxNode = true;
		this.print = print;

		// root has no hypothetical move
		setHypotheticalMove(null);
		// System.out.println(this.toString());

		countChildren();

	}

	/** For rest of game nodes - takes a game state rather than an array */
	public TreeNode(GameState parentState, Player currentPlayer,
			Player nextPlayer, Player player1, boolean maxNode,
			TreeNode parent, int depth, boolean print) {
		gameState = parentState;
		this.currentPlayer = currentPlayer;
		this.nextPlayer = nextPlayer;
		potentialMoves = new LinkedList<Node>();
		this.maxNode = maxNode;
		this.parent = parent;
		this.depth = depth;
		this.player1 = player1;

		this.print = print;

		cloneParentsKB();
		countChildren();
	}

	/**
	 * Counts the number of child nodes this node will have, and adds the
	 * potential moves to represent to a Linked List
	 */
	protected void countChildren() {
		if (gameState.hasWin()) {
			numChildren = 0;
		} else {

			// iterates through each node in the game
			for (int circles = 0; circles < gameState.getNumX(); circles++) {
				for (int lines = 0; lines < gameState.getNumY(); lines++) {
					// if a node is played by either player...
					if (gameState.getNodes()[circles][lines].getPlayer() != -1) {
						// it iterates through each of its nodes neighbors...
						for (Node i : gameState.getNodes()[circles][lines]
								.getNeighbors()) {
							// and adds them to the list of potential moves
							// if they are unplayed and not already present
							if ((!potentialMoves.contains(i))
									&& (i.getPlayer() == -1)) {
								potentialMoves.add(i);
								// increments the number of child nodes
								numChildren++;
							}
						}
					}
				}
			}
		}
	}

	protected boolean hasNextChild() {
		return !potentialMoves.isEmpty();
	}

	/** Creates the next child in the next ply */
	protected TreeNode createNextChild() {

		TreeNode childNode;
		// identical gamestate at first
		GameState childState = new GameState(gameState.getNodes());

		int player = nextPlayer.getPlayerNum();
		if (maxNode) {
			player = currentPlayer.getPlayerNum();
		}

		if (potentialMoves.isEmpty()) {
			childNode = null;

		} else {
			Node nextMove = potentialMoves.getFirst();
			// changes the single Node in GameState to reflect move being
			// evaluated
			childState.getNodes()[nextMove.getX()][nextMove.getY()]
					.setPlayer(player);

			childNode = new TreeNode(childState, currentPlayer, nextPlayer,
					player1, !maxNode, this, this.depth + 1, print);
			// removes that potential move from the list
			potentialMoves.removeFirst();

			childNode
					.setHypotheticalMove(childState.getNodes()[nextMove.getX()][nextMove
							.getY()]);

			if (print) {
				String max = "";
				if (childNode.isMaxNode()) {
					max = "MAX";
				} else {
					max = "MIN";
				}
				System.out.println("\nChild State: "
						+ max
						+ " "
						+ childState.getNodes()[nextMove.getX()][nextMove
								.getY()].toString() + " at depth "
						+ childNode.getDepth());
			}

			// adds axioms to child node's KBs
			childNode
					.addAxiomsToKB(childState.getNodes()[nextMove.getX()][nextMove
							.getY()]);

		}

		// adds childNode to Children
		children.add(childNode);
		return childNode;
	}

	private void addAxiomsToKB(Node node) {
		int numY = gameState.getNodes()[0].length;

		// adds new things to KB, but does not create edges
		for (Node i : node.getNeighbors()) {
			if (i.getPlayer() == node.getPlayer()) {

				// add to knowledge base
				// also, create entirely new edge axiom, instead of relying on
				// the edge
				Edge possibleNewEdge = new Edge(node, i, node.getPlayer(),
						numY - 1);

				if (node.getPlayer() == player1.getPlayerNum()) {
					gameState.addToP1KB(possibleNewEdge.getEdgeAxiom());
				} else {
					gameState.addToP2KB(possibleNewEdge.getEdgeAxiom());
				}
			}
		}

		// sets as a terminal node if this game state has a win/loss, and
		// evaluates
		// TODO: why does this only work for minimax...?
		if (gameState.hasWin()) {
			getPotentialMoves().clear();
			isTerminal = true;
			heuristicEvaluate();
		}

	}

	private void cloneParentsKB() {

		LinkedList<EdgeAxiom> newP1KB = new LinkedList<EdgeAxiom>();
		LinkedList<EdgeAxiom> newP2KB = new LinkedList<EdgeAxiom>();

		for (EdgeAxiom e : parent.getGameState().getWinChecker().getP1KB()) {
			newP1KB.add(e);
		}

		for (EdgeAxiom e : parent.getGameState().getWinChecker().getP2KB()) {
			newP2KB.add(e);
		}

		gameState.getWinChecker().setP1KB(newP1KB);
		gameState.getWinChecker().setP2KB(newP2KB);
	}

	/** TODO: MUST USE HEURISTIC */
	public void heuristicEvaluate() {
		// value = (int) (Math.random() * 100);
		Heuristic heuristic = new Heuristic();
		value = heuristic.evaluate(gameState, currentPlayer);
		if (print) {
			System.out.println("***********Set value of this node to " + value);
		}
	}

	/** must use classifier */
	public void classifierEvaluate() {
		// yet to be implemented
	}

	/** Prunes the game tree below this node */
	protected void prune() {
		parent.getPotentialMoves().clear();
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Just getters and setters below here
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */

	public Node[][] getGameNodes() {
		return gameState.getNodes();
	}

	private GameState getGameState() {
		return gameState;
	}

	public int getNumChildren() {
		return numChildren;
	}

	public LinkedList<Node> getPotentialMoves() {
		return potentialMoves;
	}

	public int getCurrentPlayer() {
		return currentPlayer.getPlayerNum();
	}

	public ArrayList<TreeNode> getChildren() {
		return children;
	}

	public TreeNode getParent() {
		return parent;
	}

	public boolean isMaxNode() {
		return maxNode;
	}

	public int getDepth() {
		return depth;
	}

	public void setHypotheticalMove(Node hypothetical) {
		this.hypotheticalMoveAttribute = hypothetical;
	}

	public Node getHypotheticalMove() {
		return hypotheticalMoveAttribute;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public double getAlpha() {
		return alpha;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	private void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public boolean isTerminal() {
		return isTerminal;
	}

	public String toString() {
		String max = "";
		if (maxNode) {
			max = "MAX";
		} else {
			max = "MIN";
		}

		if (this.hypotheticalMoveAttribute == null) {
			return "~~~~~ROOT NODE~~~~~";
		} else {
			return (max + " " + this.hypotheticalMoveAttribute.toString()
					+ " at depth " + depth);
		}
	}

	public void printAB() {
		String alph = String.valueOf(alpha);
		String bet = String.valueOf(beta);

		if (alpha == Integer.MIN_VALUE) {
			alph = "-inf";
		}
		if (beta == Integer.MAX_VALUE) {
			bet = "inf";
		}

		System.out.println("    " + this.toString() + " Alpha: " + alph
				+ " Beta: " + bet);
	}

}
