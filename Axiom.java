package polartictactoe;

public interface Axiom {
	
	void negate();
	// returns FALSE if substitution cannot be made
	boolean unify(Axiom toBeUnified);
	String toString();

}
