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
		Path savePath = pathMgr.getPath(0);  							//<Path> 끌고옴 
		Pathlist savePathlist = pathlistMgr.getPathlist(savePath , 0 ); //<Path>의 pathlist에서 <Pathlist> 하나가져옴
		innerRestMgr.setrest(savePathlist); 				//<Pathlist>의 restlist를 mylist로 만들려 함 

		super.init(mgr);
	}

	@Override
	void loadData(String kwd) { // 첫화면 이후 클릭헀을때
		if (!kwd.equals("")) { // 클릭했을때 번호가 있다면
			Path p = pathMgr.getPath(Integer.parseInt(kwd)); // 해당 kwd 인덱스 주문번호 가져옴
			pathlistMgr.setPath(p); // mylist에 kwd번 주문 리스트 가져옴
		}

		super.loadData("");
	}

}

