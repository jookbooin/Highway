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
import javax.swing.JLabel;
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
	int comboidx; // 콤보박스 인덱스
	int storeidx; // 콤보박스 -> 버튼 생성 인덱스

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

		restArr = restlist.stream().toArray(RestArea[]::new);// 전체리스트 배열로 바꿔줌

		for (RestArea ra : restlist)
			restnamelist.add(ra.restname);// 시작하자마자 들어온 리스트로 콤보박스 만들준비
		restnameArr = restnamelist.stream().toArray(String[]::new); // 콤보박스에 들어갈 String배열 생성

		this.setTitle("경로검색 ");

//Frame-> container화
		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		container.add(secondTop(), BorderLayout.NORTH); // north
		container.add(secondCenter(), BorderLayout.CENTER); // south

		this.setSize(1000, 500);
		this.setVisible(true);
	}

//secondFrame top 패널
	JPanel secondTop() {
		// container에 붙이는 top
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());

		// container-top 패널에 콤보박스+버튼 넣는 패널
		JPanel toppanel = new JPanel();
		toppanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		JLabel curlo = new JLabel("현재위치");
		curlo.setOpaque(true); // 불투명도를 참으로 설정하여 배경색을 보이게 한다
		
		
		
		// 콤보박스
		JComboBox<String> restcombo = new JComboBox<>(restnameArr);
		restcombo.setPreferredSize(new Dimension(100, 30));

		// 콤보박스 버튼
		RoundedButton search = new RoundedButton("조회");

		// container-top 패널에 버튼이 표시되는 패널
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

				RestButton btest = new RestButton(restlist, storeidx);
				// 버튼패널 가져와서 centerpanel 위에 붙임
				centerpanel.add(btest.makeJPanel(), BorderLayout.NORTH);
			}
		});

		toppanel.add(curlo);
		toppanel.add(restcombo);
		toppanel.add(search);
		top.add(toppanel, BorderLayout.NORTH);
		top.add(centerpanel, BorderLayout.CENTER); // top 패널안에 toppanel(버튼)/ centerpanel(휴게소) 위치함
		return top;
	}

	JPanel secondCenter() {
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());

		JTabbedPane tab = new JTabbedPane();
		JPanel gaspanel = new JPanel();
		JPanel searchpanel = new JPanel();
		tab.add("주유소", gaspanel);
		tab.add("시설/음식", searchpanel);

		center.add(tab, BorderLayout.CENTER);
		center.add(tab);
		return center;
	}

}
