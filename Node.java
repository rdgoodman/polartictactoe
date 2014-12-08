package polartictactoe;

import java.util.LinkedList;

public class Node {

	LinkedList<Node> neighbors;
	int player;
	int x;
	int y;
	int xlocOnBoard;
	int ylocOnBoard;

	/**
	 * 
	 * @param x the circle this Node is on
	 * @param y the line this Node is on
	 */
	public Node(int x, int y) {
		// node initialized to "open/no player"
		player = -1;
		this.x = x;
		this.y = y;
		// starts out with empty list of neighbors
		neighbors = new LinkedList<Node>();

	}

	/** Adds Node n to this node's list of neighbors */
	public void addNeighbor(Node n) {
		neighbors.add(n);
	}

	/** Returns all neighbors for this node */
	public LinkedList<Node> getNeighbors() {
		return neighbors;
	}

	/** Sets the color of a Node */
	public void setPlayer(int player) {
		this.player = player;
	}

	@Override
	public String toString() {
		String playerNumber = " ";
		if (player == 0){
			playerNumber = "X";
		} else if (player == 1){
			playerNumber = "O";
		}
		return ("(" + x + "," + y + "; " + playerNumber + ")");
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (this.getClass() != o.getClass()) {
			return false;
		} else if ((this.x != ((Node) o).getX())
				|| (this.y != ((Node) o).getY())
				|| (this.player != ((Node) o).getPlayer())) {
			return false;
		}

		return true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPlayer() {
		return player;
	}

	/** Makes a copy of this node (in terms of location and player) but does not link it to its neighbors*/
	public Node cloneNodeButNotNeighbors() {
		Node nodeClone = new Node(x, y);
		nodeClone.setPlayer(player);
		
		return nodeClone;
	}
	
	public void setxlocOnBoard(int xLocation){
		this.xlocOnBoard = xLocation;
	}
	
	public int getxlocOnBoard(){
		return xlocOnBoard;
	}
	
	public void setylocOnBoard(int yLocation){
		this.ylocOnBoard = yLocation;
	}
	
	public int getylocOnBoard(){
		return ylocOnBoard;
	}
}
