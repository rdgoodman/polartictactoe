package polartictactoe;

import java.util.LinkedList;

public class WinChecker {

	// separate KB for each player's edges
	LinkedList<EdgeAxiom> p1KB;
	LinkedList<EdgeAxiom> p2KB;
	// this is a separate object so that new versions can quickly and easily be made
	NegatedGoal goal;
	// these will change if a win is found
	boolean winForPlayer1 = false;
	boolean winForPlayer2 = false;
	GameState state;

	/** Note: the negated goal is hard-coded in for this game */
	public WinChecker(GameState state) {
		p1KB = new LinkedList<EdgeAxiom>();
		p2KB = new LinkedList<EdgeAxiom>();
		goal = new NegatedGoal();
		this.state = state;
	}

	/** Adds this EdgeAxiom to player 1's KB */
	public void addToP1KnowledgeBase(EdgeAxiom a) {
		p1KB.add(a);

		// resolves once there are three or more edges per player
		if (p1KB.size() >= 3) {
			resolve(p1KB);
		}
	}

	/** Adds this EdgeAxiom to player 2's KB */
	public void addToP2KnowledgeBase(EdgeAxiom a) {;
		p2KB.add(a);

		// resolves once there are three or more edges per player
		if (p2KB.size() >= 3) {
			resolve(p2KB);
		}
	}


	/** Checks for a win, but only for the player whose KB is passed in as an argument */
	private void resolve(LinkedList<EdgeAxiom> KB) {
		int counter = 0;
		
		// tries to resolve once with each arrangement of the KB
		while (counter < KB.size()){
			// starts with a new negated goal, since those from previous loops will be modified
			goal = new NegatedGoal();
			
			// attempts to unify each axiom with the negated goal
			for (EdgeAxiom e: KB){
				e.unify(goal.getGoal());
				
				// returns a win if the unification makes the goal an empty clause
				if(goal.getGoal().isEmpty()){
					reportWin(KB);
					return;
				}
			}
			
			// increments counter
			counter++;
			//takes the first axiom in the knowledge base and moves it to the end
			rotateKB(KB);
		}
		
		// if we reach this code, no win was found
		reportNoWin(KB);

	}


	/** If a win was found, reports which player it belongs to */
	private int reportWin(LinkedList<EdgeAxiom> KB) {
		state.setToHaveWin();
		
		int player = 1;
		if (KB.equals(p2KB)){
			player = 2;
		}
		
		// testing 
		if (player == 1){
			winForPlayer1 = true;
		} else {
			winForPlayer2 = true;
		}
		
		//System.out.println("\n\n**********WIN FOR PLAYER " + player + "**********\n\n");
		return player;
	}

	/** Returns -1 if no win was found */
	private int reportNoWin(LinkedList<EdgeAxiom> KB) {
		return -1;
	}

	/** Removes the first element and adds it to the end */
	public void rotateKB(LinkedList<EdgeAxiom> KB){
		EdgeAxiom oldStart = KB.remove(0);
		KB.add(oldStart);
		
	}

	public NegatedGoal getNegatedGoal() {
		return goal;
	}

	public void printp1KB() {
		System.out.println("\nPlayer 1's KB:");
		for (EdgeAxiom a : p1KB) {
			System.out.println(a.toString());
		}
	}

	public void printp2KB() {
		System.out.println("\nPlayer 2's KB:");
		for (EdgeAxiom a : p2KB) {
			System.out.println(a.toString());
		}
	}
	
	public LinkedList<EdgeAxiom> getP1KB() {
		return p1KB;
	}

	public void setP1KB(LinkedList<EdgeAxiom> p1kb) {
		p1KB = p1kb;
	}

	public LinkedList<EdgeAxiom> getP2KB() {
		return p2KB;
	}

	public void setP2KB(LinkedList<EdgeAxiom> p2kb) {
		p2KB = p2kb;
	}

	public NegatedGoal getGoal() {
		return goal;
	}

	public void setGoal(NegatedGoal goal) {
		this.goal = goal;
	}
	
	public boolean getWinForPlayer1(){
		return winForPlayer1;
	}
	
	public boolean getWinForPlayer2(){
		return winForPlayer2;
	}
}
