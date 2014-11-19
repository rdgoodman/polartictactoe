package polartictactoe;

public class LogicalOperator implements Axiom {

	Boolean and;

	public LogicalOperator(boolean and) {
		this.and = and;
	}

	@Override
	public void negate() {
		// "not and" is considered an or (note: this is not true in actual
		// logic, obviously, unless it's an XOR)
		and = !and;

	}

	@Override
	public boolean substitute() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		if (and) {
			return " AND ";
		} else {
			return "OR";
		}
	}

}
