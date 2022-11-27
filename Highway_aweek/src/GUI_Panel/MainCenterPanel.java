package GUI_Panel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Map.Direction;
import Map.HighWay;
import Map.Path;
import Method.TableMethod;

//
public class MainCenterPanel extends JPanel {

	String colName[] = { "��� ����", "����ð�", "�Ÿ�", "�����" };
	String pathlist[][] = { { "1", " ", " ", " " }, { "2", " ", " ", " " }, { "3", " ", " ", " " },
			{ "4", " ", " ", " " }, { "5", " ", " ", " " } };

	JPanel outpanel; // ūƲ �г�
	JPanel tablepanel; // ���̺� ���̴�
	JPanel imagepanel; // �̹��� ���̴�

	public MainCenterPanel() {
		// ���̺� ������ ���� ��ü ����
		DefaultTableModel model = new DefaultTableModel(colName, 0);
		JTable table = new JTable(model); // �� ���̺� ����
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		TableMethod.setJTableColumnsWidth(table, 700, 100, 50, 50, 50);  
		JScrollPane scrollPane = new JScrollPane(table);

		// ��ũ�ѿ� ���̺� ����

		// outPanel�� scrollpane ���̴���
		// MainCenterPanel �� �ٷ� ���̴���

		// ��ȸ�� direction.startID direction.arriveID �����ͼ�
		// Direction.pathlist<path> �� getUITexts() - String[] �� �����;� �ҵ�
		Direction direc = HighWay.direcMgr.find("0", "5"); // ���� �λ�
		for (Path p : direc.pathlist) { // ��� ������
//			// String[] �𵨰�ü�� getUiTexts �������� ������� - getUiTexts �������� �迭�� �̷���� (���,�ð�,�Ÿ�,�����)
			model.addRow(p.getUiTexts());
		}
		this.add(scrollPane);

		// ���̺� ���� �����
//		JTable pathtable = new Jtable(pathlist,pathcomm);

//		outpanel = new JPanel();
//		tablepanel = new JPanel();
//		imagepanel = new JPanel();
//
//		outpanel.add(tablepanel); // ūƲ�� ���̺� ����
//		outpanel.add(imagepanel); // ūƲ�� image ����
//		this.add(outpanel); // ���� �������� ūƲ ���̷��� ��

	}

	
}
