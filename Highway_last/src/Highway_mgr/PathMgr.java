package Highway_mgr;

import java.util.List;

import Map.HighWay;
import Map.Path;
import facade.DataEngineInterface;

public class PathMgr implements DataEngineInterface<Path> {

	private static PathMgr mgr = null;

	private PathMgr() {
	}

	public static PathMgr getInstance() {
		if (mgr == null)
			mgr = new PathMgr();
		return mgr;
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
	public List<Path> search(String kwd) {
		// TODO Auto-generated method stub
		return HighWay.pathMgr.findAll(kwd);
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
