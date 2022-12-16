
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
import GUI_Panel.ChangePanel;
import GUI_Panel.ImagePanel;
import GUI_Panel.MainCenterPanel;
import GUI_Panel.MainTopPanel;
import GUI_Panel.TextLabel;
import Map.Direction;
import Map.HighWay;
import Map.Path;
import Method.TableMethod;

//메인 프레임 
@SuppressWarnings("serial")
public class GUIMain extends JFrame {
	ChangePanel cp = ChangePanel.getInstance(); 
	int startidx;
	int arriveidx;
	int tableidx;

	Direction direc;
	String imgidx; // img.jpg 표현 --> startidx + arriveidx + tableidx
	
	//콤보박스
	String colName[] = { "경로 종류", "예상시간", "거리", "통행료" };
	String[] start = { "서울", "대구", "광주", "대전", "울산", "부산" };
	String[] arrive = { "서울", "대구", "광주", "대전", "울산", "부산" };

	void run() {
		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		JPanel top = new JPanel();
		top.setLayout(new BorderLayout()); // 간격은 만들어 주어야 할듯

		// 콤보박스 2개 + 버튼 붙이는 패널
		JPanel toppanel = new JPanel();
		toppanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		JComboBox<String> startC = new JComboBox<>(start);
		startC.setPreferredSize(new Dimension(200, 30));

		JComboBox<String> arriveC = new JComboBox<>(arrive);
		arriveC.setPreferredSize(new Dimension(200, 30));

		RoundedButton search = new RoundedButton("조회");
		search.setPreferredSize(new Dimension(50, 30));

		toppanel.add(startC);
		toppanel.add(arriveC);
		toppanel.add(search);

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

		// 테이블 붙이는 패널
		JPanel tablepanel = new JPanel();
		// 테이블 틀을 만듬
		DefaultTableModel model = new DefaultTableModel(colName, 0);

		// 조회 버튼 누르면 table을 초기화 해야함
		search.addActionListener(new ActionListener() {

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

		
		// 이미지 붙이는 패널
		JPanel imgoutpanel = new JPanel(); // 이미지 붙이는 패널

//		// DefaultTableModel 틀 붙임 --> 배열을 table의 row 형태로 다뤄야 함
		JTable table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		TableMethod.setJTableColumnsWidth(table, 700, 90, 40, 25, 30); // 테이블 크기 조절
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				tableidx = lsm.getMinSelectionIndex();

				System.out.println("(2) startidx: " + startidx + " arriveidx: " + arriveidx + " tableidx :" + tableidx);
				imgidx = "" + startidx + arriveidx + tableidx + "";
				System.out.println(imgidx);
				cp.updatePanel(imgoutpanel);
				ImagePanel imgpanel = new ImagePanel(new ImageIcon("./roadimage/" + imgidx + ".jpg").getImage());
				imgoutpanel.add(imgpanel);

			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

		tablepanel.add(scrollPane);
		top.add(toppanel, BorderLayout.NORTH);
		top.add(tablepanel, BorderLayout.CENTER);

		JPanel center = new JPanel();
		center.add(imgoutpanel);

		container.add(top, BorderLayout.NORTH);
		container.add(center, BorderLayout.CENTER);
		container.add(bottom(), BorderLayout.SOUTH);

		this.setSize(680, 680); // JFrame크기
		this.setResizable(false); // 창의 크기를 변경하지 못하게
		this.setLocationRelativeTo(null); // 창이 가운데
		this.setVisible(true); // 창이 보이게
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	GUIMain() {
		this.setTitle("Main");
	}

	JPanel bottom() {
		JPanel bottom = new JPanel();
		RoundedButton search2 = new RoundedButton("경로 검색 ");
		search2.setPreferredSize(new Dimension(200, 30));
		search2.addActionListener(new ActionListener() { // 조회 버튼 누르면 table을 초기화 해야함

			public void actionPerformed(ActionEvent e) {
				if (startidx != arriveidx) {
					if (tableidx >= 0) {
						new SecondFrame(startidx, arriveidx, tableidx);
						cp.updatePanel(TextLabel.pane);
					}
					else
						System.out.println("경로를 선택하십시오.");
				} else {
				}

			}
		});

		bottom.add(search2);
		return bottom;
	}
}
