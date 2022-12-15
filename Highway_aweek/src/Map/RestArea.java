package Map;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import mgr.Manageable;

public class RestArea implements Manageable {
	String waytype;
	public String restname;
	String number;
	String menu;
	String fac;

	ArrayList<String> faclist = new ArrayList<>();
	ArrayList<String> foodlist = new ArrayList<>();

	String gname = "휘발유";
	String dname = "경유";
	String lname = "lpg";

	public int gasoline;
	public int diesel;
	public int lpg;

	String electric;
	String hydrogen;

	RestArea() {
	}

	public RestArea(String restname) {
		this.restname = restname;

	}

	@Override
	public void read(Scanner scan) {
		waytype = scan.next();
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
		toString();
		indent();
		printfac();
		indent();
		printmenu();
		indent();
		printgas();
		indent();
		printcharge();
	}

	@Override
	public boolean matches(String kwd) {
		if (waytype.contains(kwd)) // ""가지면 초기화
			return true;
		if (kwd.length() >= 2) {
			if (waytype.equals(kwd))
				return true;
			if (restname.equals(kwd))
				return true;
			if (number.contains(kwd))
				return true;
			if (electric.equals(kwd))
				return true;
			if (hydrogen.equals(kwd))
				return true;
			if (menu.equals(kwd))
				return true;
			if (("" + gasoline).equals(kwd))
				return true;
			if (("" + diesel).equals(kwd))
				return true;
			if (("" + lpg).equals(kwd))
				return true;
			for (String s : faclist) // 한개라도 있으면 true
				if (s.equals(kwd))
					return true;
			for (String s : foodlist)// 한개라도 있으면 true
				if (s.equals(kwd))
					return true;
		}
		return false;
	}

	public boolean matches(String[] kwdArr) {
		for (String kwd : kwdArr) {
			if (matches(kwd)) {
				return true;
			}
		}
		return false;
	}

	public void printfac() {

		System.out.format("편의시설:");
		for (String s : faclist) {
			System.out.format(s + " ");
		}
		System.out.println();
	}

	public void printmenu() {

		System.out.format("음식:");
		for (String s : foodlist) {
			System.out.format(s + " ");
		}
		System.out.println();
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

	@Override
	public boolean matches(String startID, String arriveID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(restname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestArea other = (RestArea) obj;
		return Objects.equals(restname, other.restname);
	}

	@Override
	public String toString() {
		return "[" + waytype + "선] " + restname + "휴게소 (T." + number + ")";
	}

	public Integer getgasoline() {

		return gasoline;

	}

	public Integer getdiesel() {

		return diesel;

	}

	public Integer getlpg() {

		return lpg;
	}

}
