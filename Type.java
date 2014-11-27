package polartictactoe;

import java.util.LinkedList;

public class Type implements LogicalFunction {
	
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
	public boolean unify(LogicalFunction toBeUnified) {
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
	
	@Override
	public boolean equals(Object o){
		if (o.equals(null)){
			return false;
		}else if (!(o instanceof Type)){
			return false;
		} else if (!this.type.equals(((Type)o).type)){
			return false;
		} else if (!this.edgeName.equals(((Type)o).edgeName)){
			return false;
		} else if (!this.isTrue == ((Type)o).isTrue){
			return false;
		}
		return true;
	}

	@Override
	public LogicalFunction cloneFunction() {
			Type cloned = new Type(this.type, this.edgeName);
			if (this.isTrue == false){
				cloned.negate();
			}
			
			return cloned;
	}

}
