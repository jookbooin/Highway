package Highway_mgr;

import java.util.List;

import Map.Path;
import Map.Pathlist;
import facade.DataEngineInterface;

public class PathlistMgr implements DataEngineInterface<Pathlist>{

	private static PathlistMgr mgr = null;

	private PathlistMgr() {}
	public static PathlistMgr getInstance() {
		if (mgr == null)
			mgr = new PathlistMgr();
		return mgr;
	}
	
	
	public List<Pathlist> mylist;
	public void setPath(Path path) {
		// TODO Auto-generated method stubf
		mylist = path.pathlist;
	}
	
	public Pathlist getPathlist(Path path , int index) {
		
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
	public List<Pathlist> search(String kwd) {
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
