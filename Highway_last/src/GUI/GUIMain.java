package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Highway_mgr.RestMgr;
import Map.HighWay;

public class GUIMain {

	private static GUIMain main = null;

	private GUIMain() {}

	public static GUIMain getInstance() {
		if (main == null)
			main = new GUIMain();
		return main;
	}

	// 엔진의 인스턴스를 편리를 위해 변수에 저장한다
	static HighWay highway = HighWay.getInstance();

	public static void main(String args[]) {
		highway.run();
		startGUI();
	}

	public static void startGUI() {
		// 이벤트 처리 스레드를 만들고
		// 거기서 GUI를 생성하고 보여준다.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIMain.getInstance().createAndShowGUI();
			}
		});
	}

	//창 이름 
	static JFrame mainFrame = new JFrame("TableSelectionDemo");

	private void createAndShowGUI() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 탭을 생성하고 두개 패널을 추가한다.
		JTabbedPane jtab = new JTabbedPane();
		setupRestPane();
//		setupPathPane();
		 jtab.add("휴게소", RestAreaPane);
		 mainFrame.getContentPane().add(jtab);
		 mainFrame.pack();
	     mainFrame.setVisible(true);
	}
	
	private JPanel RestAreaPane; 							  // 휴게소 보여주는 패널 
	TableSelectionDemo restTable = new TableSelectionDemo(); // 중앙?
//	 ItemTopPanel RestTop = new ItemTopPanel(); 			 // 상단 ->검색과 상세보기 버튼을 가진 패널

	private void setupRestPane() {
		// TODO Auto-generated method stub
		
		RestAreaPane = new JPanel(new BorderLayout());
		restTable.tableTitle = "RestArea";
		restTable.addComponentsToPane(RestMgr.getInstance());
		RestAreaPane.add(restTable, BorderLayout.CENTER);
		
	}

	 			
	private void setupPathPane() {
		// TODO Auto-generated method stub

	}

	

}
