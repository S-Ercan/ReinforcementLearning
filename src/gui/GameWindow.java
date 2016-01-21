package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import maze.Maze;

public class GameWindow extends JFrame
{
	private static final long serialVersionUID = 1903142717890981086L;

	private MazePanel mazePanel;

	/**
	 * Creates a new window with a graphical representation of maze.
	 * 
	 * @param maze
	 *            maze to display
	 */
	public GameWindow(Maze maze)
	{
		mazePanel = new MazePanel(maze);
		getContentPane().add(mazePanel);

		int windowWidth = (maze.getXSize() + 4) * mazePanel.getTileWidth();
		int windowHeight = (maze.getYSize() + 5) * mazePanel.getTileHeight();

		setSize(windowWidth, windowHeight);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		
		setLocation((int) screenWidth / 2 - windowWidth / 2, (int) screenHeight / 2 - windowHeight / 2);

		setResizable(false);
		setTitle("Maze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	/**
	 * Calls mazePanel's move animation functionality.
	 * 
	 * @param x
	 *            x coordinate of destination tile
	 * @param y
	 *            y coordinate of destination tile
	 */
	public void showMoveAnimation(int x, int y)
	{
		mazePanel.showMoveAnimation(x, y);
	}
}