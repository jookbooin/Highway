package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


//휴게소 정보를 restpanel에서 보여주기 위해서 만든 상세페이지 
public class DetailDialog extends javax.swing.JDialog {
	
	String[] itemDetails;
	JLabel details[] = new JLabel[10];

	DetailDialog(String[] texts) {
		super(GUIMain.mainFrame);
		itemDetails = texts;
	}

	void setup() {
		setTitle("휴게소 상세보기");
		JPanel pane = new JPanel(new BorderLayout());
		JPanel lpane = new JPanel(new GridLayout(3, 1));
		JLabel photo = new JLabel("   Photo   ");
		photo.setOpaque(true); // JLabel은 기본이 배경 투명
		photo.setPreferredSize(new Dimension(150, 300));
		photo.setBackground(Color.YELLOW);
		details[0] = new JLabel("노선 종류: " + itemDetails[0]);
		details[1] = new JLabel("휴게소 이름: " + itemDetails[1]);
		details[2] = new JLabel("휴게소 번호: " + itemDetails[2]);
		details[3] = new JLabel("편의시설: " + itemDetails[3]);
		details[4] = new JLabel("음식 메뉴: " + itemDetails[4]);
		details[5] = new JLabel("휘발유: " + itemDetails[5]);
		details[6] = new JLabel("경유: " + itemDetails[6]);
		details[7] = new JLabel("lpg: " + itemDetails[7]);
		details[8] = new JLabel("전기충전소: " + itemDetails[8]);
		details[9] = new JLabel("수소충전소: " + itemDetails[9]);
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

		pane.add(lpane, BorderLayout.CENTER);
		pane.add(photo, BorderLayout.LINE_END);
		this.setMinimumSize(new Dimension(400, 300)); // 대화상자 크기 설정
		setContentPane(pane);
	}

}
