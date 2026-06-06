package main;

import java.awt.FileDialog;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import utilities.Utilities;
import utilities.ColliderButton;
import utilities.Debug;
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
	Vec2Int myColliderButtonPosition;
	Vec2Int myColliderButtonSize;
	
	MainMapWindow myMainMapWindow;
	MiniMapWindow myMiniMapWindow;	
	InventoryWindow myInventoryWindow;
	
	Map myMap;
	Map myLayer2;
	Vec2Int myMapSize;
	ZoomBox myZoomBox;
	ColliderButton myColliderButton;
	
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
		myColliderButtonPosition = new Vec2Int(32, 18);
		myColliderButtonSize = new Vec2Int(1,1);
		
		myMapSize = new Vec2Int(50, 50);
		myMap = new Map("Layer 1", myAppManager.myTileSize, myMapSize);
		myLayer2 = new Map("Layer 2", myAppManager.myTileSize, myMapSize);
		myZoomBox = new ZoomBox(new Vec2Int(2, 1), myMainMapWindowSize, myAppManager.myTileSize, myMapSize);
		
		myMainMapWindow = new MainMapWindow(this, myMainMapWindowPosition, myMainMapWindowSize, myAppManager.myTileSize, 1, myMap, myLayer2, myZoomBox);
		myMiniMapWindow = new MiniMapWindow(this, myMiniMapWindowPosition, myMiniMapWindowSize, myAppManager.myTileSize, 1, myMap, myLayer2, myZoomBox);
		myInventoryWindow = new InventoryWindow(this, myInventoryWindowPosition, myInventoryWindowSize, myAppManager. myTileSize, 2, myMap, myZoomBox);
		
		myColliderButton = new ColliderButton(myInventoryWindow, myColliderButtonPosition, myColliderButtonSize, "Toggle", myAppManager.myTileSize);
		
		mySelectedTile = null;
	}
	
	public void saveMap()
	{
		FileDialog fd = new FileDialog(myMainWindow, "Save Map", FileDialog.SAVE);
		fd.setFile("*.txt");
        fd.setVisible(true);

        String directory = fd.getDirectory();
        String fileName = fd.getFile();

        File file = new File(directory, fileName);
        
        ArrayList<String> mapList = new ArrayList<>();
        String line;
        int index;
        
        for (int y = 0; y < myMapSize.Y; y++)
        {
        	line = "";
        	for (int x = 0; x < myMapSize.X; x++)
        	{
        		index = myMap.getTile(x, y).getIndex();
        		if (index < 10)
        		{
        			line += "00";
        		}
        		else if (index < 100)
        		{
        			line += "0";
        		}
        		line += index + " ";
        	}
        	mapList.add(line);
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) 
        {
        	for (String string : mapList)
			{
				writer.write(string);
				writer.newLine();
			}
            
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	public void saveData()
	{
		FileDialog fd = new FileDialog(myMainWindow, "Save Data", FileDialog.SAVE);
		fd.setFile("*.txt");
        fd.setVisible(true);

        String directory = fd.getDirectory();
        String fileName = fd.getFile();

        File file = new File(directory, fileName);
        
        ArrayList<String> dataList = myInventoryWindow.getData();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) 
        {
        	for (String string : dataList)
			{
				writer.write(string);
				writer.newLine();
			}
            
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        
	}
	
	public void clearMap()
	{
		myMap.initializeMap();
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
		
		if (myUtilities.checkTileCollision(aPosition, myColliderButton.getPosition(), myColliderButton.getSize()))
		{
			myColliderButton.OnClick();
			Debug.msg(aPosition, "position");
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
	
	public void fillZoomBox()
	{
		myInventoryWindow.fillZoomBox();
	}
	
	public void fillMap()
	{
		myInventoryWindow.fillMap();
	}
	
	public void draw(Graphics2D g2)
	{
		myInventoryWindow.draw(g2);
		myMainMapWindow.draw(g2);
		myMiniMapWindow.draw(g2);
		myColliderButton.draw(g2);
	}
}