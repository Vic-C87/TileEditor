package utilities;

public class ZoomBox 
{
	int myZoomScale;
	int myTileSize;
	int myMapTileSize;
	Vec2Int myDimension;
	Vec2Int myGridSize;
	Vec2Int myWorldSize;
	Vec2Int myMapGridPosition;
	Vec2Int myMapWorldPosition;
	Vec2Int myMapSize;
	
	public ZoomBox(Vec2Int aDimension, Vec2Int aStartMapSize, int aTileSize, Vec2Int aMapSize)
	{
		myZoomScale = 1;
		myTileSize = aTileSize;
		myDimension = aDimension;
		myGridSize = new Vec2Int(aStartMapSize);
		myWorldSize = new Vec2Int(myGridSize.X * myTileSize, myGridSize.Y * myTileSize);
		myMapGridPosition = new Vec2Int();
		myMapWorldPosition = new Vec2Int();
		myMapSize = aMapSize;
	}
	
	public int getZoomScale()
	{
		return myZoomScale;
	}
	
	public void setMapTileSize(int aMapTileSize)
	{
		myMapTileSize = aMapTileSize;
	}
	
	public void zoomIn()
	{
		myZoomScale++;
		if (myZoomScale > 3)
		{
			myZoomScale = 3;
		}
		recalculateGridSize();
	}
	
	public void zoomOut()
	{
		myZoomScale--;
		if (myZoomScale < 1)
		{
			myZoomScale = 1;
		}
		recalculateGridSize();
	}
	
	public void moveBoxUp()
	{
		if (myMapWorldPosition.Y > 0)
		{
			myMapWorldPosition.Y -= myMapTileSize;
			myMapGridPosition.Y = myMapWorldPosition.X / myMapTileSize;
		}
	}
	public void moveBoxDown()
	{
		if (myMapGridPosition.Y + myGridSize.Y < myMapSize.Y)
		{
			myMapWorldPosition.Y += myMapTileSize;
			myMapGridPosition.Y = myMapWorldPosition.Y / myMapTileSize;
		}
	}
	public void moveBoxLeft()
	{
		if (myMapWorldPosition.X > 0)
		{
			myMapWorldPosition.X -= myMapTileSize;
			myMapGridPosition.X = myMapWorldPosition.X / myMapTileSize;
		}
	}
	public void moveBoxRight()
	{
		if (myMapGridPosition.X + myGridSize.X < myMapSize.X)
		{
			myMapWorldPosition.X += myMapTileSize;
			myMapGridPosition.X = myMapWorldPosition.X / myMapTileSize;
		}
	}
	
	
	void recalculateGridSize()
	{
		myGridSize.X = myWorldSize.X / (myTileSize * myZoomScale);
		myGridSize.Y = myWorldSize.Y / (myTileSize * myZoomScale);
	}
	
	public int getTileSize()
	{
		return myTileSize * myZoomScale;
	}
	
	public Vec2Int getDimension()
	{
		return myDimension;
	}

	public Vec2Int getGridSize()
	{
		return myGridSize;
	}

	public Vec2Int getWorldSize()
	{
		return myWorldSize;
	}

	public void setWorldSize(Vec2Int aWorldSize)
	{
		myWorldSize = aWorldSize;
	}

	public Vec2Int getMapGridPosition()
	{
		return myMapGridPosition;
	}

	public void setMapGridPosition(Vec2Int aMapGridPosition)
	{
		myMapGridPosition = aMapGridPosition;
	}

	public Vec2Int getMapWorldPosition()
	{
		return myMapWorldPosition;
	}

	public void setMapWorldPosition(Vec2Int aMapWorldPosition)
	{
		myMapWorldPosition = aMapWorldPosition;
	}
}
