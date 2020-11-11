

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Cursor;

public class ballGame implements MouseListener{
	int test = 0;
	boolean isRunning;
	ball ball;
	display Disp;
	Target target;
	String name;
	int height, width;
	int pCount = 0;
	JLabel label = new JLabel();
	int difficulty;
	boolean drawn = false;
	public ballGame(String title, int X, int Y, int d) {
		
		name = title;
		width = X; //k
		height = Y;
		difficulty = d;
	}
	
	
	public void init() {
		ball = new ball();
		Disp = new display(name, width, height);
		target = new Target(difficulty);
		Disp.createDisp(Disp, label, target);
		Disp.setBackground(Color.black);
		label.setForeground(Color.WHITE);
		Disp.addMouseListener(this);
		Disp.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		run();
	}
	public void run() {
		
		while (isRunning) {
			tick();
		}
		//stop();
	}
	public void tick() {
		try {
			drawn=Target.getDrawn();
			ball.checkCollision();
			ball.move();
			if(!drawn) {
				target.targetConcept(difficulty);
			}
			Disp.upd();
			ball.checkWin();
			if(pCount%2!=0) {//need a way to update the label after every target since currently, even if a target is hit, the score 
				//only changes at the next shot 
				System.out.println(ball.iLabel());
				label.setText(ball.iLabel());
				label.setVisible(true);
			}
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void begin() {
		if (isRunning) {
			return;
		}
		isRunning = true;
		init();
	}
	public void stop() {
		if(!isRunning) {
			return;
		}
		isRunning = false;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("mouseClicked");
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("mouseEntered");
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		//System.out.println("mouseExited");
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("ballGameMousePressed");
		pCount++;
		ball.mPressed();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");
		pCount++;
		ball.execute();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
