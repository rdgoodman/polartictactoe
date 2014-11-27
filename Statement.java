package polartictactoe;

import java.util.LinkedList;

public class Statement {
	
	LinkedList<Axiom> statement;
	
	public Statement(){
		statement = new LinkedList<Axiom>();
	}
	
	public boolean unify(Axiom a, String substitution){
		if(a instanceof Endpoints){
			// 1. Pull out the information from the statement
			String start = ((Endpoints) a).getStartNodeName();
			String end = ((Endpoints) a).getEndNodeName();
			String edge = ((Endpoints)a).getEdgeName();
			for (Axiom s : statement){
				if(s instanceof Endpoints){
					// attempt to substitute
					
				}
			}
		} else if (a instanceof Type){
			// call a type resolver
		}
		return false;
		
		//TODO: REPLACE ALL INSTANCES OF VARIABLE IN STATEMENT!
	}
	
	public void add(Axiom a){
		statement.add(a);
	}
	
	public boolean isEmpty(){
		return statement.isEmpty();
	}

	public String toString(){
		String statementString = "";
		
		for (Axiom a : statement){
			statementString += a.toString();
		}
		
		return statementString;
	}
}
