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
		Path savePath = pathMgr.getPath(0);  							//<Path> 끌고옴
		
//		System.out.println("savePath.pathID: "+savePath.pathID);
		Pathlist savePathlist = pathlistMgr.getPathlist(savePath , 0 ); //<Path>의 pathlist에서 <Pathlist> 하나가져옴
		
//		System.out.println("savePathlist.pathlistnum: "+savePathlist.pathlistnum);
		innerRestMgr.setrest(savePathlist); 			//<Pathlist>의 restlist를 mylist로 만들려 함 
		
//		System.out.println("innerRestMgr.mylist.get(0).restsname: "+innerRestMgr.mylist.get(4).restname);
		super.init(mgr);
	}

	@Override
	void loadData(String kwd) { // 첫화면 이후 클릭헀을때
		if (!kwd.equals("")) { // 클릭했을때 번호가 있다면
			Path savePath = pathMgr.getPath(TableSelectionDemo.pathtableidx); // 해당 kwd 인덱스 주문번호 가져옴
			Pathlist savePathlist = pathlistMgr.getPathlist(savePath ,TableSelectionDemo.pathlisttableidx);
			innerRestMgr.setrest(savePathlist);
		}

		super.loadData("");
	}

}

