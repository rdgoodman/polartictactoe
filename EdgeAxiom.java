package rolliepolartictactoe;

import java.util.LinkedList;

public class EdgeAxiom {
	String startNodeName;
	String endNodeName;
	String type;
	String edgeName;

	Boolean isTrue = true;

	public EdgeAxiom(String startNodeName, String endNodeName, String type,
			String edgeName) {
		this.startNodeName = startNodeName;
		this.endNodeName = endNodeName;
		this.type = type;
		this.edgeName = edgeName;
	}

	public void negate() {
		isTrue = !isTrue;

	}

	/**
	 * 
	 * @param e
	 *            is an axiom that is part of the negated goal
	 * @return true if the statements can be unified
	 */
	public boolean canBeUnified(EdgeAxiom e) {
		if ((!e.startNodeName.contains((CharSequence) "n") && (!e.startNodeName
				.equals(this.startNodeName)))) {
			// startNodeNames are both constants and do not match
			return false;
		} else if ((!e.endNodeName.contains((CharSequence) "n") && (!e.endNodeName
				.equals(this.endNodeName)))) {
			// endNodeNames are both constants and do not match
			return false;
		} else if ((!e.type.contains((CharSequence) "a") && (!e.type
				.equals(this.type)))) {
			// types are both constants and do not match
			return false;
		} else if ((!e.edgeName.contains((CharSequence) "e") && (!e.edgeName
				.equals(this.edgeName)))) {
			return false;
		}
		return true;
	}

	/** Helper function for unification */
	public void unify(LinkedList<EdgeAxiom> LL){
		//System.out.println("Unifying " + LL.toString() + " and " + this.toString());

		for (EdgeAxiom e: LL){
			// finds the first place where substitutions can be made and then calls unifier
			if (this.canBeUnified(e)){
				unifyAxioms(e, LL);
				break;
			}
		}
	}

	/** Actually performs the unification */
	public void unifyAxioms(EdgeAxiom e, LinkedList<EdgeAxiom> LL){
		// holders for forward substitution
		String goalStart = "";
		String goalEnd = "";
		String goalType = "";
		String goalName = "";
		
		if ((e.startNodeName.contains((CharSequence) "n"))) {
			// the start node name is a variable - sub in
			goalStart = e.startNodeName;
			e.startNodeName = this.startNodeName;
		}
		if ((e.endNodeName.contains((CharSequence) "n"))) {
			// the end node name is a variable - sub in
			goalEnd = e.endNodeName;
			e.endNodeName = this.endNodeName;
		} 
		if ((e.type.contains((CharSequence) "a"))) {
			// the start node name is a variable - sub in
			goalType = e.type;
			e.type = this.type;
		} 
		if ((e.edgeName.contains((CharSequence) "e"))) {
			// the start node name is a variable - sub in
			goalName = e.edgeName;
			e.edgeName = this.edgeName;
		}
		
		
		//carry substitutions forward (and back)
		for (EdgeAxiom currentGoalAxiom : LL){
			if (currentGoalAxiom.startNodeName.equals(goalEnd)){
				currentGoalAxiom.startNodeName = e.endNodeName;
			}
			if (currentGoalAxiom.endNodeName.equals(goalStart)){
				currentGoalAxiom.endNodeName = e.startNodeName;
			}
			if (currentGoalAxiom.startNodeName.equals(goalStart)){
				currentGoalAxiom.startNodeName = e.startNodeName;
			}
			if (currentGoalAxiom.endNodeName.equals(goalEnd)){
				currentGoalAxiom.endNodeName = e.endNodeName;
			}
			if (currentGoalAxiom.type.equals(goalType)){
				currentGoalAxiom.type = e.type;
			}
			if (currentGoalAxiom.edgeName.equals(goalName)){
				currentGoalAxiom.edgeName = e.edgeName;
			}
		}
		
		LL.remove(e);
		
		// TODO: this is where I am changing things.
		if ((LL.size()) == 1 && (LL.getFirst().isAllConstants())){
			unify(LL);
		}
		
	}

	public boolean isAllConstants() {
		if ((startNodeName.contains((CharSequence) "n"))) {
			// startNodeNames are both constants and do not match
			return false;
		} else if ((endNodeName.contains((CharSequence) "n")))  {
			// endNodeNames are both constants and do not match
			return false;
		} else if ((type.contains((CharSequence) "a"))) {
			// types are both constants and do not match
			return false;
		} else if ((edgeName.contains((CharSequence) "e"))) {
			return false;
		}
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (!(o instanceof EdgeAxiom)) {
			return false;
		} else if (!this.startNodeName.equals(((EdgeAxiom) o).startNodeName)) {
			return false;
		} else if (!this.endNodeName.equals(((EdgeAxiom) o).endNodeName)) {
			return false;
		} else if (!this.type.equals(((EdgeAxiom) o).type)) {
			return false;
		} else if (!this.edgeName.equals(((EdgeAxiom) o).edgeName)) {
			return false;
		} else if (!this.isTrue == ((EdgeAxiom) o).isTrue) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String not = " ";
		if (!isTrue) {
			not = "~";
		}

		return not + "Edge(" + startNodeName + ", " + endNodeName + ", " + type
				+ ", " + edgeName + ")";
	}

}
