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
	void loadData(String kwd) { //첫화면 이후 클릭헀을때
		if (!kwd.equals("")) {  							 //클릭했을때 번호가 있다면 
			Path p = pathMgr.getPath(Integer.parseInt(kwd)); // 해당 kwd 인덱스 주문번호 가져옴
			pathlistMgr.setPath(p); 	 // mylist에 kwd번 주문 리스트 가져옴
		}
		super.loadData("");
	}
	
}
