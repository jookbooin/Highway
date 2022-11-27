package Highway_mgr;

import java.util.List;

import Map.HighWay;
import Map.Direction;
import facade.DataEngineInterface;

public class DirecMgr implements DataEngineInterface<Direction> {

	private static DirecMgr mgr = null;

	private DirecMgr() {
	}

	public static DirecMgr getInstance() {
		if (mgr == null)
			mgr = new DirecMgr();
		return mgr;
	}
	
	public Direction getPath(int index) {
		return HighWay.direcMgr.mlist.get(index);
	}

	private String[] headers = { "번호", "출발", "목적지", "시작IC", "종료IC" };

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
	public List<Direction> search(String kwd) {
		// TODO Auto-generated method stub
		return HighWay.direcMgr.findAll(kwd);
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
