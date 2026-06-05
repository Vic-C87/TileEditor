package main;

import java.awt.Graphics2D;
import javax.swing.JFrame;

import utilities.Utilities;
import utilities.EDirection;
import utilities.Map;
import utilities.Tile;
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
	Map myLayer2;
	Vec2Int myMapSize;
	ZoomBox myZoomBox;
	
	Tile mySelectedTile;
		
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
		myMap = new Map("Layer 1", myAppManager.myTileSize, myMapSize);
		myLayer2 = new Map("Layer 2", myAppManager.myTileSize, myMapSize);
		myZoomBox = new ZoomBox(new Vec2Int(2, 1), myMainMapWindowSize, myAppManager.myTileSize, myMapSize);
		
		myMainMapWindow = new MainMapWindow(this, myMainMapWindowPosition, myMainMapWindowSize, myAppManager.myTileSize, 1, myMap, myLayer2,myZoomBox);
		myMiniMapWindow = new MiniMapWindow(this, myMiniMapWindowPosition, myMiniMapWindowSize, myAppManager.myTileSize, 1, myMap, myLayer2,myZoomBox);
		myInventoryWindow = new InventoryWindow(this, myInventoryWindowPosition, myInventoryWindowSize, myAppManager. myTileSize, 2);
		mySelectedTile = null;
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
	
	public void moveZoomBox(EDirection aDirection)
	{
		myMiniMapWindow.moveZoomBox(aDirection);
	}
	
	public void setSelectedTile(Tile aTile)
	{
		mySelectedTile = aTile;
	}
	
	public void OnLeftMouseClick(Vec2Int aPosition)
	{
		//Main Map Window
		if (myUtilities.checkTileCollision(aPosition, myMainMapWindow.getWorldPosition(), myMainMapWindow.getWorldSize()))
		{
			if (mySelectedTile != null)
			{
				myMainMapWindow.OnLeftClick(aPosition, mySelectedTile);				
			}
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
	}
	
	public void OnRightMouseClick(Vec2Int aPosition)
	{
		//Main Map Window
		if (myUtilities.checkTileCollision(aPosition, myMainMapWindow.getWorldPosition(), myMainMapWindow.getWorldSize()))
		{
			myMainMapWindow.OnRightClick(aPosition);				
		}
		
		//Mini Map Window
		if (myUtilities.checkTileCollision(aPosition, myMiniMapWindow.getWorldPosition(), myMiniMapWindow.getWorldSize()))
		{
			//Not implemented yet
		}
		
		//Inventory Window
		if (myUtilities.checkTileCollision(aPosition, myInventoryWindow.getWorldPosition(), myInventoryWindow.getWorldSize()))
		{
		}
	}
	
	public void setLayer1()
	{
		myMainMapWindow.setDrawLayer1();
	}
	
	public void setLayer2()
	{
		myMainMapWindow.setDrawLayer2();
	}
	
	public void nextPage()
	{
		myInventoryWindow.setNextPage();
	}
	
	public void previousPage()
	{
		myInventoryWindow.setPreviousPage();
	}
	
	public void draw(Graphics2D g2)
	{
		myInventoryWindow.draw(g2);
		myMainMapWindow.draw(g2);
		myMiniMapWindow.draw(g2);		
	}
}