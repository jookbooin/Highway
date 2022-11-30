package Practice;
import java.awt.Container;
import java.io.InterruptedIOException;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Map.Direction;
import Map.HighWay;
import Map.Path;
import Map.RestArea;

public class TestFrame extends JFrame {

//	String[] start = { "서울", "대구", "광주", "대전", "울산", "부산" };
	String[] start;
	
	Direction direc = HighWay.direcMgr.find("0", "5");
	Path path = direc.pathlist.get(0);
	List<RestArea> rlist = path.restlist;
	
	RestArea[] restArea = rlist.stream().toArray(RestArea[]::new);//리스트 배열로 바꿔줌
	
	
	

	TestFrame() {
		this.setTitle("절대적 위치 설정");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Null Container Sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new GridLayout(2, 1));

		JPanel north = new JPanel();
		north.setLayout(null);
		JLabel la = new JLabel("Hello, Press Buttons!");
		la.setLocation(130, 50);
		la.setSize(200, 20);
		north.add(la);

		JPanel center = new JPanel();

//		center.setLayout(null);
		center.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 80));
		for (int i = 0; i < restArea.length; i++) {
//			JButton b = new JButton(new ImageIcon("./GUIimage/circle.jpg"));
			JButton b = new JButton(restArea[i].restname, new ImageIcon("./GUIimage/circle.jpg"));
			b.setVerticalTextPosition(JButton.BOTTOM);
			b.setHorizontalTextPosition(JButton.CENTER);

//			b.setLocation(i*50, i*50);
//			b.setSize(50, 20);
			b.setBorderPainted(false);
			b.setContentAreaFilled(false);
			b.setFocusPainted(false);
			b.setOpaque(false);

			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					
					System.out.println(e.getSource().toString());
				}

			});

			center.add(b);
		}
		c.add(center);
		c.add(north);
	

//			JPanel outpanel = new JPanel(new BorderLayout(100 ,100));
//			Container container = getContentPane();
//
//	        container.setLayout(new FlowLayout(FlowLayout.CENTER,100,80));
//
//	        container.add(new JButton("add"))
//	        container.add(new JButton("sub"));
//
//	        container.add(new JButton("mul"));
//
//	        container.add(new JButton("div"));
//	        
//	        container.add(new JButton("add1"));
//
//	        container.add(new JButton("sub2"));
//
//	        container.add(new JButton("mul3"));
//
//	        container.add(new JButton("div4"));
//	        outpanel.add(container);
//	        this.add(outpanel);

		this.setSize(1000, 500);

		this.setVisible(true);
	}

	private ImageIcon imageSetsize(ImageIcon icon, int i, int j) {
		Image ximg = icon.getImage(); // ImageIcon을 Image로 변환.
		Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 HighWay highway = HighWay.getInstance();
		 highway.run();
		new TestFrame();
	}

}
