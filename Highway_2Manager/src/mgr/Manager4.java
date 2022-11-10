package mgr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager4<T extends Manageable4> {
	static public Scanner sc = new Scanner(System.in);
	
	public ArrayList<T> mList = new ArrayList<>();
	
	
	public T find(String name , String next) {
		if (name.equalsIgnoreCase("x"))
			return mList.get(0);
		
		for (T p : mList) {
			if (p.matches(name)&&p.matches(next))
				return p;

		}
		
		System.out.println("존재하지 않습니다.");
		return null;
	}
	
	public T find(String name) {
		if (name.equalsIgnoreCase("x"))
			return mList.get(0);
		
		for (T p : mList) {
			if (p.matches(name))
				return p;
			
		}
		System.out.println("존재하지 않습니다.");
		return null;
	}

	public void readAll(String filename, Factory4<T> fac) {
		Scanner filein = null;
		try {
			filein = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		while (filein.hasNext()) {
			T m = fac.create(filein); // -> HighWay(String , name)
			m.read(filein);
			mList.add(m);
			//m.print();
		}
		filein.close();
	}
	
	
	public void printAll() {
		
		for (T p  : mList) 
			p.print();
	}

	public T search() {
		String kwd = null;
		while (true) {
			System.out.print(">>");
			kwd = sc.next();
			if (kwd.contentEquals("end"))
				break;
			for (T m : mList) {
				if (m.matches(kwd))
					return m;
			}
		}
		return null;
	}
	
}