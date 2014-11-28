package polartictactoe;

import java.util.ArrayList;
import java.util.LinkedList;

public class Axiom {
	
	LinkedList<LogicalFunction> statement;
	
	public Axiom(){
		statement = new LinkedList<LogicalFunction>();
	}
	
//	/** Helper function for unifying two variables - returns end result */
//	public ArrayList<Axiom> unify(Axiom a){
//		return null;
//	}
//	
//	private Axiom unify(Axiom a, String substitution){
//		if (substitution.equals("FAIL")){
//			// TODO: a NULL result means that the unification failed
//			return null;
//		} else if (this.equals(a)){
//			// TODO: return the resulting axiom - we may need to pass in copies for this?
//			// TODO: also, check if it is equal to the negated version!!!
//			System.out.println("Unification worked");
//		} else {
//			// recursively unify components
//			// TODO: this is the hard part
//			
//		}
//		
//		return null;
//	}
//	
//	private Axiom unifyVar(LogicalFunction f1, LogicalFunction f2, String substitution){
//		return null;
//	}
	
	public void add(LogicalFunction a){
		statement.add(a);
	}
	
	public boolean isEmpty(){
		return statement.isEmpty();
	}
	
	public boolean isEmptyClause(){
		return statement.isEmpty();
	}

	public String toString(){
		String statementString = "";
		
		for (LogicalFunction a : statement){
			statementString += a.toString();
		}
		
		return statementString;
	}
	
	public Axiom cloneAxiom(){
		Axiom cloned = new Axiom();
		for (LogicalFunction f : this.statement){
			cloned.add(f.cloneFunction());
		}
		
		return cloned;
	}
	
	@Override
	public boolean equals(Object o){
		if (o == null){
			return false;
		} else if (!(o instanceof Axiom)){
			return false;
		} else {
			for (LogicalFunction f : this.statement){
				if (!((Axiom)o).statement.contains(f)){
					return false;
				}
			}
			for (LogicalFunction f : ((Axiom)o).statement){
				if (!this.statement.contains(f)){
					return false;
				}
			}
		}
		
		return true;
	}

}
