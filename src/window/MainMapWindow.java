package window;

import java.awt.Color;
import java.awt.Graphics2D;

import main.WindowManager;
import utilities.Debug;
import utilities.Vec2Int;
import utilities.ZoomBox;

public class MainMapWindow extends Window
{
	ZoomBox myZoomBox;
	
	public MainMapWindow(WindowManager aWindowManager, Vec2Int aPosition, Vec2Int aSize, int aTileSize, int aTileScale)
	{
		super(aWindowManager, aPosition, aSize, aTileSize, aTileScale);
		myZoomBox = new ZoomBox(new Vec2Int(2, 1), myGridSize, myTileSize);
	}
	
	public void zoomIn()
	{
		myZoomBox.zoomIn();
		myTileScale = myZoomBox.getZoomScale();
	}
	public void zoomOut()
	{
		myZoomBox.zoomOut();
		myTileScale = myZoomBox.getZoomScale();
	}
	
	public void OnLeftClick(Vec2Int aPosition)
	{
		Vec2Int mapCoordinates = new Vec2Int();
		mapCoordinates.X = (aPosition.X - myWorldPosition.X + myZoomBox.getMapWorldPosition().X) / myZoomBox.getTileSize();
		mapCoordinates.Y = (aPosition.Y - myWorldPosition.Y + myZoomBox.getMapWorldPosition().Y) / myZoomBox.getTileSize();
		Debug.msg(mapCoordinates, "mapCoordinates");
	}

	public void draw(Graphics2D g2)
	{
		myShouldDrawGrid = true;
		drawGrid(g2);
		drawBorder(g2, Color.red);
	}
}
