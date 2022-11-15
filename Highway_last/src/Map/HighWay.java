package Map;

import java.util.Scanner;

import mgr.Factory;
import mgr.Manager;

public class HighWay {
	static int restnum = 0;
	static Manager<RestArea> restMgr = new Manager<>();
	static Manager<Path> pathMgr = new Manager<>();
	static Manager<Pathlist> makeMgr = new Manager<>();

	void run() {
		restMgr.readAll("restarea.txt", new Factory<RestArea>() {
			@Override
			public RestArea create(Scanner scan) {
				return new RestArea();
			}
		});
		System.out.println("\n=================고속도로 종류 =================");
		restMgr.printAll();

		pathMgr.readAll("path.txt", new Factory<Path>() {
			@Override
			public Path create(Scanner scan) {
				return new Path();
			}
		});
		System.out.println("\n=================경로 종류 =================");

		makeMgr.readAll("pathmake.txt", new Factory<Pathlist>() {
			@Override
			public Pathlist create(Scanner scan) {
				return new Pathlist();
			}
		});

		pathMgr.printAll();

		System.out.println("\n=================경로 검색 =================");
//	pathMgr.searchArray(); // [시작,도착,시작ic,도착ic] 입력을 통해 Path 찾기(Path id = 3 인 경로)
//						   // path에서 세부적으로  pathlist 선택 (경부 or 영동 중부 경부.. 지나는 pathlist)

		System.out.println("pathMgr");
		Pathlist selectPath = makeMgr.mlist.get(0);
		System.out.print(selectPath.pathID + " " + selectPath.pathlistnum);
		
//		selectPath.search();///경로 휴게소내에서 검색 
		selectPath.search();



	}

	public static void main(String[] args) {
		HighWay hw = new HighWay();
		hw.run();
	}
}
