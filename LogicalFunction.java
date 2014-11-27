package polartictactoe;

public interface LogicalFunction {
	
	void negate();
	// returns FALSE if substitution cannot be made
	boolean unify(LogicalFunction toBeUnified);
	String toString();
	LogicalFunction cloneFunction();

}
