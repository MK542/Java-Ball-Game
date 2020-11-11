import java.awt.Color;
import java.awt.Graphics;

public class Target {
static int level;
static int x = 0;
static int y = 0;
static int height;
static int width;
static int score;
static boolean drawn = false;
static boolean remove = false;
	
	public Target(int difficulty) {
		this.level = difficulty;
		
		
		targetConcept(this.level);
		
	}
	
	public static void targetConcept(int l) {
		height=10*l;
		width = 10*l;

		//coord = (int) ((Math.random() * (max-min)) + min);
		x = (int) ((Math.random() * ((620 - width/2) - (width/2))) + width/2);
		y = (int) ((Math.random() * ((620 - height/2) - (height/2))) + height/2);
	}
	
	public static void draw(Graphics g) {
			g.setColor(Color.RED);
			g.fillOval(x-width/2, y-height/2, width, height);
			drawn = true;
	}
	
	public static void score() {
			score++;
			remove = true;
	}//need a way to cover/remove old target, can't draw over in remove since need to pass a graphics object
	public static void remove(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x-width/2, y-height/2, width, height);
		remove = false;
		drawn=false;
	}
	
	public static int getX() {
		return x;
	}
	public static int getY() {
		return y;
	}
	
	public static int getHeight() {
		return height;
	}
	public static int getWidth() {
		return width;
	}
	
	public static int getScore() {
		return score;
	}
	public static boolean getRemove() {
		return remove;
	}
	public static boolean getDrawn() {
		return drawn;
	}
}
