package Highway_mgr;

import java.util.List;

import Map.HighWay;
import Map.RestArea;
import facade.DataEngineInterface;

public class RestMgr implements DataEngineInterface<RestArea> {
	private static RestMgr mgr = null;
	private RestMgr() {}
	public static RestMgr getInstance() {
		if (mgr == null)
			mgr = new RestMgr();
		return mgr;
	}
	
	private String[] headers = {"노선", "휴게소명", "전화번호"};
	@Override
	public int getColumnCount() {
		//
		return 0;
	}
	@Override
	public String[] getColumnNames() {
		return headers;
	}
	@Override
	public void readAll(String filename) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<RestArea> search(String kwd) {
		// TODO Auto-generated method stub
		return HighWay.restMgr.findAll(kwd);
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
