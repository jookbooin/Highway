package Map;

import java.util.Scanner;

import mgr.Factory;
import mgr.Manager;

public class HighWay {

	static Manager<RestArea> restMgr = new Manager<>();
	Manager<Path> pathMgr = new Manager<>();

	void run() {
		restMgr.readAll("restarea.txt", new Factory<RestArea>() {
			@Override
			public RestArea create(Scanner scan) {
				// TODO Auto-generated method stub
				return new RestArea();
			}
		});
		restMgr.printAll();
	
	
	pathMgr.readAll("path.txt", new Factory<Path>() {
		@Override
		public Path create(Scanner scan) {
			// TODO Auto-generated method stub
			return new Path();
		}
	});
	restMgr.printAll();
	
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HighWay hw = new HighWay();
		hw.run();
	}
}
