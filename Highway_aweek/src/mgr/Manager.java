package mgr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager<T extends Manageable> {
	static public Scanner sc = new Scanner(System.in);

	public ArrayList<T> mlist = new ArrayList<>();

	public T find(String name) {
		if (name.equalsIgnoreCase("x"))
			return mlist.get(0);

		for (T p : mlist) {
			if (p.matches(name))
				return p;

		}
		System.out.println("존재하지 않습니다.");
		return null;
	}
	
	public T find(String startID, String arriveID) {
//		if (startID.equalsIgnoreCase("x"))
//			return mlist.get(0);

		for (T p : mlist) {
			if (p.matches(startID,arriveID))
				return p;

		}
		System.out.println("존재하지 않습니다.");
		return null;
	}

	public void readAll(String filename, Factory<T> fac) {
		Scanner filein = null;
		try {
			filein = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		while (filein.hasNext()) {
			T m = fac.create(filein);
			m.read(filein);
			mlist.add(m);

		}
		filein.close();
	}

	public void printAll() {

		for (T p : mlist)
			p.print();
	}

	public T search() {
		String kwd = null;
		while (true) {
			System.out.print(">>");
			kwd = sc.next();
			if (kwd.contentEquals("end"))
				break;
			for (T m : mlist) {
				if (m.matches(kwd))
					return m;
			}
		}
		return null;
	}
	
	//찾은 다음 list로 내보냄?
	public List<T> findAll(String kwd) {
		List<T> results = new ArrayList<>();
		for (T m: mlist)
			if (m.matches(kwd))
				results.add(m);
		return results;
	}

	public static void indent() {
		System.out.print("    ");
	}

//	public T search() {
//		String kwd = null;
//		String[] kwdArr;
//		while (true) {
////			System.out.print("*검색키워드 여러개(빈칸으로 구분):(end)종료");
//			System.out.print(">>");
//			kwd = sc.nextLine();
//			if (kwd.contentEquals("end"))
//				break;
//			kwdArr = kwd.split(" ");
//			for (T m : mlist) {
//				if (m.matches(kwdArr))
//					return m;
//			}
//		}
//		return null;
//
//	}

//		while (true) {
//			System.out.print("*검색키워드 여러개(빈칸으로 구분):(0)종료");
//			line = scan.nextLine();
//			if (line.equals("0"))
//				break;
//			kwdArr = line.split(" ");
//			for (Student st : studentList) {
//				if (st.matches(kwdArr)) {
//					st.print();
//				}
//			}
//		return null;
//	}

}