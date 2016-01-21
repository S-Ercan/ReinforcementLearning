package maze;

import strategy.StrategyProfile;

public class Agent
{
	private final int numSteps = 100;
	private final int moveDelay = 500;

	private Maze maze;
	private StrategyProfile profile;
	private int xPosition;
	private int yPosition;
	private int score;

	public Agent(Maze maze)
	{
		this.maze = maze;
		profile = new StrategyProfile(maze.getXSize(), maze.getYSize());
		xPosition = 0;
		yPosition = 0;
		score = 0;
	}

	public void run()
	{
		int stepCounter = 0;
		while (stepCounter < numSteps)
		{
			try
			{
				Thread.sleep(moveDelay);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			chooseMove();
			stepCounter++;
		}
	}

	public void chooseMove()
	{
		Direction direction = profile.chooseDirectionFromTile(xPosition, yPosition);
		int x = direction == Direction.LEFT ? xPosition - 1
				: direction == Direction.RIGHT ? xPosition + 1 : xPosition;
		int y = direction == Direction.DOWN ? yPosition + 1
				: direction == Direction.UP ? yPosition - 1 : yPosition;
		executeMove(x, y, direction);
	}

	public void executeMove(int x, int y, Direction direction)
	{
		int scoreChange = EnvironmentManager.executeMove(maze, x, y);
		if (scoreChange != -1)
		{
			update(x, y, direction, scoreChange);
		}
		else
		{
			profile.excludeDirectionFromTile(xPosition, yPosition, direction);
		}
	}

	public void update(int x, int y, Direction direction, int scoreChange)
	{
		System.out.println("Moved to (" + x + ", " + y + ")");

		score += scoreChange;
		System.out.println("Score: " + score);

		profile.updateStrategy(xPosition, yPosition, direction, x, y, scoreChange);

		xPosition = x;
		yPosition = y;
	}
}