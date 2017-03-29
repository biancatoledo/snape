package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JComponent;

public class RenderPanel extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Snake snake = Snake.snake;

		//display start menu
		if (snake.onMenu)
			g.drawImage(snake.menuImage, 0, 0, 800, 700, null);
		
		else
		{
		g.drawImage(snake.backgroundImage, 0, 0, 800, 700, null);
		
		//each direction is assigned a different picture
		//for the snake head
		BufferedImage headImage;
		if (snake.direction == snake.UP || snake.direction == snake.DOWN)
			headImage = snake.headUpDownImage;
		else if (snake.direction == snake.LEFT)
			headImage = snake.headLeftImage;
		else
			headImage = snake.headRightImage;
		//draws head
		g.drawImage(headImage, snake.head.x * Snake.SCALE,
			snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE, null);

		//for every Point in snake.snakeParts we draw the body image
		for(Point point : snake.snakeParts)
			g.drawImage(snake.bodyImage, point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE, null);
		//draws food/golden snitch
		g.drawImage(snake.foodImage, snake.food.x * Snake.SCALE,
			snake.food.y * Snake.SCALE, Snake.SCALE, Snake.SCALE, null);
		
		//displays score and length of snake
		String str = "Score: " + snake.score + ", Length: "
				+ snake.tailLength;

		//font and color of letters
		Font font = new Font("Roman_Baseline", Font.BOLD, 30);
	        g.setFont(font);
		g.setColor(Color.black); 
		
		//str_x is the rectangle that str is contained in,
		//have to use this to center due to g's font changing
		//str can be thought of as an image
		int str_x = (int) g.getFontMetrics().getStringBounds(str, g).getWidth();
		g.drawString(str, (getWidth() - str_x)/2, 30);
		
		if (snake.over)
		{
			str = "Game Over! Press SPACEBAR to Restart!";
			str_x = (int) g.getFontMetrics().getStringBounds(str, g).getWidth();
			g.drawString(str, (getWidth() - str_x)/2, getHeight()/2);
		}
		else if (snake.paused)
		{
			str = "Paused!";
			str_x = (int) g.getFontMetrics().getStringBounds(str, g).getWidth();
			g.drawString(str, (getWidth() - str_x)/2, getHeight()/2);
		}
	} //end else statement
	}
}
