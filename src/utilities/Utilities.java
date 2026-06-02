package utilities;

public class Utilities
{
	public boolean checkTileCollision(Vec2Int aPosition, Vec2Int aTargetPosition, Vec2Int aTargetSize)
	{		
		if(aPosition.X >= aTargetPosition.X && aPosition.X < aTargetPosition.X + aTargetSize.X
				&& aPosition.Y >= aTargetPosition.Y && aPosition.Y < aTargetPosition.Y + aTargetSize.Y)
		{
			return true;
		}
		return false;
	}
	
	public int getIndexFrom2DGrid(Vec2Int aGridSize, Vec2Int aPoint, int aGridScale)
	{
		Vec2Int scaledGridInt = new Vec2Int(aGridSize.X/aGridScale, aGridSize.Y/aGridScale);		
		int index = getIndexFrom2DGrid(scaledGridInt, aPoint);
		
		return index;
	}
	
	public int getIndexFrom2DGrid(Vec2Int aGridSize, Vec2Int aPoint)
	{
		int index = aPoint.Y * aGridSize.X;		
		index += aPoint.X;
		
		return index;
	}
}
