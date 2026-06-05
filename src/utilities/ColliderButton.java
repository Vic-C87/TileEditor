package utilities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import window.InventoryWindow;

public class ColliderButton implements IButton
{
	InventoryWindow myInventoryWindow;
	
	public ColliderButton(InventoryWindow anInventoryWindow, Vec2Int aGridPosition, Vec2Int aGridSize, String someButtonText, int aTileSize)
	{
		myInventoryWindow = anInventoryWindow;
		myPosition.X = aGridPosition.X * aTileSize;
		myPosition.Y = aGridPosition.Y * aTileSize;
		mySize.X = aGridSize.X * aTileSize;
		mySize.Y = aGridSize.Y * aTileSize;
	}
	
	@Override
	public void OnClick()
	{
		myInventoryWindow.setColliderOnSelectedTile();
	}

	@Override
	public BufferedImage getIcon()
	{
		return myIcon;
	}

	@Override
	public BufferedImage getPressedIcon()
	{
		return myPressedIcon;
	}

	@Override
	public String getText()
	{
		return myText;
	}

	@Override
	public Vec2Int getPosition()
	{
		return myPosition;
	}

	@Override
	public Vec2Int getSize()
	{
		return mySize;
	}
	
	public void draw(Graphics2D g2)
	{
		g2.setColor(Color.green);
		g2.drawRect(myPosition.X, myPosition.Y, mySize.X, mySize.Y);
		//Debug.msg(myPosition, "position");
	}

}
