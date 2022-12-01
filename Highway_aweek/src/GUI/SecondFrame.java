package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.util.ArrayList;
import java.util.List;

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
	
	//restlist��
	int gasolinIdx;	//gasoline min �ε��� 
	int dieselIdx;  //diesel min �ε���
	int lpgIdx;     //lpg min �ε���

	List<RestArea> restlist;
	List<String> restnamelist = new ArrayList<>();
	RestArea[] restArr;
	String[] restnameArr = null;
	
	RestButton btest;
	

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

//Frame-> containerȭ
		
		Container container = getContentPane();
		 this.getContentPane().setBackground(new Color(255,255,255));
		container.setLayout(new BorderLayout());

		container.add(secondTop(), BorderLayout.CENTER); // north
		container.add(secondCenter(), BorderLayout.SOUTH); // south

		this.setSize(1350, 500);
		this.setVisible(true);
	}

//secondFrame top �г�
	JPanel secondTop() {
		// container�� ���̴� top
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());

		// container-top �гο� �޺��ڽ�+��ư �ִ� �г�
		JPanel toppanel = new JPanel();
		toppanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		JLabel curlo = new JLabel("������ġ");
		curlo.setOpaque(true); // �������� ������ �����Ͽ� ������ ���̰� �Ѵ�

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

				btest = new RestButton(restlist, storeidx);
				
				// ��ư�г� �����ͼ� centerpanel ���� ����
				centerpanel.add(btest.makeJPanel(), BorderLayout.NORTH);
				System.out.println("MIN gosolineIdx:"+btest.findIdx(btest.findMin(1)));
				System.out.println("MIN dieIdx:"+btest.findIdx(btest.findMin(2)));
				System.out.println("MIN lpgIdx:"+btest.findIdx(btest.findMin(3)));
				
				gasolinIdx = btest.findIdx(btest.findMin(1));
				dieselIdx = btest.findIdx(btest.findMin(2));
				lpgIdx = btest.findIdx(btest.findMin(3));
			}
		});

		toppanel.add(curlo);
		toppanel.add(restcombo);
		toppanel.add(search);
		top.add(toppanel, BorderLayout.NORTH);
		top.add(centerpanel, BorderLayout.CENTER); // top �гξȿ� toppanel(��ư)/ centerpanel(�ްԼ�) ��ġ��
		return top;
	}

	JPanel secondCenter() {
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());

		JTabbedPane tab = new JTabbedPane();

		JPanel searchpanel = new JPanel();

		tab.add("�˻�", searchpanel); // search �гξȿ��� �۾��� �̷������ �� .

		JPanel leftpanel = new JPanel();
		// �󺧵��̿��ؼ� �ްԼ� �����ִ°��ε�?

		JPanel rightpanel = new JPanel();
		rightpanel.setLayout(new GridLayout(2, 1));

		JPanel righttop = new JPanel();
		righttop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel curlo = new JLabel("�ü�/����");
		JTextField tf = new JTextField(20);
		RoundedButton tfsbt = new RoundedButton("�˻�");
		righttop.add(curlo);
		righttop.add(tf);
		righttop.add(tfsbt);

		JPanel rightbottom = new JPanel();
		rightbottom.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
		RoundedButton gasbt = new RoundedButton("�ֹ���");
		RoundedButton diebt = new RoundedButton("����");
		RoundedButton lpgbt = new RoundedButton("LPG");
		rightbottom.add(gasbt);
		rightbottom.add(diebt);
		rightbottom.add(lpgbt);
		
		
		
		
//�ֹ��� , ���� , lpg �ּڰ� ��ư 
//gasolineidx , dieselidx , lpgidx
		gasbt.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				btest.btArr[gasolinIdx].setBackground(new Color(255,255,00));
			}
		});

		diebt.addActionListener(new ActionListener() { 

			public void actionPerformed(ActionEvent e) {
				btest.btArr[dieselIdx].setBackground(new Color(0,255,100));
			}
		});

		lpgbt.addActionListener(new ActionListener() { 

			public void actionPerformed(ActionEvent e) {
				btest.btArr[lpgIdx].setBackground(new Color(150,150,150));
			}
		});

		rightpanel.add(righttop);
		rightpanel.add(rightbottom);

		searchpanel.add(leftpanel, BorderLayout.CENTER);
		searchpanel.add(rightpanel, BorderLayout.LINE_END);

		center.add(tab, BorderLayout.CENTER);
		return center;
	}

}
