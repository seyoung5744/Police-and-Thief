package final_exam;

import java.awt.*;
import javax.swing.*;

/*
 *   ����, ������ ��Ÿ�� �� �׸��� �׸� ��ü.
 */
public class Human extends JPanel {
	private Color color;
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		this.setSize(65, 70);
		this.setLocation(5, 5);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setBackground(Color.white);
		g.setColor(color);
		g.fillOval(5, 10, 50, 50);
	}
	
	// ����, ���� Color set
	public void setColor(Color color) { 
		this.color = (Color) color;
	}
}
