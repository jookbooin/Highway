package GUI_Panel;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;

//(패널크기에맞)게 에 이미지 올리는 클래스
@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	private Image img;

	public ImagePanel(Image img) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		setLayout(null);
	}

	public void paintComponent(Graphics g){
		Dimension d = getSize();			//현재 ImagePanel dimension 크기 가져오는 것 ->
											//크기를 가져오고 싶은 위치
		g.drawImage(img,0,0,d.width,d.height,null);  //패널의 크기(d) 만큼에 사진 집어 넣음 
		}

//	public ImagePanel(Image img) {
//		this.img = img;
//		setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
//		setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
//		setLayout(null);
//		
//	}
//	
//	public void paintComponent(Graphics g){
//		g.drawImage(img,0,0,null);
//	}

}
