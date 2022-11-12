package Map;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class RestArea implements Manageable {


	String roadtype;
	String restname;
	String number;
	String menu;
	String fac;
	
	ArrayList<String> faclist = new ArrayList<>();
	ArrayList<String> foodlist = new ArrayList<>();

	String gname = "휘발유";
	String dname = "경유";
	String lname = "lpg";

	int gasoline;
	int diesel;
	int lpg;

	String electric;
	 String hydrogen;

	RestArea() {	}

	@Override
	public void read(Scanner scan) {
		roadtype = scan.next();
		restname = scan.next();
		number = scan.next();
		while (true) {
			fac = scan.next();
			if (fac.equals("E"))
				break;
			faclist.add(fac);
		}

		while (true) {
			menu = scan.next();
			if (menu.equals("E"))
				break;
			foodlist.add(menu);
		}

		gasoline = scan.nextInt();
		diesel = scan.nextInt();
		lpg = scan.nextInt();

		electric = scan.next();
		hydrogen = scan.next();
	}

	@Override
	public void print() {
		System.out.format("[%s선] %s휴게소 (T.%12s)\n",roadtype, restname, number);
		printfac();
		printmenu();
		printgas();
		printcharge();
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

	public boolean matches(String[] kwd) {

		return true;
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

	public boolean check(int n) {
		if (n > 0)
			return true;
		return false;
	}

	public void printfac() {
		indent();
		System.out.format("편의시설:");
		for (String s : faclist) {
			System.out.format(s + " ");
		}
		System.out.println();
	}

	public void printmenu() {
		indent();
		System.out.format("음식:");
		for (String s : foodlist) {
			System.out.format(s + " ");
		}
		System.out.println();
	}

	public void printgas() {
		indent();
		System.out.format("(%s):%4d원 | (%s):%4d원 | (%s):%4d원\n", gname, gasoline, dname, diesel, lname, lpg);
	}

	public void printcharge() {
		indent();
		System.out.format("(전기):%s | (수소):%s\n", electric, hydrogen);
		System.out.println();
	}

	void indent() {
		System.out.print("  ");
	}
}
