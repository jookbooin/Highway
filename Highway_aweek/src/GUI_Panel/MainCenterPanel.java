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

public class MainCenterPanel extends JPanel {

	String colName[] = { "��� ����", "����ð�", "�Ÿ�", "�����" };

	DefaultTableModel model;

	Direction direc;
	JTable table;
	JScrollPane scrollPane;

	public MainCenterPanel() {
		// ���̺� ������ ���� ��ü ����
		model = new DefaultTableModel(colName, 0);
//		direc = HighWay.direcMgr.find("0", "5");
//		for (Path p : direc.pathlist)
//			model.addRow(p.getUiTexts());
		
//		model.setRowCount(0);		//���� ������� -> ���̺� ������ �ְ� ������ �̸� �����ؾ��� 
		
		direc = HighWay.direcMgr.find("0", "1");
		for (Path p : direc.pathlist)
			model.addRow(p.getUiTexts());
		
		
		
		table = new JTable(model); // �� ���̺� ����
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		TableMethod.setJTableColumnsWidth(table, 700, 100, 50, 50, 50);
		scrollPane = new JScrollPane(table);
		this.add(scrollPane);

	}

	public void loadData(Direction direc2) {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		for(Path p:direc.pathlist)
			model.addRow(p.getUiTexts());
		table = new JTable(model); // �� ���̺� ����
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		TableMethod.setJTableColumnsWidth(table, 700, 100, 50, 50, 50);
		scrollPane = new JScrollPane(table);
	}

//	void init() {

//	void loadData(Direction direc) {

//		model.setRowCount(0);
//		for(Path p:direc.pathlist)
//			model.addRow(p.getUiTexts());
//		
//		JTable table = new JTable(model); 								 // �� ���̺� ����
//		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
//		TableMethod.setJTableColumnsWidth(table, 700, 100, 50, 50, 50);  
//		JScrollPane scrollPane = new JScrollPane(table);
//		this.add(scrollPane);
//	}

}
