package utilities;

import java.awt.image.BufferedImage;

public interface IButton
{
	Vec2Int myPosition = new Vec2Int();
	Vec2Int mySize = new Vec2Int();
	String myText = "";
	BufferedImage myIcon = null;
	BufferedImage myPressedIcon = null;
	
	
	public void OnClick();
	
	public BufferedImage getIcon();
	
	public BufferedImage getPressedIcon();
	
	public String getText();
	
	public Vec2Int getPosition();
	
	public Vec2Int getSize();
}
