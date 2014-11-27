package polartictactoe;

public class Endpoints implements Axiom {
	
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

}
