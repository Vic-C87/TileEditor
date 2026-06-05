package window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import main.WindowManager;
import utilities.Tile;
import utilities.Vec2Int;

public class TileInfoWindow extends Window
{
	String mySelectedFileName;
	boolean mySelectedHaveCollider;
	int mySelectedIndex;
	
	boolean myShouldDisplayInfo = false;
	
	Vec2Int myFileNameTextPosition;
	Vec2Int myHaveColliderTextPosition;
	Vec2Int myIndexTextPosition;
	
	public TileInfoWindow(WindowManager aWindowManager, Vec2Int aPosition, Vec2Int aSize, int aTileSize, int aTileScale)
	{
		super(aWindowManager, aPosition, aSize, aTileSize, aTileScale);
		myShouldDrawGrid = true;
		myFileNameTextPosition = new Vec2Int(1, 2);
		myHaveColliderTextPosition = new  Vec2Int(1, 4);
		myIndexTextPosition = new Vec2Int(1, 6);
		setOffsetPosition();
	}
	
	void setOffsetPosition()
	{
		myFileNameTextPosition.X = myFileNameTextPosition.X * myTileSize + myWorldPosition.X;
		myFileNameTextPosition.Y = myFileNameTextPosition.Y * myTileSize + myWorldPosition.Y;
		myHaveColliderTextPosition.X = myHaveColliderTextPosition.X * myTileSize + myWorldPosition.X;
		myHaveColliderTextPosition.Y = myHaveColliderTextPosition.Y * myTileSize + myWorldPosition.Y;
		myIndexTextPosition.X = myIndexTextPosition.X * myTileSize + myWorldPosition.X;
		myIndexTextPosition.Y = myIndexTextPosition.Y * myTileSize + myWorldPosition.Y;
	}
	
	public void displaySelectedTile(Tile aSelectedTile)
	{
		mySelectedFileName = aSelectedTile.getFileName();
		mySelectedHaveCollider = aSelectedTile.getHaveCollider();
		mySelectedIndex = aSelectedTile.getIndex();
		myShouldDisplayInfo = true;
	}
	
	public void drawText(Graphics2D g2, Vec2Int aPosition, String aText)
	{
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		g2.setFont(new Font("Arial", Font.PLAIN, 20));
		g2.drawString(aText, aPosition.X, aPosition.Y);
	}
	
	public void draw(Graphics2D g2)
	{
		//drawGrid(g2);
		if (myShouldDisplayInfo)
		{
			drawText(g2,myFileNameTextPosition, mySelectedFileName);
			drawText(g2, myHaveColliderTextPosition, String.valueOf(mySelectedHaveCollider));
			drawText(g2, myIndexTextPosition, String.valueOf(mySelectedIndex)
					);
		}
		
		drawBorder(g2, Color.red);
	}

}
