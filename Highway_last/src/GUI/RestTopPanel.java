package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RestTopPanel extends JPanel {
	// Item 검색 탭의 상단 패널 구성하기
    JTextField kwdTextField = new JTextField("",20 );   //입력하는 부분 크기
    
    void setupTopPane(TableSelectionDemo tableDemo) {
    	JPanel topPane = new JPanel();
    	
    	//상단 버튼1
        JButton detail = new JButton("상세보기");
        topPane.add(detail, BorderLayout.LINE_START);
        topPane.add(kwdTextField, BorderLayout.CENTER);
        
        //상단 버튼2
        JButton search = new JButton("검색");
        topPane.add(search, BorderLayout.LINE_END);
        add(topPane, BorderLayout.PAGE_START);

        detail.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (e.getActionCommand().equals("상세보기")) {
        			tableDemo.showDetail();
            	}
           }
        });
        
        search.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (e.getActionCommand().equals("검색")) {
        			tableDemo.loadData(kwdTextField.getText());
            	}
           }
        });
    }
}
