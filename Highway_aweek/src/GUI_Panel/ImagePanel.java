package GUI_Panel;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;

//(패널크기에맞)게 에 이미지 올리는 클래스
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