package GUI;

import Map.HighWay;

//실행 화면 
public class STARTGUI {
	 static HighWay highway = HighWay.getInstance();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		highway.run();
		new GUIMain();
	}

}
