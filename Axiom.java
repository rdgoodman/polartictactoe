package polartictactoe;

import java.util.LinkedList;

public class Axiom {
	
	LinkedList<LogicalFunction> statement;
	
	public Axiom(){
		statement = new LinkedList<LogicalFunction>();
	}
	
	public boolean unify(LogicalFunction a, String substitution){
//		if(a instanceof Endpoints){
//			// 1. Pull out the information from the statement
//			String start = ((Endpoints) a).getStartNodeName();
//			String end = ((Endpoints) a).getEndNodeName();
//			String edge = ((Endpoints)a).getEdgeName();
//			for (LogicalFunction s : statement){
//				if(s instanceof Endpoints){
//					// attempt to substitute
//					
//				}
//			}
//		} else if (a instanceof Type){
//			// call a type resolver
//		}
		return false;
		
		//TODO: REPLACE ALL INSTANCES OF VARIABLE IN STATEMENT!
	}
	
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
}
