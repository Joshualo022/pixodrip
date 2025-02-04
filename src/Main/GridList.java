package Main;

import java.io.Serializable;
import java.util.ArrayList;

public class GridList implements Serializable{
   
    private ArrayList<Grid> grids;

    public GridList() {
        grids = new ArrayList<Grid>();
    }
    public void addGrid(Grid grid){
        grids.add(grid);
    }
    public ArrayList<Grid> getGrids(){
        return grids;
    }
    public void replaceGrid(int index, Grid grid){
        grids.set(index, grid);
    }
    public void removeAll(){
        grids.clear();
    }
    public void removeGrid(int index){
        grids.remove(index);
    }
}
