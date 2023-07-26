/**
 * This class will initiate everything.
 * 
 * 
 * assignment #9
 * 
 * Class CS1420
 * @author  ---Yimin Jiang---
 * @version November 15, 2022
 */

package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import path.Path;

public class Control implements Runnable, ActionListener, MouseListener,MouseMotionListener
{
	private Map<String, BufferedImage> imageCache;
	
	private State state;
	private View view;
	private Path path;
	private int mouseX, mouseY;
	private GameObject current;
	/**
	 * program initiate here
	 */
	public Control() 
	{
		SwingUtilities.invokeLater(this);
	}
	
	/**
	 * this is where to build thing inside JPanel
	 */
	public void run()
	{
		 //build map here
        imageCache = new HashMap<String, BufferedImage>();
		
        //create path
		ClassLoader myLoader = this.getClass().getClassLoader();
        InputStream pathStream = myLoader.getResourceAsStream("resources/path_2.txt");
        Scanner pathScanner = new Scanner(pathStream);
        path = new Path(pathScanner);
        
		state = new State();
		view = new View(this, state);
		view.addMouseListener(this);
        
		state.startFrame();
		
		state.addGameObject(new Background(state, this));
		state.addGameObject(new Menu(state, this));
		state.addGameObject(new Snail(state, this));
		state.addGameObject(new EnemySCargo(state, this));
		state.addGameObject(new towerPlacement(state,this));
		state.addGameObject(new BeerTowerButton(state, this));
		state.addGameObject(new Generate(state,this));
		state.addGameObject(new Generate2(state,this));

		state.finishFrame();
		
		view.repaint();
		
		
		Timer t = new Timer (16, this);
		t.start();
		
		//add listeners
		view.addMouseListener(this);
		view.addMouseMotionListener(this);
		
	}
	
	/**
	 * this function will return current path object.
	 * @return
	 */
	public Path getpath()
	{
		return path;
	}
	
	/**
	 * this function will get needed picture and load as a value
	 * @param filename
	 * @return
	 */
	public BufferedImage getImage(String filename) {
		if(imageCache.containsKey(filename)) {
			return imageCache.get(filename);

		}
		try {
			ClassLoader myLoader = this.getClass().getClassLoader();
			InputStream imageStream = myLoader.getResourceAsStream("resources/" + filename);
			BufferedImage image =javax.imageio.ImageIO.read(imageStream);
			imageCache.put(filename, image);
			System.out.println(filename + " load.");
			return image;
		} catch (IOException e) {
			System.out.println("Could not find or load resources/" + filename);
			System.exit((int)state.getElaspsedTime()); // Close the frame, bail out.
			return null; // Does not happen, the application has exited.
		}
	}
	
	public int getMouseX()
	{
		return mouseX;
	}
	
	 public int getMouseY()
	 {
		 return mouseY;
	 }

	@Override
	/**
	 * a simple frame update loop
	 */
	public void actionPerformed(ActionEvent e) 
	{
		state.startFrame();
//		state.startTime = System.currentTimeMillis();
        for (GameObject go : state.getFrameObjects())
        {
        	go.update(state.getElaspsedTime()); 
            if(state.getLives() < 0) {
            	state.addGameObject(new GameOver(state, this));
            	break;
    		}
        }
//        state.totalTime = System.currentTimeMillis(); 
//        state.elapsedTime = state.totalTime - state.startTime;
        state.finishFrame();
        view.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
//		System.out.print(mouseX + ",");
//		System.out.println(mouseY);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		List<GameObject> list = state.getFrameObjects();
		
		for(GameObject go : list)
		{
			if(go instanceof Clickable)
			{
				Clickable c = (Clickable) go;
				if (c.consumeClick())
					break;
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
