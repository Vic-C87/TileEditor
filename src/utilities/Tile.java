package utilities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile 
{
	BufferedImage myImage;
	Vec2Int myPosition;
	boolean myHaveCollider = false;
	int myIndex;
	String myFileName;
	

	public Tile()
	{
		myImage = null;
		myPosition = new Vec2Int();
		myIndex = 0;
		myFileName = "";
	}
	
	public Tile(BufferedImage anImage, int anIndex, String aFileName)
	{
		myImage = anImage;
		myPosition = new Vec2Int();
		myIndex = anIndex;
		myFileName = aFileName;
	}
	
	public BufferedImage getMyImage() 
	{
		return myImage;
	}

	public void setMyImage(BufferedImage anImage) 
	{
		myImage = anImage;
	}

	public Vec2Int getMyPosition() 
	{
		return myPosition;
	}

	public void setMyPosition(int anXPosition, int aYPosition) 
	{
		myPosition.X = anXPosition;
		myPosition.Y = aYPosition;
	}

	public boolean getHaveCollider() 
	{
		return myHaveCollider;
	}
	
	public String getFileName()
	{
		return myFileName;
	}
	
	public int getIndex()
	{
		return myIndex;
	}

	public void setHaveCollider(boolean aHaveCollider) 
	{
		myHaveCollider = aHaveCollider;
	}
	
	public void draw(Graphics2D g2, int aSize)
	{
		g2.drawImage(myImage, myPosition.X, myPosition.Y, aSize, aSize, null);
	}
	
	public void draw(Graphics2D g2, Vec2Int aSize)
	{
		g2.drawImage(myImage, myPosition.X, myPosition.Y, aSize.X, aSize.Y, null);
	}
}
