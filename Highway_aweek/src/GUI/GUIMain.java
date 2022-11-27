package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

//	MainCenterPanel maincenterpanel = new MainCenterPanel();
//	MainTopPanel maintoppanel = new MainTopPanel();	

	String[] start = { "서울", "대구", "광주", "대전", "울산", "부산" };
	String[] arrive = { "서울", "대구", "광주", "대전", "울산", "부산" };
	JComboBox<String> startC;
	JComboBox<String> arriveC;

	int startidx;
	int arriveidx;
	Direction direc;

	GUIMain() {
		//표지이름 
		this.setTitle("경로 선택");

		// combopan
		JPanel commbopane = new JPanel();
		JComboBox<String> startC = new JComboBox<>(start);
		startC.setPreferredSize(new Dimension(200, 30));

		JComboBox<String> arriveC = new JComboBox<>(arrive);
		arriveC.setPreferredSize(new Dimension(200, 30));

		JPanel buttonPanel = new JPanel(new BorderLayout());
		JButton search = new JButton("조회");

		search.setPreferredSize(new Dimension(100, 30));
		buttonPanel.add(search, BorderLayout.CENTER);

		commbopane.add(startC);
		commbopane.add(arriveC);
		commbopane.add(buttonPanel);

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

		// scrollPane
		// 중앙
		String colName[] = { "경로 종류", "예상시간", "거리", "통행료" };
		DefaultTableModel model = new DefaultTableModel(colName, 0);

		// maintoppanel 조회 버튼을 통해서 direction 인덱스 전달
		// 인덱스 통해서 path에서 경로 찾아서 table로 표현

		search.addActionListener(new ActionListener() { // 조회 버튼 누르면 table을 초기화 해야함

			public void actionPerformed(ActionEvent e) {

				if (startidx == arriveidx)
					System.out.println("같은 지역입니다.");
				else {
					// 인덱스 확인
					System.out.println("startidx: " + startidx + " arriveidx: " + arriveidx);

					direc = HighWay.direcMgr.find("" + startidx, "" + arriveidx); // -> Main 에 전달해야함

					model.setRowCount(0);
					for (Path p : direc.pathlist)
						model.addRow(p.getUiTexts());
				}

			}
		});

		JTable table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		TableMethod.setJTableColumnsWidth(table, 700, 100, 50, 50, 50);
		JScrollPane scrollPane = new JScrollPane(table);

		JPanel outpanel = new JPanel(new BorderLayout());

//		maintoppanel.setDirec(maincenterpanel);
		outpanel.add(commbopane, BorderLayout.NORTH); // combobox
		outpanel.add(scrollPane, BorderLayout.CENTER); // 리스트 나오도록

		this.add(outpanel);
		this.setSize(600, 800); // JFrame크기
		this.setResizable(false); // 창의 크기를 변경하지 못하게
		this.setLocationRelativeTo(null); // 창이 가운데
		this.setVisible(true); // 창이 보이게
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

//	ImagePanel imagepanel = new ImagePanel(new ImageIcon("./image/main.jpg").getImage());
//	setPreferredSize(new Dimension(500,400));
//	add(imagepanel ,BorderLayout.CENTER);
//	pack();

}
