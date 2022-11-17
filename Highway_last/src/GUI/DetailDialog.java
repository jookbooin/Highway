package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DetailDialog extends javax.swing.JDialog {
	String[] itemDetails;
	JLabel details[] = new JLabel[5];
	DetailDialog(String[] texts) {
		super(GUIMain.mainFrame);
		itemDetails = texts;
	}
	
	void setup() {
		setTitle("상품상세보기");
		JPanel pane = new JPanel(new BorderLayout());
		JPanel lpane = new JPanel(new GridLayout(3, 1));
		JLabel photo = new JLabel("   Photo   ");
		photo.setOpaque(true);  // JLabel은 기본이 배경 투명
		photo.setPreferredSize(new Dimension(150, 150));
		photo.setBackground(Color.YELLOW);
		details[0] = new JLabel("1: " + itemDetails[0]);
		details[1] = new JLabel("2: " + itemDetails[1]);
		details[2] = new JLabel("3: " + itemDetails[2]);
		lpane.add(details[0]);
		lpane.add(details[1]);
		lpane.add(details[2]);
		pane.add(lpane, BorderLayout.CENTER);
		pane.add(photo, BorderLayout.LINE_END);
		this.setMinimumSize(new Dimension(400, 150));  // 대화상자 크기 설정
		setContentPane(pane);
	}
	
}
