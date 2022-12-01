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
//	private BevelBorder bb = new BevelBorder(BevelBorder.LOWERED);  --> 버튼 눌리는 효과 나중에 볼것 
	Direction direc = HighWay.direcMgr.find("0", "5");
	Path path = direc.pathlist.get(0);
	List<RestArea> restlist = path.restlist;// 현재 휴게소 리스트
	List<String> restnamelist = new ArrayList<>();

	String[] restnameArr = null;// 콤보박스 들어가는 배열
	RestArea[] restArr = restlist.stream().toArray(RestArea[]::new);// 전체리스트 배열로 바꿔줌
	RestArea[] subArr = null; // sublist 배열

	int comboidx; // 콤보박스 인덱스
	int storeidx; // 콤보박스 -> 버튼 생성 인덱스

	List<RestArea> sublist;

	TestFrame() {

		this.setTitle("휴게소 리스트");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		for (RestArea ra : restlist)
			restnamelist.add(ra.restname);// 시작하자마자 들어온 리스트로 콤보박스 만들준비

		restnameArr = restnamelist.stream().toArray(String[]::new); // 콤보박스에 들어갈 String배열 생성

//container에 붙이는 top
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());

//container-top 패널에 콤보박스+버튼 넣는 패널
		JPanel toppanel = new JPanel();
		toppanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		// 콤보박스
		JComboBox<String> restcombo = new JComboBox<>(restnameArr);
		restcombo.setPreferredSize(new Dimension(100, 30));

		// 콤보박스 버튼
		RoundedButton search = new RoundedButton("조회");
		JButton bt;

//container-top 패널에 버튼이 표시되는 패널
		JPanel centerpanel = new JPanel();
		centerpanel.setLayout(new BorderLayout());

		restcombo.addActionListener(new ActionListener() { // 콤보박스 이벤트-서울 선택

			@Override
			public void actionPerformed(ActionEvent e) {

				JComboBox jcb = (JComboBox) e.getSource();
				comboidx = jcb.getSelectedIndex(); //
				System.out.println("storeidx:" + comboidx);
			}
		});

		search.addActionListener(new ActionListener() { // 조회 버튼 누르면 table을 초기화 해야함

			public void actionPerformed(ActionEvent e) {
				// 시작위치 설정
				storeidx = comboidx;
				System.out.println("startidx:" + storeidx);

				// 처음부터 사라짐
				centerpanel.removeAll();
				centerpanel.revalidate();
				centerpanel.repaint();

				ButtonTest btest = new ButtonTest(restlist, storeidx);
				// 버튼패널 가져와서 centerpanel 위에 붙임
				centerpanel.add(btest.makeJPanel(), BorderLayout.NORTH);
			}
		});

		toppanel.add(restcombo);
		toppanel.add(search);
		top.add(toppanel, BorderLayout.NORTH);
		top.add(centerpanel, BorderLayout.CENTER); // top 패널안에 toppanel(버튼)/ centerpanel(휴게소) 위치함

//center-> 

		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		
		JTabbedPane tab = new JTabbedPane();
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		tab.add("주유소", jp1); 
		tab.add("시설/음식", jp2); 
		
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
