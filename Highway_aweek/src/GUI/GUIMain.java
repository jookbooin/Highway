package GUI;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import GUI_Panel.ImagePanel;
import GUI_Panel.MainCenterPanel;
import GUI_Panel.MainTopPanel;


//���� ������ 
@SuppressWarnings("serial")
public class GUIMain extends JFrame {
	
	GUIMain(){
		this.setTitle("��� ����");

		MainTopPanel maintoppanel = new MainTopPanel();		
		
		//maintoppanel ��ȸ ��ư�� ���ؼ� direction �ε��� ����
		//�ε��� ���ؼ� path���� ��� ã�Ƽ� table�� ǥ��
		MainCenterPanel maincenterPanel = new MainCenterPanel();// 
//		
		this.add(maintoppanel,BorderLayout.NORTH); 		//combobox
		this.add(maincenterPanel ,BorderLayout.CENTER); //����Ʈ ��������
		
		
//		ImagePanel imagepanel = new ImagePanel(new ImageIcon("./image/main.jpg").getImage());
//		setPreferredSize(new Dimension(500,400));
//		add(imagepanel ,BorderLayout.CENTER);
//		pack();
		
		this.setSize(900,500);				//JFrameũ��
		this.setResizable(false);			//â�� ũ�⸦ �������� ���ϰ�
		this.setLocationRelativeTo(null);	//â�� ��� 
		this.setVisible(true);				//â�� ���̰� 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}
	
}
