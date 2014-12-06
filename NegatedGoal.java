package rolliepolartictactoe;

import java.util.LinkedList;

public class NegatedGoal {
	
	LinkedList<EdgeAxiom> goal;
	
	/** Hard-codes in the negated goal */
	public NegatedGoal(){
		goal = new LinkedList<EdgeAxiom>();
		
		EdgeAxiom edge1 = new EdgeAxiom("n1", "n2", "a", "e1");
		edge1.negate();
		EdgeAxiom edge2 = new EdgeAxiom("n2", "n3", "a", "e2");
		edge2.negate();
		EdgeAxiom edge3 = new EdgeAxiom("n3", "n4", "a", "e3");
		edge3.negate();
		
		goal.add(edge1);
		goal.add(edge2);
		goal.add(edge3);
		
	}
	
	@Override
	public String toString(){
		String negatedGoal = "";
		for (EdgeAxiom e: goal){
			negatedGoal += e.toString() + " ";
		}
		return negatedGoal;
	}
	
	public LinkedList<EdgeAxiom> getGoal(){
		return goal;
	}

}
