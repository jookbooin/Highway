package GUI;

import Highway_mgr.DirecMgr;
import Highway_mgr.PathMgr;
import Map.Direction;
import facade.DataEngineInterface;

@SuppressWarnings("serial")
public class PathTable extends TableSelectionDemo {
	DirecMgr direcMgr = DirecMgr.getInstance();
	PathMgr pathMgr = PathMgr.getInstance();
	
	@Override
	void init(DataEngineInterface<?> mgr) { 
		pathMgr.setPath(direcMgr.getPath(0));
		super.init(mgr);
	}

	@Override
	void loadData(String kwd) { 									  //ùȭ�� ���� Ŭ��������
		if (!kwd.equals("")) {  									  //Ŭ�������� ��ȣ�� �ִٸ� 
			Direction p = direcMgr.getPath(Integer.parseInt(kwd));   // �ش� kwd �ε��� �ֹ���ȣ ������
			pathMgr.setPath(p); 							 		  // mylist�� kwd�� �ֹ� ����Ʈ ������
		}
		super.loadData("");
	}
	
}
