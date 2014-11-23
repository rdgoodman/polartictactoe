package polartictactoe;

import java.util.ArrayList;
import java.util.LinkedList;

public class TreeNode {

	GameState gameState;
	boolean maxNode;
	// note: these refer to whose turn it is in the game,
	// NOT whose move generated the current ply
	int currentPlayer;
	int nextPlayer;
	// used to generate children - 1:1 relationship
	LinkedList<Node> potentialMoves;
	int numChildren;
	ArrayList<TreeNode> children = new ArrayList<TreeNode>();
	// doubly linked
	TreeNode parent;
	// comes from heuristic
	double value;
	double alpha;
	double beta;
	// TODO: may not need depth if we can get a traversal right
	int depth;
	// TODO: this is just for testing
	Node hypotheticalMoveAttribute;
	

	/** For root */
	public TreeNode(Node[][] currentState, int currentPlayer, int nextPlayer,
			TreeNode parent, int depth) {
		gameState = new GameState(currentState);
		this.currentPlayer = currentPlayer;
		this.nextPlayer = nextPlayer;
		potentialMoves = new LinkedList<Node>();
		this.parent = parent;
		this.depth = depth;

		// root is always a max node
		maxNode = true;

	}

	/** For rest of game nodes - takes a game state rather than an array */
	public TreeNode(GameState parentState, int currentPlayer, int nextPlayer,
			boolean maxNode, TreeNode parent, int depth) {
		gameState = parentState;
		this.currentPlayer = currentPlayer;
		this.nextPlayer = nextPlayer;
		potentialMoves = new LinkedList<Node>();
		this.maxNode = maxNode;
		this.parent = parent;
		this.depth = depth;
	}

	
	/** TODO: will need to win-check */
	
	
	/**
	 * Counts the number of child nodes this node will have, and adds the
	 * potential moves to represent to a Linked List
	 */
	protected void countChildren() {
		// iterates through each node in the game
		for (int circles = 0; circles < gameState.getNumX(); circles++) {
			for (int lines = 0; lines < gameState.getNumY(); lines++) {
				// if a node is played by either player...
				if (gameState.getNodes()[circles][lines].getPlayer() != 0) {
					// it iterates through each of its nodes neighbors...
					for (Node i : gameState.getNodes()[circles][lines]
							.getNeighbors()) {
						// and adds them to the list of potential moves
						// if they are unplayed and not already present
						if ((!potentialMoves.contains(i))
								&& (i.getPlayer() == 0)) {
							potentialMoves.add(i);
							// increments the number of child nodes
							numChildren++;
						}
					}
				}
			}
		}
	}

	/** Calls countChildren() and createAllChildren() */
	protected void createNextBranch() {
		countChildren();
		createAllChildren();
	}

	
	/** Evaluates children using heuristic */
	protected void evaluateChildren(){
		for (TreeNode i : children){
			System.out.println("\nEvaluated " + i.toString());
			i.evaluate();
		}
	}

	/** Loops through all potential moves, calls child creation for each */
	private void createAllChildren() {
		for (Node i : potentialMoves) {
			children.add(createChildNode(i));
		}
	}

	/**
	 * Creates a child node with the hypotheticalMove node played by the current
	 * player - also ensures that the player on the child node is the other
	 * player
	 */
	private TreeNode createChildNode(Node hypotheticalMove) {
		// chooses player for next ply - if this is a max node, the next
		// ply will be generated using the opponent's moves and vice versa
		int player;
		if (maxNode) {
			player = currentPlayer;
		} else {
			player = nextPlayer;
		}

		// creates a child node with game state identical to the current game
		// state
		// except with one move made
		GameState childState = new GameState(gameState.getNodes());
		childState.getNodes()[hypotheticalMove.getX()][hypotheticalMove.getY()]
				.setPlayer(player);
		TreeNode childNode = new TreeNode(childState, currentPlayer,
				nextPlayer, !maxNode, this, this.depth + 1);

		// TODO: testing, remove
		String max = "";
		if (childNode.isMaxNode()) {
			max = "MAX";
		} else {
			max = "MIN";
		}
		System.out
				.println("\nChild State: "
						+ max
						+ " "
						+ childState.getNodes()[hypotheticalMove.getX()][hypotheticalMove
								.getY()].toString());
		childNode.setHypotheticalMove(hypotheticalMove);

		return childNode;
	}

	/** TODO: MUST USE HEURISTIC*/
	public void evaluate(){
		value = Math.random();
		System.out.println("Set value of this node to " + value);
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

	public Node[][] getGameState() {
		return gameState.getNodes();
	}

	public int getNumChildren() {
		return numChildren;
	}

	public LinkedList<Node> getPotentialMoves() {
		return potentialMoves;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
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
	
	//TODO: again, just testing
	public void setHypotheticalMove(Node hypothetical){
		this.hypotheticalMoveAttribute = hypothetical;
	}
	
	public String toString(){
		String max = "";
		if (maxNode) {
			max = "MAX";
		} else {
			max = "MIN";
		}
		return (maxNode + this.hypotheticalMoveAttribute.toString() + " at depth " + depth);
	}

}
