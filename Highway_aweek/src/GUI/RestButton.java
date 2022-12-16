package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import GUI_Panel.ChangePanel;
import GUI_Panel.TextLabel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Map.RestArea;

public class RestButton {
	ChangePanel cp = ChangePanel.getInstance(); 
	int startidx;
	int clickidx;
	List<RestArea> restlist;
	static List<RestArea> sublist;
	static JButton[] btArr;

	ArrayList<Integer> price;

	public RestButton(List<RestArea> restlist, int startidx) {
		this.restlist = restlist;
		this.startidx = startidx;
	}

	RestArea findMin(int num) { // 객체 가져온다

		RestArea minArea;
		if (num == 1) {
			minArea = sublist.stream().filter(ra -> ra.getgasoline() > 0)
					.min(Comparator.comparing(RestArea::getgasoline)).get();
			System.out.println(minArea.restname + ": " + minArea.gasoline);
			return minArea;
		}
		if (num == 2) {
			minArea = sublist.stream().filter(ra -> ra.getdiesel() > 0).min(Comparator.comparing(RestArea::getdiesel))
					.get();
			System.out.println(minArea.restname + ": " + minArea.diesel);
			return minArea;
		}
		if (num == 3) {
			minArea = sublist.stream().filter(ra -> ra.getlpg() > 0).min(Comparator.comparing(RestArea::getlpg)).get(); // 0이나온다...
			System.out.println(minArea.restname + ": " + minArea.lpg);
			return minArea;
		}
		return null;
	}

	int findIdx(RestArea ra) {// sublist에서 최솟값을 가지는 객체 restArea를 가져오면서
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

			btArr[i].setBackground(new Color(255, 255, 255));
			btArr[i].setBorderPainted(false);
//			abtArr[i].setContentAreaFilled(false);
			btArr[i].setFocusPainted(false);
//			btArr[i].setOpaque(false);

			//위치 버튼 누르면 패널정보 최신화됨.
			btArr[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//leftpanel 관련
					cp.updatePanel(TextLabel.pane);
					JButton buttonSource = (JButton) e.getSource();
					String restname = buttonSource.getText();
					System.out.println("1: "+restname);
					
					RestArea curRestArea = null;
					for(RestArea ra : sublist)
						if(ra.restname.equals(restname)) {
							curRestArea = ra;
							System.out.println("2: "+curRestArea.restname);
						}
					TextLabel tl = new TextLabel(curRestArea.getUiTexts());
					SecondFrame.textpanel.add(tl.textfield());
				}
			});

			restimgpanel.add(btArr[i]);
		}
		return restimgpanel;
	}

}
