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
		Direction saveDirec = direcMgr.getPath(0);  		    //<Direction> 끌고옴
		Path savePath = pathMgr.getPathlist(saveDirec , 0 );	 //<Directioin>의 pathlist에서 <Path> 하나가져옴
		restlistMgr.setrest(savePath); 						//<Path>의 restlist를 mylist로 만들려 함 		
		super.init(mgr);
	}

	@Override
	void loadData(String kwd) { 									// 첫화면 이후 클릭헀을때
		if (!kwd.equals("")) { 										// 클릭했을때 번호(인덱스)가 있다면
			Direction saveDirec = direcMgr.getPath(TableSelectionDemo.directableidx); // 해당 kwd 인덱스 주문번호 가져옴
			Path savePath = pathMgr.getPathlist(saveDirec ,TableSelectionDemo.pathtableidx);
			restlistMgr.setrest(savePath);
		}

		super.loadData("");
	}

}

