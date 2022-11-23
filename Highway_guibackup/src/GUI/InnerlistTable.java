package GUI;

import Highway_mgr.InnerRestlistMgr;
import Highway_mgr.PathMgr;
import Highway_mgr.PathlistMgr;
import Map.Path;
import Map.Pathlist;
import facade.DataEngineInterface;

@SuppressWarnings("serial")
public class InnerlistTable extends PathlistTable {
	PathMgr pathMgr = PathMgr.getInstance();
	PathlistMgr pathlistMgr = PathlistMgr.getInstance();
	InnerRestlistMgr innerRestMgr = InnerRestlistMgr.getInstance();

	void init(DataEngineInterface<?> mgr) {
		Path savePath = pathMgr.getPath(0);  							//<Path> ����� 
		Pathlist savePathlist = pathlistMgr.getPathlist(savePath , 0 ); //<Path>�� pathlist���� <Pathlist> �ϳ�������
		innerRestMgr.setrest(savePathlist); 				//<Pathlist>�� restlist�� mylist�� ����� �� 

		super.init(mgr);
	}

	@Override
	void loadData(String kwd) { // ùȭ�� ���� Ŭ��������
		if (!kwd.equals("")) { // Ŭ�������� ��ȣ�� �ִٸ�
			Path p = pathMgr.getPath(Integer.parseInt(kwd)); // �ش� kwd �ε��� �ֹ���ȣ ������
			pathlistMgr.setPath(p); // mylist�� kwd�� �ֹ� ����Ʈ ������
		}

		super.loadData("");
	}

}

