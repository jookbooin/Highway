package GUI;

import Highway_mgr.InnerRestlistMgr;
import Highway_mgr.PathMgr;
import Highway_mgr.PathlistMgr;
import Map.Path;
import Map.Pathlist;
import facade.DataEngineInterface;

@SuppressWarnings("serial")
public class InnerlistTable extends TableSelectionDemo {

	PathMgr pathMgr = PathMgr.getInstance();
	PathlistMgr pathlistMgr = PathlistMgr.getInstance();
	
	InnerRestlistMgr innerRestMgr = InnerRestlistMgr.getInstance();
	@Override
	void init(DataEngineInterface<?> mgr) {
		Path savePath = pathMgr.getPath(0);  							//<Path> �����
		
//		System.out.println("savePath.pathID: "+savePath.pathID);
		Pathlist savePathlist = pathlistMgr.getPathlist(savePath , 0 ); //<Path>�� pathlist���� <Pathlist> �ϳ�������
		
//		System.out.println("savePathlist.pathlistnum: "+savePathlist.pathlistnum);
		innerRestMgr.setrest(savePathlist); 			//<Pathlist>�� restlist�� mylist�� ����� �� 
		
//		System.out.println("innerRestMgr.mylist.get(0).restsname: "+innerRestMgr.mylist.get(4).restname);
		super.init(mgr);
	}

	@Override
	void loadData(String kwd) { // ùȭ�� ���� Ŭ��������
		if (!kwd.equals("")) { // Ŭ�������� ��ȣ�� �ִٸ�
			Path savePath = pathMgr.getPath(TableSelectionDemo.pathtableidx); // �ش� kwd �ε��� �ֹ���ȣ ������
			Pathlist savePathlist = pathlistMgr.getPathlist(savePath ,TableSelectionDemo.pathlisttableidx);
			innerRestMgr.setrest(savePathlist);
		}

		super.loadData("");
	}

}

