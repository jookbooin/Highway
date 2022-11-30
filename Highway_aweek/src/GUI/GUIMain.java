
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import GUI_Custom.RoundedButton;
import GUI_Panel.ImagePanel;
import GUI_Panel.MainCenterPanel;
import GUI_Panel.MainTopPanel;
import Map.Direction;
import Map.HighWay;
import Map.Path;
import Method.TableMethod;

//메인 프레임 
@SuppressWarnings("serial")
public class GUIMain extends JFrame {

	int startidx;
	int arriveidx;
	int tableidx;

	Direction direc;
	JPanel outpanel = new JPanel(); ////////////////////////// 제일 바깥 패널
	
	JPanel toppanel = new JPanel(new BorderLayout());///////// '조회'버튼 + table 붙이는 바깥 패널
	JPanel combopanel = new JPanel(); //////////////////////// toppanel에 버튼 붙이는 패널
	
	JPanel bottompanel = new JPanel(new BorderLayout());  // 이미지와 버튼을 붙여서 out에 넣는 패널
	
	JPanel imgoutpanel = new JPanel();///////////////////////// 이미지패널 붙이는 패널
	
	JPanel ImageButton = new JPanel(new BorderLayout());
	JPanel btnspanel = new JPanel();////////////////////////// 경로검색 '버튼 있는 패널
	
	
	void run() {
		outTopPane();
		outbottomPane();

		this.add(outpanel);
		this.setSize(800, 700); // JFrame크기
		this.setResizable(false); // 창의 크기를 변경하지 못하게
		this.setLocationRelativeTo(null); // 창이 가운데
		this.setVisible(true); // 창이 보이게
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	GUIMain() {
		this.setTitle("Main");
	}
	// ./image/053.jpg;

	void outTopPane() {
		// 버튼 붙이는 패널

		String colName[] = { "경로 종류", "예상시간", "거리", "통행료" };
		String[] start = { "서울", "대구", "광주", "대전", "울산", "부산" };
		String[] arrive = { "서울", "대구", "광주", "대전", "울산", "부산" };

		

		JComboBox<String> startC = new JComboBox<>(start);
		startC.setPreferredSize(new Dimension(200, 30));

		JComboBox<String> arriveC = new JComboBox<>(arrive);
		arriveC.setPreferredSize(new Dimension(200, 30));

		//
		JPanel btnpanel = new JPanel(new BorderLayout());
//		JButton search = new JButton("조회");
		RoundedButton search = new RoundedButton("조회");
		btnpanel.add(search, BorderLayout.CENTER);

		// 붙임
		combopanel.add(startC);
		combopanel.add(arriveC);
		combopanel.add(btnpanel);

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

		// 테이블 틀을 만듬
		DefaultTableModel model = new DefaultTableModel(colName, 0);

		search.addActionListener(new ActionListener() { // 조회 버튼 누르면 table을 초기화 해야함

			public void actionPerformed(ActionEvent e) {

				if (startidx == arriveidx)
					System.out.println("같은 지역입니다.");
				else {
					// 인덱스 확인
					System.out.println(
							"(1) startidx: " + startidx + " arriveidx: " + arriveidx + " tableidx :" + tableidx);

					direc = HighWay.direcMgr.find("" + startidx, "" + arriveidx); // -> Main 에 전달해야함
					// 매번 눌리면 이전것 먼저 삭제
					model.setRowCount(0);
					for (Path p : direc.pathlist)
						model.addRow(p.getUiTexts());
				}

			}
		});

		// DefaultTableModel 틀 붙임 --> 배열을 table의 row 형태로 다뤄야 함
		JTable table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 80));
		TableMethod.setJTableColumnsWidth(table, 700, 100, 30, 30, 30); // 테이블 크기 조절

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				tableidx = lsm.getMinSelectionIndex();

				System.out.println("(2) startidx: " + startidx + " arriveidx: " + arriveidx + " tableidx :" + tableidx);

			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 10));

		toppanel.add(combopanel, BorderLayout.NORTH); // combobox
		toppanel.add(scrollPane, BorderLayout.CENTER); // 리스트 나오도록


		outpanel.add(toppanel);
	}
	
	void outbottomPane() {
		imagePane();
		bottombtnPane();
		
		outpanel.add(bottompanel);
		
	}

	void imagePane() {

		ImagePanel imgpanel = new ImagePanel(new ImageIcon("./image/051.jpg").getImage());
		imgoutpanel.add(imgpanel);
		// 현재 이미지는 imgoutpane 위에 있음

		bottompanel.add(imgoutpanel, BorderLayout.CENTER);

	}

	void bottombtnPane() {
		
		JButton search2 = new JButton("경로 검색 ");
		search2.setPreferredSize(new Dimension(200, 30));
		search2.addActionListener(new ActionListener() { // 조회 버튼 누르면 table을 초기화 해야함

			public void actionPerformed(ActionEvent e) {
				if (tableidx >= 0)
					new SecondFrame(startidx, arriveidx, tableidx);
				else
					System.out.println("경로를 선택하십시오.");

			}
		});

		btnspanel.add(search2); // 현재 버튼은 btnpanel2에 있음

		bottompanel.add(btnspanel,BorderLayout.SOUTH);

	}
}
