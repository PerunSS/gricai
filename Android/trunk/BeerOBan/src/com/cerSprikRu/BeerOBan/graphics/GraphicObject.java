package com.cerSprikRu.BeerOBan.graphics;

import android.graphics.Bitmap;

public class GraphicObject {
    private Bitmap bitmap;
    private Coordinates coordinates;
 
    public GraphicObject(Bitmap bitmap) {
    	this.bitmap = bitmap;
        coordinates = new Coordinates();
    }
    public GraphicObject(Bitmap bitmap, int x, int y) {
    	this.bitmap = bitmap;
        coordinates.setX(x);
        coordinates.setY(y);
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
