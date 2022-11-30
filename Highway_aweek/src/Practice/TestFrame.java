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
	private BevelBorder bb = new BevelBorder(BevelBorder.LOWERED);
	Direction direc = HighWay.direcMgr.find("0", "5");
	Path path = direc.pathlist.get(0);
	List<RestArea> rlist = path.restlist;// ���� �ްԼ� ����Ʈ
	List<String> restnamelist = new ArrayList<>();

	RestArea[] restArea = rlist.stream().toArray(RestArea[]::new);// ����Ʈ �迭�� �ٲ���
	String[] restnamearray = null;

	int storeidx ;
	int startidx ;

	List<RestArea> sublist;

	TestFrame() {
		this.setTitle("������ ��ġ ����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		for (RestArea ra : rlist) 
			restnamelist.add(ra.restname);// �������ڸ��� ���� ����Ʈ�� �޺��ڽ� �����غ� 
			
		restnamearray = restnamelist.stream().toArray(String[]::new);  //�޺��ڽ��� �� String�迭 ����
		

		JPanel toppanel = new JPanel(); 						// �޺��ڽ�
		toppanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		JComboBox<String> restcombo = new JComboBox<>(restnamearray);
		restcombo.setPreferredSize(new Dimension(100, 30));
		restcombo.addActionListener(new ActionListener() { // �޺��ڽ� �̺�Ʈ-���� ����

			@Override
			public void actionPerformed(ActionEvent e) {

				JComboBox jcb = (JComboBox) e.getSource();
				storeidx = jcb.getSelectedIndex(); //
				System.out.println("storeidx:"+ storeidx);

			}
		});
		
		
		RoundedButton search = new RoundedButton("��ȸ");
		search.addActionListener(new ActionListener() { // ��ȸ ��ư ������ table�� �ʱ�ȭ �ؾ���

			public void actionPerformed(ActionEvent e) {
				startidx = storeidx;
				System.out.println("startidx:"+startidx);
								}
			});
		
		toppanel.add(restcombo);
		toppanel.add(search);

		
		
//		toppanel.setLayout(null);	
		JPanel centerpanel = new JPanel();
//		center.setLayout(null);			//�� �гξȿ��� �ڵ���ġ�� �ƴ� �����ġ�� ����ϱ� ���ؼ� 
		centerpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50)); // �߾Ӻ��� �迭�ɼ� �ֵ��� ���
		
		for (int i = 0; i < restArea.length; i++) {

			JButton bt = new JButton(restArea[i].restname, new ImageIcon("./GUIimage/circle.jpg"));
			bt.setVerticalTextPosition(JButton.BOTTOM);
			bt.setHorizontalTextPosition(JButton.CENTER);

//			b.setBorderPainted(false);
			bt.setContentAreaFilled(false);
			bt.setFocusPainted(false);
//			b.setOpaque(false);

			bt.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					System.out.println(bt.getText());
					int idx = rlist.indexOf(new RestArea(bt.getText()));
					System.out.println(idx);
					sublist = rlist.stream().skip(idx).collect(Collectors.toList()); // sublist ����
					for (RestArea ra : sublist)
						System.out.println(ra);

				
				}
			});

			centerpanel.add(bt);
		}

		JPanel bottompanel = new JPanel();
		bottompanel.setLayout(null);
		JLabel la = new JLabel("Hello, Press Buttons!");
		la.setLocation(130, 50);
		la.setSize(200, 20);
		bottompanel.add(la);

		c.add(toppanel,BorderLayout.NORTH); // north
		c.add(centerpanel,BorderLayout.CENTER); // center
		c.add(bottompanel,BorderLayout.SOUTH); // south

//			JPanel outpanel = new JPanel(new BorderLayout(100 ,100));
//			Container container = getContentPane();
//
//	        container.setLayout(new FlowLayout(FlowLayout.CENTER,100,80));
//
//	        container.add(new JButton("add"))
//	        container.add(new JButton("sub"));
//
//	        container.add(new JButton("mul"));
//
//	        container.add(new JButton("div"));
//	        
//	        container.add(new JButton("add1"));
//
//	        container.add(new JButton("sub2"));
//
//	        container.add(new JButton("mul3"));
//
//	        container.add(new JButton("div4"));
//	        outpanel.add(container);
//	        this.add(outpanel);

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
