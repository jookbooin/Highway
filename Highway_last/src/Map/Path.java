package Map;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class Path implements Manageable {

	String pathID;
	String start;
	String startIC;
	String arriveIC;
	String arrive;

	ArrayList<Pathlist> pathlist = new ArrayList<>();

	void addPathlist(Pathlist pl) {
		pathlist.add(pl);
	}

	@Override
	public void read(Scanner scan) {
		pathID = scan.next();
		start = scan.next();
		arrive = scan.next();
		startIC = scan.next();
		arriveIC = scan.next();

	}

	@Override
	public void print() {
		printIC();
		printlist();
	}

	@Override
	public boolean matches(String kwd) {
		if (pathID.equals(kwd))
			return true;
		if (start.equals(kwd))
			return true;
		if (startIC.equals(kwd))
			return true;
		if (arriveIC.equals(kwd))
			return true;
		if (arrive.equals(kwd))
			return true;
		return false;
	}

	@Override
	public boolean matches(String[] kwdArr) {
		// TODO Auto-generated method stub
		return false;
	}

	void printIC() {
		System.out.format("[%s->%s] 시작IC:%sIC 종료IC:%sIC\n", start, arrive, startIC, arriveIC);
	}

	void printlist() {
		for (Pathlist pl : pathlist)
			pl.print();
		System.out.println();
	}

}
