package GUI_Panel;

import java.awt.*;
import java.awt.event.*;

import javax.sql.rowset.spi.SyncResolver;
import javax.swing.*;
import javax.swing.event.*;

import Map.Direction;
import Map.HighWay;

import java.awt.*;

@SuppressWarnings("serial")
public class MainTopPanel extends JPanel {

	String[] start = { "서울", "대구", "광주", "대전", "울산", "부산" };
	String[] arrive = { "서울", "대구", "광주", "대전", "울산", "부산" };

	JComboBox<String> startC;
	JComboBox<String> arriveC;
	
	int startidx;
	int arriveidx;
	Direction direc;
	JButton search;
	JPanel buttonPanel;

	public MainTopPanel() {
//		

		startC = new JComboBox<>(start);
		startC.setPreferredSize(new Dimension(200, 30));

		arriveC = new JComboBox<>(arrive);
		arriveC.setPreferredSize(new Dimension(200, 30));

		buttonPanel = new JPanel(new BorderLayout());
		search = new JButton("조회");

		search.setPreferredSize(new Dimension(100, 30));
		buttonPanel.add(search, BorderLayout.CENTER);

		this.add(startC);
		this.add(arriveC);
		this.add(buttonPanel);
		

		startC.addActionListener(new ActionListener() { // 콤보박스 이벤트-서울 선택

			@Override
			public void actionPerformed(ActionEvent e) {

				JComboBox jcb = (JComboBox) e.getSource();
				startidx = jcb.getSelectedIndex(); //

			}
		});

		arriveC.addActionListener(new ActionListener() { // 콤보박스 이벤트-광주 선택

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				arriveidx = jcb.getSelectedIndex();

			}
		});

	}
	
	public void setDirec(MainCenterPanel maincenterpanel) {
		
		search.addActionListener(new ActionListener() {				 //조회 버튼 누르면 table을 초기화 해야함
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (startidx == arriveidx)
					System.out.println("같은 지역입니다.");
				else {
					System.out.println("startidx: " + startidx + " arriveidx: " + arriveidx);
					
					direc = HighWay.direcMgr.find(""+startidx , ""+arriveidx); //-> Main 에 전달해야함
					maincenterpanel.loadData(direc);  // ->이벤트가 생기면 maincenterpanel로 direc전달???
				}

			}
		});

	}

}
