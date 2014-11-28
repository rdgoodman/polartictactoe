package polartictactoe;

public class LogicalOperator implements LogicalFunction {

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
	public boolean unify(LogicalFunction toBeUnified) {
		// TODO Auto-generated method stub
		// should probably just remove these honestly
		return false;
	}

	@Override
	public String toString() {
		if (and) {
			return " AND ";
		} else {
			return " OR ";
		}
	}
	
	@Override
	public boolean equals(Object o){
		if (o.equals(null)){
			return false;
		} else if (!(o instanceof LogicalOperator)){
			return false;
		} else if (this.and != ((LogicalOperator)o).and){
			return false;
		}
		
		return true;
	}

	@Override
	public LogicalFunction cloneFunction() {
		return new LogicalOperator(this.and);
	}

	@Override
	public boolean equalsNegated(LogicalFunction f) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean passesOccursCheck(String o) {
		// TODO Auto-generated method stub
		return false;
	}

}
