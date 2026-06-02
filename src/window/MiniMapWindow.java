package window;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.WindowManager;
import utilities.Map;
import utilities.Tile;
import utilities.Vec2Int;

public class MiniMapWindow extends Window
{
	Map myMap;
	Vec2Int myMapSize = new Vec2Int();
	Vec2Int myMapWorldPosition = new Vec2Int();
	int myMapTileSize;
	
	public MiniMapWindow(WindowManager aWindowManager, Vec2Int aPosition, Vec2Int aSize, int aTileSize, int aTileScale, Map aMap)
	{
		super(aWindowManager, aPosition, aSize, aTileSize, aTileScale);
		myMap = aMap;
		setupMap();
	}
	
	void setupMap()
	{
		myMapSize = myMap.getMySize();
		myMapTileSize = (myGridSize.X * myTileSize) / myMapSize.X;
		int remainder = (myGridSize.X * myTileSize) % myMapSize.X;
		myMapWorldPosition.X = myWorldPosition.X + (remainder / 2);
		myMapWorldPosition.Y = myWorldPosition.Y + (remainder / 2);
	}
	
	public void addTile(Vec2Int aPosition, Tile aTile)
	{
		myMap.setTile(aPosition.X, aPosition.Y, aTile);
	}
	
	public void draw(Graphics2D g2)
	{
		
		
		for (int y = 0; y < myMapSize.Y; y++)
		{
			for (int x = 0; x < myMapSize.X; x++)
			{
				BufferedImage image = myMap.getImage(x, y);
				if (image != null)
				{
					g2.drawImage(image, myMapWorldPosition.X + (x * myMapTileSize), myMapWorldPosition.Y + (y * myMapTileSize), myMapTileSize, myMapTileSize, null);
					
				}
			}
		}
		drawBorder(g2, Color.red);
	}

}
