package polartictactoe;

public interface LogicalFunction {
	
	void negate();
	// returns FALSE if substitution cannot be made
	boolean unify(LogicalFunction toBeUnified);
	boolean equalsNegated(LogicalFunction f);
	boolean passesOccursCheck(String o);
	String toString();
	LogicalFunction cloneFunction();
	

}
