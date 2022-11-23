package Map;

import java.util.ArrayList;
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
	Directioin direc;
	String rest;
//	public InnerRestlist irl = new InnerRestlist(this);
	public ArrayList<RestArea> restlist = new ArrayList<>();
	String highwayname = "";

	// 경로상 휴게소 전체
	Set<String> restset = new HashSet<>(); // 경로에 지나는 고속도로들

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
