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

	// ������ �ν��Ͻ��� ���� ���� ������ �����Ѵ�
	static HighWay highway = HighWay.getInstance();

	public static void main(String args[]) {
		highway.run();
		startGUI();
	}

	public static void startGUI() {
		// �̺�Ʈ ó�� �����带 �����
		// �ű⼭ GUI�� �����ϰ� �����ش�.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIMain.getInstance().createAndShowGUI();
			}
		});
	}

	//â �̸� 
	static JFrame mainFrame = new JFrame("TableSelectionDemo");

	private void createAndShowGUI() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���� �����ϰ� �ΰ� �г��� �߰��Ѵ�.
		JTabbedPane jtab = new JTabbedPane();
		setupRestPane();
//		setupPathPane();
		 jtab.add("�ްԼ�", RestAreaPane);
		 mainFrame.getContentPane().add(jtab);
		 mainFrame.pack();
	     mainFrame.setVisible(true);
	}
	
	private JPanel RestAreaPane; 							  // �ްԼ� �����ִ� �г� 
	TableSelectionDemo restTable = new TableSelectionDemo(); // �߾�?
//	 ItemTopPanel RestTop = new ItemTopPanel(); 			 // ��� ->�˻��� �󼼺��� ��ư�� ���� �г�

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
