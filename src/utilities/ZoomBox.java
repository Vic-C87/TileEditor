package utilities;

public class ZoomBox 
{
	int myZoomScale;
	int myTileSize;
	Vec2Int myDimension;
	Vec2Int myGridSize;
	Vec2Int myWorldSize;
	Vec2Int myMapGridPosition;
	Vec2Int myMapWorldPosition;
	
	public ZoomBox(Vec2Int aDimension, Vec2Int aStartMapSize, int aTileSize)
	{
		myZoomScale = 1;
		myTileSize = aTileSize;
		myDimension = aDimension;
		myGridSize = new Vec2Int(aStartMapSize);
		myWorldSize = new Vec2Int(myGridSize.X * myTileSize, myGridSize.Y * myTileSize);
		myMapGridPosition = new Vec2Int();
		myMapWorldPosition = new Vec2Int();
	}
	
	public int getZoomScale()
	{
		return myZoomScale;
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
