package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RestTopPanel extends JPanel {
	// Item �˻� ���� ��� �г� �����ϱ�
    JTextField kwdTextField = new JTextField("",20 );   //�Է��ϴ� �κ� ũ��
    
    void setupTopPane(TableSelectionDemo tableDemo) {
    	JPanel topPane = new JPanel();
    	
    	//��� ��ư1
        JButton detail = new JButton("�󼼺���");
        topPane.add(detail, BorderLayout.LINE_START);
        topPane.add(kwdTextField, BorderLayout.CENTER);
        
        //��� ��ư2
        JButton search = new JButton("�˻�");
        topPane.add(search, BorderLayout.LINE_END);
        add(topPane, BorderLayout.PAGE_START);

        detail.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (e.getActionCommand().equals("�󼼺���")) {
        			tableDemo.showDetail();
            	}
           }
        });
        
        search.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (e.getActionCommand().equals("�˻�")) {
        			tableDemo.loadData(kwdTextField.getText());
            	}
           }
        });
    }
}
