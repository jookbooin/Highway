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

	// ��λ� �ްԼ� ��ü
	Set<String> restset = new HashSet<>(); // ��ο� ������ ��ӵ��ε�
	
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
		System.out.printf("[���%s]:", pathnum);

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
		System.out.println("�ްԼ��̸� �Է�");
		RestArea currest = HighWay.restMgr.search();

		int loIdx = restlist.indexOf(currest); // ������ġ
		sublist = new ArrayList<>(restlist.subList(loIdx, restlist.size() - 1)); // ������ġ ~ ��γ�

		System.out.println("1.������ �˻� 2.������ �˻�");
		int num1 = Manager.sc.nextInt();
		int num2;
		if (num1 == 1) {
			System.out.println("1.�ֹ��� 2.���� 3.������");
			num2 = Manager.sc.nextInt();
			compgas(sublist, num2);
			printsubgas();
		}

		if (num1 == 2) {
			System.out.println("1.���� 2.����");
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
