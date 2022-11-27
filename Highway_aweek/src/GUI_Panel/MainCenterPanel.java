package GUI_Panel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Map.Direction;
import Map.HighWay;
import Map.Path;
import Method.TableMethod;

//
public class MainCenterPanel extends JPanel {

	String colName[] = { "경로 종류", "예상시간", "거리", "통행료" };
	String pathlist[][] = { { "1", " ", " ", " " }, { "2", " ", " ", " " }, { "3", " ", " ", " " },
			{ "4", " ", " ", " " }, { "5", " ", " ", " " } };

	JPanel outpanel; // 큰틀 패널
	JPanel tablepanel; // 테이블 붙이는
	JPanel imagepanel; // 이미지 붙이는

	public MainCenterPanel() {
		// 테이블 데이터 관리 객체 생성
		DefaultTableModel model = new DefaultTableModel(colName, 0);
		JTable table = new JTable(model); // 모델 테이블에 넣음
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		TableMethod.setJTableColumnsWidth(table, 700, 100, 50, 50, 50);  
		JScrollPane scrollPane = new JScrollPane(table);

		// 스크롤에 테이블 넣음

		// outPanel에 scrollpane 붙이던가
		// MainCenterPanel 에 바로 붙이던가

		// 조회로 direction.startID direction.arriveID 가져와서
		// Direction.pathlist<path> 의 getUITexts() - String[] 를 가져와야 할듯
		Direction direc = HighWay.direcMgr.find("0", "5"); // 서울 부산
		for (Path p : direc.pathlist) { // 경로 가져옴
//			// String[] 모델객체에 getUiTexts 참조변수 집어넣음 - getUiTexts 참조변수 배열로 이루어짐 (경로,시간,거리,통행료)
			model.addRow(p.getUiTexts());
		}
		this.add(scrollPane);

		// 테이블 부터 만들기
//		JTable pathtable = new Jtable(pathlist,pathcomm);

//		outpanel = new JPanel();
//		tablepanel = new JPanel();
//		imagepanel = new JPanel();
//
//		outpanel.add(tablepanel); // 큰틀에 데이블 붙임
//		outpanel.add(imagepanel); // 큰틀에 image 붙임
//		this.add(outpanel); // 메인 페이지에 큰틀 붙이려고 함

	}

	
}
