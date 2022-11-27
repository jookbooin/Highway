package Map;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class Direction implements Manageable {

	public String startID;
	public String arriveID;	
	String start;
	String arrive;

	public ArrayList<Path> pathlist = new ArrayList<>();

	void addPathlist(Path path) {
		pathlist.add(path);
	}

	@Override
	public void read(Scanner scan) {
		startID = scan.next();
		arriveID= scan.next();
		start = scan.next();
		arrive = scan.next();
	}

	@Override
	public void print() {
		System.out.format("[Ãâ¹ß:%s(%s)  µµÂø:%s(%s)] \n", start,startID, arrive,arriveID);
		printlist();
	}

	@Override
	public boolean matches(String kwd) {
		if (kwd.length() == 0)
			return true;
		if (startID.equals(kwd))
			return true;
		if (start.equals(kwd))
			return true;
		if (arrive.equals(kwd))
			return true;
		return false;
	}
	
	public boolean matches(String startID, String arriveID) {
		if(startID.equals(this.startID)&& arriveID.equals(this.arriveID))
			return true;
		return false;
	}

	@Override
	public boolean matches(String[] kwdArr) {
		for (String kwd : kwdArr) {
			if (matches(kwd)) {
				return true;
			}
		}
		return false;
	}


	void printlist() {
		for (Path pl : pathlist)
			pl.print();
		System.out.println();
	}


}
