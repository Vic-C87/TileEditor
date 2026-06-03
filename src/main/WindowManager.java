package main;

import java.awt.Graphics2D;
import javax.swing.JFrame;

import utilities.Utilities;
import utilities.Debug;
import utilities.Map;
import utilities.Vec2Int;
import utilities.ZoomBox;
import window.InventoryWindow;
import window.MainMapWindow;
import window.MiniMapWindow;

public class WindowManager 
{
	
	JFrame myMainWindow;
	AppManager myAppManager;
	Utilities myUtilities;
	
	Vec2Int myInventoryWindowPosition;
	Vec2Int myInventoryWindowSize;
	Vec2Int myMainMapWindowPosition;
	Vec2Int myMainMapWindowSize;
	Vec2Int myMiniMapWindowPosition;
	Vec2Int myMiniMapWindowSize;
	
	MainMapWindow myMainMapWindow;
	MiniMapWindow myMiniMapWindow;	
	InventoryWindow myInventoryWindow;
	
	Map myMap;
	Vec2Int myMapSize;
		
	public WindowManager(JFrame aMainWindow, AppManager anAppManager, Utilities someUtilities)
	{
		myMainWindow = aMainWindow;
		myAppManager = anAppManager;
		myUtilities = someUtilities;
		
		myMainMapWindowPosition = new Vec2Int(2, 2);
		myMainMapWindowSize = new Vec2Int(24, 12);
		myMiniMapWindowPosition = new Vec2Int(33, 2);
		myMiniMapWindowSize = new Vec2Int(10, 10);
		myInventoryWindowPosition = new Vec2Int(1, 17);
		myInventoryWindowSize = new Vec2Int(30, 6);
		
		myMapSize = new Vec2Int(50, 50);
		myMap = new Map("", myAppManager.myTileSize, myMapSize);
		
		myMainMapWindow = new MainMapWindow(this, myMainMapWindowPosition, myMainMapWindowSize, myAppManager.myTileSize, 1);
		myMiniMapWindow = new MiniMapWindow(this, myMiniMapWindowPosition, myMiniMapWindowSize, myAppManager.myTileSize, 1, myMap);
		myInventoryWindow = new InventoryWindow(this, myInventoryWindowPosition, myInventoryWindowSize, myAppManager. myTileSize, 2);
	}
	
	public void loadTiles()
	{
		myInventoryWindow.loadTiles(myMainWindow);
	}
	
	public void zoomIn()
	{
		myMainMapWindow.zoomIn();
	}
	
	public void zoomOut()
	{
		myMainMapWindow.zoomOut();
	}
	
	public void TileTest()
	{
		for (int y = 1; y < myMap.getMySize().Y; y++)
		{
			for (int x = 0; x < myMap.getMySize().X; x++)
			{
				myMiniMapWindow.addTile(new Vec2Int(x, y),  myInventoryWindow.myLoadedTiles.get(0));
			}
		}
	}
	
	public void OnLeftMouseClick(Vec2Int aPosition)
	{
		//Main Map Window
		if (myUtilities.checkTileCollision(aPosition, myMainMapWindow.getWorldPosition(), myMainMapWindow.getWorldSize()))
		{
			myMainMapWindow.OnLeftClick(aPosition);
		}
		
		//Mini Map Window
		if (myUtilities.checkTileCollision(aPosition, myMiniMapWindow.getWorldPosition(), myMiniMapWindow.getWorldSize()))
		{
			//Not implemented yet
			myMiniMapWindow.OnLeftClick(aPosition);
		}
		
		//Inventory Window
		if (myUtilities.checkTileCollision(aPosition, myInventoryWindow.getWorldPosition(), myInventoryWindow.getWorldSize()))
		{
			myInventoryWindow.OnLeftClick(aPosition);		
		}
		Debug.msg(aPosition, "aPosition");
	}
	
	public void draw(Graphics2D g2)
	{
		myInventoryWindow.draw(g2);
		myMainMapWindow.draw(g2);
		myMiniMapWindow.draw(g2);		
	}
}