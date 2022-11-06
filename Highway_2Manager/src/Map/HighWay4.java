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

	String wayname; // ��μ�
	String direction; // ����
	String restname;

	HighWay4(String wayname, String direction) {
		this.wayname = wayname;
		this.direction = direction;
	}

	@Override
	public void read(Scanner scan) {

		if (wayname.equals("��μ�") && direction.equals("����")) {
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

		if (wayname.equals("��μ�") && direction.equals("����")) {
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
			System.out.print("���� �ްԼ� ��ġ(������ X �Է�):");
			String curLo = Manager.sc.next(); // ��õ �ްԼ� / X = 1�� �ްԼ�
			RestArea4 curRest = find(curLo);
			if (curRest == null)
				continue;
			searchFunc(curRest);
		}
	}

	void searchFunc(RestArea4 curRest) {
		while (true) {
			System.out.print("1.������ ������ 2.������ ����Ȯ�� 3.�׿� 4.����");
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
		System.out.println("� ������ �˻��Ͻðڽ��ϱ�(1.�ֹ��� 2.���� 3.lpg)");
		int num = Manager.sc.nextInt();

		int end = restMgr.mList.size();

		int min = findMinPrice(curRest, curRest.restnum, end, num);
		compareGas(curRest.restnum, end, num, min);
		printSavelist(curRest, func);
		savelist.clear();

	}

	void searchCharge(RestArea4 curRest, int func) {
		int end = restMgr.mList.size();

		System.out.println("� ������ �˻��Ͻðڽ��ϱ�(1������ 2.����)");
		int num = Manager.sc.nextInt();
		findCharge(curRest, curRest.restnum, end, num);
		printSavelist(curRest, func);
		savelist.clear();

	}

	// Manager�� �� �� ������?
	RestArea4 find(String kwd) {
		if (kwd.equalsIgnoreCase("X"))
			return restMgr.mList.get(0);
		for (RestArea4 r : restMgr.mList) {
			if (r.restname.equals(kwd))
				return r;
		}
		System.out.println("�ش� �ްԼҰ� ���� �����ʽ��ϴ�");
		return null;
	}

//	RestArea4 find( ,int n)

	// ������ ã��
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

	// ���������� �ްԼ� ã��
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

	// ���� �ްԼ� ���
	void printSavelist(RestArea4 curRest, int func) {
		if (savelist.size() > 0) {
			for (RestArea4 r : savelist) {
				System.out.format("%s�ްԼ�-", r.restname);
				if (func == 1)
					r.printgas();
				if (func == 2)
					r.printcharge();
			}
		} else {
			System.out.format("%s�ްԼ�-", curRest.restname);
			if (func == 1)
				curRest.printgas();
			if (func == 2)
				curRest.printcharge();
		}
		System.out.println();

	}
}
