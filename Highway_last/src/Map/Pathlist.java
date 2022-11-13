package Map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import mgr.Manageable;

public class Pathlist implements Manageable {

	String pathID;
	String pathlistnum;
	Path path;
	String rest;
	

	ArrayList<RestArea> restlist = new ArrayList<>(); //경로상 휴게소 전체
	Set<String> restset = new HashSet<>();			  //경로에 지나는 고속도로들 

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
		indent();
		System.out.printf("[경로%s]:",pathlistnum);
		Iterator<String> it = restset.iterator();
		while(it.hasNext()) {
		System.out.print(it.next()+" ");	
		}
		System.out.println();
		
		for (RestArea ra : restlist) {
			indent();
			indent();
			ra.printname();
		}
		System.out.println();
	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		return false;
	}
	void indent() {
		System.out.print("    ");
	}

	@Override
	public boolean matches(String[] kwdArr) {
		// TODO Auto-generated method stub
		return false;
	}
}

	