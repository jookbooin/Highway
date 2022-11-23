package Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import facade.UIData;
import mgr.Manageable;
import mgr.Manager;

public class InnerRestlist implements Manageable ,UIData {
	Pathlist pathlist;
	public ArrayList<RestArea> restlist = new ArrayList<>();
	ArrayList<RestArea> sublist;
	

	public InnerRestlist(Pathlist pathlist) {
		this.pathlist = pathlist;
	}
	void search() {
//		System.out.println("휴게소이름 입력");
//		RestArea currest = HighWay.restMgr.search();
//
//		int loIdx = restlist.indexOf(currest); // 현재위치
//		sublist = new ArrayList<>(restlist.subList(loIdx, restlist.size() - 1)); // 현재위치 ~ 경로끝
//
//		System.out.println("1.주유소 검색 2.충전소 검색");
//		int num1 = Manager.sc.nextInt();
//		int num2;
//		if (num1 == 1) {
//			System.out.println("1.휘발유 2.경유 3.충전소");
//			num2 = Manager.sc.nextInt();
//			compgas(sublist, num2);
//			printsubgas();
//		}
//
//		if (num1 == 2) {
//			System.out.println("1.전기 2.수소");
//			num2 = Manager.sc.nextInt();
//			printsubcharge(num2);
//		}
		
		
		
	}

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub

	}

	@Override
	public void print() {
		for (RestArea ra : restlist) {
			Manager.indent();
			Manager.indent();
			System.out.print((restlist.indexOf(ra) + 1) + ".");
			ra.printname();
		}

	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean matches(String[] kwdArr) {
		// TODO Auto-generated method stub
		return false;
	}

	void printsubgas() {
		for (RestArea ra : sublist) {
			System.out.print(ra.restname + ":");
			ra.printgas();
		}
	}

	void printsubcharge(int num2) {
		if (num2 == 1) {
			for (RestArea ra : sublist) {
				if (ra.electric.matches("O")) {
					System.out.print(ra.restname + ":");
					ra.printcharge();
				}
			}
		}
		if (num2 == 2) {
			for (RestArea ra : sublist) {
				if (ra.hydrogen.matches("O")) {
					System.out.print(ra.restname + ":");
					ra.printcharge();
				}
			}
		}

	}

	void compgas(ArrayList<RestArea> sublist, int num2) {

		switch (num2) {
		case 1:
			Collections.sort(sublist, new Comparator<RestArea>() {
				@Override
				public int compare(RestArea o1, RestArea o2) {
					return o1.gasoline - o2.gasoline;
				}
			});
			break;
		case 2:
			Collections.sort(sublist, new Comparator<RestArea>() {
				@Override
				public int compare(RestArea o1, RestArea o2) {
					return o1.diesel - o2.diesel;
				}
			});
			break;
		case 3:
			Collections.sort(sublist, new Comparator<RestArea>() {
				@Override
				public int compare(RestArea o1, RestArea o2) {
					return o1.lpg - o2.lpg;
				}
			});
			break;
		}
	}

	@Override
	public void set(Object[] uitexts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getUiTexts() {
//		String[] texts = new String[5];
//		
//		texts[0] = pathlistnum;
//		texts[1] = HighWay.pathMgr.find(pathID).start;
//		texts[2] = HighWay.pathMgr.find(pathID).arrive;
//		texts[3] = passrestname;
//		return texts;
		return null;
	}

}
