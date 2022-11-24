package Highway_mgr;

import java.util.List;

import Map.Direction;
import Map.Path;
import facade.DataEngineInterface;

public class PathMgr implements DataEngineInterface<Path>{

	private static PathMgr mgr = null;

	private PathMgr() {}
	public static PathMgr getInstance() {
		if (mgr == null)
			mgr = new PathMgr();
		return mgr;
	}
	
	
	public List<Path> mylist;
	public void setPath(Direction path) {
		mylist = path.pathlist;
	}
	
	public Path getPathlist(Direction path , int index) {
		
		return path.pathlist.get(index);     // ����� <Path> �� pathlist���� <Pathlist>�� �ε����� ���� ������? 
	}
	
	
	private String[] headers = {"��� ��ȣ", "���", "������", "����" };
	
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
//		List<Pathlist> result = new ArrayList<>();
//		for(Pathlist pl : mylist)
//			result.add(pl);
//		return result;
		return mylist;
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
		
	}
	

	

}
