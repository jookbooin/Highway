package GUI;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import GUI_Panel.ImagePanel;
import GUI_Panel.MainCenterPanel;
import GUI_Panel.MainTopPanel;


//메인 프레임 
@SuppressWarnings("serial")
public class GUIMain extends JFrame {
	
	GUIMain(){
		this.setTitle("경로 선택");

		MainTopPanel maintoppanel = new MainTopPanel();		
		
		//maintoppanel 조회 버튼을 통해서 direction 인덱스 전달
		//인덱스 통해서 path에서 경로 찾아서 table로 표현
		MainCenterPanel maincenterPanel = new MainCenterPanel();// 
//		
		this.add(maintoppanel,BorderLayout.NORTH); 		//combobox
		this.add(maincenterPanel ,BorderLayout.CENTER); //리스트 나오도록
		
		
//		ImagePanel imagepanel = new ImagePanel(new ImageIcon("./image/main.jpg").getImage());
//		setPreferredSize(new Dimension(500,400));
//		add(imagepanel ,BorderLayout.CENTER);
//		pack();
		
		this.setSize(900,500);				//JFrame크기
		this.setResizable(false);			//창의 크기를 변경하지 못하게
		this.setLocationRelativeTo(null);	//창이 가운데 
		this.setVisible(true);				//창이 보이게 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}
	
}
