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

		String[] startby = { " ", "서울", "수원", "이하이" };
		String[] destby = { " ", "서울", "수원" };

		JComboBox start = new JComboBox(startby);
		JComboBox dest = new JComboBox(destby);
		JButton search = new JButton("검색");

		toppane1.add(start);
		toppane2.add(dest);
		toppane3.add(search);

		toppane.add(toppane1);
		toppane.add(toppane2);
		toppane.add(toppane3);
		
		

		JPanel content = new JPanel(new GridLayout(1, 2));
		JPanel panelleft = new JPanel();
		JPanel panelright = new JPanel();

		String pathcomm[] = { "경로번호", "고속도로 종류" };
		String pathlist[][] = { { "1", "종류를 찾아보자" }, { "2", "종류를 찾아보자" }, { "3", "종류를 찾아보자" }, { "4", "종류를 찾아보자" },
				{ "5", "종류를 찾아보자" }, { "6", "종류를 찾아보자" }, { "7", "종류를 찾아보자" }, { "8", "종류를 찾아보자" },
				{ "9", "종류를 찾아보자" }, };

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

		JButton restlink = new JButton("휴게소");
		imgpane.add(realimg, BorderLayout.CENTER);
		imgpane.add(restlink, BorderLayout.SOUTH);

		panelright.add(imgpane);
		content.add(panelleft);
		content.add(panelright);
		mainp.add(content);
		mainp.add(toppane, BorderLayout.NORTH);
		mainframe.add(mainp);

		mainframe.setTitle("2019맵");
		mainframe.setSize(800, 600); // 창의 사이즈를 설정
		mainframe.setDefaultCloseOperation(EXIT_ON_CLOSE); // 종료버튼 누를시에 바로 종료
		mainframe.setVisible(true); // 가시성 부여 (눈에 보이게 하기)

	}

	class ImgPanel extends JPanel {
		private ImageIcon icon = new ImageIcon(
				"\"C:\\Users\\pc\\Desktop\\java\\20221125 팀프로젝트\\src\\img\\testicon.png\"");
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