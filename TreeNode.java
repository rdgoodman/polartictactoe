package polartictactoe;

import java.util.ArrayList;
import java.util.LinkedList;

public class TreeNode {

	Node[][] gameState;
	int numChildren;
	LinkedList<Node> potentialMoves;
	int currentPlayer;
	ArrayList<TreeNode> children = new ArrayList<TreeNode>();
	// TODO: make doubly linked (sort of already is)
	TreeNode parent;
	

	public TreeNode(Node[][] gameState, int currentPlayer, TreeNode parent) {
		this.gameState = new Node[gameState.length][gameState[0].length];
		this.currentPlayer = currentPlayer;
		potentialMoves = new LinkedList<Node>();

		// creates a copy of the game state passed in, without pointer issues
		// TODO: this would probably be a good place to count nodes evaluated (# treenodes?)
		for (int circles = 0; circles < gameState.length; circles++) {
			for (int lines = 0; lines < gameState[0].length; lines++) {
				Node entry = new Node(gameState[circles][lines].getX(),gameState[circles][lines].getY());
				entry.setPlayer(gameState[circles][lines].getPlayer());
				this.gameState[circles][lines] = entry;
			}
		}

		//countChildren();
		//createAllChildren();
	}

	/**
	 * Counts the number of child nodes this node will have, and adds the
	 * potential moves to represent to a Linked List
	 */
	private void countChildren() {
		// iterates through each node in the game
		for (int circles = 0; circles < gameState.length; circles++) {
			for (int lines = 0; lines < gameState[0].length; lines++) {
				// if a node is played by either player...
				if (gameState[circles][lines].getPlayer() != 0) {
					// it iterates through each of its nodes neighbors...
					for (Node i : gameState[circles][lines].getNeighbors()) {
						System.out.println("visited a neighbor");
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
	
	protected void createNextPly(){
		countChildren();
		createAllChildren();
		System.out.println("\nNumber of children: " + children.size());
	}
	
	/** Loops through all potential moves, calls child creation for each */
	private void createAllChildren(){
		for(Node i : potentialMoves){
			children.add(createChildNode(i));
		}
	}

	/**
	 * Creates a child node with the hypotheticalMove node played by the current
	 * player - also ensures that the player on the child node is the other
	 * player
	 */
	private TreeNode createChildNode(Node hypotheticalMove) {
		Node[][] childState = new Node[gameState.length][gameState[0].length];

		// creates a copy of the current game state
		for (int circles = 0; circles < gameState.length; circles++) {
			for (int lines = 0; lines < gameState[0].length; lines++) {
				childState[circles][lines] = gameState[circles][lines];
			}
		}
		
		childState[hypotheticalMove.getX()][hypotheticalMove.getY()].setPlayer(currentPlayer);
		int nextPlayer = 1 - currentPlayer;
		
		TreeNode childNode = new TreeNode(childState, nextPlayer, this);
		return childNode;
	}

	//TODO: make a method that will score each child node as it is created using heuristics

	public Node[][] getGameState() {
		return gameState;
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
	
	
	
}
