package main.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.maze.Direction;
import main.maze.Maze;

/**
 * Main frame for graphical maze representation.
 */
public class GameWindow extends JFrame
{
	private static final long serialVersionUID = 1903142717890981086L;

	private JPanel mainPanel;
	private MazePanel mazePanel;
	private QValuePanel qValuePanel;
	private JLabel scoreLabel;

	/**
	 * Creates a new window with a graphical representation of maze.
	 * 
	 * @param maze
	 *            maze to display
	 */
	public GameWindow(Maze maze)
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		getContentPane().add(mainPanel);

		mainPanel.add(createMazeAndControlsPanel(maze));

		qValuePanel = new QValuePanel(maze.getXSize(), maze.getYSize());
		qValuePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(qValuePanel);

		// Adjust size to maze dimension
		int windowWidth = (maze.getXSize() + 5) * mazePanel.getTileWidth() * 2;
		int windowHeight = (maze.getYSize() + 5) * mazePanel.getTileHeight();
		setSize(windowWidth, windowHeight);

		// Adjust location to size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		setLocation((int) screenWidth / 2 - windowWidth / 2,
				(int) screenHeight / 2 - windowHeight / 2);

		setResizable(false);
		setTitle("Maze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	public JPanel createMazeAndControlsPanel(Maze maze)
	{
		JPanel mazeAndControlsPanel = new JPanel();
		mazeAndControlsPanel.setLayout(new BoxLayout(mazeAndControlsPanel, BoxLayout.Y_AXIS));

		int mazePanelWidth = maze.getXSize() * MazePanel.tileWidth
				+ (maze.getXSize() + 1) * MazePanel.xMargin;
		int mazePanelHeight = maze.getYSize() * MazePanel.tileHeight
				+ (maze.getYSize() + 1) * MazePanel.yMargin;

		mazePanel = new MazePanel(maze);
		mazePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mazePanel.setMinimumSize(new Dimension(mazePanelWidth, mazePanelHeight));
		mazePanel.setPreferredSize(new Dimension(mazePanelWidth, mazePanelHeight));
		mazePanel.setMaximumSize(new Dimension(mazePanelWidth, mazePanelHeight));
		mazePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		mazeAndControlsPanel.add(mazePanel);

		JPanel controlsPanel = new JPanel();
		controlsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
		controlsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		scoreLabel = new JLabel("Score: 0");
		scoreLabel.setFont(new Font("", Font.BOLD, 16));
		controlsPanel.add(scoreLabel);
		controlsPanel.add(new JButton("Go"));
		controlsPanel.add(new JButton("Pause"));
		mazeAndControlsPanel.add(controlsPanel);

		return mazeAndControlsPanel;
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

	public void updateScore(int score)
	{
		scoreLabel.setText("Score: " + score);
	}

	public void updateQValue(int x, int y, Direction direction, double q)
	{
		qValuePanel.updateQValue(x, y, direction, q);
	}
}