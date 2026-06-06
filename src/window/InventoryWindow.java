package window;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import main.WindowManager;
import utilities.Debug;
import utilities.Map;
import utilities.Tile;
import utilities.Vec2Int;
import utilities.ZoomBox;

public class InventoryWindow extends Window
{
	int myCurrentPage = 1;
	Vec2Int mySelectedBorderCoordinates = new Vec2Int();
	boolean myBorderIsSelected = false;
	
	String myLoadedDirectoryPath = "";
	ArrayList<String> myRawFilenames = new ArrayList<>();
	public ArrayList<Tile> myLoadedTiles = new ArrayList<>();
	Tile mySelectedTile = null;
	
	TileInfoWindow myTileInfoWindow;
	Vec2Int myTileInfoSize = new Vec2Int(6, 8);
	Vec2Int myTileInfoPosition = new Vec2Int(35, 15);
	
	Map myMap;
	ZoomBox myZoomBox;
	
	public InventoryWindow(WindowManager aWindowManager, Vec2Int aPosition, Vec2Int aSize, int aTileSize, int aTileScale, Map aMap, ZoomBox aZoomBox)
	{
		super(aWindowManager, aPosition, aSize, aTileSize, aTileScale);
		myTileInfoWindow = new TileInfoWindow(myWindowManager, myTileInfoPosition, myTileInfoSize, aTileSize, aTileScale);
		myMap = aMap;
		myZoomBox = aZoomBox;
	}
	
	public void OnLeftClick(Vec2Int aPosition)
	{
		Vec2Int inventoryCoordinates = new Vec2Int();
		inventoryCoordinates.X = (aPosition.X - myWorldPosition.X) / (myTileSize*2);
		inventoryCoordinates.Y = (aPosition.Y - myWorldPosition.Y) / (myTileSize*2);
		
		int index = setSelectedBoarder(inventoryCoordinates.X, inventoryCoordinates.Y, myLoadedTiles.size());

		if (index != 999)
		{
			mySelectedTile = myLoadedTiles.get(index);
			myWindowManager.setSelectedTile(mySelectedTile);
			myTileInfoWindow.displaySelectedTile(mySelectedTile);
			Debug.msg(mySelectedTile.getFileName());
		}
	}
	
	public void setColliderOnSelectedTile()
	{
		if (mySelectedTile != null)
		{
			mySelectedTile.setHaveCollider(!mySelectedTile.getHaveCollider());
			myTileInfoWindow.displaySelectedTile(mySelectedTile);			
		}
	}
	
	public void clearLoadedTiles()
	{
		myLoadedTiles.clear();
	}
	
	public void loadTiles(JFrame aMainWindow)
	{
		myRawFilenames.clear();
		
		String rawFilename;
		FileDialog fd = new FileDialog(aMainWindow, "Load PNGs", FileDialog.LOAD);
		fd.setMultipleMode(true);
        fd.setVisible(true);
        myLoadedDirectoryPath = fd.getDirectory();
        
        
        for (int i = 0; i < fd.getFiles().length; i++)
        {
        	rawFilename = fd.getFiles()[i].toString();       	
        	String[] splitCheck = rawFilename.split("\\.");

        	if (splitCheck[splitCheck.length-1].equals("png"))
        	{
        		myRawFilenames.add(rawFilename);
        	}
        }
        
        String fileName;
              
        try 
		{
        	for (int i = 0; i < myRawFilenames.size(); i++)
        	{
        		String[] splitName = myRawFilenames.get(i).split("\\\\");
        		fileName = splitName[splitName.length -1];
        		File tmpFile = new File(myRawFilenames.get(i));
        		BufferedImage image = ImageIO.read(tmpFile);
        		int index = myLoadedTiles.size();
        		Tile tile = new Tile(image, index, fileName);
        		myLoadedTiles.add(tile);
        	}
		} catch(IOException e)
		{
			e.printStackTrace();
		}
        setTilePositions();
	}
	
