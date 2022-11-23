package Highway_mgr;

import java.util.List;

import Map.Path;
import Map.RestArea;
import facade.DataEngineInterface;

public class RestlistMgr implements DataEngineInterface<RestArea> {
	
	private static RestlistMgr mgr = null;

	private RestlistMgr() {}
	public static RestlistMgr getInstance() {
		if (mgr == null)
			mgr = new RestlistMgr();
		return mgr;
	}
	
	public List<RestArea> mylist;
	public void setrest(Path path) {
		mylist = path.restlist;
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
