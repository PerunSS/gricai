package com.cerSprikRu.BeerOBan.view.graphicobjects;

import com.cerSprikRu.BeerOBan.view.Constants;

import android.graphics.Bitmap;

public abstract class GraphicObject {
    private Bitmap bitmap;
    private Coordinates coordinates;
    private boolean scaled = false;
 
    public GraphicObject(Bitmap bitmap) {
    	this.bitmap = bitmap;
        coordinates = new Coordinates();
    }
    public GraphicObject(Bitmap bitmap, Coordinates coordinates) {
    	this.bitmap = bitmap;
        this.coordinates = coordinates;
    }
 
    public Bitmap getBitmap() {
    	if(!scaled)
    		bitmap = Bitmap.createScaledBitmap(bitmap, Constants.getInstance().getTileSize(), Constants.getInstance().getTileSize(), false);
    	scaled = true;
        return bitmap;
    }
 
    public Coordinates getCoordinates() {
        return coordinates;
    }
 
    /**
     * Contains the coordinates of the graphic.
     */
    public class Coordinates {
        private int x = 100;
        private int y = 0;
 
        public int getX() {
            return x;
        }
 
        public void setX(int value) {
            x = value - bitmap.getWidth() / 2;
        }
 
        public int getY() {
            return y;
        }
 
        public void setY(int value) {
            y = value - bitmap.getHeight() / 2;
        }
 
        public String toString() {
            return "Coordinates: (" + x + "/" + y + ")";
        }
    }
}
