package rolliepolartictactoe;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class PolarTTTPanel extends JPanel {
	
	private final int WIDTH = 700;
	private final int HEIGHT = 650;

	public PolarTTTPanel() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.WHITE);
	}

}
