package Main;

import java.util.ArrayList;

public class Area {
    private ArrayList<Coordinate> coords;
    private int tileSize;
    private int width;
    private int height;


    public Area(Tile center, int tileSize, int canvasWidth, int canvasHeight) {
        coords = new ArrayList<>();
        Coordinate centerCoords = new Coordinate(center.getX(), center.getY(), canvasWidth, canvasHeight);

        coords.add(centerCoords);

        this.tileSize = tileSize;
        this.width = canvasWidth;
        this.height = canvasHeight;
    }
    

    public void addCoords(Tile centerTile, int radius) {
        //add surrounding tiles to the list

        if (radius > 1){
        coords.add(new Coordinate(centerTile.getX() + tileSize, centerTile.getY(), width, height));

        coords.add(new Coordinate(centerTile.getX() - tileSize, centerTile.getY(), width, height));

        coords.add(new Coordinate(centerTile.getX(), centerTile.getY() + tileSize, width, height));

        coords.add(new Coordinate(centerTile.getX(), centerTile.getY() - tileSize,  width, height));
        }

        if(radius > 2){

            Tile left = new Tile(centerTile.getX() - tileSize, centerTile.getY(), null, 0.0f);
            addCoords(left, radius - 1);

            Tile right = new Tile(centerTile.getX() + tileSize, centerTile.getY(), null, 0.0f);
            addCoords(right, radius - 1);

            Tile up = new Tile(centerTile.getX(), centerTile.getY() - tileSize, null, 0.0f);
            addCoords(up, radius - 1);

            Tile down = new Tile(centerTile.getX(), centerTile.getY() + tileSize, null, 0.0f);
            addCoords(down, radius - 1);
        }

        cleanUp();
    }

    public void cleanUp() {
        
        for (int i = 0; i < coords.size(); i++) {
            //remove out of bounds
            if (!coords.get(i).checkBounds()) {
                coords.remove(i);
            }
            //remove duplicates
            for (int check = i + 1; check < coords.size(); check++) {
                if (coords.get(i).equals(coords.get(check))) {
                    coords.remove(check);                    
                }
            }
            
            
        }
    }


    public ArrayList<Coordinate> getArea() {
        return coords;
    }

}
