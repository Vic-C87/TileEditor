package window;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.WindowManager;
import utilities.Map;
import utilities.Tile;
import utilities.Vec2Int;
import utilities.ZoomBox;

public class MainMapWindow extends Window
{
	Map myMap;
	Map myLayer2;
	ZoomBox myZoomBox;
	boolean shouldDrawLayer1 = true;
	boolean shouldDrawLayer2 = true;
	
	public MainMapWindow(WindowManager aWindowManager, Vec2Int aPosition, Vec2Int aSize, int aTileSize, int aTileScale, Map aMap, Map aSecondLayer, ZoomBox aZoomBox)
	{
		super(aWindowManager, aPosition, aSize, aTileSize, aTileScale);
		myMap = aMap;
		myLayer2 = aSecondLayer;
		myZoomBox = aZoomBox;
	}
	
	public void zoomIn()
	{
		myZoomBox.zoomIn();
		myTileScale = myZoomBox.getZoomScale();
	}
	public void zoomOut()
	{
		myZoomBox.zoomOut();
		myTileScale = myZoomBox.getZoomScale();
	}
	
	public void OnLeftClick(Vec2Int aPosition, Tile aSelectedTile)
	{
		Vec2Int mapCoordinates = new Vec2Int();
		mapCoordinates.X = (aPosition.X - myWorldPosition.X) / myZoomBox.getTileSize();
		mapCoordinates.Y = (aPosition.Y - myWorldPosition.Y) / myZoomBox.getTileSize();
		if(!myMap.setTile(mapCoordinates.X + myZoomBox.getMapGridPosition().X, mapCoordinates.Y + myZoomBox.getMapGridPosition().Y, aSelectedTile))
		{
			myLayer2.setTile(mapCoordinates.X + myZoomBox.getMapGridPosition().X, mapCoordinates.Y + myZoomBox.getMapGridPosition().Y, aSelectedTile);
		}
	}
	
	public void OnRightClick(Vec2Int aPosition)
	{
		Vec2Int mapCoordinates = new Vec2Int();
		mapCoordinates.X = (aPosition.X - myWorldPosition.X) / myZoomBox.getTileSize();
		mapCoordinates.Y = (aPosition.Y - myWorldPosition.Y) / myZoomBox.getTileSize();
		if (myLayer2.checkTile(mapCoordinates.X + myZoomBox.getMapGridPosition().X, mapCoordinates.Y + myZoomBox.getMapGridPosition().Y))
		{
			myLayer2.clearTile(mapCoordinates.X + myZoomBox.getMapGridPosition().X, mapCoordinates.Y + myZoomBox.getMapGridPosition().Y);
		}
		else if (myMap.checkTile(mapCoordinates.X + myZoomBox.getMapGridPosition().X, mapCoordinates.Y + myZoomBox.getMapGridPosition().Y))
		{
			myMap.clearTile(mapCoordinates.X + myZoomBox.getMapGridPosition().X, mapCoordinates.Y + myZoomBox.getMapGridPosition().Y);
		}
	}
	
	public void setDrawLayer1()
	{
		shouldDrawLayer1 = !shouldDrawLayer1;
	}
	
	public void setDrawLayer2()
	{
		shouldDrawLayer2 = !shouldDrawLayer2;
	}
	
	public void drawTilesFromMap(Graphics2D g2)
	{
		int xPos;
		int yPos;
		for (int y = myZoomBox.getMapGridPosition().Y; y < myZoomBox.getGridSize().Y + myZoomBox.getMapGridPosition().Y; y++)
		{
			for (int x = myZoomBox.getMapGridPosition().X; x < myZoomBox.getGridSize().X + myZoomBox.getMapGridPosition().X; x++)
			{
				if (x < myMap.getMySize().X && y < myMap.getMySize().Y)
				{
					BufferedImage image1 = myMap.getImage(x, y);
					BufferedImage image2 = myLayer2.getImage(x, y);
					if (image1 != null && shouldDrawLayer1)
					{
						xPos = x - myZoomBox.getMapGridPosition().X;
						yPos = y - myZoomBox.getMapGridPosition().Y;
						g2.drawImage(image1, myWorldPosition.X + (xPos * myTileScale * myTileSize), myWorldPosition.Y + (yPos * myTileScale * myTileSize), myTileScale * myTileSize, myTileScale * myTileSize, null);
					}
					if (image2 != null && shouldDrawLayer2)
					{
						xPos = x - myZoomBox.getMapGridPosition().X;
						yPos = y - myZoomBox.getMapGridPosition().Y;
						g2.drawImage(image2, myWorldPosition.X + (xPos * myTileScale * myTileSize), myWorldPosition.Y + (yPos * myTileScale * myTileSize), myTileScale * myTileSize, myTileScale * myTileSize, null);
					}
				}
			}
		}
	}

	public void draw(Graphics2D g2)
	{
		drawGrid(g2);
		drawTilesFromMap(g2);
		drawBorder(g2, Color.red);
	}
}
