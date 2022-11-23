package Map;

import java.util.Scanner;

import mgr.Factory;
import mgr.Manager;

public class HighWay {
	
	private static HighWay highway = null;
	private HighWay() {}
	public static HighWay getInstance() {
		if (highway == null)
			highway = new HighWay();
		return highway;
	}
	
	static int restnum = 0;
	public static Manager<RestArea> restMgr = new Manager<>();
	public static Manager<Directioin> direcMgr = new Manager<>();
	public static Manager<Path> pathMgr = new Manager<>();

	public void run() {
		restMgr.readAll("restarea.txt", new Factory<RestArea>() {
			@Override
			public RestArea create(Scanner scan) {
				return new RestArea();
			}
		});
		System.out.println("\n=================고속도로 종류 =================");
		restMgr.printAll();

		direcMgr.readAll("direction.txt", new Factory<Directioin>() {
			@Override
			public Directioin create(Scanner scan) {
				return new Directioin();
			}
		});
		System.out.println("\n=================경로 종류 =================");

		pathMgr.readAll("path.txt", new Factory<Path>() {
			@Override
			public Path create(Scanner scan) {
				return new Path();
			}
		});

		pathMgr.printAll();
//
//		System.out.println("\n=================경로 검색 =================");
//		System.out.println("pathMgr");
//		Path selectPath = pathMgr.mlist.get(0);
//		System.out.print(selectPath.pathID + " " + selectPath.pathlistnum);
//		selectPath.search();///경로 휴게소내에서 검색 
//		selectPath.search();
	}

	public static void main(String[] args) {
		HighWay hw = new HighWay();
		hw.run();
	}
}
