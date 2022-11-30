
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import GUI_Custom.RoundedButton;
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

	int startidx;
	int arriveidx;
	int tableidx;

	Direction direc;
	JPanel outpanel = new JPanel(); ////////////////////////// ���� �ٱ� �г�
	
	JPanel toppanel = new JPanel(new BorderLayout());///////// '��ȸ'��ư + table ���̴� �ٱ� �г�
	JPanel combopanel = new JPanel(); //////////////////////// toppanel�� ��ư ���̴� �г�
	
	JPanel bottompanel = new JPanel(new BorderLayout());  // �̹����� ��ư�� �ٿ��� out�� �ִ� �г�
	
	JPanel imgoutpanel = new JPanel();///////////////////////// �̹����г� ���̴� �г�
	
	JPanel ImageButton = new JPanel(new BorderLayout());
	JPanel btnspanel = new JPanel();////////////////////////// ��ΰ˻� '��ư �ִ� �г�
	
	
	void run() {
		outTopPane();
		outbottomPane();

		this.add(outpanel);
		this.setSize(800, 700); // JFrameũ��
		this.setResizable(false); // â�� ũ�⸦ �������� ���ϰ�
		this.setLocationRelativeTo(null); // â�� ���
		this.setVisible(true); // â�� ���̰�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	GUIMain() {
		this.setTitle("Main");
	}
	// ./image/053.jpg;

	void outTopPane() {
		// ��ư ���̴� �г�

		String colName[] = { "��� ����", "����ð�", "�Ÿ�", "�����" };
		String[] start = { "����", "�뱸", "����", "����", "���", "�λ�" };
		String[] arrive = { "����", "�뱸", "����", "����", "���", "�λ�" };

		

		JComboBox<String> startC = new JComboBox<>(start);
		startC.setPreferredSize(new Dimension(200, 30));

		JComboBox<String> arriveC = new JComboBox<>(arrive);
		arriveC.setPreferredSize(new Dimension(200, 30));

		//
		JPanel btnpanel = new JPanel(new BorderLayout());
//		JButton search = new JButton("��ȸ");
		RoundedButton search = new RoundedButton("��ȸ");
		btnpanel.add(search, BorderLayout.CENTER);

		// ����
		combopanel.add(startC);
		combopanel.add(arriveC);
		combopanel.add(btnpanel);

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
					System.out.println(
							"(1) startidx: " + startidx + " arriveidx: " + arriveidx + " tableidx :" + tableidx);

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
		table.setPreferredScrollableViewportSize(new Dimension(500, 80));
		TableMethod.setJTableColumnsWidth(table, 700, 100, 30, 30, 30); // ���̺� ũ�� ����

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				tableidx = lsm.getMinSelectionIndex();

				System.out.println("(2) startidx: " + startidx + " arriveidx: " + arriveidx + " tableidx :" + tableidx);

			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 10));

		toppanel.add(combopanel, BorderLayout.NORTH); // combobox
		toppanel.add(scrollPane, BorderLayout.CENTER); // ����Ʈ ��������


		outpanel.add(toppanel);
	}
	
	void outbottomPane() {
		imagePane();
		bottombtnPane();
		
		outpanel.add(bottompanel);
		
	}

	void imagePane() {

		ImagePanel imgpanel = new ImagePanel(new ImageIcon("./image/051.jpg").getImage());
		imgoutpanel.add(imgpanel);
		// ���� �̹����� imgoutpane ���� ����

		bottompanel.add(imgoutpanel, BorderLayout.CENTER);

	}

	void bottombtnPane() {
		
		JButton search2 = new JButton("��� �˻� ");
		search2.setPreferredSize(new Dimension(200, 30));
		search2.addActionListener(new ActionListener() { // ��ȸ ��ư ������ table�� �ʱ�ȭ �ؾ���

			public void actionPerformed(ActionEvent e) {
				if (tableidx >= 0)
					new SecondFrame(startidx, arriveidx, tableidx);
				else
					System.out.println("��θ� �����Ͻʽÿ�.");

			}
		});

		btnspanel.add(search2); // ���� ��ư�� btnpanel2�� ����

		bottompanel.add(btnspanel,BorderLayout.SOUTH);

	}
}
