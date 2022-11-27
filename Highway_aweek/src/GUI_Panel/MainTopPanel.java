package GUI_Panel;

import java.awt.*;
import java.awt.event.*;

import javax.sql.rowset.spi.SyncResolver;
import javax.swing.*;
import javax.swing.event.*;

import Map.Direction;
import Map.HighWay;

import java.awt.*;

@SuppressWarnings("serial")
public class MainTopPanel extends JPanel {

	String[] start = { "����", "�뱸", "����", "����", "���", "�λ�" };
	String[] arrive = { "����", "�뱸", "����", "����", "���", "�λ�" };

	JComboBox<String> startC;
	JComboBox<String> arriveC;
	
	int startidx;
	int arriveidx;
	Direction direc;
	JButton search;
	JPanel buttonPanel;

	public MainTopPanel() {
//		

		startC = new JComboBox<>(start);
		startC.setPreferredSize(new Dimension(200, 30));

		arriveC = new JComboBox<>(arrive);
		arriveC.setPreferredSize(new Dimension(200, 30));

		buttonPanel = new JPanel(new BorderLayout());
		search = new JButton("��ȸ");

		search.setPreferredSize(new Dimension(100, 30));
		buttonPanel.add(search, BorderLayout.CENTER);

		this.add(startC);
		this.add(arriveC);
		this.add(buttonPanel);
		

		startC.addActionListener(new ActionListener() { // �޺��ڽ� �̺�Ʈ-���� ����

			@Override
			public void actionPerformed(ActionEvent e) {

				JComboBox jcb = (JComboBox) e.getSource();
				startidx = jcb.getSelectedIndex(); //

			}
		});

		arriveC.addActionListener(new ActionListener() { // �޺��ڽ� �̺�Ʈ-���� ����

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				arriveidx = jcb.getSelectedIndex();

			}
		});

	}
	
	public void setDirec(MainCenterPanel maincenterpanel) {
		
		search.addActionListener(new ActionListener() {				 //��ȸ ��ư ������ table�� �ʱ�ȭ �ؾ���
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (startidx == arriveidx)
					System.out.println("���� �����Դϴ�.");
				else {
					System.out.println("startidx: " + startidx + " arriveidx: " + arriveidx);
					
					direc = HighWay.direcMgr.find(""+startidx , ""+arriveidx); //-> Main �� �����ؾ���
					maincenterpanel.loadData(direc);  // ->�̺�Ʈ�� ����� maincenterpanel�� direc����???
				}

			}
		});

	}

}
