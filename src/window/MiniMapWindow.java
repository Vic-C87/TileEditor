package window;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.WindowManager;
import utilities.EDirection;
import utilities.Map;
import utilities.Vec2Int;
import utilities.ZoomBox;

public class MiniMapWindow extends Window
{
	Map myMap;
	Map myLayer2;
	ZoomBox myZoomBox;
	Vec2Int myMapSize = new Vec2Int();
	Vec2Int myMapWorldPosition = new Vec2Int();
	int myMapTileSize;
	
	public MiniMapWindow(WindowManager aWindowManager, Vec2Int aPosition, Vec2Int aSize, int aTileSize, int aTileScale, Map aMap, Map aSecondLayer, ZoomBox aZoomBox)
	{
		super(aWindowManager, aPosition, aSize, aTileSize, aTileScale);
		myMap = aMap;
		myLayer2 = aSecondLayer;
		setupMap();
		myZoomBox = aZoomBox;
		myZoomBox.setMapTileSize(myMapTileSize);
	}
	
	void setupMap()
	{
		myMapSize = myMap.getMySize();
		myMapTileSize = (myGridSize.X * myTileSize) / myMapSize.X;
		int remainder = (myGridSize.X * myTileSize) % myMapSize.X;
		myMapWorldPosition.X = myWorldPosition.X + (remainder / 2);
		myMapWorldPosition.Y = myWorldPosition.Y + (remainder / 2);
	}
	
	public void moveZoomBox(EDirection aDirection)
	{
		switch (aDirection)
			{
			case up:
				myZoomBox.moveBoxUp();
				break;
			case down:
				myZoomBox.moveBoxDown();
				break;
			case left:
				myZoomBox.moveBoxLeft();
				break;
			case right:
				myZoomBox.moveBoxRight();
				break;

			default:
				break;
			}
	}
	
	void drawZoomBoxBorder(Graphics2D g2)
	{
		g2.setColor(Color.blue);
		g2.drawRect(myZoomBox.getMapWorldPosition().X + myMapWorldPosition.X, myZoomBox.getMapWorldPosition().Y + myMapWorldPosition.Y, myZoomBox.getGridSize().X * myMapTileSize, myZoomBox.getGridSize().Y * myMapTileSize);
	}
	
	public void draw(Graphics2D g2)
	{
		for (int y = 0; y < myMapSize.Y; y++)
		{
			for (int x = 0; x < myMapSize.X; x++)
			{
				BufferedImage image1 = myMap.getImage(x, y);
				BufferedImage image2 = myLayer2.getImage(x, y);
				if (image1 != null)
				{
					g2.drawImage(image1, myMapWorldPosition.X + (x * myMapTileSize), myMapWorldPosition.Y + (y * myMapTileSize), myMapTileSize, myMapTileSize, null);				
				}
				if (image2 != null)
				{
					g2.drawImage(image2, myMapWorldPosition.X + (x * myMapTileSize), myMapWorldPosition.Y + (y * myMapTileSize), myMapTileSize, myMapTileSize, null);				
				}
			}
		}
		drawZoomBoxBorder(g2);
		drawBorder(g2, Color.red);
	}
}
