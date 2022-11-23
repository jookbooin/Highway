package GUI;

import Highway_mgr.PathMgr;
import Highway_mgr.PathlistMgr;
import Map.Path;
import facade.DataEngineInterface;

@SuppressWarnings("serial")
public class PathlistTable extends TableSelectionDemo {
	PathMgr pathMgr = PathMgr.getInstance();
	PathlistMgr pathlistMgr = PathlistMgr.getInstance();
	
	void init(DataEngineInterface<?> mgr) { 
		pathlistMgr.setPath(pathMgr.getPath(0));
		super.init(mgr);
	}

	@Override
	void loadData(String kwd) { //ùȭ�� ���� Ŭ��������
		if (!kwd.equals("")) {  							 //Ŭ�������� ��ȣ�� �ִٸ� 
			Path p = pathMgr.getPath(Integer.parseInt(kwd)); // �ش� kwd �ε��� �ֹ���ȣ ������
			pathlistMgr.setPath(p); 	 // mylist�� kwd�� �ֹ� ����Ʈ ������
		}
		super.loadData("");
	}
	
}
