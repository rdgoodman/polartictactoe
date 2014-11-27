package polartictactoe;

import java.util.LinkedList;

public class Type implements Axiom {
	
	String type;
	String edgeName;
	Boolean isTrue = true;

	
	public Type(String type, String name){
		this.type = type;
		this.edgeName = name;
	}

	@Override
	public void negate() {
		isTrue = !isTrue;

	}

	@Override
	public boolean unify(Axiom toBeUnified) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString(){
		String not = " ";
		if(!isTrue){
			not = "~";
		}
		
		return not + "Type(" + type + ", " + edgeName+")";
	}

}
