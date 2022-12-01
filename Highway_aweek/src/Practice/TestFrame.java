package Practice;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.*;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import GUI_Custom.RoundedButton;
import Map.Direction;
import Map.HighWay;
import Map.Path;
import Map.RestArea;

public class TestFrame extends JFrame {

//	
	String[] start;
//	private BevelBorder bb = new BevelBorder(BevelBorder.LOWERED);  --> ��ư ������ ȿ�� ���߿� ���� 
	Direction direc = HighWay.direcMgr.find("0", "5");
	Path path = direc.pathlist.get(0);
	List<RestArea> restlist = path.restlist;// ���� �ްԼ� ����Ʈ
	List<String> restnamelist = new ArrayList<>();

	String[] restnameArr = null;// �޺��ڽ� ���� �迭
	RestArea[] restArr = restlist.stream().toArray(RestArea[]::new);// ��ü����Ʈ �迭�� �ٲ���
	RestArea[] subArr = null; // sublist �迭

	int comboidx; // �޺��ڽ� �ε���
	int storeidx; // �޺��ڽ� -> ��ư ���� �ε���

	List<RestArea> sublist;

	TestFrame() {

		this.setTitle("�ްԼ� ����Ʈ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		for (RestArea ra : restlist)
			restnamelist.add(ra.restname);// �������ڸ��� ���� ����Ʈ�� �޺��ڽ� �����غ�

		restnameArr = restnamelist.stream().toArray(String[]::new); // �޺��ڽ��� �� String�迭 ����

//container�� ���̴� top
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());

//container-top �гο� �޺��ڽ�+��ư �ִ� �г�
		JPanel toppanel = new JPanel();
		toppanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		// �޺��ڽ�
		JComboBox<String> restcombo = new JComboBox<>(restnameArr);
		restcombo.setPreferredSize(new Dimension(100, 30));

		// �޺��ڽ� ��ư
		RoundedButton search = new RoundedButton("��ȸ");
		JButton bt;

//container-top �гο� ��ư�� ǥ�õǴ� �г�
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

				ButtonTest btest = new ButtonTest(restlist, storeidx);
				// ��ư�г� �����ͼ� centerpanel ���� ����
				centerpanel.add(btest.makeJPanel(), BorderLayout.NORTH);
			}
		});

		toppanel.add(restcombo);
		toppanel.add(search);
		top.add(toppanel, BorderLayout.NORTH);
		top.add(centerpanel, BorderLayout.CENTER); // top �гξȿ� toppanel(��ư)/ centerpanel(�ްԼ�) ��ġ��

//center-> 

		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		
		JTabbedPane tab = new JTabbedPane();
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		tab.add("������", jp1); 
		tab.add("�ü�/����", jp2); 
		
//		JLabel la = new JLabel("Hello, Press Buttons!");
//		la.setLocation(130, 50);
//		la.setSize(200, 20);
//		center.add(la);
		center.add(tab,BorderLayout.CENTER);
		
		container.add(top, BorderLayout.NORTH);		 // north
		container.add(center, BorderLayout.CENTER); // south

		this.setSize(1000, 500);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HighWay highway = HighWay.getInstance();
		highway.run();
		new TestFrame();
	}

}
