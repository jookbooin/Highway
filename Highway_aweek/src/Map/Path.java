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


// ��� ǥ���Ҷ� UIDATA�� ��� / ����ð� / �Ÿ� / �����  -> ������ �Է¹޾ƾ� ��  
public class Path implements Manageable ,UIData{

	String startID;
	String arriveID;
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

		startID = scan.next();
		arriveID = scan.next();
		pathnum = scan.next();

		while (true) {
			rest = scan.next();
			if (rest.equals("0"))
				break;
			RestArea restarea = HighWay.restMgr.find(rest);

			restlist.add(restarea);
			restset.add(restarea.waytype);
		}
		
		//���� ��� �а� ��θ� �����Ѵ�.
		Iterator<String> it = restset.iterator();
		while (it.hasNext()) {
			highwayname += it.next() + " ";
		}
		direc = HighWay.direcMgr.find(startID,arriveID);	//���� , �� ���� direc ã��
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
		if(startID.equals(kwd))
			return true;
		
		return false;
	}

	@Override
	public boolean matches(String[] kwdArr) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	@Override
	public String[] getUiTexts() {
		String[] texts = new String[4];
		texts[0] = highwayname;    			//���
		texts[1] = "����ð�"	;
		texts[2] = "����Ÿ�"	;
		texts[3] = "�����"	;
		return texts;
	}
	

	void printPathlist() {
		Manager.indent();
		System.out.printf("[���%s]:", pathnum);

		
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
	public boolean matches(String startID, String arriveID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
