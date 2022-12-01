package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import GUI_Custom.RoundedButton;
import Map.Direction;
import Map.HighWay;
import Map.Path;
import Map.RestArea;

public class SecondFrame extends JFrame {

	int startidx;
	int arriveidx;
	int tableidx;
	int comboidx; // �޺��ڽ� �ε���
	int storeidx; // �޺��ڽ� -> ��ư ���� �ε���

	List<RestArea> restlist;
	List<String> restnamelist = new ArrayList<>();
	RestArea[] restArr;
	String[] restnameArr = null;

	SecondFrame(int startidx, int arriveidx, int tableidx) {
		this.startidx = startidx;
		this.arriveidx = arriveidx;
		this.tableidx = tableidx;
		System.out.println("(3) startidx: " + startidx + " arriveidx: " + arriveidx + " tableidx :" + tableidx);

		Direction direc = HighWay.direcMgr.find("" + startidx, "" + arriveidx);
		Path path = direc.pathlist.get(tableidx);
		restlist = path.restlist;

		restArr = restlist.stream().toArray(RestArea[]::new);// ��ü����Ʈ �迭�� �ٲ���

		for (RestArea ra : restlist)
			restnamelist.add(ra.restname);// �������ڸ��� ���� ����Ʈ�� �޺��ڽ� �����غ�
		restnameArr = restnamelist.stream().toArray(String[]::new); // �޺��ڽ��� �� String�迭 ����

		this.setTitle("��ΰ˻� ");

		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		// container�� ���̴� top
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());

		// container-top �гο� �޺��ڽ�+��ư �ִ� �г�
		JPanel toppanel = new JPanel();
		toppanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		// �޺��ڽ�
		JComboBox<String> restcombo = new JComboBox<>(restnameArr);
		restcombo.setPreferredSize(new Dimension(100, 30));

		// �޺��ڽ� ��ư
		RoundedButton search = new RoundedButton("��ȸ");

		// container-top �гο� ��ư�� ǥ�õǴ� �г�
		JPanel centerpanel = new JPanel();
		centerpanel.setLayout(new BorderLayout());

		restcombo.addActionListener(new ActionListener() { // �޺��ڽ� �̺�Ʈ-���� ����

			@Override
			public void actionPerformed(ActionEvent e) {

				JComboBox jcb = (JComboBox) e.getSource();
				comboidx = jcb.getSelectedIndex(); //
				System.out.println("storeidx:" + comboidx);
			}
		});

		search.addActionListener(new ActionListener() { // ��ȸ ��ư ������ table�� �ʱ�ȭ �ؾ���

			public void actionPerformed(ActionEvent e) {
				// ������ġ ����
				storeidx = comboidx;
				System.out.println("startidx:" + storeidx);

				// ó������ �����
				centerpanel.removeAll();
				centerpanel.revalidate();
				centerpanel.repaint();

				RestButton btest = new RestButton(restlist, storeidx);
				// ��ư�г� �����ͼ� centerpanel ���� ����
				centerpanel.add(btest.makeJPanel(), BorderLayout.NORTH);
			}
		});

		toppanel.add(restcombo);
		toppanel.add(search);
		top.add(toppanel, BorderLayout.NORTH);
		top.add(centerpanel, BorderLayout.CENTER); // top �гξȿ� toppanel(��ư)/ centerpanel(�ްԼ�) ��ġ��

		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());

		JTabbedPane tab = new JTabbedPane();
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		tab.add("������", jp1);
		tab.add("�ü�/����", jp2);

		center.add(tab, BorderLayout.CENTER);

		container.add(top, BorderLayout.NORTH); // north
		container.add(center, BorderLayout.CENTER); // south

		this.setSize(1000, 500);
		this.setVisible(true);
	}

}
