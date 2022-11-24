package Map;

import java.util.ArrayList;
import java.util.Scanner;

import Highway_mgr.RestMgr;
import facade.UIData;
import mgr.Manageable;

public class RestArea implements Manageable, UIData {
	int  guinum=0;
	int  num; // 전체 restArea의 개수를 알기위해서
	int pathnum = HighWay.restnum; // 경로 생성했을때 번호 - GUI 표시용도
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

	int gasoline;
	int diesel;
	int lpg;

	String electric;
	String hydrogen;

	RestArea() {
	}

	
	@Override
	public void read(Scanner scan) {
		HighWay.restnum++;
		num = HighWay.restnum;
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
		System.out.print(num + ".");
		printname();
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
		if (waytype.contains(kwd))			//""가지면 초기화
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

	@Override
	public void set(Object[] uitexts) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getUiTexts() {
		// TODO Auto-generated method stub
		String[] texts = new String[5];
		
		texts[0] = ""+(pathnum+1);
		texts[1] = waytype;
		texts[2] = restname;
		texts[3] = number;
		return texts;
	}

//
//
//
	
	public void printname() {
		System.out.format("[%s선] %s휴게소 (T.%12s)\n", waytype, restname, number);
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

}
