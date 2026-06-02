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
import utilities.Tile;
import utilities.Vec2Int;

public class InventoryWindow extends Window
{
	Vec2Int mySelectedBorderCoordinates = new Vec2Int();
	boolean myBorderIsSelected = false;
	
	String myLoadedDirectoryPath = "";
	ArrayList<String> myRawFilenames = new ArrayList<>();
	public ArrayList<Tile> myLoadedTiles = new ArrayList<Tile>();
	Tile mySelectedTile = null;
	
	public InventoryWindow(WindowManager aWindowManager, Vec2Int aPosition, Vec2Int aSize, int aTileSize, int aTileScale)
	{
		super(aWindowManager, aPosition, aSize, aTileSize, aTileScale);
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
        
        System.out.println("Directory" + myLoadedDirectoryPath);
        
        for (int i = 0; i < fd.getFiles().length; i++)
        {
        	rawFilename = fd.getFiles()[i].toString();
        	
        	String[] splitCheck = rawFilename.split("\\.");

        	if (splitCheck[splitCheck.length-1].equals("png"))
        	{
        		myRawFilenames.add(rawFilename);
        		System.out.println("File : " + rawFilename);        	
        	}
        	else
        	{
            	System.out.println("Could not load: " + rawFilename + "\nNot a PNG");
        	}
        }
              
        try 
		{
        	for (int i = 0; i < myRawFilenames.size(); i++)
        	{
        		File tmpFile = new File(myRawFilenames.get(i));
        		BufferedImage image = ImageIO.read(tmpFile);
        		int index = myLoadedTiles.size();
        		Tile tile = new Tile(image, index);
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
					z++;
				}
			}
		}
	}
	
	public int setSelectedBoarder(int x, int y, int myLoadedTilesSize)
	{
		Vec2Int borderPosition = new Vec2Int(x, y);
		int index = myUtilities.getIndexFrom2DGrid(myGridSize, borderPosition, myTileScale);
		if (index < myLoadedTilesSize)
		{
			mySelectedBorderCoordinates = borderPosition;
			myBorderIsSelected = true;
			return index;
		}
		
		return 999;
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
		for (Tile tile : myLoadedTiles)
		{
			tile.draw(g2, myTileSize * myTileScale);
		}
		if (myBorderIsSelected)
		{
			drawSelectedBorder(g2, mySelectedBorderCoordinates.X, mySelectedBorderCoordinates.Y);
		}
		drawBorder(g2, Color.red);
	}

}
