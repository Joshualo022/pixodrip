package Main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.EOFException;
import java.io.FileInputStream;
//files
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//import javax.swing.BoxLayout;
import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Main {
    static JFrame window;
    static StartPanel home;
    static int tileSize = 32;
    static int[] tileSizeOptions = {6, 10, 16, 24, 32};
    static int tileSizeIndex = 4;
    static float backgroundHue = 0.0f;
    static GridList gridList = new GridList();
    static int saveIndex = -1;
    static boolean running;

    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
            
        while(true){
            //create jframe window object
            

            //create container
            /*JPanel container = new JPanel();
            container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));*/

            home = new StartPanel();
            window.add(home);
            window.pack();


            //read data from file
            try {
                FileInputStream fileIn = new FileInputStream("data/gridData.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);

                try {
                    while (true) {
                        Object obj = in.readObject();
                        GridList objToGridList = (GridList) obj;
                        gridList = objToGridList;
                    }
                } catch (EOFException e) {
                    // End of file reached
                }

                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
                System.out.println("file empty or not found");

                //if the file is not found or serialized, write empty gridList into file
                try {
                    FileOutputStream fileOut = new FileOutputStream("data/gridData.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(gridList);
                    out.close();
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            
            } catch (ClassNotFoundException c) {
                System.out.println("Class not found");
                c.printStackTrace();
                return;
            }

            System.out.println("files checked");

            running = true;
            //menu logistics loop
            while (running){
                System.out.print("");
                    //check if new canvas or load canvas button was clicked
                if (home.getNewCanvasClicked()){
                    //open set new panel
                    home.setVisible(false);
                    window.remove(home);
                    SetNewPanel newPanel = new SetNewPanel();
                    window.add(newPanel); 
                    window.pack();

                    
                    while (true){
                        System.out.print("");
                        if (newPanel.getTileSizeClicked()){
                            if (tileSizeIndex < tileSizeOptions.length - 1){
                                tileSizeIndex++;
                            } else {
                                tileSizeIndex = 0;
                            }
                            tileSize = tileSizeOptions[tileSizeIndex];
                            newPanel.resetTileSizeButton();
                            newPanel.repaint();
                            System.out.println(tileSize);
                        }
                        if (newPanel.getBaseHClicked()){                    
                            backgroundHue += 0.0323f;
                            newPanel.resetBaseHButton();
                            newPanel.repaint();
                            System.out.println(backgroundHue);
                        }
                        if (newPanel.getNewCanvasClicked()){
                            //close the start panel
                            newPanel.setVisible(false);
                            window.remove(newPanel);
                            launchGame(tileSize, backgroundHue, null, -1);
                            System.out.println("new canvas break");
                            running = false;
                            break;
                        }
                        if (newPanel.getBackButtonClicked()){
                            newPanel.setVisible(false);
                            window.remove(newPanel);
                            running = false;
                            break;
                        }
                    }
                }
                if (home.getLoadCanvasClicked()){

                    //close the start panel
                    home.setVisible(false);
                    window.remove(home);
                    LoadPanel loadPanel = new LoadPanel();
                    JScrollPane scrollPane = new JScrollPane(loadPanel);
                    scrollPane.setPreferredSize(new Dimension(820, 600));
                    window.add(scrollPane);
                    window.pack();
                    while (loadPanel.getCanvasSelected() == false){
                        System.out.print("");
                        if (loadPanel.getTrashClicked()){
                            saveIndex = loadPanel.getTrashIndex();
                            System.out.println("save index: " + saveIndex + "Deleted: " + gridList.getGrids().get(saveIndex).getName());
                            gridList.removeGrid(saveIndex);
                            try {
                                FileOutputStream fileOut = new FileOutputStream("data/gridData.ser");
                                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                                out.writeObject(gridList);
                                out.close();
                                fileOut.close();
                            } catch (IOException i) {
                                i.printStackTrace();
                            }  
                            loadPanel.resetTrashClicked();

                        }
                        if (loadPanel.getBackButtonClicked()){
                            loadPanel.setVisible(false);
                            window.remove(loadPanel);
                            break;
                        }
                    }
                    if (loadPanel.getCanvasSelected() == true){
                        Grid selectedGrid = loadPanel.getSelectedGrid();
                        int selectedGridIndex = loadPanel.getSelectedGridIndex();
                        loadPanel.resetCanvasSelected();
                        window.remove(scrollPane);
                        tileSize = selectedGrid.getTileSize();
                        launchGame(tileSize, backgroundHue, selectedGrid, selectedGridIndex);
                        System.out.println("load canvas break");
                        running = false;
                        break;
                    } else {
                        window.remove(scrollPane);
                        running = false;
                        break;
                    }
                } 
                if (home.getCreditsClicked()){
                    //open set new panel
                    home.setVisible(false);
                    window.remove(home);
                    CreditsPanel creditsPanel = new CreditsPanel();
                    window.add(creditsPanel); 
                    window.pack();

                    
                    while (true){
                        System.out.print("");

                        if (creditsPanel.getBackButtonClicked()){
                            creditsPanel.setVisible(false);
                            window.remove(creditsPanel);
                            running = false;
                            break;
                        }
                    
                    }
                }

                           
            }
            
            System.out.println("loop broken");
        }

        
    }
    public static void hidePanels(){
        window.getContentPane().removeAll();
    }

    public static void launchGame(int tileSize, float backgroundHue, Grid grid, int saveIndex){
        //create container
        JPanel container = new JPanel();
        container.setLayout(new FlowLayout()); 
        //create game panel object and add it to the window
        GamePanel panel = new GamePanel(tileSize, backgroundHue, grid);
        
        panel.setPreferredSize(new Dimension(800, 600));
        
        SettingsPanel panel2 = new SettingsPanel();
        panel2.setPreferredSize(new Dimension(200, 600));
        // Add panels to container and add container to window
        container.add(panel);
        container.add(panel2);
        
        window.add(container);
        window.pack();
        //window.setSize(new Dimension(1000, 600));
        window.setVisible(true);
        //start the game panel, will start game loop
        panel.start();

        while(true){
            System.out.print("");
            if (panel2.getSaveClicked()){
                panel2.resetSaveClicked();
                Grid currentGrid = panel.getGrid();

                //close the game panel
                panel.stop();
                window.remove(container);

                //open save panel
                SavePanel savePanel = new SavePanel(currentGrid.getName());
                window.add(savePanel);
                window.pack();
                
                while (savePanel.getSaveClicked() == false || savePanel.getBackClicked() == false){
                    System.out.print("");
                    if (savePanel.getSaveClicked() == true){
                        
                        currentGrid.setName(savePanel.getGridName());
                        //save the grid
                        if (saveIndex == -1){
                            gridList.addGrid(currentGrid);
                        } else {
                            gridList.replaceGrid(saveIndex, currentGrid);
                        }
                        try {
                            FileOutputStream fileOut = new FileOutputStream("data/gridData.ser");
                            ObjectOutputStream out = new ObjectOutputStream(fileOut);
                            out.writeObject(gridList);
                            out.close();
                            fileOut.close();
                        } catch (IOException i) {
                            i.printStackTrace();
                        }  
                        
                        
                        savePanel.resetSaveClicked();
                        savePanel.resetBackClicked();
                        break;
                    }
                }
                savePanel.setVisible(false);
                window.remove(savePanel);

                window.repaint();
                break;                                             
            }            
        }  

    } 
}
