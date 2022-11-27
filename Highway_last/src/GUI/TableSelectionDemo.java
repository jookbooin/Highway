﻿package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import facade.DataEngineInterface;
import facade.UIData;

/*
 * 테이블을 포함한 패널로 테이블과 테이블모델을 생성하여 초기화하고(init) 
 * 매니저에서 데이터를 받아와 테이블 행을 추가하는 기능(loadData)과
 * 행이 선택되었을 때 필요한 일을 하는 기능(valueChanged)를 가진다.
 */

public class TableSelectionDemo extends JPanel implements ListSelectionListener {
	private static final long serialVersionUID = 1L;
	JTable table;
	DefaultTableModel tableModel;
	int selectedIndex = -1; // 테이블에서 선택된 행의 인덱스를 가질 변수
	String tableTitle = null;
	DataEngineInterface<?> dataMgr; 			// 엔진 쪽의 데이터를 관리하는 매니저 파사드 인터페이스

	static int directableidx; 					// title : 'direc' 눌렀을때 인덱스 저장
	static int pathtableidx; 					// title : 'path' 눌렀을때 인덱스 저장
	static int restlisttableidx;

	public TableSelectionDemo() {
		super(new BorderLayout());
	}

	// 테이블을 생성하여 초기화하고 스크롤 붙여서 패널에 add한다
	void addComponentsToPane(DataEngineInterface<?> mgr) {
		init(mgr);
		JScrollPane center = new JScrollPane(table);
		add(center, BorderLayout.CENTER);
	}

	// 테이블의 기본 설정을 하는 부분 (테이블 모델을 생성하고 초기 데이터 불러오고
	// 테이블에 필요한 설정을 초기화한다
	// 장바구니 테이블은 오버라이드하여 가져올 주문번호를 세팅한 후 수퍼 호출
	@SuppressWarnings("serial")
	void init(DataEngineInterface<?> mgr) {
		dataMgr = mgr;
		tableModel = new DefaultTableModel(mgr.getColumnNames(), 0) {
			// 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		loadData("");

		table = new JTable(tableModel);
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(this);
		table.setPreferredScrollableViewportSize(new Dimension(800, 150));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	// 매니저에서 검색된 객체들을 테이블에 보여준다. kwd가 ""면 모두 출력
	void loadData(String kwd) {
		List<?> result = dataMgr.search(kwd); // 매니저에서 검색결과(mylist)를 가져옴
		tableModel.setRowCount(0); 			  // 현재 데이터모델의 행을 모두 지운다
		for (Object m : result) 				  // 한 행씩 추가함
			tableModel.addRow(((UIData) m).getUiTexts());
	}

	// 아이템 패널에서 상세보기 버튼을 눌렀을 때 실행되는 메소드
	void showDetail() {
		if (selectedIndex < 0)
			return;
		String[] rowTexts = new String[tableModel.getColumnCount()];
		for (int i = 0; i < rowTexts.length; i++)
			rowTexts[i] = (String) tableModel.getValueAt(selectedIndex, i);
		DetailDialog dlg = new DetailDialog(rowTexts);
		dlg.setup();
		dlg.pack();
		dlg.setVisible(true);
	}

	// 선택된 행이 변경되면 그 내용을 편집창으로 보냄
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedIndex = lsm.getMinSelectionIndex();

			// index
			System.out.println(selectedIndex); // ->...? 누르는 layout의 인덱스만 가져오는듯 -> 연관관계 x

			String name = (String) tableModel.getValueAt(selectedIndex, 2);
			
			if (tableTitle.equals("RestArea")) {
				GUIMain.getInstance().restTop.kwdTextField.setText(name);
			} 
			else if (tableTitle.equals("direc")) {
				directableidx = selectedIndex;
				pathtableidx = 0; // 초기화
				restlisttableidx = 0;
				
				GUIMain.getInstance().pathTable.loadData("" + directableidx);
				GUIMain.getInstance().restlistTable.loadData(""+pathtableidx);
				
				System.out.println("(1)pathtableidx: " + directableidx);
				System.out.println("(1)pathlisttableidx: " + pathtableidx);
				System.out.println();

			} else if (tableTitle.equals("path")) {
				pathtableidx = selectedIndex;
        		GUIMain.getInstance().restlistTable.loadData(""+pathtableidx);

				System.out.println("(2)pathtableidx: " + directableidx);
				System.out.println("(2)pathlisttableidx: " + pathtableidx);
				System.out.println();
				
			} else if(tableTitle.equals("restlist")) {
				restlisttableidx = selectedIndex;
				
			}
		}
	}
}
