package RestArea;

import Map.RestArea4;

public class GasStation4 extends RestArea4 {


	protected GasStation4(int restnum) {
		super(restnum);
		// TODO Auto-generated constructor stub
	}
	
	String gname = "�ֹ���";
	String dname = "����";
	String lname = "lpg";
	

	@Override
	public void print() {
		System.out.format("%s-(%s):%4d�� | (%s):%4d�� | (%s):%4d��\n",restname, gname, gasoline, dname, diesel, lname, lpg);
	}
	
	
}
