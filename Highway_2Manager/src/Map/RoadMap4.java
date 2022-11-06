package Map;

import java.util.Scanner;

import mgr.Factory;
import mgr.Manager;

public class RoadMap4 {

	Manager<HighWay4> hMgr = new Manager<HighWay4>();

	String roadType;
	String direction;

	void run() {
		hMgr.readAll("HighWay.txt", new Factory<HighWay4>() {
			@Override
			public HighWay4 create(Scanner scan) {
				roadType = scan.next();
				direction = scan.next();
				return new HighWay4(roadType, direction);
			}
		});
		search();
	}

	void search() {
		System.out.println("=========== 검색 메뉴 ============");
		while (true) {
			System.out.print("현재 고속도로 종류(종료 end):");
			String rtype = Manager.sc.next();
			if (rtype.equals("end"))
				break;
			System.out.println("방향(상행/하행):");
			String direc = Manager.sc.next();
			HighWay4 driveWay = find(rtype, direc); // 고속도로 종류 -> 리스트 가져오기
			driveWay.search();
		}
	}

	HighWay4 find(String roadtype, String direction) {
		for (HighWay4 h : hMgr.mList) // 고속도로(경부선 하행) 가져옴
			if (h.matches(roadtype) && h.matches(direction)) {
				return h;
			}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RoadMap4 rm = new RoadMap4();
		rm.run();
	}
}
