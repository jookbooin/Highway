package GUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CurLoPanel extends JPanel {
	
	
	 public CurLoPanel() {
		JPanel searchPane = new JPanel(new BorderLayout());
		JButton detail = new JButton("°Ë»ö");
		searchPane.add(detail, BorderLayout.CENTER);
		
	 }
	
	 
}
