package final_exam;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
/*
 *  바둑판 모양 맵 정의
 */
public class Map extends JPanel {
	private List<JPanel> panelList;
	private JPanel lab;
	private Room[][] map1 = new Room[8][9];
	
	public Map() {
		setLevel1();
		this.setLayout(new GridLayout(8, 8));
		panelList = new ArrayList<>();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 9; j++) {
				lab = new JPanel(); // 맵 Panel 정의
				lab.setLayout( new GridLayout(1, 1));
				lab.setBackground(Color.white);
				lab.setBorder(new CompoundBorder(
						new MatteBorder(map1[i][j].getUp(), map1[i][j].getLeft(), map1[i][j].getDown(), map1[i][j].getRight(),  Color.black),
						new LineBorder(new Color(191,254,255))));
		
				this.add(lab);
				panelList.add(lab);
			}
		}
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 밖 margin추가
	}
	
	// room 리스트 get
	public List<JPanel> getPanelList() {
		return panelList;
	}
	// 말이 갈수있는 위치 없는 위치를 표시하기 위한 객체 배열
	private void setLevel1() {
		map1[0][0]= new Room(2, 0, 2, 2);
		map1[0][1]= new Room(0, 2, 2, 0);
		map1[0][2]= new Room(2, 0, 2, 0);
		map1[0][3]= new Room(0, 0, 2, 0);
		map1[0][4]= new Room(0, 0, 2, 0);
		map1[0][5]= new Room(0, 0, 2, 0);
		map1[0][6]= new Room(0, 0, 2, 2);
		map1[0][7]= new Room(0, 0, 2, 0);
		map1[0][8]= new Room(0, 2, 2, 2);

		map1[1][0]= new Room(-2,-2,-2,-2);
		map1[1][1]= new Room(2, 0, 0, 0);
		map1[1][2]= new Room(0, 0, 0, 0);
		map1[1][3]= new Room(0, 0, 0, 0);
		map1[1][4]= new Room(0, 0, 0, 0);
		map1[1][5]= new Room(0, 0, 0, 0);
		map1[1][6]= new Room(0, 0, 2, 0);
		map1[1][7]= new Room(0, 2, 0, 0);
		map1[1][8]= new Room(2, 2, 2, 0);

		map1[2][0]= new Room(-2,-2,-2,-2);
		map1[2][1]= new Room(2, 0, 0, 0);
		map1[2][2]= new Room(0, 0, 0, 0);
		map1[2][3]= new Room(0, 0, 0, 0);
		map1[2][4]= new Room(0, 0, 0, 0);
		map1[2][5]= new Room(0, 2, 0, 0);
		map1[2][6]= new Room(2, 0, 0, 0);
		map1[2][7]= new Room(0, 0, 0, 0);
		map1[2][8]= new Room(0, 2, 0, 0);

		map1[3][0]= new Room(-2,-2,-2,-2);
		map1[3][1]= new Room(2, 0, 0, 0);
		map1[3][2]= new Room(0, 0, 0, 2);
		map1[3][3]= new Room(0, 0, 0, 0);
		map1[3][4]= new Room(0, 2, 0, 0);
		map1[3][5]= new Room(2, 0, 0, 0);
		map1[3][6]= new Room(0, 0, 0, 2);
		map1[3][7]= new Room(0, 0, 0, 0);
		map1[3][8]= new Room(0, 2, 0, 0);

		map1[4][0]= new Room(-2,-2,-2,-2);
		map1[4][1]= new Room(2, 0, 0, 0);
		map1[4][2]= new Room(0, 0, 2, 0);
		map1[4][3]= new Room(0, 2, 0, 0);
		map1[4][4]= new Room(2, 0, 0, 0);
		map1[4][5]= new Room(0, 0, 0, 0);
		map1[4][6]= new Room(0, 0, 2, 0);
		map1[4][7]= new Room(0, 0, 0, 0);
		map1[4][8]= new Room(0, 2, 0, 0);

		map1[5][0]= new Room(-2,-2,-2,-2);
		map1[5][1]= new Room(2, 2, 0, 2);
		map1[5][2]= new Room(2, 2, 0, 0);
		map1[5][3]= new Room(2, 0, 0, 0);
		map1[5][4]= new Room(0, 0, 0, 0);
		map1[5][5]= new Room(0, 0, 0, 0);
		map1[5][6]= new Room(0, 2, 0, 0);
		map1[5][7]= new Room(2, 0, 0, 2);
		map1[5][8]= new Room(0, 2, 0, 0);

		map1[6][0]= new Room(-2,-2,-2,-2);
		map1[6][1]= new Room(2, 0, 2, 2);
		map1[6][2]= new Room(0, 2, 0, 0);
		map1[6][3]= new Room(2, 0, 0, 0);
		map1[6][4]= new Room(0, 2, 0, 0);
		map1[6][5]= new Room(2, 0, 0, 2);
		map1[6][6]= new Room(0, 0, 0, 0);
		map1[6][7]= new Room(0, 0, 2, 0);
		map1[6][8]= new Room(0, 2, 0, 0);

		map1[7][0]= new Room(-2,-2,-2,-2);
		map1[7][1]= new Room(2, 0, 2, 2);
		map1[7][2]= new Room(0, 0, 0, 2);
		map1[7][3]= new Room(0, 0, 0, 2);
		map1[7][4]= new Room(0, 0, 0, 2);
		map1[7][5]= new Room(0, 2, 2, 2);
		map1[7][6]= new Room(2, 0, 0, 2);
		map1[7][7]= new Room(0, 0, 0, 2);
		map1[7][8]= new Room(0, 2, 0, 2);


	}
	
	public Room[][] getLevel1() {
		return map1;
	}
}
