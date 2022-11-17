package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Highway_mgr.PathMgr;
import Highway_mgr.RestMgr;
import Map.HighWay;

public class GUIMain {

	private static GUIMain main = null;

	private GUIMain() {
	}

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

	// 창 이름
	static JFrame mainFrame = new JFrame("TableSelectionDemo");

	private void createAndShowGUI() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane jtab = new JTabbedPane();
		
		setupRestPane();
		setupPathPane();
		
		// 탭을 생성하고 두개 패널을 추가한다.
		jtab.add("휴게소", restareaPane);
		jtab.add("경로", pathPane);
		
		mainFrame.getContentPane().add(jtab);
		
		//화면 보여주는것 
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private JPanel restareaPane; // 휴게소 보여주는 패널
	TableSelectionDemo restTable = new TableSelectionDemo(); // 중앙?
	RestTopPanel restTop = new RestTopPanel(); // 상단 ->검색과 상세보기 버튼을 가진 패널

	private void setupRestPane() {
		// TODO Auto-generated method stub

		restareaPane = new JPanel(new BorderLayout());

		restTable.tableTitle = "RestArea";
		restTable.addComponentsToPane(RestMgr.getInstance()); // RestMgr에서의 싱글톤
//		restTop.setupTopPane(restTable);
		
		restareaPane.add(restTop, BorderLayout.NORTH);
		restareaPane.add(restTable, BorderLayout.CENTER);
	}

	private JPanel pathPane; // 휴게소 보여주는 패널
	TableSelectionDemo pathTable = new TableSelectionDemo(); // 중앙?
//	RestTopPanel restTop = new RestTopPanel(); // 상단 ->검색과 상세보기 버튼을 가진 패널
	
	private void setupPathPane() {
		// TODO Auto-generated method stub
		pathPane = new JPanel(new BorderLayout());
		
		pathTable.tableTitle = "path";
		pathTable.addComponentsToPane(PathMgr.getInstance());
		pathPane.add(pathTable, BorderLayout.CENTER);
		
	}

}
