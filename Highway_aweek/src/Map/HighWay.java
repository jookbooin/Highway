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
	public static Manager<Direction> direcMgr = new Manager<>();
	public static Manager<Path> pathMgr = new Manager<>();

	public void run() {
		restMgr.readAll("restarea.txt", new Factory<RestArea>() {
			@Override
			public RestArea create(Scanner scan) {
				return new RestArea();
			}
		});
//		System.out.println("\n=================고속도로 종류 =================");
//		restMgr.printAll();

		direcMgr.readAll("direction.txt", new Factory<Direction>() {
			@Override
			public Direction create(Scanner scan) {
				return new Direction();
			}
		});
//		direcMgr.printAll();
		System.out.println("\n=================경로 종류 =================");
		

		pathMgr.readAll("path.txt", new Factory<Path>() {
			@Override
			public Path create(Scanner scan) {
				return new Path();
			}
		});
		direcMgr.printAll();
//		
//		Direction direc = direcMgr.find("0", "5");
//		System.out.println(direc.arrive);
//		for (Path p : direc.pathlist) {
//			System.out.println(p.pathnum);
//			System.out.println(p.highwayname);
//		}
//		
//		direcMgr.printAll();

//		System.out.println("\n=================경로 검색 =================");
//		System.out.println("pathMgr");
//		Path selectPath = pathMgr.mlist.get(0);
//		System.out.print(selectPath.pathID + " " + selectPath.pathnum);
//		selectPath.search();///경로 휴게소내에서 검색 

	}

	public static void main(String[] args) {
		HighWay hw = new HighWay();
		hw.run();
	}
}
