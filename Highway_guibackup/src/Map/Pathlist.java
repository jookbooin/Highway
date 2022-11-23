package Map;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import facade.UIData;
import mgr.Manageable;
import mgr.Manager;

public class Pathlist implements Manageable, UIData {

	String pathID;
	String pathlistnum;
	Path path;
	String rest;
	public InnerRestlist irl = new InnerRestlist(this);
	String passrestname = "";

	// 경로상 휴게소 전체
	Set<String> restset = new HashSet<>(); // 경로에 지나는 고속도로들

	@Override
	public void read(Scanner scan) {

		pathID = scan.next();
		pathlistnum = scan.next();

		while (true) {
			rest = scan.next();
			if (rest.equals("0"))
				break;
			RestArea restarea = HighWay.restMgr.find(rest);

			irl.restlist.add(restarea);
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

	@Override
	public boolean matches(String kwd) {
		if (kwd.length() == 0)
			return true;
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
		System.out.printf("[경로%s]:", pathlistnum);

		Iterator<String> it = restset.iterator();
		while (it.hasNext()) {
			passrestname += it.next() + " ";

		}
		System.out.println(passrestname);
	}

	void printAllrest() {
		irl.print();
		System.out.println();
	}

	void search() {
		irl.search();
	}

	@Override
	public void set(Object[] uitexts) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getUiTexts() {
		String[] texts = new String[5];
	
		texts[0] = pathlistnum;
		texts[1] = HighWay.pathMgr.find(pathID).start;
		texts[2] = HighWay.pathMgr.find(pathID).arrive;
		texts[3] = passrestname;
		return texts;
	}
}
