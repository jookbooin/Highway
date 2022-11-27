package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import GUI_Panel.ImagePanel;
import GUI_Panel.MainCenterPanel;
import GUI_Panel.MainTopPanel;
import Map.Direction;
import Map.HighWay;
import Map.Path;
import Method.TableMethod;

//���� ������ 
@SuppressWarnings("serial")
public class GUIMain extends JFrame {

//	MainCenterPanel maincenterpanel = new MainCenterPanel();
//	MainTopPanel maintoppanel = new MainTopPanel();	

	String[] start = { "����", "�뱸", "����", "����", "���", "�λ�" };
	String[] arrive = { "����", "�뱸", "����", "����", "���", "�λ�" };
	JComboBox<String> startC;
	JComboBox<String> arriveC;

	int startidx;
	int arriveidx;
	Direction direc;

	GUIMain() {
		//ǥ���̸� 
		this.setTitle("��� ����");

		// combopan
		JPanel commbopane = new JPanel();
		JComboBox<String> startC = new JComboBox<>(start);
		startC.setPreferredSize(new Dimension(200, 30));

		JComboBox<String> arriveC = new JComboBox<>(arrive);
		arriveC.setPreferredSize(new Dimension(200, 30));

		JPanel buttonPanel = new JPanel(new BorderLayout());
		JButton search = new JButton("��ȸ");

		search.setPreferredSize(new Dimension(100, 30));
		buttonPanel.add(search, BorderLayout.CENTER);

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

		// scrollPane
		// �߾�
		String colName[] = { "��� ����", "����ð�", "�Ÿ�", "�����" };
		DefaultTableModel model = new DefaultTableModel(colName, 0);

		// maintoppanel ��ȸ ��ư�� ���ؼ� direction �ε��� ����
		// �ε��� ���ؼ� path���� ��� ã�Ƽ� table�� ǥ��

		search.addActionListener(new ActionListener() { // ��ȸ ��ư ������ table�� �ʱ�ȭ �ؾ���

			public void actionPerformed(ActionEvent e) {

				if (startidx == arriveidx)
					System.out.println("���� �����Դϴ�.");
				else {
					// �ε��� Ȯ��
					System.out.println("startidx: " + startidx + " arriveidx: " + arriveidx);

					direc = HighWay.direcMgr.find("" + startidx, "" + arriveidx); // -> Main �� �����ؾ���

					model.setRowCount(0);
					for (Path p : direc.pathlist)
						model.addRow(p.getUiTexts());
				}

			}
		});

		JTable table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		TableMethod.setJTableColumnsWidth(table, 700, 100, 50, 50, 50);
		JScrollPane scrollPane = new JScrollPane(table);

		JPanel outpanel = new JPanel(new BorderLayout());

//		maintoppanel.setDirec(maincenterpanel);
		outpanel.add(commbopane, BorderLayout.NORTH); // combobox
		outpanel.add(scrollPane, BorderLayout.CENTER); // ����Ʈ ��������

		this.add(outpanel);
		this.setSize(600, 800); // JFrameũ��
		this.setResizable(false); // â�� ũ�⸦ �������� ���ϰ�
		this.setLocationRelativeTo(null); // â�� ���
		this.setVisible(true); // â�� ���̰�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

//	ImagePanel imagepanel = new ImagePanel(new ImageIcon("./image/main.jpg").getImage());
//	setPreferredSize(new Dimension(500,400));
//	add(imagepanel ,BorderLayout.CENTER);
//	pack();

}
