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
		this.setTitle("경로검색 ");
		
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
		
		this.setSize(700, 600); // JFrame크기
		this.setResizable(false); // 창의 크기를 변경하지 못하게
		this.setLocationRelativeTo(null); // 창이 가운데
		this.setVisible(true); // 창이 보이게
		
	}
	
}
