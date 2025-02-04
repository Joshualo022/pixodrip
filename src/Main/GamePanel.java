package Main;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //instance variables for the application useful for tiles
    private int tileSize;
    final int width = 800;
    final int height = 600;
    final int rows;
    final int cols;
    private int brushSize = 1;
    private volatile boolean running = false;

    //create thread object in game panel. Thread is object that runs the game loop
    Thread thread;
    MouseHandler mouseHandler = new MouseHandler();
    KeyHandler keyHandler = new KeyHandler();
    Grid grid;
    

    //set my fps
    int FPS = 60;
    

    //constructor for game panel. set dimensions and background color.
    public GamePanel(int tileSize, float baseH, Grid grid){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        //add mouse handler to game panel and make it able to focus and unfocus
        this.addMouseMotionListener(mouseHandler);
        this.addMouseListener(mouseHandler);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        
        
        this.rows = height/tileSize;
        this.cols = width/tileSize;
        this.tileSize = tileSize;

        if (grid != null){
            this.grid = grid;
        }
        else{
            this.grid = new Grid(rows, cols, tileSize, baseH);
        }
    }

    //start method for game panel. initialize thread object and start the thread.
    public void start(){
        running = true;
        this.requestFocusInWindow();
        thread = new Thread(this);
        thread.start();
    }

    //stop method
    public void stop(){
        running = false;
    }

    //implements runnable will add this run method that is run when thread is started
    @Override
    public void run() {
        
        //find total time until next frame and set time for next frame
        double looptime = 1000000000/FPS;
        double nextTime = System.nanoTime() + looptime;

        //game loop
        while(running){

            //System.out.println("Running");
            // update game
            update();

            // render tiles - call repaint instead of paintComponent to render tiles
            repaint();

            //try to find difference between current time and next frame. sleep for the difference
            try{
                long waitTime = (long) (nextTime - System.nanoTime()) / 1000000;

                if(waitTime < 0){
                    waitTime = 0;
                }

                Thread.sleep(waitTime);
                nextTime = System.nanoTime() + looptime;

            }catch(Exception e){
                e.printStackTrace();
            }

        }

    }

    public void update(){
            // set brush size to brushsize
            brushSize = KeyHandler.getBrushSize();
            //get mouse x and y
            int mousey = mouseHandler.mousey;
            int mousex = mouseHandler.mousex;
        
            //get canvas size
            int canvasWidth = width - (width%tileSize);
            int canvasHeight = height - (height%tileSize);
            
            //get the tile that the mouse is on
            int r = mousey/tileSize;
            int c = mousex/tileSize;
            
            if (c < canvasWidth/tileSize && r < canvasHeight/tileSize && c >= 0 && r >= 0){;
                
                //Create area object to get coordinates
                Area area = new Area(grid.getGrid()[r][c], tileSize, canvasWidth, canvasHeight);
                area.addCoords(grid.getGrid()[r][c], brushSize);
            
                //change the hsb values of the tile that the mouse is on
                if (mouseHandler.isPressed){
                    for (Coordinate coord: area.getArea()){
                        if (coord.checkBounds()){
                            int gridx = coord.getX()/tileSize;
                            int gridy = coord.getY()/tileSize;
                            if (SettingsPanel.brushID == 0){
                                grid.getGrid()[gridy][gridx].changeH(0.005f);
                            }
                            if (SettingsPanel.brushID == 1){
                                grid.getGrid()[gridy][gridx].changeS(0.005f);
                            }
                            if (SettingsPanel.brushID == 2){
                                grid.getGrid()[gridy][gridx].changeS(-0.005f);
                            }
                            if (SettingsPanel.brushID == 3){
                                grid.getGrid()[gridy][gridx].changeB(0.005f);
                            }
                            if (SettingsPanel.brushID == 4){
                                grid.getGrid()[gridy][gridx].changeB(-0.005f);
                            }
                        }
                    }                                    
                }
            }
        
        

    }

    //this is a built in method in java and it will take in a graphics argument
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //draw tiles
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                Tile currentTile = grid.getGrid()[r][c];
                float h = currentTile.getHsb()[0];
                float s = currentTile.getHsb()[1];
                float b = currentTile.getHsb()[2];
                int rgb = Color.HSBtoRGB(h, s, b);
                Color color = new Color(rgb);
                g.setColor(color);
                g.fillRect(currentTile.getX(), currentTile.getY(), tileSize, tileSize);      
                
                
       
            }
        }
        //draw dircle outline that follows mouse
        g.setColor(new Color (255, 255, 255, 50));
        int diameter = tileSize * brushSize;
        g.drawOval(mouseHandler.mousex - diameter/2, mouseHandler.mousey - diameter/2, diameter, diameter);
    }

    public Grid getGrid(){
        return grid;
    }
}
