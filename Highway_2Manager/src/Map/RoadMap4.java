package Map;

import java.util.Scanner;

import mgr.Factory4;
import mgr.Manager4;

public class RoadMap4 {

	Manager4<HighWay4> hMgr = new Manager4<HighWay4>();

	String roadType;
	String direction;

	void run() {
		hMgr.readAll("HighWay.txt", new Factory4<HighWay4>() {
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
		HighWay4 driveWay = null;
		System.out.println("=========== 검색 메뉴 ============");
		while (true) {
			System.out.print("현재 고속도로 종류(종료 end):");
			String rtype = Manager4.sc.next();
			if (rtype.equals("end"))
				break;
			System.out.println("방향(상행/하행):");
			String direc = Manager4.sc.next();
			driveWay = hMgr.find(rtype,direc); // 고속도로 종류 -> 리스트 가져오기
			driveWay.search();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RoadMap4 rm = new RoadMap4();
		rm.run();
	}
}
