package GUI_Panel;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextLabel extends JPanel {
	String[] itemDetails;
	JLabel details[] = new JLabel[10];

	public TextLabel(String[] texts) {// rextarea의 getUiTexts()
		itemDetails = texts;
	}
	

	// 라벨 실제 붙이는 패널

	public static JPanel pane = new JPanel(new BorderLayout());
	
	public JPanel textfield() {

		
		JPanel lpane = new JPanel(new GridLayout(10, 1));

		details[0] = new JLabel("고속도로: " + itemDetails[0]);
		details[1] = new JLabel("휴게소이름: " + itemDetails[1]);
		details[2] = new JLabel("번호: " + itemDetails[2]);
		details[3] = new JLabel("휴게/시설: " + itemDetails[3]);
		details[4] = new JLabel("음식: " + itemDetails[4]);
		details[5] = new JLabel("휘발유: " + itemDetails[5]);
		details[6] = new JLabel("경유: " + itemDetails[6]);
		details[7] = new JLabel("lpg: " + itemDetails[7]);
		details[8] = new JLabel("전기 충전소: " + itemDetails[8]);
		details[9] = new JLabel("수소 충전소: " + itemDetails[9]);
		lpane.add(details[0]);
		lpane.add(details[1]);
		lpane.add(details[2]);
		lpane.add(details[3]);
		lpane.add(details[4]);
		lpane.add(details[5]);
		lpane.add(details[6]);
		lpane.add(details[7]);
		lpane.add(details[8]);
		lpane.add(details[9]);
		lpane.setBackground(Color.WHITE);
		pane.add(lpane, BorderLayout.CENTER);
		// 대화상자 크기 설정
//		setContentPane(pane);
		return pane;
	}

}
