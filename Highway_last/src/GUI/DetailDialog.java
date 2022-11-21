package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


//�ްԼ� ������ restpanel���� �����ֱ� ���ؼ� ���� �������� 
public class DetailDialog extends javax.swing.JDialog {
	
	String[] itemDetails;
	JLabel details[] = new JLabel[10];

	DetailDialog(String[] texts) {
		super(GUIMain.mainFrame);
		itemDetails = texts;
	}

	void setup() {
		setTitle("�ްԼ� �󼼺���");
		JPanel pane = new JPanel(new BorderLayout());
		JPanel lpane = new JPanel(new GridLayout(3, 1));
		JLabel photo = new JLabel("   Photo   ");
		photo.setOpaque(true); // JLabel�� �⺻�� ��� ����
		photo.setPreferredSize(new Dimension(150, 300));
		photo.setBackground(Color.YELLOW);
		details[0] = new JLabel("�뼱 ����: " + itemDetails[0]);
		details[1] = new JLabel("�ްԼ� �̸�: " + itemDetails[1]);
		details[2] = new JLabel("�ްԼ� ��ȣ: " + itemDetails[2]);
		details[3] = new JLabel("���ǽü�: " + itemDetails[3]);
		details[4] = new JLabel("���� �޴�: " + itemDetails[4]);
		details[5] = new JLabel("�ֹ���: " + itemDetails[5]);
		details[6] = new JLabel("����: " + itemDetails[6]);
		details[7] = new JLabel("lpg: " + itemDetails[7]);
		details[8] = new JLabel("����������: " + itemDetails[8]);
		details[9] = new JLabel("����������: " + itemDetails[9]);
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
		this.setMinimumSize(new Dimension(400, 300)); // ��ȭ���� ũ�� ����
		setContentPane(pane);
	}

}