	void setTilePositions()
	{
		int z = 0;
		for (int y = 0; y < myGridSize.Y; y += 2)
		{
			for (int x = 0; x < myGridSize.X; x +=2)
			{
				if (z < myLoadedTiles.size())
				{
					myLoadedTiles.get(z).setMyPosition((x + myGridPosition.X) * myTileSize, (y + myGridPosition.Y) * myTileSize);
				}
				z++;
				if (z == 45 || z == 90 || z == 135)
				{
					y = 0;
					x = -2;
				}
			}
		}
	}
	
	public int setSelectedBoarder(int x, int y, int myLoadedTilesSize)
	{
		Vec2Int borderPosition = new Vec2Int(x, y);
		int index = myUtilities.getIndexFrom2DGrid(myGridSize, borderPosition, myTileScale);
		index += ((myCurrentPage -1) * 45);
		if (index < myLoadedTilesSize)
		{
			mySelectedBorderCoordinates = borderPosition;
			myBorderIsSelected = true;
			return index;
		}
		
		return 999;
	}
	
	public void setNextPage()
	{
		myCurrentPage++;
		myCurrentPage = myCurrentPage > 4 ? 4 : myCurrentPage;
	}
	
	public Tile getSelectedTile()
	{
		return mySelectedTile;
	}
	
	public void setPreviousPage()
	{
		myCurrentPage--;
		myCurrentPage = myCurrentPage < 1 ? 1: myCurrentPage;
	}
	
	public void fillZoomBox()
	{
		if (mySelectedTile != null)
		{
			int yStart = myZoomBox.getMapGridPosition().Y;
			int yMax = yStart + myZoomBox.getGridSize().Y;
			int xStart = myZoomBox.getMapGridPosition().X;
			int xMax = xStart + myZoomBox.getGridSize().X;
			for (int y = yStart; y < yMax ; y++)
			{
				for (int x = xStart; x < xMax; x++)
				{
					myMap.setTile(x, y, mySelectedTile);
				}
			}			
		}
	}
	
	public void fillMap()
	{
		if (mySelectedTile != null)
		{
			for (int y = 0; y < myMap.getMySize().Y; y++)
			{
				for (int x = 0; x < myMap.getMySize().X; x++)
				{
					myMap.setTile(x, y, mySelectedTile);
				}
			}			
		}
	}
	
	public ArrayList<String> getData()
	{
		ArrayList<String> data = new ArrayList<String>();
		String fileName;
		String haveCollider;
		
		for (Tile tile : myLoadedTiles)
		{
			fileName = tile.getFileName();
			haveCollider = String.valueOf(tile.getHaveCollider());
			data.add(fileName);
			data.add(haveCollider);
		}
		
		return data;
	}
	
	void drawSelectedBorder(Graphics2D g2, int anXPosition, int aYPosition)
	{
		g2.setColor(Color.blue);
		Stroke tmp = g2.getStroke();
		g2.setStroke(new BasicStroke(5));
		g2.drawRect((anXPosition + anXPosition + myGridPosition.X) * myTileSize, (aYPosition + aYPosition + myGridPosition.Y) * myTileSize, myTileSize*2, myTileSize*2);
		g2.setStroke(tmp);			
	}	
	
	public void draw(Graphics2D g2)
	{
		drawGrid(g2);
		for (int i = 0; i < myLoadedTiles.size(); i++)
		{
			if (myCurrentPage == 1 && i < 45)
			{
				myLoadedTiles.get(i).draw(g2, myTileSize * myTileScale);
			}
			else if (myCurrentPage == 2 && i > 44 && i < 90)
			{
				myLoadedTiles.get(i).draw(g2, myTileSize * myTileScale);
			}
			else if (myCurrentPage == 3 && i > 89 && i < 135)
			{
				myLoadedTiles.get(i).draw(g2, myTileSize * myTileScale);
			}
			else if (myCurrentPage == 4 && i > 134 && i < 180)
			{
				myLoadedTiles.get(i).draw(g2, myTileSize * myTileScale);
			}
		}
		
		if (myBorderIsSelected)
		{
			drawSelectedBorder(g2, mySelectedBorderCoordinates.X, mySelectedBorderCoordinates.Y);
		}
		drawBorder(g2, Color.red);
		myTileInfoWindow.draw(g2);
	}

}
