package zHonggongjin;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class Honggongjin_main extends JFrame {

	private ImgPanel realimg = new ImgPanel();

	public Honggongjin_main() {
		JFrame mainframe = new JFrame();
		JPanel mainp = new JPanel(new BorderLayout());
		
		JPanel toppane = new JPanel(new GridLayout(1, 3));
		JPanel toppane1 = new JPanel();
		JPanel toppane2 = new JPanel();
		JPanel toppane3 = new JPanel();

		String[] startby = { " ", "����", "����", "������" };
		String[] destby = { " ", "����", "����" };

		JComboBox start = new JComboBox(startby);
		JComboBox dest = new JComboBox(destby);
		JButton search = new JButton("�˻�");

		toppane1.add(start);
		toppane2.add(dest);
		toppane3.add(search);

		toppane.add(toppane1);
		toppane.add(toppane2);
		toppane.add(toppane3);
		
		

		JPanel content = new JPanel(new GridLayout(1, 2));
		JPanel panelleft = new JPanel();
		JPanel panelright = new JPanel();

		String pathcomm[] = { "��ι�ȣ", "��ӵ��� ����" };
		String pathlist[][] = { { "1", "������ ã�ƺ���" }, { "2", "������ ã�ƺ���" }, { "3", "������ ã�ƺ���" }, { "4", "������ ã�ƺ���" },
				{ "5", "������ ã�ƺ���" }, { "6", "������ ã�ƺ���" }, { "7", "������ ã�ƺ���" }, { "8", "������ ã�ƺ���" },
				{ "9", "������ ã�ƺ���" }, };

		JTable pathtable = new JTable(pathlist, pathcomm);
		pathtable.setPreferredScrollableViewportSize(new Dimension(300, 400));
		pathtable.setFillsViewportHeight(true);
		panelleft.add(new JScrollPane(pathtable));
		pathtable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelleft.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelright.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JPanel imgpane = new JPanel(new BorderLayout());
		ImgPanel realimg = new ImgPanel();
		setContentPane(realimg);
		setSize(200, 300);
		realimg.setVisible(true);

		JButton restlink = new JButton("�ްԼ�");
		imgpane.add(realimg, BorderLayout.CENTER);
		imgpane.add(restlink, BorderLayout.SOUTH);

		panelright.add(imgpane);
		content.add(panelleft);
		content.add(panelright);
		mainp.add(content);
		mainp.add(toppane, BorderLayout.NORTH);
		mainframe.add(mainp);

		mainframe.setTitle("2019��");
		mainframe.setSize(800, 600); // â�� ����� ����
		mainframe.setDefaultCloseOperation(EXIT_ON_CLOSE); // �����ư �����ÿ� �ٷ� ����
		mainframe.setVisible(true); // ���ü� �ο� (���� ���̰� �ϱ�)

	}

	class ImgPanel extends JPanel {
		private ImageIcon icon = new ImageIcon(
				"\"C:\\Users\\pc\\Desktop\\java\\20221125 ��������Ʈ\\src\\img\\testicon.png\"");
		private Image img = icon.getImage();

		public void paintComponenet(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}

	}

	public static void main(String[] args) {
		new Honggongjin_main();
	}
}