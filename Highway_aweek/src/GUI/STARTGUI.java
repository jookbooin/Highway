package GUI;

import Map.HighWay;

//���� ȭ�� 
public class STARTGUI {
	 static HighWay highway = HighWay.getInstance();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		highway.run();
		new GUIMain();
	}

}
