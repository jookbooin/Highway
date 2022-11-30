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
	List<RestArea> rlist = path.restlist;// 현재 휴게소 리스트
	List<String> restnamelist = new ArrayList<>();

	RestArea[] restArea = rlist.stream().toArray(RestArea[]::new);// 리스트 배열로 바꿔줌
	String[] restnamearray = null;

	int storeidx ;
	int startidx ;

	List<RestArea> sublist;

	TestFrame() {
		this.setTitle("절대적 위치 설정");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		for (RestArea ra : rlist) 
			restnamelist.add(ra.restname);// 시작하자마자 들어온 리스트로 콤보박스 만들준비 
			
		restnamearray = restnamelist.stream().toArray(String[]::new);  //콤보박스에 들어갈 String배열 생성
		

		JPanel toppanel = new JPanel(); 						// 콤보박스
		toppanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		JComboBox<String> restcombo = new JComboBox<>(restnamearray);
		restcombo.setPreferredSize(new Dimension(100, 30));
		restcombo.addActionListener(new ActionListener() { // 콤보박스 이벤트-서울 선택

			@Override
			public void actionPerformed(ActionEvent e) {

				JComboBox jcb = (JComboBox) e.getSource();
				storeidx = jcb.getSelectedIndex(); //
				System.out.println("storeidx:"+ storeidx);

			}
		});
		
		
		RoundedButton search = new RoundedButton("조회");
		search.addActionListener(new ActionListener() { // 조회 버튼 누르면 table을 초기화 해야함

			public void actionPerformed(ActionEvent e) {
				startidx = storeidx;
				System.out.println("startidx:"+startidx);
								}
			});
		
		toppanel.add(restcombo);
		toppanel.add(search);

		
		
//		toppanel.setLayout(null);	
		JPanel centerpanel = new JPanel();
//		center.setLayout(null);			//이 패널안에서 자동배치가 아닌 절대배치를 사용하기 위해서 
		centerpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50)); // 중앙부터 배열될수 있도록 사용
		
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
					sublist = rlist.stream().skip(idx).collect(Collectors.toList()); // sublist 생성
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
