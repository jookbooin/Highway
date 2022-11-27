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
	void loadData(String kwd) { 									  //첫화면 이후 클릭헀을때
		if (!kwd.equals("")) {  									  //클릭했을때 번호가 있다면 
			Direction p = direcMgr.getPath(Integer.parseInt(kwd));   // 해당 kwd 인덱스 주문번호 가져옴
			pathMgr.setPath(p); 							 		  // mylist에 kwd번 주문 리스트 가져옴
		}
		super.loadData("");
	}
	
}
