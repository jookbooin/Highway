package GUI_Panel;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;

//(�г�ũ�⿡��)�� �� �̹��� �ø��� Ŭ����
@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	private Image img;

	 public ImagePanel(Image img) {
	      this.img = img;
	      setPreferredSize(new Dimension(400, 400));
	      setLayout(null);
	  }
	  
	  public void paintComponent(Graphics g) {
	      Dimension d = getSize();
	      
	      g.drawImage(img,0,0,d.width, d.height,null);
	  }
	  
	}