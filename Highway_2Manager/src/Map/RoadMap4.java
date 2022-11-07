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
		System.out.println("=========== �˻� �޴� ============");
		while (true) {
			System.out.print("���� ��ӵ��� ����(���� end):");
			String rtype = Manager4.sc.next();
			if (rtype.equals("end"))
				break;
			System.out.println("����(����/����):");
			String direc = Manager4.sc.next();
			driveWay = hMgr.find(rtype,direc); // ��ӵ��� ���� -> ����Ʈ ��������
			driveWay.search();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RoadMap4 rm = new RoadMap4();
		rm.run();
	}
}
