package GUI;

import java.io.File;

import Map.HighWay;

//���� ȭ�� 
public class STARTGUI {
	 static HighWay highway = HighWay.getInstance();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		highway.run();
		GUIMain gm = new GUIMain();
		gm.run();
//		gm.imagetest();
		
		File path = new File("GUIMain.java");
		System.out.println(path.getAbsolutePath());
	}

}
