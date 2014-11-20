package polartictactoe;

import java.util.ArrayList;

public class WinChecker {

	ArrayList<Axiom> KB;
	static Statement negatedGoal;

	/** Note: the negated goal is hard-coded in for this game */
	public WinChecker() {
		KB = new ArrayList<Axiom>();
		negatedGoal = new Statement();

		// builds axioms
		Axiom endpts1 = new Endpoints("n1", "n2", "e1");
		endpts1.negate();
		Axiom endpts2 = new Endpoints("n2", "n3", "e2");
		endpts2.negate();
		Axiom endpts3 = new Endpoints("n3", "n4", "e3");
		endpts3.negate();
		Axiom type1 = new Type("a", "e1");
		type1.negate();
		Axiom type2 = new Type("a", "e2");
		type2.negate();
		Axiom type3 = new Type("a", "e3");
		type3.negate();
		Axiom connector1 = new LogicalOperator(true);
		connector1.negate();
		Axiom connector2 = new LogicalOperator(true);
		connector2.negate();
		Axiom connector3 = new LogicalOperator(true);
		connector3.negate();
		Axiom connector4 = new LogicalOperator(true);
		connector4.negate();
		Axiom connector5 = new LogicalOperator(true);
		connector5.negate();

		// adds axioms to negated goal
		negatedGoal.add(endpts1);
		negatedGoal.add(connector1);
		negatedGoal.add(endpts2);
		negatedGoal.add(connector2);
		negatedGoal.add(endpts3);
		negatedGoal.add(connector3);
		negatedGoal.add(type1);
		negatedGoal.add(connector4);
		negatedGoal.add(type2);
		negatedGoal.add(connector5);
		negatedGoal.add(type3);

	}

	public void addToKnowledgeBase(Axiom a) {
		KB.add(a);
		System.out.println(a.getClass().toString());

		// resolves once there are three or more edges per player
		if (KB.size() >= 12) {
			 resolve();
		}
	}

	private void resolve() {
		while (!KB.isEmpty()) {
			boolean unifies = false;

			// empty clause -> win !
			if (negatedGoal.isEmpty()) {
				// TODO: will need to find which player has the win and pass
				// in
				reportWin();
			}

			// attempts to unify goal with every axiom in KB
			// TODO: how are you going to do the deletion along with the unification?
			for (Axiom a : KB) {
				
				// don't try to unify operators, obviously
				if (!(a instanceof LogicalOperator)) {
					negatedGoal.unify(a);
				}

			}

			// note: if we reach this code, no win found
			// if unification fails, break
			if (!unifies) {
				reportNoWin();
			}
		}

	}

	// TODO: reportWin() and NoWin are stubs still
	private int reportWin() {
		return -1;
	}

	private int reportNoWin() {
		return -5;
	}

	public Statement getNegatedGoal() {
		return negatedGoal;
	}

	public void printKB() {
		for (Axiom a : KB) {
			System.out.println(a.toString());
		}
	}
}
