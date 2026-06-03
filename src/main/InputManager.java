package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import utilities.Vec2Int;

public class InputManager implements KeyListener, MouseListener
{
	public boolean myUpPressed, myDownPressed, myLeftPressed, myRightPressed;
	
	Vec2Int myMousePosition = new Vec2Int();
	
	WindowManager myWindowManager;
	
	public InputManager(WindowManager aWindowManager)
	{
		myWindowManager = aWindowManager;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			myWindowManager.zoomIn();
		}
		if (code == KeyEvent.VK_S) {
			myWindowManager.zoomOut();
		}
		if (code == KeyEvent.VK_A) {
			myLeftPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			myRightPressed = true;
		}		
		if (code == KeyEvent.VK_L) 
		{
			myWindowManager.loadTiles();
		}
		if (code == KeyEvent.VK_T)
		{
			//ADD TILES
			myWindowManager.TileTest();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			myUpPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			myDownPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			myLeftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			myRightPressed = false;
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
			//Right click
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
