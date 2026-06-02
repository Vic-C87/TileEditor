package window;

import java.awt.Color;
import java.awt.Graphics2D;

import main.WindowManager;
import utilities.Utilities;
import utilities.Vec2Int;

public class Window 
{
	Vec2Int myGridPosition = new Vec2Int();
	Vec2Int myWorldPosition = new Vec2Int(); 
	Vec2Int myGridSize = new Vec2Int();
	Vec2Int myWorldSize = new Vec2Int();
	int myTileSize = 0;
	int myTileScale = 1;
	Utilities myUtilities;
	WindowManager myWindowManager;
	
	
	protected boolean myShouldDrawGrid = false;
	
	public Window(WindowManager aWindowManager, Vec2Int aPosition, Vec2Int aSize, int aTileSize, int aTileScale)
	{
		myUtilities = new Utilities();
		myGridPosition = aPosition;
		myGridSize = aSize;
		myTileSize = aTileSize;
		myTileScale = aTileScale;
		myWorldPosition.X = myGridPosition.X * myTileSize;
		myWorldPosition.Y = myGridPosition.Y * myTileSize;
		myWorldSize.X = myGridSize.X * myTileSize;
		myWorldSize.Y = myGridSize.Y * myTileSize;
		myWindowManager = aWindowManager;
	}
	
	public Vec2Int getGridSize()
	{
		return myGridSize;
	}

	public Vec2Int getWorldSize()
	{
		return myWorldSize;
	}

	public int getSizeX()
	{
		return myGridSize.X;
	}
	
	public int getSizeY()
	{
		return myGridSize.Y;
	}
	
	public int getTileSize()
	{
		return myTileSize;
	}
	
	public Vec2Int getGridPosition()
	{
		return myGridPosition;
	}
	
	public Vec2Int getWorldPosition()
	{
		return myWorldPosition;
	}
	
	protected void drawGrid(Graphics2D g2)
	{
		Color tmpColor = g2.getColor();
		g2.setColor(Color.orange);
		for (int y = 0; y < myGridSize.Y; y += myTileScale)
		{
			for (int x = 0; x < myGridSize.X; x += myTileScale)
			{
				g2.drawRect((x + myGridPosition.X) * myTileSize, (y + myGridPosition.Y) * myTileSize, myTileSize * myTileScale, myTileSize * myTileScale);
			}
		}
		g2.setColor(tmpColor);
	}
	
	protected void drawBorder(Graphics2D g2, Color aBorderColor)
	{
		g2.setColor(aBorderColor);
		g2.drawRect((myGridPosition.X * myTileSize) - 5, (myGridPosition.Y * myTileSize) - 5, (myGridSize.X * myTileSize) + 10, (myGridSize.Y * myTileSize) + 10);
	}	
	
	public void draw(Graphics2D g2)
	{
		if(myShouldDrawGrid)
		{
			drawGrid(g2);			
		}
		
		drawBorder(g2, Color.red);
	}
}
