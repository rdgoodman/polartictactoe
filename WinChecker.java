package polartictactoe;

import java.util.ArrayList;

public class WinChecker {
	
	ArrayList<Axiom> KB;
	static Statement negatedGoal;
	
	/** Note: the negated goal is hard-coded in for this game */
	public WinChecker(){
		negatedGoal = new Statement();
		
		// builds negated goal with **empty names** - TODO: fix this!!!
		Axiom endpts1 = new Endpoints("", " ", " ");
		endpts1.negate();
		Axiom endpts2 = new Endpoints("", " ", " ");
		endpts2.negate();
		Axiom endpts3 = new Endpoints("", " ", " ");
		endpts3.negate();
		Axiom type1 = new Type("", " ");
		type1.negate();
		Axiom type2 = new Type("", " ");
		type2.negate();
		Axiom type3 = new Type("", " ");
		type3.negate();
		Axiom connector1 = new LogicalOperator(true);
		connector1.negate();
		Axiom connector2 = new LogicalOperator(true);
		connector2.negate();
		Axiom connector3 = new LogicalOperator(true);
		connector3.negate();
		
		
	}
	
	public void addToKnowledgeBase(Axiom a){
		// TODO: "a" should be created in the GameState class and passed to the winchecker
		KB.add(a);
	}

}
