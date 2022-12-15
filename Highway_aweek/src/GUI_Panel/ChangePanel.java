package GUI_Panel;

import javax.swing.JPanel;

//데이터들을 초기화 시켜야 하는 경우 패널 초기화를 많이 사용했다.
//이를 패턴을 이용해 초기 객체 1개만 할당받아 기능을 사용하려는 목적으로 만듬 
public class ChangePanel {
	private static ChangePanel cpanel = null;
	private ChangePanel() {}
	public static ChangePanel getInstance() {
		if (cpanel == null)
			cpanel = new ChangePanel();
		return cpanel;
	}

	JPanel panel;
	public void updatePanel(JPanel curpanel) {
		panel = curpanel;
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}
	

	
}
