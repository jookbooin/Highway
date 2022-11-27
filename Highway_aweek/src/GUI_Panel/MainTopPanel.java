package GUI_Panel;

import java.awt.*;
import java.awt.event.*;

import javax.sql.rowset.spi.SyncResolver;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MainTopPanel extends JPanel {

	String[] start = { "서울", "대구", "광주","대전" ,"울산", "부산" };
	String[] arrive = { "서울", "대구", "광주","대전" ,"울산", "부산" };

	JComboBox<String> startC;
	JComboBox<String> arriveC;

//	String cstart;
//	String carrive;
	int startidx;
	int arriveidx;
	JLabel indent1 ;
	JLabel indent2 ;

	public MainTopPanel() {
//		

		startC = new JComboBox<>(start);
		startC.setPreferredSize(new Dimension(200, 30));
		
		indent1 = new JLabel("");
	
		

		arriveC = new JComboBox<>(arrive);
		arriveC.setPreferredSize(new Dimension(200, 30));

		JPanel buttonPanel = new JPanel(new BorderLayout());
		JButton search = new JButton("조회");

		search.setPreferredSize(new Dimension(100, 30));
		buttonPanel.add(search, BorderLayout.CENTER);

		this.add(startC);
		this.add(arriveC);
		this.add(buttonPanel);

		startC.addActionListener(new ActionListener() { // 콤보박스 이벤트-서울 선택

			@Override
			public void actionPerformed(ActionEvent e) {
//				   if(e.getSource().equals("서울"))
//					   cstart = (String)e.getSource();

				JComboBox jcb = (JComboBox) e.getSource();
				startidx = jcb.getSelectedIndex(); //

			}
		});

		arriveC.addActionListener(new ActionListener() { // 콤보박스 이벤트-광주 선택

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				arriveidx = jcb.getSelectedIndex();

			}
		});

		search.addActionListener(new ActionListener() { // 콤보박스 이벤트-광주 선택

			@Override
			public void actionPerformed(ActionEvent e) {
				if(startidx == arriveidx)
					System.out.println("같은 지역입니다.");
				else
					System.out.println("startidx: " + startidx + " arriveidx: " + arriveidx);

			}
		});

	}

}
