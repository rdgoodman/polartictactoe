package polartictactoe;

import java.util.Scanner;

import javax.swing.JFrame;

public class PTTT {
	Game game;
	int circles;
	int lines;
	Player pa;
	Player pb;
	static PTTT thisGame;

	public static void main(String[] args) {

		thisGame = new PTTT();

	}

	public PTTT() {
		Scanner commandLineScanner = new Scanner(System.in);

		circles = 4;
		lines = 12;

		// step 2: get general types of players
		int input2 = 3;
		while ((input2 > 2) || (input2 < 0)) {
			System.out.println("> Please enter 0 if both players are humans,");
			System.out
					.println("> 1 if one player is human and the other is an AI,");
			System.out.println("> or 2 if both players are AIs.");
			input2 = commandLineScanner.nextInt();
		}

		String input3 = "";
		// step 3: get specific types of players
		if (input2 > 0) {
			System.out
					.println("> Please enter y if you want the AI player to use alpha-beta pruning");
			System.out.println("> Or n otherwise ");
			input3 = commandLineScanner.next();
		}

		// step 4: randomize player numbers
		double playerNum = Math.random();
		if (playerNum > .5) {
			playerNum = 1;
		} else {
			playerNum = 0;
		}

		// step 5: create players
		if (input2 == 0) {
			pa = new HumanPlayer((int) playerNum);
			pb = new HumanPlayer((int) (1 - playerNum));
		} else if (input2 == 1) {
			pa = new HumanPlayer((int) playerNum);
			if (input3 == "y") {
				pb = new AIPlayerMinimaxAB((int) (1 - playerNum));
			} else {
				pb = new AIPlayerMinimax((int) (1 - playerNum));
			}
		} else {
			if (input3 == "y") {
				pa = new AIPlayerMinimaxAB((int) playerNum);
				pb = new AIPlayerMinimaxAB((int) (1 - playerNum));
			} else {
				pa = new AIPlayerMinimax((int) playerNum);
				pb = new AIPlayerMinimax((int) (1 - playerNum));
			}
		}

		// step 6: get max search depth
		System.out
				.println("> Please enter the maximum search depth desired (>=4 recommended to avoid running out of heap space)");
		int input4 = 1;
		boolean correctInput = false;
		while (!correctInput) {
			input4 = commandLineScanner.nextInt();
			if (input4 > 0){
				correctInput = true;
			}
		}

		// TODO: normalize the pa, pb things
		if (pa.getPlayerNum() == 0) {
			game = new Game(circles, lines, pa, pb, input4);
		} else {
			game = new Game(circles, lines, pb, pa, input4);
		}

		commandLineScanner.close();
	}

}