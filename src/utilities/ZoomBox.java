package utilities;

public class ZoomBox 
{
	int myZoomLevel;
	int myZoomScale;
	int myCurrentTileSize;
	Vec2Int myDimension;
	Vec2Int myGridSize;
	Vec2Int myWorldSize;
	Vec2Int myMapGridPosition;
	Vec2Int myMapWorldPosition;
	
	public ZoomBox(Vec2Int aDimension, Vec2Int aStartMapSize, int aStartTileSize)
	{
		myZoomLevel = 10;
		myZoomScale = 1;
		myCurrentTileSize = aStartTileSize;
		myDimension = aDimension;
		myGridSize = new Vec2Int(aStartMapSize);
		myWorldSize = new Vec2Int();
		myMapGridPosition = new Vec2Int(myGridSize.X * myCurrentTileSize, myGridSize.Y * myCurrentTileSize);
		myMapWorldPosition = new Vec2Int();
	}

	public int getZoomLevel()
	{
		return myZoomLevel;
	}
	
	public void setZoomLevel(int aZoomLevel)
	{
		myZoomLevel = aZoomLevel;
	}
	
	public int getZoomScale()
	{
		return myZoomScale;
	}
	
	public void setZoomScale(int aZoomScale)
	{
		myZoomScale = aZoomScale;
	}
	
	public Vec2Int getDimension()
	{
		return myDimension;
	}

	public Vec2Int getGridSize()
	{
		return myGridSize;
	}

	public void setGridSize(Vec2Int aGridSize)
	{
		myGridSize = aGridSize;
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
