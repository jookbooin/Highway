package Highway_mgr;

import java.util.List;

import Map.HighWay;
import Map.Pathlist;
import facade.DataEngineInterface;

public class PathlistMgr implements DataEngineInterface<Pathlist>{

	private static PathlistMgr mgr = null;

	private PathlistMgr() {
	}

	public static PathlistMgr getInstance() {
		if (mgr == null)
			mgr = new PathlistMgr();
		return mgr;
	}

	private String[] headers = { "��ȣ", "���", "������", "����" };
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
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
	public List<Pathlist> search(String kwd) {
		// TODO Auto-generated method stub
		return HighWay.makeMgr.findAll(kwd);
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
