package polartictactoe;

public class Endpoints implements LogicalFunction {
	
	String startNodeName;
	String endNodeName;
	String edgeName;
	Boolean isTrue = true;
	

	public Endpoints(String startNodeName, String endNodeName, String edgeName) {
		this.startNodeName = startNodeName;
		this.endNodeName = endNodeName;
		this.edgeName = edgeName;
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
		
		return not + "Endpoints(" + startNodeName + ", " + endNodeName + ", " + edgeName+")";
	}

	public String getStartNodeName() {
		return startNodeName;
	}

	public void setStartNodeName(String startNodeName) {
		this.startNodeName = startNodeName;
	}

	public String getEndNodeName() {
		return endNodeName;
	}

	public void setEndNodeName(String endNodeName) {
		this.endNodeName = endNodeName;
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
	
	@Override
	public boolean equals(Object o){
		if (o.equals(null)){
			return false;
		}else if (!(o instanceof Endpoints)){
			return false;
		} else if (!this.startNodeName.equals(((Endpoints)o).startNodeName)){
			return false;
		} else if (!this.endNodeName.equals(((Endpoints)o).endNodeName)){
			return false;
		} else if (!this.edgeName.equals(((Endpoints)o).edgeName)){
			return false;
		} else if (!this.isTrue == ((Endpoints)o).isTrue){
			return false;
		}
		return true;
	}

	@Override
	public LogicalFunction cloneFunction() {
		Endpoints cloned = new Endpoints(this.startNodeName, this.endNodeName, this.edgeName);
		if (this.isTrue == false){
			cloned.negate();
		}
		
		return cloned;
	}

}
