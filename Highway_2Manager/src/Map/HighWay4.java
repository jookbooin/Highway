package Map;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

public class HighWay4 implements Manageable {

	Manager<RestArea4> restMgr = new Manager<>();
	
	ArrayList<RestArea4> savelist = new ArrayList<RestArea4>();
	int num = 0;

	String wayname; // 경부선
	String direction; // 하행
	String restname;

	HighWay4(String wayname, String direction) {
		this.wayname = wayname;
		this.direction = direction;
	}

	@Override
	public void read(Scanner scan) {

		if (wayname.equals("경부선") && direction.equals("상행")) {
			restMgr.readAll("kyoungbuUp.txt", new Factory<RestArea4>() {
				@Override
				public RestArea4 create(Scanner scan) {
					// TODO Auto-generated method stub
					return new RestArea4(num);
				}
			});
			print();
			restMgr.printAll();
		}

		if (wayname.equals("경부선") && direction.equals("하행")) {
			restMgr.readAll("kyoungbuDown.txt", new Factory<RestArea4>() {
				@Override
				public RestArea4 create(Scanner scan) {
					// TODO Auto-generated method stub
					return new RestArea4(num);
				}
			});
			System.out.println();
			print();
			restMgr.printAll();
		}
	}

	@Override
	public void print() {
		System.out.format("[%s-%s]\n", wayname, direction);
	}

	@Override
	public boolean matches(String kwd) {
		if (wayname.equals(kwd))
			return true;
		if (direction.equals(kwd))
			return true;
		return false;
	}

	void search() {
		while (true) {
			System.out.print("현재 휴게소 위치(시작은 X 입력):");
			String curLo = Manager.sc.next(); // 건천 휴게소 / X = 1번 휴게소
			RestArea4 curRest = find(curLo);
			if (curRest == null)
				continue;
			searchFunc(curRest);
		}
	}

	void searchFunc(RestArea4 curRest) {
		while (true) {
			System.out.print("1.최저값 주유소 2.충전소 유무확인 3.그외 4.이전");
			int func = Manager.sc.nextInt();
			if (func == 1)
				searchGas(curRest, func);
			if (func == 2)
				searchCharge(curRest, func);

			if (func == 4)
				break;
		}
	}

	void searchGas(RestArea4 curRest, int func) {
		System.out.println("어떤 종류를 검색하시겠습니까(1.휘발유 2.경유 3.lpg)");
		int num = Manager.sc.nextInt();

		int end = restMgr.mList.size();

		int min = findMinPrice(curRest, curRest.restnum, end, num);
		compareGas(curRest.restnum, end, num, min);
		printSavelist(curRest, func);
		savelist.clear();

	}

	void searchCharge(RestArea4 curRest, int func) {
		int end = restMgr.mList.size();

		System.out.println("어떤 종류를 검색하시겠습니까(1충전소 2.수소)");
		int num = Manager.sc.nextInt();
		findCharge(curRest, curRest.restnum, end, num);
		printSavelist(curRest, func);
		savelist.clear();

	}

	// Manager로 뺄 수 있을까?
	RestArea4 find(String kwd) {
		if (kwd.equalsIgnoreCase("X"))
			return restMgr.mList.get(0);
		for (RestArea4 r : restMgr.mList) {
			if (r.restname.equals(kwd))
				return r;
		}
		System.out.println("해당 휴게소가 존재 하지않습니다");
		return null;
	}

//	RestArea4 find( ,int n)

	// 최저값 찾기
	int findMinPrice(RestArea4 curRest, int start, int end, int num) {
		int min = 0;

		for (int i = start; i < end; i++) {
			RestArea4 compare = restMgr.mList.get(i);

			if (compare.restnum > curRest.restnum) {
				if (num == 1 && compare.gasoline > 0 && compare.gasoline < curRest.gasoline)
					min = compare.gasoline;
				if (num == 2 && compare.diesel > 0 && compare.diesel < curRest.diesel)
					min = compare.diesel;
				if (num == 3 && compare.lpg > 0 && compare.lpg < curRest.lpg)
					min = compare.lpg;
			}
		}
		return min;
	}

	void findCharge(RestArea4 curRest, int start, int end, int num) {
		for (int i = start; i < end; i++) {
			RestArea4 compare = restMgr.mList.get(i);
			if (num == 1 && compare.electric.matches("O"))
				savelist.add(compare);
			if (num == 2 && compare.hydrogen.matches("O"))
				savelist.add(compare);
		}
	}

	// 최저값으로 휴게소 찾기
	void compareGas(int start, int end, int num, int min) {

		for (int i = start; i < end; i++) {
			RestArea4 compare = restMgr.mList.get(i);
			if (num == 1 && compare.matches(min))
				savelist.add(compare);
			if (num == 2 && compare.matches(min))
				savelist.add(compare);
			if (num == 3 && compare.matches(min))
				savelist.add(compare);
		}
	}

	// 최저 휴게소 출력
	void printSavelist(RestArea4 curRest, int func) {
		if (savelist.size() > 0) {
			for (RestArea4 r : savelist) {
				System.out.format("%s휴게소-", r.restname);
				if (func == 1)
					r.printgas();
				if (func == 2)
					r.printcharge();
			}
		} else {
			System.out.format("%s휴게소-", curRest.restname);
			if (func == 1)
				curRest.printgas();
			if (func == 2)
				curRest.printcharge();
		}
		System.out.println();

	}
}
