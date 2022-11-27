package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Highway_mgr.RestlistMgr;
import Highway_mgr.DirecMgr;
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

		setupRestPane(); // �ްԼ� ��� �����ִ� ȭ��
		setupPathPane(); // ��� + ����Ʈ �����ִ� ȭ��

		// ���� �����ϰ� �ΰ� �г��� �߰��Ѵ�.
		jtab.add("�ްԼ�", restareaPane);
		jtab.add("���", direcPane);
		mainFrame.getContentPane().add(jtab);

		// ȭ�� �����ִ°�
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
		restTop.setupTopPane(restTable);
		restareaPane.add(restTop, BorderLayout.NORTH);
		restareaPane.add(restTable, BorderLayout.CENTER);
	}

	private JPanel direcPane; 									// ���, �ްԼ� �����ִ� �г�
	TableSelectionDemo direcTable = new TableSelectionDemo(); 	// �߾�?
	PathTable pathTable = new PathTable(); 						// ��� ->�˻��� �󼼺��� ��ư�� ���� �г�
	RestlistTable restlistTable = new RestlistTable();
	

	private void setupPathPane() {
		// TODO Auto-generated method stub
		direcPane = new JPanel(new BorderLayout());

		direcTable.tableTitle = "direc";
		direcTable.addComponentsToPane(DirecMgr.getInstance());
		direcPane.add(direcTable, BorderLayout.NORTH);

		JPanel middle = new JPanel(); // panel �ȿ� pathtalbe (jtable) ���� �ø�
		pathTable.tableTitle = "path";
		pathTable.addComponentsToPane(PathMgr.getInstance());

		middle.add(pathTable, BorderLayout.CENTER);
		direcPane.add(middle, BorderLayout.CENTER);

		JPanel bottom = new JPanel(new BorderLayout());
		restlistTable.tableTitle = "restlist";
		restlistTable.addComponentsToPane(RestlistMgr.getInstance());
		bottom.add(restlistTable, BorderLayout.CENTER);
		

		//��â���� -> ��ü�� �E �� �����ٵ� ����
		//restlistTable ��  pathtableidx �� ���ο� ����Ʈ�� ���� �� ������ ��� 
		JButton detail = new JButton("���� ��ġ");
		detail.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (e.getActionCommand().equals("���� ��ġ")) {
        			new curLoWindow(restlistTable);
            	}
           }
        });
		
		bottom.add(detail ,BorderLayout.SOUTH );
		direcPane.add(bottom, BorderLayout.SOUTH);

	}

}
