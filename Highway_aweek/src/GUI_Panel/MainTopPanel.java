package GUI_Panel;

import java.awt.*;
import java.awt.event.*;

import javax.sql.rowset.spi.SyncResolver;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MainTopPanel extends JPanel {

	String[] start = { "����", "�뱸", "����","����" ,"���", "�λ�" };
	String[] arrive = { "����", "�뱸", "����","����" ,"���", "�λ�" };

	JComboBox<String> startC;
	JComboBox<String> arriveC;

//	String cstart;
//	String carrive;
	int startidx;
	int arriveidx;
	JLabel indent1 ;
	JLabel indent2 ;

	public MainTopPanel() {
//		

		startC = new JComboBox<>(start);
		startC.setPreferredSize(new Dimension(200, 30));
		
		indent1 = new JLabel("");
	
		

		arriveC = new JComboBox<>(arrive);
		arriveC.setPreferredSize(new Dimension(200, 30));

		JPanel buttonPanel = new JPanel(new BorderLayout());
		JButton search = new JButton("��ȸ");

		search.setPreferredSize(new Dimension(100, 30));
		buttonPanel.add(search, BorderLayout.CENTER);

		this.add(startC);
		this.add(arriveC);
		this.add(buttonPanel);

		startC.addActionListener(new ActionListener() { // �޺��ڽ� �̺�Ʈ-���� ����

			@Override
			public void actionPerformed(ActionEvent e) {
//				   if(e.getSource().equals("����"))
//					   cstart = (String)e.getSource();

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

		search.addActionListener(new ActionListener() { // �޺��ڽ� �̺�Ʈ-���� ����

			@Override
			public void actionPerformed(ActionEvent e) {
				if(startidx == arriveidx)
					System.out.println("���� �����Դϴ�.");
				else
					System.out.println("startidx: " + startidx + " arriveidx: " + arriveidx);

			}
		});

	}

}
