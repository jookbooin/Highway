package Highway_mgr;

import java.util.List;

import Map.InnerRestlist;
import Map.Pathlist;
import Map.RestArea;
import facade.DataEngineInterface;

public class InnerRestlistMgr implements DataEngineInterface<RestArea> {
	
	private static InnerRestlistMgr mgr = null;

	private InnerRestlistMgr() {}
	public static InnerRestlistMgr getInstance() {
		if (mgr == null)
			mgr = new InnerRestlistMgr();
		return mgr;
	}
	
	List<RestArea> mylist;
	public void setrest(Pathlist pathlist) {
		mylist = pathlist.irl.restlist;
	}
	private String[] headers = {"순서","노선","휴게소명","번호"};

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return headers.length;
	}

	@Override
	public String[] getColumnNames() {
		// TODO Auto-generated method stub
		return headers;
	}

	@Override
	public void readAll(String filename) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RestArea> search(String kwd) {
		// TODO Auto-generated method stub
		return mylist ;
	}

	@Override
	public void addNewItem(String[] uiTexts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String[] uiTexts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String kwd) {
		// TODO Auto-generated method stub
		
	}
	
	

}
