package polartictactoe;

import java.util.LinkedList;

public class Statement {
	
	LinkedList<Axiom> statement;
	
	public Statement(){
		statement = new LinkedList<Axiom>();
	}
	
	public boolean unify(Axiom a, String substitution){
		if(a instanceof Endpoints){
			// call an endpoint resolver
			// TODO: for all Endpoints in
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
