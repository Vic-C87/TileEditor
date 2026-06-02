package main;

import javax.swing.JFrame;

public class Main 
{

	public static void main(String[] args) 
	{
		JFrame window = new JFrame("Tile Map Editor");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		AppManager appManager = new AppManager(window);
		
		window.add(appManager);
		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		appManager.startGameThread();
	}

}
