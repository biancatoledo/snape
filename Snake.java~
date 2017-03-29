package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {

	//declare variables
	public static Snake snake = new Snake();
	public JFrame jframe;
	public RenderPanel renderPanel;
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 40;
	public Timer timer = new Timer(55, this);
	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	public int direction = DOWN, score, tailLength = 1;
	public Point head, food;
	public Random random;
	public boolean over = false, paused, onMenu = true;
	public Dimension dim;
	
	//declare BufferedImages for each direction and for the food
	public BufferedImage headUpDownImage;
	public BufferedImage headLeftImage;
	public BufferedImage headRightImage;
	public BufferedImage bodyImage;
	public BufferedImage backgroundImage;
	public BufferedImage foodImage;
	public BufferedImage menuImage;

	public Snake() {
		//gets the dimensions of the screen
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Snake");
		jframe.setVisible(true);
		//size of jframe window
		jframe.setSize(805, 700);
		jframe.setResizable(true);
		//sets the window in the middle of the computer screen
		jframe.setLocation((dim.width - jframe.getWidth())/2, (dim.height
				- jframe.getHeight())/2);
		//adds the renderPanel program, which displays the images
		jframe.add(renderPanel = new RenderPanel());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		
		//exception handling for each direction
		//the head image can change for each direction the snake is going
		try { headUpDownImage = ImageIO.read(getClass().getResource("serverus.png")); }
			catch(IOException e) { System.out.println("Catch 1"); }
		try { headLeftImage = ImageIO.read(getClass().getResource("snapeLeft.png")); }
			catch(IOException e) { System.out.println("Catch 3"); }
		try { headRightImage = ImageIO.read(getClass().getResource("snapeRight.png")); }
			catch(IOException e) { System.out.println("Catch 4"); }
		try { bodyImage = ImageIO.read(getClass().getResource("snakescale.jpg")); }
			catch(IOException e) { System.out.println("Catch 5"); }
		try { backgroundImage = ImageIO.read(getClass().getResource("quidditch.jpg")); }
			catch(IOException e) { System.out.println("Catch 6"); }
		try { foodImage = ImageIO.read(getClass().getResource("snitch.png")); }
			catch(IOException e) { System.out.println("Catch 7"); }
		try { menuImage = ImageIO.read(getClass().getResource("Snapemenu.png")); }
			catch(IOException e) { System.out.println("Catch 8"); }
			
		onMenu = true;
	}

	public void startGame() {
		onMenu = false;
		over = false;
		paused = false;
		score = 0;
		//starting taillength
		tailLength = 1;
		direction = DOWN;
		head = new Point(0, -1);
		random = new Random();
		snakeParts.clear();
		//puts the food at random points on screen
		food = new Point(random.nextInt(600/SCALE), random.nextInt(500/SCALE));
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//snake grows upon eating 'food' and loses game if edges or body are hit
		renderPanel.repaint();
		if (head != null && !over && !paused)
		{
			snakeParts.add(new Point(head.x, head.y));
			if (direction == UP)
				if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1))
					head = new Point(head.x, head.y - 1);
				else
					over = true;
			if (direction == DOWN)
				if (head.y + 1 < 700/SCALE && noTailAt(head.x, head.y + 1))
					head = new Point(head.x, head.y + 1);
				else
					over = true;
			if (direction == LEFT)
				if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y))
					head = new Point(head.x - 1, head.y);
				else
					over = true;
			if (direction == RIGHT)
				if (head.x + 1 < 800/SCALE && noTailAt(head.x + 1, head.y))
					head = new Point(head.x + 1, head.y);
				else
					over = true;
			if (snakeParts.size() > tailLength)
				snakeParts.remove(0); //because we already increased body above
			if (food != null)
			{
				if (head.equals(food))
				{
					score += 10;
					tailLength++;
					food.setLocation(random.nextInt(600/SCALE), random.nextInt(500/SCALE));
				}
			}
		}
	}

	//determines if the head touches the body
	public boolean noTailAt(int x, int y) {
		for (Point point : snakeParts) 
			if (point.equals(new Point(x, y))) 
				return false;
		return true;
	}
	//main method
	public static void main(String[] args) {
		Snake snake = Snake.snake;
	}
	//assign keys
	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT)  && direction != RIGHT)
			direction = LEFT;
		else if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != LEFT)
			direction = RIGHT;
		else if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP)  && direction != DOWN)
			direction = UP;
		else if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != UP)
			direction = DOWN;
		else if (i == KeyEvent.VK_SPACE || i == KeyEvent.VK_ENTER)
			if (over || onMenu)
				startGame();
			else
				paused = !paused;
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
