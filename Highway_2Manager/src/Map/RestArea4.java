package Map;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable4;

public class RestArea4 implements Manageable4 {

	int restnum;

	public String restname;
	String number;
	String menu;

	String fac;
	ArrayList<String> faclist = new ArrayList<>();
	
	String gname = "휘발유";
	String dname = "경유";
	String lname = "lpg";

	protected int gasoline;
	protected int diesel;
	protected int lpg;

	protected String electric;
	protected String hydrogen;

	protected RestArea4(int restnum) {
		this.restnum = restnum;
	}

	@Override
	public void read(Scanner scan) {
		restnum = scan.nextInt();
		restname = scan.next();
		number = scan.next();
		while (true) {
			fac = scan.next();
			if (fac.equals("E"))
				break;
			faclist.add(fac);
		}

		menu = scan.next();
		gasoline = scan.nextInt();
		diesel = scan.nextInt();
		lpg = scan.nextInt();

		electric = scan.next();
		hydrogen = scan.next();
	}

	@Override
	public void print() {
		System.out.format("%d.%s휴게소 (T.%12s)\n", restnum, restname, number);
		System.out.format("  편의시설:");
		for (String s : faclist) {
			System.out.format(s + " ");
		}

		System.out.println();
		indent();
		printmenu();
		indent();
		printgas();
		indent();
		printcharge();
		System.out.println();
	}

	@Override
	public boolean matches(String kwd) {
		if (restname.equals(kwd))
			return true;
		if (electric.equals(kwd))
			return true;
		if (hydrogen.equals(kwd))
			return true;
		if (menu.equals(kwd))
			return true;
		return false;
	}

	public boolean matches(int num) {
		if (gasoline == num)
			return true;
		if (diesel == num)
			return true;
		if (lpg == num)
			return true;
		return false;
	}

	public void printmenu() {
		System.out.format("대표메뉴:%s\n", menu);
	}

	public void printgas() {
		System.out.format("(%s):%4d원 | (%s):%4d원 | (%s):%4d원\n", gname, gasoline, dname, diesel, lname, lpg);
	}

	public void printcharge() {
		System.out.format("(전기):%s | (수소):%s\n", electric, hydrogen);
	}

	void indent() {
		System.out.print("  ");
	}
}
