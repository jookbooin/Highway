package GUI_Panel;

import javax.swing.JPanel;

//�����͵��� �ʱ�ȭ ���Ѿ� �ϴ� ��� �г� �ʱ�ȭ�� ���� ����ߴ�.
//�̸� ������ �̿��� �ʱ� ��ü 1���� �Ҵ�޾� ����� ����Ϸ��� �������� ���� 
public class ChangePanel {
	private static ChangePanel cpanel = null;
	private ChangePanel() {}
	public static ChangePanel getInstance() {
		if (cpanel == null)
			cpanel = new ChangePanel();
		return cpanel;
	}


	public void updatePanel(JPanel curpanel) {
		curpanel.removeAll();
		curpanel.revalidate();
		curpanel.repaint();
		
	}
	

	
}
