package Map;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Factory4;
import mgr.Manageable4;
import mgr.Manager4;

public class HighWay4 implements Manageable4 {

	Manager4<RestArea4> restMgr = new Manager4<>();

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
			restMgr.readAll("kyoungbuUp.txt", new Factory4<RestArea4>() {
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
			restMgr.readAll("kyoungbuDown.txt", new Factory4<RestArea4>() {

				@Override
				public RestArea4 create(Scanner scan) {
					// TODO Auto-generated method stub
					return new RestArea4(num);
				}
			});
			print();
			restMgr.printAll();
		}

		if (wayname.equals("�ߺγ�����") && direction.equals("����")) {
			restMgr.readAll("joongbunaeUp.txt", new Factory4<RestArea4>() {
				@Override
				public RestArea4 create(Scanner scan) {
					// TODO Auto-generated method stub
					return new RestArea4(num);
				}
			});
			print();
			restMgr.printAll();
		}

		if (wayname.equals("�ߺγ�����") && direction.equals("����"))

		{
			restMgr.readAll("joongbunaeDown.txt", new Factory4<RestArea4>() {
				@Override
				public RestArea4 create(Scanner scan) {
					// TODO Auto-generated method stub
					return new RestArea4(num);
				}
			});
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
			String curLo = Manager4.sc.next(); // ��õ �ްԼ� / X = 1�� �ްԼ�
			RestArea4 curRest = restMgr.find(curLo, null);

			if (curRest == null)
				continue;
			searchFunc(curRest);
		}
	}
	// Manager�� �� �� ������?

	void searchFunc(RestArea4 curRest) {
		while (true) {
			System.out.print("1.������ ������ 2.������ ����Ȯ�� 3.�׿� 4.����");
			int func = Manager4.sc.nextInt();
			if (func == 1)
				searchGas(curRest, func);
			if (func == 2)
				searchCharge(curRest, func);
//			if (func == 3)

			if (func == 4)
				break;
		}
	}

	void searchGas(RestArea4 curRest, int func) {
		System.out.println("� ������ �˻��Ͻðڽ��ϱ�(1.�ֹ��� 2.���� 3.lpg)");
		int num = Manager4.sc.nextInt();
		int end = restMgr.mList.size();

		int min = findMinPrice(curRest, curRest.restnum, end, num);
		System.out.printf("(%d)������: %d��\n",num,min);
		compareGas(curRest.restnum, end, num, min);
		printSavelist(curRest, func);
		savelist.clear();

	}

	void searchCharge(RestArea4 curRest, int func) {
		int end = restMgr.mList.size();

		System.out.println("� ������ �˻��Ͻðڽ��ϱ�(1������ 2.����)");
		int num = Manager4.sc.nextInt();
		findCharge(curRest, curRest.restnum, end, num);
		printSavelist(curRest, func);
		savelist.clear();
	}

//	RestArea4 find( ,int n)

	// ������ ã��
	// ������ - 0�� cur�� �������� min �� ������ 0 �� �Ǵ� ���
	int findMinPrice(RestArea4 curRest, int start, int end, int num) {

		int min = 0;
		int next = 0;

		for (int i = start; i < end; i++) {
			if (num == 1 && curRest.gasoline > 0)
				break;
			if (num == 2 && curRest.diesel > 0)
				break;
			if (num == 3 && curRest.lpg > 0)
				break;
			else {
				curRest = restMgr.mList.get(i);
				next = i;
				}
			}

		for (int i = next; i < end; i++) {

			RestArea4 compare = restMgr.mList.get(i);

			if (compare.restnum > curRest.restnum) {
				if (num == 1 && compare.gasoline > 0 && compare.gasoline <= curRest.gasoline)
					min = compare.gasoline;
				if (num == 2 && compare.diesel > 0 && compare.diesel <= curRest.diesel)
					min = compare.diesel;

				if (num == 3 && compare.lpg > 0 && compare.lpg <= curRest.lpg)
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
