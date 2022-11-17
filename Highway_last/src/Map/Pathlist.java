package Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import mgr.Manageable;
import mgr.Manager;

public class Pathlist implements Manageable {

	String pathID;
	String pathlistnum;
	Path path;
	String rest;

	ArrayList<RestArea> restlist = new ArrayList<>(); // 경로상 휴게소 전체
	Set<String> restset = new HashSet<>(); // 경로에 지나는 고속도로들
	ArrayList<RestArea> sublist;

	void search() {
		System.out.println("휴게소이름 입력");
		RestArea currest = HighWay.restMgr.search();

		int loIdx = restlist.indexOf(currest); // 현재위치
		sublist = new ArrayList<>(restlist.subList(loIdx, restlist.size() - 1)); // 현재위치 ~ 경로끝
//		while (true) {
//			System.out.println("1.주유소 검색 2.충전소 검색");
//			int num1 = Manager.sc.nextInt();
//			int num2;
//			if (num1 == 1) {
//				System.out.println("1.휘발유 2.경유 3.충전소");
//				num2 = Manager.sc.nextInt();
//				compgas(sublist, num2);
//				printsubgas();
//			}
//
//			if (num1 == 2) {
//				System.out.println("1.전기 2.수소");
//				num2 = Manager.sc.nextInt();
//				printsubcharge(num2);
//			}
//		}
		System.out.println("키워드 입력");
		String elements = Manager.sc.nextLine();
		String[] kwd =elements.split(" ");
		for(RestArea ra : sublist)
			if(ra.matches(kwd))
				ra.print();

	}

	@Override
	public void read(Scanner scan) {

		pathID = scan.next();
		pathlistnum = scan.next();

		while (true) {
			rest = scan.next();
			if (rest.equals("0"))
				break;
			RestArea restarea = HighWay.restMgr.find(rest);

			restlist.add(restarea);
			restset.add(restarea.waytype);
		}

		path = HighWay.pathMgr.find(pathID);
		path.addPathlist(this);
	}

	@Override
	public void print() {
		printPathlist();
		printAllrest();
	}

	void printPathlist() {
		indent();
		System.out.printf("[경로%s]:", pathlistnum);

		Iterator<String> it = restset.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
	}

	void printAllrest() {
		for (RestArea ra : restlist) {
			indent();
			indent();
			ra.printname();
		}
		System.out.println();
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

	void indent() {
		System.out.print("    ");
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
}
