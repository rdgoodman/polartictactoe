package polartictactoe;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class WinCheckerTest {

	WinChecker testwc1 = new WinChecker();

	@Test
	public void testProblemResolution(){
		HumanPlayer pX = new HumanPlayer(0);
		HumanPlayer pO = new HumanPlayer(1);
		
		Game game = new Game(3, 12, pX, pO, 2);
		
	}
	
}
