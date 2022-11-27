package GUI_Panel;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;

//(�г�ũ�⿡��)�� �� �̹��� �ø��� Ŭ����
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
		Dimension d = getSize();			//���� ImagePanel dimension ũ�� �������� �� ->
											//ũ�⸦ �������� ���� ��ġ
		g.drawImage(img,0,0,d.width,d.height,null);  //�г��� ũ��(d) ��ŭ�� ���� ���� ���� 
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
