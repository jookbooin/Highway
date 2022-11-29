package GUI_Panel;

import java.awt.*;
import java.awt.event.*;

import javax.sql.rowset.spi.SyncResolver;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import Map.Direction;
import Map.HighWay;
import Map.Path;
import Method.TableMethod;

import java.awt.*;

@SuppressWarnings("serial")
public class MainTopPanel extends JPanel {
	int startidx;
	int arriveidx;
	Direction direc;
	
	void setTopPanel(JFrame frame) {
		frame.setTitle("Main");
		// 버튼 붙이는 패널
		String colName[] = { "경로 종류", "예상시간", "거리", "통행료" };
		String[] start = { "서울", "대구", "광주", "대전", "울산", "부산" };
		String[] arrive = { "서울", "대구", "광주", "대전", "울산", "부산" };

		JPanel outpanel = new JPanel(new BorderLayout()); // 제일 바깥 패널
		JPanel commbopane = new JPanel(); // 버튼 붙이는 패널

		JComboBox<String> startC = new JComboBox<>(start);
		startC.setPreferredSize(new Dimension(200, 30));

		JComboBox<String> arriveC = new JComboBox<>(arrive);
		arriveC.setPreferredSize(new Dimension(200, 30));

		//
		JPanel buttonPanel = new JPanel(new BorderLayout());
		JButton search = new JButton("조회");
		buttonPanel.add(search, BorderLayout.CENTER);

		// 붙임
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

		// 테이블 틀을 만듬
		DefaultTableModel model = new DefaultTableModel(colName, 0);

		search.addActionListener(new ActionListener() { // 조회 버튼 누르면 table을 초기화 해야함

			public void actionPerformed(ActionEvent e) {

				if (startidx == arriveidx)
					System.out.println("같은 지역입니다.");
				else {
					// 인덱스 확인
//					System.out.println("startidx: " + startidx + " arriveidx: " + arriveidx);

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
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		TableMethod.setJTableColumnsWidth(table, 700, 100, 50, 50, 50); // 테이블 크기 조절
		JScrollPane scrollPane = new JScrollPane(table);

		outpanel.add(commbopane, BorderLayout.NORTH); // combobox
		outpanel.add(scrollPane, BorderLayout.CENTER); // 리스트 나오도록
		frame.add(outpanel);
		
		frame.setSize(600, 800); // JFrame크기
		frame.setResizable(false); // 창의 크기를 변경하지 못하게
		frame.setLocationRelativeTo(null); // 창이 가운데
		frame.setVisible(true); // 창이 보이게
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		return outpanel;
	}
	
}
