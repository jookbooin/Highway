package mgr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager<T extends Manageable> {
	static public Scanner sc = new Scanner(System.in);
	public ArrayList<T> mList = new ArrayList<>();
	
	public void readAll(String filename, Factory<T> fac) {
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
	
	public void search() {
		
	}
	
}