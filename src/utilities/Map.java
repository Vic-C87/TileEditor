package utilities;

import java.awt.image.BufferedImage;

public class Map
{
	String myTitle;
	int myTileSize;
	Vec2Int mySize;
	Tile[][] myMap;
	
	public Map(String aTitle, int aTileSize, Vec2Int aSize)
	{
		myTitle = aTitle;
		myTileSize = aTileSize;
		mySize = aSize;
		myMap = new Tile[aSize.X][aSize.Y];
		initializeMap();		
	}
	
	public void initializeMap()
	{
		for (int y = 0; y < mySize.Y; y++)
		{
			for (int x = 0; x < mySize.X; x++)
			{
				clearTile(x, y);
			}
		}
	}
	
	public Vec2Int getMySize()
	{
		return mySize;
	}
	
	public Tile getTile(int anX, int aY)
	{
		return myMap[anX][aY];
	}
	
	public boolean setTile(int anX, int aY, Tile aTile)
	{
		if (myMap[anX][aY].getMyImage() == null)
		{
			myMap[anX][aY] = aTile;
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public void clearTile(int anX, int aY)
	{
		myMap[anX][aY] = new Tile();
	}
	
	public boolean checkTile(int anX, int aY)
	{
		if (myMap[anX][aY].getMyImage() == null)
		{
			return false;
		}
		return true;
	}
	
	public BufferedImage getImage(int anX, int aY)
	{
		return myMap[anX][aY].myImage;
	}
}
