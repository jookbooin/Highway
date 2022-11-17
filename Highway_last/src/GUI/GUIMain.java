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

	// â �̸�
	static JFrame mainFrame = new JFrame("TableSelectionDemo");

	private void createAndShowGUI() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane jtab = new JTabbedPane();
		
		setupRestPane();
		setupPathPane();
		
		// ���� �����ϰ� �ΰ� �г��� �߰��Ѵ�.
		jtab.add("�ްԼ�", restareaPane);
		jtab.add("���", pathPane);
		
		mainFrame.getContentPane().add(jtab);
		
		//ȭ�� �����ִ°� 
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private JPanel restareaPane; // �ްԼ� �����ִ� �г�
	TableSelectionDemo restTable = new TableSelectionDemo(); // �߾�?
	RestTopPanel restTop = new RestTopPanel(); // ��� ->�˻��� �󼼺��� ��ư�� ���� �г�

	private void setupRestPane() {
		// TODO Auto-generated method stub

		restareaPane = new JPanel(new BorderLayout());

		restTable.tableTitle = "RestArea";
		restTable.addComponentsToPane(RestMgr.getInstance()); // RestMgr������ �̱���
//		restTop.setupTopPane(restTable);
		
		restareaPane.add(restTop, BorderLayout.NORTH);
		restareaPane.add(restTable, BorderLayout.CENTER);
	}

	private JPanel pathPane; // �ްԼ� �����ִ� �г�
	TableSelectionDemo pathTable = new TableSelectionDemo(); // �߾�?
//	RestTopPanel restTop = new RestTopPanel(); // ��� ->�˻��� �󼼺��� ��ư�� ���� �г�
	
	private void setupPathPane() {
		// TODO Auto-generated method stub
		pathPane = new JPanel(new BorderLayout());
		
		pathTable.tableTitle = "path";
		pathTable.addComponentsToPane(PathMgr.getInstance());
		pathPane.add(pathTable, BorderLayout.CENTER);
		
	}

}
