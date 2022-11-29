package GUI_Panel;

import java.awt.*;
import java.awt.event.*;

import javax.sql.rowset.spi.SyncResolver;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import Map.Direction;
import Map.HighWay;
import Map.Path;
import Method.TableMethod;

import java.awt.*;

@SuppressWarnings("serial")
public class MainTopPanel extends JPanel {
	int startidx;
	int arriveidx;
	Direction direc;
	
	void setTopPanel(JFrame frame) {
		frame.setTitle("Main");
		// ��ư ���̴� �г�
		String colName[] = { "��� ����", "����ð�", "�Ÿ�", "�����" };
		String[] start = { "����", "�뱸", "����", "����", "���", "�λ�" };
		String[] arrive = { "����", "�뱸", "����", "����", "���", "�λ�" };

		JPanel outpanel = new JPanel(new BorderLayout()); // ���� �ٱ� �г�
		JPanel commbopane = new JPanel(); // ��ư ���̴� �г�

		JComboBox<String> startC = new JComboBox<>(start);
		startC.setPreferredSize(new Dimension(200, 30));

		JComboBox<String> arriveC = new JComboBox<>(arrive);
		arriveC.setPreferredSize(new Dimension(200, 30));

		//
		JPanel buttonPanel = new JPanel(new BorderLayout());
		JButton search = new JButton("��ȸ");
		buttonPanel.add(search, BorderLayout.CENTER);

		// ����
		commbopane.add(startC);
		commbopane.add(arriveC);
		commbopane.add(buttonPanel);

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

		// ���̺� Ʋ�� ����
		DefaultTableModel model = new DefaultTableModel(colName, 0);

		search.addActionListener(new ActionListener() { // ��ȸ ��ư ������ table�� �ʱ�ȭ �ؾ���

			public void actionPerformed(ActionEvent e) {

				if (startidx == arriveidx)
					System.out.println("���� �����Դϴ�.");
				else {
					// �ε��� Ȯ��
//					System.out.println("startidx: " + startidx + " arriveidx: " + arriveidx);

					direc = HighWay.direcMgr.find("" + startidx, "" + arriveidx); // -> Main �� �����ؾ���
					// �Ź� ������ ������ ���� ����
					model.setRowCount(0);
					for (Path p : direc.pathlist)
						model.addRow(p.getUiTexts());
				}

			}
		});

		// DefaultTableModel Ʋ ���� --> �迭�� table�� row ���·� �ٷ�� ��
		JTable table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		TableMethod.setJTableColumnsWidth(table, 700, 100, 50, 50, 50); // ���̺� ũ�� ����
		JScrollPane scrollPane = new JScrollPane(table);

		outpanel.add(commbopane, BorderLayout.NORTH); // combobox
		outpanel.add(scrollPane, BorderLayout.CENTER); // ����Ʈ ��������
		frame.add(outpanel);
		
		frame.setSize(600, 800); // JFrameũ��
		frame.setResizable(false); // â�� ũ�⸦ �������� ���ϰ�
		frame.setLocationRelativeTo(null); // â�� ���
		frame.setVisible(true); // â�� ���̰�
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		return outpanel;
	}
	
}
