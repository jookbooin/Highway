package Map;

import java.util.ArrayList;
import java.util.Scanner;

import facade.UIData;
import mgr.Manageable;

public class Direction implements Manageable , UIData{

	public String pathID;
	String start;
	String startIC;
	String arriveIC;
	String arrive;

	public ArrayList<Path> pathlist = new ArrayList<>();

	void addPathlist(Path path) {
		pathlist.add(path);
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
		if (kwd.length() == 0)
			return true;
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
		for (String kwd : kwdArr) {
			if (matches(kwd)) {
				return true;
			}
		}
		return false;
	}

	void printIC() {
		System.out.format("[%s->%s] 시작IC:%sIC 종료IC:%sIC\n", start, arrive, startIC, arriveIC);
	}

	void printlist() {
		for (Path pl : pathlist)
			pl.print();
		System.out.println();
	}

	@Override
	public void set(Object[] uitexts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getUiTexts() {        //"번호","출발", "목적지", "시작IC","종료IC"
		// TODO Auto-generated method stub
		String[] texts = new String[5];
		texts[0] = pathID;
		texts[1] = start;
		texts[2] = arrive;
		texts[3] = startIC;
		texts[4] = arriveIC;
		return texts;
	}

}
