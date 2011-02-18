package com.cerSprikRu.BeerOBan.graphics;

import android.content.res.Resources;
import android.graphics.Bitmap;

public abstract class GraphicObject {
    private Bitmap bitmap;
    private Coordinates coordinates;
    private Resources resources;
 
    public GraphicObject(Resources resources, Bitmap bitmap) {
    	this.bitmap = bitmap;
    	this.resources = resources;
        coordinates = new Coordinates();
    }
    public GraphicObject(Resources resources, Bitmap bitmap, Coordinates coordinates) {
    	this.bitmap = bitmap;
    	this.resources = resources;
        coordinates.setX(coordinates.getX());
        coordinates.setY(coordinates.getY());
    }
 
    public Bitmap getGraphic() {
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
