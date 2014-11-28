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
	/** Returns true only if the inputted string does not appear anywhere in this function */
	public boolean passesOccursCheck(String o) {
		if (type.equals(o)){
			return false;
		} else if (edgeName.equals(o)){
			return false;
		}
		return true;
	}

	@Override
	public boolean equals(Object o){
		if (o == null){
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
	public boolean equalsNegated(LogicalFunction f) {
		if (!(f instanceof Type)){
			return false;
		} else if (!this.type.equals(((Type)f).type)){
			return false;
		} else if (!this.edgeName.equals(((Type)f).edgeName)){
			return false;
		} else if (this.isTrue == ((Type)f).isTrue){
			// returns false if they have the same name and type, but both are true
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEdgeName() {
		return edgeName;
	}

	public void setEdgeName(String edgeName) {
		this.edgeName = edgeName;
	}

	public Boolean getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}

}
