package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utilities.Utilities;
import utilities.Vec2Int;

public class AppManager extends JPanel implements Runnable
{
	private static final long serialVersionUID = -7360309997633695148L;
	final int myOriginalTileSize = 16;
	final int myScale = 2;
	
	public final int myTileSize = myOriginalTileSize * myScale;
	public final int myMaxScreenCol = 44;
	public final int myMaxScreenRow = 24;
	public final int myScreenWidth = myTileSize * myMaxScreenCol;
	public final int myScreenHeight = myTileSize * myMaxScreenRow;
	public final int myFPS = 60;
	public final Vec2Int myScreenCenter;
	
	Utilities myUtilities;
	InputManager myInputManager;
	Thread myAppThread;
		
	WindowManager myWindowManager;
	
	public AppManager(JFrame aMainWindow)
	{
		myUtilities = new Utilities();
		myWindowManager = new WindowManager(aMainWindow, this, myUtilities);
		myInputManager = new InputManager(myWindowManager);
		this.setPreferredSize(new Dimension(myScreenWidth, myScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(myInputManager);
		this.addMouseListener(myInputManager);
		this.setFocusable(true);
		
		myScreenCenter = new Vec2Int(myScreenWidth/2 - myTileSize/2, myScreenHeight/2 - myTileSize/2);		
		
	}

	public void startGameThread() 
	{
		myAppThread = new Thread(this);
		myAppThread.start();
	}
	
	@Override
	public void run() 
	{
		double drawInterval = 1000000000 / myFPS;
		
		long currentTime = System.nanoTime();
		long lastTime = currentTime;
		long deltaTime = currentTime - lastTime;
		
		while (myAppThread != null) 
		{
			currentTime = System.nanoTime();
			deltaTime = currentTime - lastTime;
			if (deltaTime >= drawInterval)
			{
				update();
				
				repaint();
				lastTime = currentTime;
			}	
		}	
	}
	
	public void update()
	{
		myWindowManager.moveZoomBox(myInputManager.myDirection);
	}
	
	public void paintComponent(Graphics g) 
	{		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		for (int y = 0; y < myMaxScreenRow; y++)
		{
			for (int x = 0; x < myMaxScreenCol; x++)
			{
				g2.drawRect(x * myTileSize, y * myTileSize, myTileSize, myTileSize);
			}
		}
		
		//Draw MainWindow
		myWindowManager.draw(g2);
				
		g2.dispose();	
	}

}
