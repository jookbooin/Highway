package RestArea;

import Map.RestArea4;

public class ChargeStation4 extends RestArea4{
	
	
//	protected ChargeStation4(int restnum) {
//		super(restnum);
//		// TODO Auto-generated constructor stub
//	}
	@Override
	public void print() {
		System.out.format("%s�ްԼ�-(����):%s | (����):%s\n",restname, electric, hydrogen);
	}

}
