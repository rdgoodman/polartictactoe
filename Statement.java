package polartictactoe;

import java.util.LinkedList;

public class Statement {
	
	LinkedList<Axiom> statement;
	
	public Statement(){
		statement = new LinkedList<Axiom>();
	}
	
	public void unify(Axiom a){
		// TODO: stub
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
