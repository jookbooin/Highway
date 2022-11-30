package GUI;

import java.util.List;

import javax.swing.JFrame;

import Map.Direction;
import Map.HighWay;
import Map.Path;
import Map.RestArea;

public class SecondFrame extends JFrame{
	
	int startidx;
	int arriveidx;
	int tableidx;
	List<RestArea> guirestlist;
	
	SecondFrame(int startidx,int arriveidx,int tableidx){
		this.setTitle("��ΰ˻� ");
		
		this.startidx=startidx;
		this.arriveidx=arriveidx;
		this.tableidx=tableidx;
		
		
		System.out.println(startidx);
		System.out.println(arriveidx);
		System.out.println(tableidx);
		
		Direction direc = HighWay.direcMgr.find(""+startidx,""+arriveidx);
		Path path = direc.pathlist.get(tableidx);
		guirestlist = path.restlist;
		
		System.out.println(path.highwayname);
		for(RestArea a : guirestlist) 
			System.out.println(a);
		
		this.setSize(700, 600); // JFrameũ��
		this.setResizable(false); // â�� ũ�⸦ �������� ���ϰ�
		this.setLocationRelativeTo(null); // â�� ���
		this.setVisible(true); // â�� ���̰�
		
	}
	
}
