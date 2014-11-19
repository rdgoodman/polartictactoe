package polartictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

public class WinCheckerTest {
	
	WinChecker testwc1 = new WinChecker();

	@Test
	public void testNegatedGoalCreation() {
		System.out.println("Negated goal: " );
		System.out.println(testwc1.getNegatedGoal().toString() + "\n");
	}

}
