package GUI_Panel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

//
public class MainCenterPanel extends JPanel {

	String colName[] = { "��� ����", "����ð�", "�Ÿ�", "�����" };
	String pathlist[][] = { { "1", " ", " ", " " }, { "2", " ", " ", " " }, { "3", " ", " ", " " },
							{ "4", " ", " ", " " }, { "5", " ", " ", " " } };

	JPanel outpanel;	//ūƲ �г�
	JPanel tablepanel; // ���̺� ���̴�
	JPanel imagepanel; // �̹��� ���̴�

	public MainCenterPanel() {
		//���̺� ������ ���� ��ü ���� 
		DefaultTableModel model = new DefaultTableModel(colName,0);
		JTable table = new JTable(model); //�� ���̺� ���� 
		JScrollPane scrollPane = new JScrollPane(table); //��ũ�ѿ� ���̺� ����
		
		//outPanel�� scrollpane ���̴���
		//MainCenterPanel �� �ٷ� ���̴���
		
		//��ȸ�� direction.startID direction.arriveID �����ͼ�
		//Direction.pathlist<path> �� getUITexts() - String[] �� �����;� �ҵ�
		
		
		//���̺� ���� �����
//		JTable pathtable = new Jtable(pathlist,pathcomm);
		
		outpanel = new JPanel();
		tablepanel = new JPanel();
		imagepanel = new JPanel();
		
		outpanel.add(tablepanel);  // ūƲ�� ���̺� ����    
		outpanel.add(imagepanel);  // ūƲ�� image ����
		this.add(outpanel);			//���� ��������  ūƲ ���̷��� �� 
		
		

	}
}
