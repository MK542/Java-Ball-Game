

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class display extends JPanel{
	String title;
	int height;
	int width;
	display inst;
	JLabel lab;
	Target target;
	public display(String header, int xDim, int yDim) {
		
		title = header;
		height = yDim;
		width = xDim;
		
	}
	
	
	public void createDisp(display d, JLabel l, Target targ) {
		JFrame frame = new JFrame(title);
		inst = d;
		lab = l;
		target = targ;
		inst.add(lab);
		frame.add(inst);
		frame.setSize(height,width);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		ball.draw(g);
		target.draw(g);
		if(Target.getRemove()) {
			Target.remove(g);
		}
	}
	
	public void upd() {
		repaint();
	}
}
