package Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import facade.UIData;
import mgr.Manageable;
import mgr.Manager;

public class Path implements Manageable, UIData {

	String pathID;
	public String pathnum;
	Direction direc;
	String rest;
//	public InnerRestlist irl = new InnerRestlist(this);
	public ArrayList<RestArea> restlist = new ArrayList<>();
	String highwayname = "";

	// 경로상 휴게소 전체
	Set<String> restset = new HashSet<>(); // 경로에 지나는 고속도로들
	
	ArrayList<RestArea> sublist;
	
	@Override
	public void read(Scanner scan) {

		pathID = scan.next();
		pathnum = scan.next();

		while (true) {
			rest = scan.next();
			if (rest.equals("0"))
				break;
			RestArea restarea = HighWay.restMgr.find(rest);

			restlist.add(restarea);
			restset.add(restarea.waytype);
		}

		direc = HighWay.direcMgr.find(pathID);
		direc.addPathlist(this);
	}

	@Override
	public void print() {
		printPathlist();
		printAllrest();
	}

	@Override
	public boolean matches(String kwd) {
//		if (kwd.length() == 0)
//			return true;
		if(pathID.equals(kwd))
			return true;
		
		return false;
	}

	@Override
	public boolean matches(String[] kwdArr) {
		// TODO Auto-generated method stub
		return true;
	}

	void printPathlist() {
		Manager.indent();
		System.out.printf("[경로%s]:", pathnum);

		Iterator<String> it = restset.iterator();
		while (it.hasNext()) {
			highwayname += it.next() + " ";

		}
		System.out.println(highwayname);
	}

	void printAllrest() {
		for (RestArea ra : restlist) {
			Manager.indent();
			Manager.indent();
			
			System.out.print((restlist.indexOf(ra) + 1) + ".");
			ra.printname();
		}
		System.out.println();
	}

	void search() {
//		irl.search();
		System.out.println("휴게소이름 입력");
		RestArea currest = HighWay.restMgr.search();

		int loIdx = restlist.indexOf(currest); // 현재위치
		sublist = new ArrayList<>(restlist.subList(loIdx, restlist.size() - 1)); // 현재위치 ~ 경로끝

		System.out.println("1.주유소 검색 2.충전소 검색");
		int num1 = Manager.sc.nextInt();
		int num2;
		if (num1 == 1) {
			System.out.println("1.휘발유 2.경유 3.충전소");
			num2 = Manager.sc.nextInt();
			compgas(sublist, num2);
			printsubgas();
		}

		if (num1 == 2) {
			System.out.println("1.전기 2.수소");
			num2 = Manager.sc.nextInt();
			printsubcharge(num2);
		}
	}
	
	void printsubgas() {
		for (RestArea ra : sublist) {
			System.out.print(ra.restname + ":");
			ra.printgas();
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
	

	@Override
	public void set(Object[] uitexts) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getUiTexts() {
		String[] texts = new String[5];
	
		texts[0] = pathnum;
		texts[1] = HighWay.direcMgr.find(pathID).start;
		texts[2] = HighWay.direcMgr.find(pathID).arrive;
		texts[3] = highwayname;
		return texts;
	}
}
