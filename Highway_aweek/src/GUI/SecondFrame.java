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
	int comboidx; // 콤보박스 인덱스
	int storeidx; // 콤보박스 -> 버튼 생성 인덱스

	// restlist중
	int gasolinIdx; // gasoline min 인덱스
	int dieselIdx; // diesel min 인덱스
	int lpgIdx; // lpg min 인덱스

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

		restArr = restlist.stream().toArray(RestArea[]::new);// 전체리스트 배열로 바꿔줌

		for (RestArea ra : restlist)
			restnamelist.add(ra.restname);// 시작하자마자 들어온 리스트로 콤보박스 만들준비
		restnameArr = restnamelist.stream().toArray(String[]::new); // 콤보박스에 들어갈 String배열 생성

		this.setTitle("경로검색 ");

//Frame-> container화

		Container container = getContentPane();
		this.getContentPane().setBackground(new Color(255, 255, 255));
		container.setLayout(new BorderLayout());

		container.add(secondTop(), BorderLayout.CENTER); // north
		container.add(secondCenter(), BorderLayout.SOUTH); // south

		this.setSize(1350, 520);
		this.setVisible(true);
	}

//secondFrame top 패널
	JPanel secondTop() {
		// container에 붙이는 top
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());

		// containeㄹr-top 패널에 콤보박스+버튼 넣는 패널
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
		cbuttonpanel = new JPanel();
		cbuttonpanel.setLayout(new BorderLayout());

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

				cp.updatePanel(cbuttonpanel);
				cp.updatePanel(TextLabel.pane);
				restbutton = new RestButton(restlist, storeidx);
				
				// 버튼패널 가져와서 centerpanel 위에 붙임
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
		top.add(cbuttonpanel, BorderLayout.CENTER); // top 패널안에 toppanel(버튼)/ centerpanel(휴게소) 위치함
		return top;
	}

	JPanel secondCenter() {
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());

		JTabbedPane tab = new JTabbedPane();

		JPanel searchpanel = new JPanel();
		searchpanel.setLayout(new GridLayout(1, 3));
		tab.add("검색", searchpanel); // search 패널안에서 작업이 이루어져야 함 .

		JPanel leftpanel = new JPanel();
		leftpanel.setLayout(new BorderLayout());
		// 라벨들이용해서
		
//		JScrollPane scrollPane = new JScrollPane(textpanel);
		leftpanel.add(textpanel,BorderLayout.CENTER);
		

		JPanel rightpanel = new JPanel();
		rightpanel.setLayout(new GridLayout(2, 1));

		JPanel rtoppanel = new JPanel();
		rtoppanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JLabel textlabel = new JLabel("시설/음식");
		JTextField tf = new JTextField(30);
		RoundedButton tfsbt = new RoundedButton("검색");
		rtoppanel.add(textlabel);
		rtoppanel.add(tf);
		rtoppanel.add(tfsbt);

		JPanel rbotpanel = new JPanel();
		rbotpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
		RoundedButton gasbt = new RoundedButton("휘발유");
		RoundedButton diebt = new RoundedButton("경유");
		RoundedButton lpgbt = new RoundedButton("LPG");
		rbotpanel.add(gasbt);
		rbotpanel.add(diebt);
		rbotpanel.add(lpgbt);

//휘발유 , 경유 , lpg 최솟값버튼 찾기 위해서
//gasolineidx , dieselidx , lpgidx
//버튼 누를때 마다 centerpanel안의 버튼 들이 변함 
		
		gasbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 처음부터 사라짐
				cp.updatePanel(cbuttonpanel);
				restbutton = new RestButton(restlist, storeidx);
				// 버튼패널 가져와서 centerpanel 위에 붙임
				cbuttonpanel.add(restbutton.makeJPanel(), BorderLayout.NORTH);
				restbutton.btArr[gasolinIdx].setBackground(new Color(255, 255, 0));
			}
		});
		gasbt.setPreferredSize(new Dimension(80, 40));

		diebt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				cp.updatePanel(cbuttonpanel);
				restbutton = new RestButton(restlist, storeidx);
				// 버튼패널 가져와서 centerpanel 위에 붙임
				cbuttonpanel.add(restbutton.makeJPanel(), BorderLayout.NORTH);

				// 버튼패널 가져와서 centerpanel 위에 붙임
				cbuttonpanel.add(restbutton.makeJPanel(), BorderLayout.NORTH);
				restbutton.btArr[dieselIdx].setBackground(new Color(0, 255, 100));
			}
		});
		diebt.setPreferredSize(new Dimension(80, 40));

		lpgbt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 처음부터 사라짐
				cp.updatePanel(cbuttonpanel);
				restbutton = new RestButton(restlist, storeidx);
				// 버튼패널 가져와서 centerpanel 위에 붙임
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
