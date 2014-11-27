package polartictactoe;

import java.util.ArrayList;

public class WinChecker {

	// separate KB for each player's edges
	ArrayList<Axiom> p1KB;
	ArrayList<Axiom> p2KB;
	// todo: do we need to duplicate the goal?
	Axiom negatedGoal;

	/** Note: the negated goal is hard-coded in for this game */
	public WinChecker() {
		p1KB = new ArrayList<Axiom>();
		p2KB = new ArrayList<Axiom>();
		negatedGoal = new Axiom();

		// builds axioms
		LogicalFunction endpts1 = new Endpoints("n1", "n2", "e1");
		endpts1.negate();
		LogicalFunction endpts2 = new Endpoints("n2", "n3", "e2");
		endpts2.negate();
		LogicalFunction endpts3 = new Endpoints("n3", "n4", "e3");
		endpts3.negate();
		LogicalFunction type1 = new Type("a", "e1");
		type1.negate();
		LogicalFunction type2 = new Type("a", "e2");
		type2.negate();
		LogicalFunction type3 = new Type("a", "e3");
		type3.negate();
//		LogicalFunction connector1 = new LogicalOperator(true);
//		connector1.negate();
//		LogicalFunction connector2 = new LogicalOperator(true);
//		connector2.negate();
//		LogicalFunction connector3 = new LogicalOperator(true);
//		connector3.negate();
//		LogicalFunction connector4 = new LogicalOperator(true);
//		connector4.negate();
//		LogicalFunction connector5 = new LogicalOperator(true);
//		connector5.negate();

		// adds axioms to negated goal
		negatedGoal.add(endpts1);
		//negatedGoal.add(connector1);
		negatedGoal.add(endpts2);
		//negatedGoal.add(connector2);
		negatedGoal.add(endpts3);
		//negatedGoal.add(connector3);
		negatedGoal.add(type1);
		//negatedGoal.add(connector4);
		negatedGoal.add(type2);
		//negatedGoal.add(connector5);
		negatedGoal.add(type3);

		// adds negated goal to each player's knowledge base
		addGoalToKnowledgeBases();
	}

	public void addToP1KnowledgeBase(LogicalFunction a) {
		// Transforms a into a Statement, then adds it to the KB
		Axiom newAxiom = new Axiom();
		newAxiom.add(a);
		p1KB.add(newAxiom);

		// resolves once there are three or more edges per player
		if (p1KB.size() >= 7) {
			 resolve(p1KB);
		}
	}

	public void addToP2KnowledgeBase(LogicalFunction a) {
		// Transforms a into a Statement, then adds it to the KB
		Axiom newAxiom = new Axiom();
		newAxiom.add(a);
		p2KB.add(newAxiom);

		// resolves once there are three or more edges per player
		if (p2KB.size() >= 7) {
			 resolve(p2KB);
		}
	}

	private void addGoalToKnowledgeBases() {
		p1KB.add(negatedGoal);
		p2KB.add(negatedGoal);

	}

	// TODO: need to specify whose win we're checking via which KB is passed in
	private void resolve(ArrayList<Axiom> KB) {
		
		// makes a new version of the knowledge base that can be modified without changing the original
		ArrayList<Axiom> clauses = new ArrayList<Axiom>();
		for (Axiom a : KB) {
			clauses.add(a.cloneAxiom());
		}
		
		System.out.println("\n*****Cloned version: *****");
		if (KB.equals(p1KB)){
			printp1KB();
		} else {
			printp2KB();
		}
		
		boolean unified = false;
		
		while(true){
			int counter = 0;
			for (Axiom c : clauses){
				for (Axiom a : clauses){
					if (!a.equals(c)){
						System.out.println(counter + ": resolve these");
						ArrayList<Axiom> newAxioms = c.unify(a);
						// TODO: if empty clause, break and report a win
						// if something unifies, set unified to true
						// TODO: unify should probably return a new clause, actually
						counter++;
					}
				}
			}
			// TODO: if unified is still false here, report no win
			// add new stuff to clauses
			break;
		}
	}

	// TODO: reportWin() and NoWin are stubs still
	private int reportWin() {
		return -1;
	}

	private int reportNoWin() {
		return -5;
	}

	public Axiom getNegatedGoal() {
		return negatedGoal;
	}

	public void printp1KB() {
		System.out.println("\nPlayer 1's KB:");
		for (Axiom a : p1KB) {
			System.out.println(a.toString());
		}
	}

	public void printp2KB() {
		System.out.println("\nPlayer 2's KB:");
		for (Axiom a : p2KB) {
			System.out.println(a.toString());
		}
	}
}
