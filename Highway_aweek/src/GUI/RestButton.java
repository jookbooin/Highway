package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import Map.RestArea;

public class RestButton {

	int startidx;
	List<RestArea> restlist;
	List<RestArea> sublist;

	public RestButton(List<RestArea> restlist, int startidx) {
		this.restlist = restlist;
		this.startidx = startidx;
	}

	public JPanel makeJPanel() {

		JPanel restimgpanel = new JPanel(); 
		restimgpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50)); // �߾Ӻ��� �迭�ɼ� �ֵ��� ���

		RestArea[] restArr = restlist.stream().toArray(RestArea[]::new);
		
		JButton bt;
//		JButton[] btArr = new JButton[10];  // ��ư �־�� �ұ�???
		
		
		for (int i = startidx; i < restArr.length; i++) {

			if (i == startidx)
				bt = new JButton(restArr[i].restname, new ImageIcon("./GUIimage/suv.jpg"));
			else
				bt = new JButton(restArr[i].restname, new ImageIcon("./GUIimage/circle.jpg"));

			bt.setVerticalTextPosition(JButton.BOTTOM);
			bt.setHorizontalTextPosition(JButton.CENTER);

//			bt.setBorderPainted(false);		
			bt.setContentAreaFilled(false);
			bt.setFocusPainted(false);
//			b.setOpaque(false);

			bt.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

//sublist ���� -> �ǹ� ������? 
//					sublist = restlist.stream().skip(startidx).collect(Collectors.toList());
//					for (RestArea ra : sublist)
//						System.out.println(ra);
				}
			});

			restimgpanel.add(bt);
		}
		return restimgpanel;
	}

}
