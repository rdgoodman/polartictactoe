package polartictactoe;

import java.util.LinkedList;

public class Statement {
	
	LinkedList<Axiom> statement;
	
	public Statement(){
		statement = new LinkedList<Axiom>();
	}
	
	public void add(Axiom a){
		statement.add(a);
	}

}
