package window;

import java.awt.Color;
import java.awt.Graphics2D;

import main.WindowManager;
import utilities.Vec2Int;
import utilities.ZoomBox;

public class MainMapWindow extends Window
{
	ZoomBox myZoomBox;
	
	public MainMapWindow(WindowManager aWindowManager, Vec2Int aPosition, Vec2Int aSize, int aTileSize, int aTileScale)
	{
		super(aWindowManager, aPosition, aSize, aTileSize, aTileScale);
		myZoomBox = new ZoomBox(new Vec2Int(2, 1), myGridSize, aTileScale);
	}
	
	public void setupZoomBox()
	{
		myZoomBox.setZoomScale(myTileSize);
		
	}

	public void draw(Graphics2D g2)
	{
		myShouldDrawGrid = true;
		drawGrid(g2);
		drawBorder(g2, Color.red);
	}
}
