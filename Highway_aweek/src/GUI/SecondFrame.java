package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import GUI_Custom.RoundedButton;
import GUI_Panel.ChangePanel;
import GUI_Panel.TextLabel;
import Map.Direction;
import Map.HighWay;
import Map.Path;
import Map.RestArea;

public class SecondFrame extends JFrame {
	
	ChangePanel cp = ChangePanel.getInstance(); 
	int startidx;
	int arriveidx;
	int tableidx;
	int comboidx; // �޺��ڽ� �ε���
	int storeidx; // �޺��ڽ� -> ��ư ���� �ε���

	// restlist��
	int gasolinIdx; // gasoline min �ε���
	int dieselIdx; // diesel min �ε���
	int lpgIdx; // lpg min �ε���

	List<RestArea> restlist;
	List<String> restnamelist = new ArrayList<>();
	RestArea[] restArr;
	String[] restnameArr = null;
	JPanel cbuttonpanel;
	RestButton restbutton;
	public static JPanel textpanel = new JPanel(new BorderLayout());

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
		this.getContentPane().setBackground(new Color(255, 255, 255));
		container.setLayout(new BorderLayout());

		container.add(secondTop(), BorderLayout.CENTER); // north
		container.add(secondCenter(), BorderLayout.SOUTH); // south

		this.setSize(1350, 520);
		this.setVisible(true);
	}

//secondFrame top �г�
	JPanel secondTop() {
		// container�� ���̴� top
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());

		// containe��r-top �гο� �޺��ڽ�+��ư �ִ� �г�
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
		cbuttonpanel = new JPanel();
		cbuttonpanel.setLayout(new BorderLayout());

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

				cp.updatePanel(cbuttonpanel);
				cp.updatePanel(TextLabel.pane);
				restbutton = new RestButton(restlist, storeidx);
				
				// ��ư�г� �����ͼ� centerpanel ���� ����
				cbuttonpanel.add(restbutton.makeJPanel(), BorderLayout.NORTH);
				System.out.println("MIN gosolineIdx:" + restbutton.findIdx(restbutton.findMin(1)));
				System.out.println("MIN dieIdx:" + restbutton.findIdx(restbutton.findMin(2)));
				System.out.println("MIN lpgIdx:" + restbutton.findIdx(restbutton.findMin(3)));
				System.out.println();

				gasolinIdx = restbutton.findIdx(restbutton.findMin(1));
				dieselIdx = restbutton.findIdx(restbutton.findMin(2));
				lpgIdx = restbutton.findIdx(restbutton.findMin(3));
			}
		});

		toppanel.add(curlo);
		toppanel.add(restcombo);
		toppanel.add(search);
		top.add(toppanel, BorderLayout.NORTH);
		top.add(cbuttonpanel, BorderLayout.CENTER); // top �гξȿ� toppanel(��ư)/ centerpanel(�ްԼ�) ��ġ��
		return top;
	}

	JPanel secondCenter() {
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());

		JTabbedPane tab = new JTabbedPane();

		JPanel searchpanel = new JPanel();
		searchpanel.setLayout(new GridLayout(1, 3));
		tab.add("�˻�", searchpanel); // search �гξȿ��� �۾��� �̷������ �� .

		JPanel leftpanel = new JPanel();
		leftpanel.setLayout(new BorderLayout());
		// �󺧵��̿��ؼ�
		
//		JScrollPane scrollPane = new JScrollPane(textpanel);
		leftpanel.add(textpanel,BorderLayout.CENTER);
		

		JPanel rightpanel = new JPanel();
		rightpanel.setLayout(new GridLayout(2, 1));

		JPanel rtoppanel = new JPanel();
		rtoppanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel textlabel = new JLabel("�ü�/����");
		JTextField tf = new JTextField(30);
		RoundedButton tfsbt = new RoundedButton("�˻�");
		rtoppanel.add(textlabel);
		rtoppanel.add(tf);
		rtoppanel.add(tfsbt);

		JPanel rbotpanel = new JPanel();
		rbotpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
		RoundedButton gasbt = new RoundedButton("�ֹ���");
		RoundedButton diebt = new RoundedButton("����");
		RoundedButton lpgbt = new RoundedButton("LPG");
		rbotpanel.add(gasbt);
		rbotpanel.add(diebt);
		rbotpanel.add(lpgbt);

//�ֹ��� , ���� , lpg �ּڰ���ư ã�� ���ؼ�
//gasolineidx , dieselidx , lpgidx
//��ư ������ ���� centerpanel���� ��ư ���� ���� 
		
		gasbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ó������ �����
				cp.updatePanel(cbuttonpanel);
				restbutton = new RestButton(restlist, storeidx);
				// ��ư�г� �����ͼ� centerpanel ���� ����
				cbuttonpanel.add(restbutton.makeJPanel(), BorderLayout.NORTH);
				restbutton.btArr[gasolinIdx].setBackground(new Color(255, 255, 0));
			}
		});
		gasbt.setPreferredSize(new Dimension(80, 40));

		diebt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				cp.updatePanel(cbuttonpanel);
				restbutton = new RestButton(restlist, storeidx);
				// ��ư�г� �����ͼ� centerpanel ���� ����
				cbuttonpanel.add(restbutton.makeJPanel(), BorderLayout.NORTH);

				// ��ư�г� �����ͼ� centerpanel ���� ����
				cbuttonpanel.add(restbutton.makeJPanel(), BorderLayout.NORTH);
				restbutton.btArr[dieselIdx].setBackground(new Color(0, 255, 100));
			}
		});
		diebt.setPreferredSize(new Dimension(80, 40));

		lpgbt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// ó������ �����
				cp.updatePanel(cbuttonpanel);
				restbutton = new RestButton(restlist, storeidx);
				// ��ư�г� �����ͼ� centerpanel ���� ����
				cbuttonpanel.add(restbutton.makeJPanel(), BorderLayout.NORTH);
				restbutton.btArr[lpgIdx].setBackground(new Color(150, 150, 150));
			}
		});
		lpgbt.setPreferredSize(new Dimension(80, 40));

		rightpanel.add(rtoppanel);
		rightpanel.add(rbotpanel);

		searchpanel.add(leftpanel);
		searchpanel.add(rightpanel);

		center.add(tab, BorderLayout.CENTER);
		return center;
	}

}
