package final_exam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.*;

public class Game extends JFrame implements GameComponent {
	private Map map; // 맵
	private Human thief; // 도둑
	private Human police; // 경찰

	private List<JPanel> panelList; // 칸 리스트
	private Room[][] map1; // 도둑, 경찰 동작 유무

	private int thiefX, thiefY;
	private int policeX, policeY;
	private JLabel timeLabel;
	private Thread timeThread;
	private int timeStr;
	private boolean threadFlag = true;
	
	public Game() {
		setTitle("경찰과 도둑");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		init();
		initTimeUI();
		setTimeThread();
		
		map.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 도둑 왼쪽
					if (map1[thiefX][thiefY].getLeft() == 0) {
						panelList.get(thiefX * 9 + thiefY).remove(thief);

						panelList.get(thiefX * 9 + thiefY).repaint();

						thiefY -= 1;
						panelList.get(thiefX * 9 + thiefY).add(thief);
						movePoliceUI();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 도둑 오른쪽
					if (map1[thiefX][thiefY].getRight() == 0) {
						panelList.get(thiefX * 9 + thiefY).remove(thief);

						panelList.get(thiefX * 9 + thiefY).repaint();

						thiefY += 1;
						panelList.get(thiefX * 9 + thiefY).add(thief);
						movePoliceUI();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_UP) { // 도둑 위쪽
					if (map1[thiefX][thiefY].getUp() == 0) {
						panelList.get(thiefX * 9 + thiefY).remove(thief);

						panelList.get(thiefX * 9 + thiefY).repaint();

						thiefX -= 1;
						panelList.get(thiefX * 9 + thiefY).add(thief);
						movePoliceUI();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) { // 도둑 아래쪽
					if (map1[thiefX][thiefY].getDown() == 0) {
						panelList.get(thiefX * 9 + thiefY).remove(thief);

						panelList.get(thiefX * 9 + thiefY).repaint();

						thiefX += 1;
						panelList.get(thiefX * 9 + thiefY).add(thief);
						movePoliceUI();
					}
				}else if(e.getKeyCode() == KeyEvent.VK_S) { // S키 눌렀을 시 경찰만 이동
					movePoliceUI();
				}
				getThief();
				arriveDetination();
			}
		});

		this.add(map, BorderLayout.CENTER);

		setBounds(600, 150, 700, 700); // 프레임 위치, 크기 조절
		setResizable(false); // 창 크기 변환X
		setVisible(true);
		map.setFocusable(true); // map Panel에 키보드 이벤트 활성화
	}

	public void init() {
		// 객체 초기화
		map = new Map();
		thief = new Human();
		police = new Human();

		panelList = map.getPanelList(); // 맵 칸 객체 리스트 얻기
		// 못가는 벽을 얻기 위한 객체 리스트
		map1 = map.getLevel1();

		thief.setColor(new Color(255, 0, 0)); // 도둑 색 정의
		police.setColor(new Color(0, 0, 255)); // 경찰 색 정의

		// 출구 라벨 초기화
		setArriveLabel();
		initPosition(false);
	}

	// 출구 라벨 초기화
	public void setArriveLabel() {
		JLabel arrive = new JLabel("출구"); 
		arrive.setFont(new Font("Serif",Font.BOLD,20));
		arrive.setBounds(15, 15, 50,50);
		panelList.get(0).setLayout(null);
		panelList.get(0).add(arrive);
	}
	@Override
	public void arriveDetination() {
		if (thiefX == 0 && thiefY == 0) {
			threadFlag = false; // 시간 스레드 스탑
			int result = JOptionPane.showConfirmDialog(null, "탈출!!\n" + timeStr + "초","메시지", JOptionPane.DEFAULT_OPTION);
			
			if(result == JOptionPane.OK_OPTION) {
				initPosition(true);
			}
		}
	}

	// 경찰이 도둑 잡을 때.
	@Override
	public void getThief() {
		if ((thiefX == policeX) && (thiefY == policeY)) {
			threadFlag = false; // 시간 스레드 스탑
			int result = JOptionPane.showConfirmDialog(null, "잡았다!!도둑!!\n" + timeStr + "초","메시지", JOptionPane.DEFAULT_OPTION);
			
			if(result == JOptionPane.OK_OPTION) {
				initPosition(true);
			}
		}
	}

	// 경찰, 도둑 위치 초기화
	@Override
	public void initPosition(boolean init) {
		// 도둑, 경찰 UI 추가
		if (init) { // 초기 시작엔 필요없는 부분이므로 boolean으로 구분
			panelList.get(thiefX * 9 + thiefY).remove(thief);
			panelList.get(thiefX * 9 + thiefY).repaint();
			panelList.get(policeX * 9 + policeY).remove(police);
			panelList.get(policeX * 9 + policeY).repaint();
			
			// 시간 스레드 초기화 및 재시작
			threadFlag = true;
			timeStr = 0;
			setTimeThread();
		}
		// 도둑 초기 위치
		thiefX = 1;
		thiefY = 2;

		// 경찰 초기 위치
		policeX = 0;
		policeY = 1;

		panelList.get(thiefX * 9 + thiefY).add(thief);
		panelList.get(policeX * 9 + policeY).add(police);

	}

	// 경찰 UI 위치 조정
	public void movePoliceUI() {
		panelList.get(policeX * 9 + policeY).remove(police);
		panelList.get(policeX * 9 + policeY).repaint();
		movePoliceRule();
		movePoliceRule();
		panelList.get(policeX * 9 + policeY).add(police);
	}

	// 경찰 X,Y값 조정.
	@Override
	public void movePoliceRule() {
		int dis = distance();
		int dis2;
		int temp;

		if (map1[policeX][policeY].getLeft() == 0) {
			temp = policeY;
			policeY -= 1;
			dis2 = distance();
			if (dis2 > dis) // 안움직일 때
				policeY = temp;
			else // 움직일 때
				return;
		}
		if (map1[policeX][policeY].getRight() == 0) {
			temp = policeY;
			policeY += 1;
			dis2 = distance();
			if (dis2 > dis)
				policeY = temp;
			else
				return;
		}
		if (map1[policeX][policeY].getUp() == 0) {
			temp = policeX;
			policeX -= 1;
			dis2 = distance();
			if (dis2 > dis)
				policeX = temp;
			else
				return;
		}
		if (map1[policeX][policeY].getDown() == 0) {
			temp = policeX;
			policeX += 1;

			dis2 = distance();
			if (dis2 > dis)
				policeX = temp;
			else
				return;
		}

	}

	@Override
	public int distance() {
		return Math.abs(thiefX - policeX) + Math.abs(thiefY - policeY);
	}
	
	
	@Override
	public void initTimeUI() {
		timeLabel = new JLabel();
		timeStr = 0;
	
		panelList.get(9).add(timeLabel);
		panelList.get(9).setLayout(null);
		timeLabel.setText(timeStr+"");
		timeLabel.setFont(new Font("Serif",Font.BOLD,20));
		timeLabel.setBounds(28, 15, 50,50);
	}
	
	@Override
	public void setTimeThread() {
		timeThread = new Thread(() ->{
			while(threadFlag) {
				timeLabel.setText(timeStr + "");
				
				try {
					Thread.sleep(1000);
					timeStr++;
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		timeThread.start();
	}
}
