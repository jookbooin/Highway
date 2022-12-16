
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
import GUI_Panel.ChangePanel;
import GUI_Panel.ImagePanel;
import GUI_Panel.MainCenterPanel;
import GUI_Panel.MainTopPanel;
import GUI_Panel.TextLabel;
import Map.Direction;
import Map.HighWay;
import Map.Path;
import Method.TableMethod;

//���� ������ 
@SuppressWarnings("serial")
public class GUIMain extends JFrame {
	ChangePanel cp = ChangePanel.getInstance(); 
	int startidx;
	int arriveidx;
	int tableidx;

	Direction direc;
	String imgidx; // img.jpg ǥ�� --> startidx + arriveidx + tableidx
	
	//�޺��ڽ�
	String colName[] = { "��� ����", "����ð�", "�Ÿ�", "�����" };
	String[] start = { "����", "�뱸", "����", "����", "���", "�λ�" };
	String[] arrive = { "����", "�뱸", "����", "����", "���", "�λ�" };

	void run() {
		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		JPanel top = new JPanel();
		top.setLayout(new BorderLayout()); // ������ ����� �־�� �ҵ�

		// �޺��ڽ� 2�� + ��ư ���̴� �г�
		JPanel toppanel = new JPanel();
		toppanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		JComboBox<String> startC = new JComboBox<>(start);
		startC.setPreferredSize(new Dimension(200, 30));

		JComboBox<String> arriveC = new JComboBox<>(arrive);
		arriveC.setPreferredSize(new Dimension(200, 30));

		RoundedButton search = new RoundedButton("��ȸ");
		search.setPreferredSize(new Dimension(50, 30));

		toppanel.add(startC);
		toppanel.add(arriveC);
		toppanel.add(search);

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

		// ���̺� ���̴� �г�
		JPanel tablepanel = new JPanel();
		// ���̺� Ʋ�� ����
		DefaultTableModel model = new DefaultTableModel(colName, 0);

		// ��ȸ ��ư ������ table�� �ʱ�ȭ �ؾ���
		search.addActionListener(new ActionListener() {

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

		
		// �̹��� ���̴� �г�
		JPanel imgoutpanel = new JPanel(); // �̹��� ���̴� �г�

//		// DefaultTableModel Ʋ ���� --> �迭�� table�� row ���·� �ٷ�� ��
		JTable table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		TableMethod.setJTableColumnsWidth(table, 700, 90, 40, 25, 30); // ���̺� ũ�� ����
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				tableidx = lsm.getMinSelectionIndex();

				System.out.println("(2) startidx: " + startidx + " arriveidx: " + arriveidx + " tableidx :" + tableidx);
				imgidx = "" + startidx + arriveidx + tableidx + "";
				System.out.println(imgidx);
				cp.updatePanel(imgoutpanel);
				ImagePanel imgpanel = new ImagePanel(new ImageIcon("./roadimage/" + imgidx + ".jpg").getImage());
				imgoutpanel.add(imgpanel);

			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

		tablepanel.add(scrollPane);
		top.add(toppanel, BorderLayout.NORTH);
		top.add(tablepanel, BorderLayout.CENTER);

		JPanel center = new JPanel();
		center.add(imgoutpanel);

		container.add(top, BorderLayout.NORTH);
		container.add(center, BorderLayout.CENTER);
		container.add(bottom(), BorderLayout.SOUTH);

		this.setSize(680, 680); // JFrameũ��
		this.setResizable(false); // â�� ũ�⸦ �������� ���ϰ�
		this.setLocationRelativeTo(null); // â�� ���
		this.setVisible(true); // â�� ���̰�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	GUIMain() {
		this.setTitle("Main");
	}

	JPanel bottom() {
		JPanel bottom = new JPanel();
		RoundedButton search2 = new RoundedButton("��� �˻� ");
		search2.setPreferredSize(new Dimension(200, 30));
		search2.addActionListener(new ActionListener() { // ��ȸ ��ư ������ table�� �ʱ�ȭ �ؾ���

			public void actionPerformed(ActionEvent e) {
				if (startidx != arriveidx) {
					if (tableidx >= 0) {
						new SecondFrame(startidx, arriveidx, tableidx);
						cp.updatePanel(TextLabel.pane);
					}
					else
						System.out.println("��θ� �����Ͻʽÿ�.");
				} else {
				}

			}
		});

		bottom.add(search2);
		return bottom;
	}
}
