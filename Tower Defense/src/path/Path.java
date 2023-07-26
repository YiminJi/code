/**
 * This class will create path objects
 * 
 * 
 * assignment #9
 * 
 * Class CS1420
 * @author  ---Yimin Jiang---
 * @version November 15, 2022
 */

package path;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

public class Path {
    List<Point> pointArray;
    Scanner scanner;
    // setup list and scanner
    
    /*
     * create path holder
     */
    public Path() {
    	this.pointArray = new ArrayList<>();
    }

    public Path(Scanner scanner){
         this.pointArray = new ArrayList<>();
         int n = scanner.nextInt();
         for(int i = 0; i < n;i++) {
        	 int x = scanner.nextInt();
        	 int y = scanner.nextInt();
        	 add(x,y);
         }
    }
    
    /*
     * get the length of the array and return it size
     */
    public int getPointCount(){
        return this.pointArray.size();
    }

    /*
     * this function will loop over the all the point and find the require postion's point and return the
     * x value of the point
     */
    public int getX(int n){
        int size = pointArray.size();
        int X_cor = -1;
        for(int i = 0; i < size;i++){
            if(i == n){
                X_cor = pointArray.get(i).x;
                break;
            }
        }
        return X_cor;
    }

    /*
     * this function will loop over the all the point and find the require postion's point and return the
     * x value of the point
     */
    public int getY(int n){
        int size = pointArray.size();
        int Y_cor = -1;
        for(int i = 0; i < size;i++){
            if(i == n){
                Y_cor = pointArray.get(i).y;
                break;
            }
        }
        return Y_cor;
    }

    /*
     * this function will add point into pointArray
     */
    public void add(int x, int y){
        Point new_point = new Point(x,y);
        this.pointArray.add(new_point);
    }

  /**
   * this program will format all points and put them into string
   */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(pointArray.size() + "\n");
        for(Point p : pointArray){
            String X_string = p.x + " ";
            String Y_string = p.y + "";
            sb.append(X_string).append(Y_string).append("\n");
        }
        return sb.toString();
    }
    /**
     * this method will draw the each point and draw line between them
     * @param g
     */
    public void paint(Graphics g) {
    	g.setColor(Color.RED);
    	for(int i = 0; i < pointArray.size(); i++) {
    		g.fillOval(pointArray.get(i).x-5, pointArray.get(i).y-5, 10, 10);
    	}
    	for(int i = 1; i < pointArray.size(); i++) {
    		g.drawLine(pointArray.get(i).x, pointArray.get(i).y, pointArray.get(i-1).x, pointArray.get(i-1).y);
    	}
    }

	public Point convertToCoordinates(double percentage) {
		if(percentage <= 0.0) {
			return pointArray.get(0);
		}
		if(percentage > 1.0) {
			return pointArray.get(pointArray.size() - 1);
		}
		
		
		double totalLength = 0;
		//to get the total length
		for(int i = 1; i<pointArray.size();i++) {
			double xP = Math.pow(getX(i)-getX(i-1),2);
			double yP = Math.pow(getY(i)-getY(i-1),2);
			totalLength += Math.sqrt(xP + yP);
		}
		
		double Xresult = 0;
		double Yresult = 0;
		double target = percentage * totalLength;
		Point p1 = new  Point();
		Point p2 = new  Point();
				
		double totalTmp = 0;
		for(int i = 1; i<pointArray.size();i++) {
			double xP1 = Math.pow(getX(i)-getX(i-1),2);
			double yP1 = Math.pow(getY(i)-getY(i-1),2);
			totalTmp += Math.sqrt(xP1 + yP1);
			if(target < totalTmp) {
				p1 = pointArray.get(i-1);
				p2 = pointArray.get(i);
				break;
			}
		}
		// Xresult = (1 - p) * Xstart + (p) * Xend
		// Yresult = (1 - p) * Ystart + (p) * Yend
		Xresult = (1- percentage) * p1.x + percentage* p2.x;
		Yresult = (1- percentage) * p1.y +percentage* p2.y;
		Point new_point = new Point(((int)Xresult), ((int)Yresult));
//		System.out.println(new_point);
		return new_point;
	}


}