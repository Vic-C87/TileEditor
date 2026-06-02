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
	
	void initializeMap()
	{
		for (int y = 0; y < mySize.Y; y++)
		{
			for (int x = 0; x < mySize.X; x++)
			{
				myMap[x][y] = new Tile();
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
	
	public void setTile(int anX, int aY, Tile aTile)
	{
		myMap[anX][aY] = aTile;
	}
	
	public BufferedImage getImage(int anX, int aY)
	{
		return myMap[anX][aY].myImage;
	}
}
