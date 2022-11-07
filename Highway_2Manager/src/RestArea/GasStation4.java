package RestArea;

import Map.RestArea4;

public class GasStation4 extends RestArea4 {


	protected GasStation4(int restnum) {
		super(restnum);
		// TODO Auto-generated constructor stub
	}
	
	String gname = "휘발유";
	String dname = "경유";
	String lname = "lpg";
	

	@Override
	public void print() {
		System.out.format("%s-(%s):%4d원 | (%s):%4d원 | (%s):%4d원\n",restname, gname, gasoline, dname, diesel, lname, lpg);
	}
	
	
}
