package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import utilities.EDirection;
import utilities.Vec2Int;

public class InputManager implements KeyListener, MouseListener
{
	public boolean myUpPressed, myDownPressed, myLeftPressed, myRightPressed;
	public EDirection myDirection;
	
	Vec2Int myMousePosition = new Vec2Int();
	
	WindowManager myWindowManager;
	
	public InputManager(WindowManager aWindowManager)
	{
		myWindowManager = aWindowManager;
		myDirection = EDirection.none;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_UP)
		{
			myWindowManager.zoomIn();
		}
		if (code == KeyEvent.VK_DOWN)
		{
			myWindowManager.zoomOut();
		}
		if (code == KeyEvent.VK_LEFT)
		{
			myWindowManager.previousPage();
		}
		if (code == KeyEvent.VK_RIGHT)
		{
			myWindowManager.nextPage();
		}
		
		if (code == KeyEvent.VK_W) 
		{
			myDirection = EDirection.up;
		}
		if (code == KeyEvent.VK_S) 
		{
			myDirection = EDirection.down;
		}
		if (code == KeyEvent.VK_A) 
		{
			myDirection = EDirection.left;
		}
		if (code == KeyEvent.VK_D) 
		{
			myDirection = EDirection.right;
		}		
		if (code == KeyEvent.VK_L) 
		{
			myWindowManager.loadTiles();
		}
		if (code == KeyEvent.VK_1)
		{
			myWindowManager.setLayer1();
		}
		if (code == KeyEvent.VK_2)
		{
			myWindowManager.setLayer2();
		}
		if (code == KeyEvent.VK_Z)
		{
			myWindowManager.fillZoomBox();
		}
		if (code == KeyEvent.VK_M)
		{
			myWindowManager.fillMap();
		}
		if (code == KeyEvent.VK_0)
		{
			myWindowManager.saveMap();
		}
		if (code == KeyEvent.VK_9)
		{
			myWindowManager.saveData();
		}
		if (code == KeyEvent.VK_C)
		{
			myWindowManager.clearMap();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) 
		{
			myDirection = EDirection.none;
		}
		if (code == KeyEvent.VK_S) 
		{
			myDirection = EDirection.none;
		}
		if (code == KeyEvent.VK_A) 
		{
			myDirection = EDirection.none;
		}
		if (code == KeyEvent.VK_D) 
		{
			myDirection = EDirection.none;
		}		
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		myMousePosition.set(e.getX(), e.getY());
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			myWindowManager.OnLeftMouseClick(myMousePosition);
		}
		
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			myWindowManager.OnRightMouseClick(myMousePosition);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Vec2Int getMousePosition()
	{
		return myMousePosition;
	}
	
}
