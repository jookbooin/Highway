package GUI;

import Highway_mgr.RestlistMgr;
import Highway_mgr.DirecMgr;
import Highway_mgr.PathMgr;
import Map.Direction;
import Map.Path;
import facade.DataEngineInterface;

@SuppressWarnings("serial")
public class RestlistTable extends TableSelectionDemo {

	DirecMgr direcMgr = DirecMgr.getInstance();
	PathMgr pathMgr = PathMgr.getInstance();
	RestlistMgr restlistMgr = RestlistMgr.getInstance();
	
	@Override
	void init(DataEngineInterface<?> mgr) {
		Direction saveDirec = direcMgr.getPath(0);  		    //<Direction> �����
		Path savePath = pathMgr.getPathlist(saveDirec , 0 );	 //<Directioin>�� pathlist���� <Path> �ϳ�������
		restlistMgr.setrest(savePath); 						//<Path>�� restlist�� mylist�� ����� �� 		
		super.init(mgr);
	}

	@Override
	void loadData(String kwd) { 									// ùȭ�� ���� Ŭ��������
		if (!kwd.equals("")) { 										// Ŭ�������� ��ȣ(�ε���)�� �ִٸ�
			Direction saveDirec = direcMgr.getPath(TableSelectionDemo.directableidx); // �ش� kwd �ε��� �ֹ���ȣ ������
			Path savePath = pathMgr.getPathlist(saveDirec ,TableSelectionDemo.pathtableidx);
			restlistMgr.setrest(savePath);
		}

		super.loadData("");
	}

}

