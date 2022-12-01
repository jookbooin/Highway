package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Map.RestArea;

public class RestButton {

	int startidx;
	List<RestArea> restlist;
	static List<RestArea> sublist;
	static JButton[] btArr;

	ArrayList<Integer> price;

	public RestButton(List<RestArea> restlist, int startidx) {
		this.restlist = restlist;
		this.startidx = startidx;
	}

//	void makeList(int num) { // 가격으로 된 리스트를 만듬
//		price = new ArrayList<>();
//		if (num == 1) {// gasoline
//			for (RestArea ra : sublist)
//				price.add(ra.gasoline);
//		} else if (num == 2) { // diessel
//			for (RestArea ra : sublist)
//				price.add(ra.diesel);
//		} else if (num == 3) {// lpg
//			for (RestArea ra : sublist)
//				price.add(ra.lpg);
//		}
//	}

	RestArea findMin(int num) {	//객체 가져온다
//		int min = 0; // 최저값 
		RestArea ra;
		if (num == 1) {
			ra = sublist.stream().min(Comparator.comparing(RestArea::getgasoline)).get();
//			min = ra.gasoline;
			return ra;
		}
		if (num == 2) {

			ra = sublist.stream().min(Comparator.comparing(RestArea::getdiesel)).get();
//			min = ra.diesel;
			return ra;
		}
		if (num == 3) {
			ra = sublist.stream().min(Comparator.comparing(RestArea::getlpg)).get(); // 0이나온다...
//			min = ra.lpg;
			return ra;
		}
		return null;
	}
	

	int findIdx(RestArea ra) {//sublist에서 최솟값을 가지는 객체 restArea를 가져오면서
		return restlist.indexOf(ra);
	}

	public JPanel makeJPanel() {

		JPanel restimgpanel = new JPanel();
		restimgpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50)); // 중앙부터 배열될수 있도록 사용

		RestArea[] restArr = restlist.stream().toArray(RestArea[]::new);

		sublist = restlist.stream().skip(startidx).collect(Collectors.toList());

		JButton bt;
		btArr = new JButton[restArr.length]; // 버튼 넣어야 할까???

		for (int i = startidx; i < restArr.length; i++) {

			if (i == startidx)
				btArr[i] = new JButton(restArr[i].restname, new ImageIcon("./GUIimage/suv.jpg"));
			else
				btArr[i] = new JButton(restArr[i].restname, new ImageIcon("./GUIimage/circle.jpg"));

			btArr[i].setVerticalTextPosition(JButton.BOTTOM);
			btArr[i].setHorizontalTextPosition(JButton.CENTER);
			
			btArr[i].setBackground(new Color(255,255,255));
			btArr[i].setBorderPainted(false);		
//			btArr[i].setContentAreaFilled(false);
			btArr[i].setFocusPainted(false);
//			btArr[i].setOpaque(false);

			btArr[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

//sublist 생성 -> 의미 없을듯? 

//					for (RestArea ra : sublist)
//						System.out.println(ra);
				}
			});

			restimgpanel.add(btArr[i]);
		}
		return restimgpanel;
	}

}
