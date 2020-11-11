

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class ball {
	double resistance = 0.9999;
	int xPower = 0;
	int yPower = 0;
	int xIncrement = 0;
	int yIncrement = 0;
	int counter=0;//for testing
	static int xPos;//ball Pos
	static int yPos;//ball Pos
	static int x1=0;//calculation coordinates
	static int y1=0;
	static int x2=0;
	static int y2=0;
	static int x3=0;
	static int y3=0;
	double xVel, yVel;
	public ball(){
		xPos = 310;
		yPos = 310;
		xVel = 0;
		yVel = 0;
	}
	public static void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(xPos-10,  yPos-10, 20, 20);
	}
	
	public void execute() {
		xPower = 0;
		yPower = 0;
		xVel = 0;
		yVel = 0;
		PointerInfo z = MouseInfo.getPointerInfo();
		Point q = z.getLocation();
		x2 = (int) q.getX();
		y2 = (int) q.getY();
		System.out.println("(" + x2 + "," + y2+ ")");
		xPower = 2*(Math.abs(x2-x1));
		yPower = 2*(Math.abs(y2-y1));
		if(x1>x2) {
		xVel = xPower;
		}
		else {
			xVel = (xPower*-1);
		}
		if(y1>y2) {
			yVel = yPower;
		}
		else {
			yVel=(-1*yPower);
		}
		
		
	}
	
	
	
	public void checkCollision() {
		xIncrement = (int) Math.floor(xVel/300);
		yIncrement = (int) Math.floor(yVel/300);
		
		
		//ball appears to get stuck on one of the planes (X/Y) after running for a bit.
		//I believe this to be the result of the plane's velocity 
		//dropping to <300 and then xIncr being Math.floor(<300/300) which is 0, 
		//this is not an accurate representation of real life - need to reexamine how apply friction.
		
		//current solution is just to stop ball if either (X or Y) falls to 0, might want to come back to this
		
		if(xPos+xIncrement<11) {
			xIncrement = xPos-11;
			System.out.println("xIncrement "+xIncrement);
			System.out.println("xVel " + xVel);
			xVel=-xVel;
		}else if(xPos+xIncrement>600) {
			xIncrement = 601 - xPos;
			System.out.println("xIncrement "+xIncrement);
			System.out.println("xVel " + xVel);
			xVel=-xVel;
		}
		if(yPos+yIncrement<11) {
			yIncrement = yPos-11;
			System.out.println("yIncrement "+yIncrement);
			System.out.println("yVel " + yVel);
			yVel=-yVel;
		}else if(yPos+yIncrement>580) {
			yIncrement = 580 - yPos;
			System.out.println("yIncrement "+yIncrement);
			System.out.println("yVel " + yVel);
			yVel=-yVel;
		}
		if(Math.abs(xVel)<300 ||Math.abs(yVel)<300) {
			xVel = 0;
			yVel = 0;
		}
		
		
		
//		if (yPos<11||yPos>580) {
//			yVel=-yVel;
//			System.out.println("yIncr" + yIncrement);
//		}
//		if(xPos<11||xPos>600) {
//			xVel=-xVel;
//			System.out.println("xIncr" + xIncrement);
//		}
	}
	
	public void move() {
		
		xPos += xIncrement;
		yPos += yIncrement;
		if(xVel>=0.1||xVel<=-0.1) {//never gets to 0.1 since at Vel <300, Increment = 0 therefore 0 movement in said plane.
			xVel*=resistance;
		}
		else {
			xVel=0;
		}
		if(yVel>=0.1||yVel<=-0.1) {
			yVel*=resistance;
		}
		else {
			yVel=0;
		}
		if(xVel==0&&yVel==0) {
			counter++;
			if(counter>600) {
			System.out.println("Ball pos: " + "(" + xPos +","+yPos+")");
			counter=0;
			}
		}
	}
	
	public void checkWin() {
		if(((xPos-10>=Target.getX()-Target.getWidth()/2)&&(xPos-10<=Target.getX()+Target.getWidth()/2))||
				((xPos+10>=Target.getX()-Target.getWidth()/2)&&(xPos+10<=Target.getX()+Target.getWidth()/2))) {
			if(((yPos-10>=Target.getY()-Target.getHeight()/2)&&(yPos-10<=Target.getY()+Target.getHeight()/2))||
					((yPos+10>=Target.getY()-Target.getHeight()/2)&&(yPos+10<=Target.getY()+Target.getHeight()/2))) {
				Target.score();
			}
		}
	}
	
	public String iLabel() {
		int xP = 0, yP = 0;
		PointerInfo w = MouseInfo.getPointerInfo();
		Point p = w.getLocation();
		x3 = (int) p.getX();
		y3 = (int) p.getY();
		xP = x1-x3;
		yP = y1-y3;
		return "X Power: " + xP + " Y Power: " + yP + " Score: " + Target.getScore() + " ";
	}
	public void mPressed() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		x1 = (int) b.getX();
		y1 = (int) b.getY();
		System.out.println("(" + x1 + "," + y1+ ")");
	}
	
}
