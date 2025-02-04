package Main;

import java.io.Serializable;

public class Grid implements Serializable{

    private Tile[][] grid;
    private String name;
    private int tileSize;

    //constructor that creates a grid of tiles
    public Grid(int rows, int cols, int tileSize, float baseH) {
        this.tileSize = tileSize;
        grid = new Tile[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = new Tile(c * tileSize, r * tileSize, new float[]{baseH, 0.5f, 0.7f}, baseH);
            }
        }
        this.name = "Untitled";
    }

    public Tile[][] getGrid() {
        return grid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getTileSize() {
        return tileSize;
    }
}
