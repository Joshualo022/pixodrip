package Main;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import java.io.EOFException;
//read files
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;




public class LoadPanel extends JPanel{
    final int width = 800;
    final Color bgColor = new Color(04, 04, 04);
    JScrollPane scrollPane;
    private boolean CanvasSelected = false;
    private Grid selectedGrid;
    private int selectedGridIndex;
    private GridList grids = new GridList();
    private boolean trashClicked = false;
    private int trashIndex;
    private ArrayList<JButton> foundCanvasButtons = new ArrayList<JButton>();
    private ArrayList<JButton> trashButtons = new ArrayList<JButton>();
    private boolean backButtonClicked;  
    private int height = Main.gridList.getGrids().size() * 40 + 250;  

    public LoadPanel(){

        //set up panel and boolean variables to check for buttons
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(bgColor);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        //need to set layout to null so that buttons can be placed at specific locations
        this.setLayout(null);
        this.backButtonClicked = false;

        
        //read file
        try {
            FileInputStream fileIn = new FileInputStream("data/gridData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            try {
                while (true) {
                    Object obj = in.readObject();
                    GridList objToGridList = (GridList) obj;
                    grids = objToGridList; 
                }
            } catch (EOFException e) {
                // End of file reached
            }

            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }


        //create buttons 
        for (int i = 0; i < grids.getGrids().size(); i++){
            JButton foundCanvasButton = new JButton(grids.getGrids().get(i).getName()){
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() != null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setPaint(getBackground());
                    g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                    g2d.dispose();
                }
                super.paintComponent(g);
            }
            protected void paintBorder(Graphics g) {
                g.setColor(getForeground());
                g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
            }
        };

            foundCanvasButton.setBounds(200, 150 + (i*40), 400, 30);
            foundCanvasButton.setContentAreaFilled(true);

            final int index = i;

            //add action listeners to button
            foundCanvasButton.addActionListener(e -> {
                selectedGrid = grids.getGrids().get(index);
                selectedGridIndex = index;
                CanvasSelected = true;
            });

            //add mouse listeners to buttons for animation
            foundCanvasButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    foundCanvasButton.setBackground(Color.WHITE);
                }
            
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    foundCanvasButton.setBackground(new Color(200,200,200));
                }
            });
            foundCanvasButton.setContentAreaFilled(false);
            foundCanvasButton.setFocusable(false);


            foundCanvasButton.setBackground(new Color(200,200,200));
            foundCanvasButton.setBorder(new LineBorder(new Color(180,180,180), 3));
            this.add(foundCanvasButton);
            foundCanvasButtons.add(foundCanvasButton);

        }
        for (int i = 0; i < grids.getGrids().size(); i++){
            ImageIcon trashIcon = new ImageIcon("src/Main/Assets/trashButton.png");
            Image image = trashIcon.getImage();
            Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            trashIcon = new ImageIcon(newImage);            

            final int index = i;
            
            JButton trashButton = new JButton(trashIcon);
            trashButton.setBounds(610, 150 + (i*40), 30, 30);
            trashButton.setContentAreaFilled(false);
            trashButton.setBorderPainted(false);

            //add action listeners to button
            trashButton.addActionListener(e -> {
                trashIndex = index;
                System.out.println("Trash index: " + trashIndex);
                trashClicked = true;
                
                this.remove(foundCanvasButtons.get(index));
                foundCanvasButtons.remove(index);
                grids.removeGrid(index);
                

                //reposition buttons
                for (int j = foundCanvasButtons.size() - 1; j >= 0; j--){
                    foundCanvasButtons.get(j).setBounds(200, 150 + (j*40), 400, 30);
                    final int ind = j;
                    foundCanvasButtons.get(j).removeActionListener(foundCanvasButtons.get(j).getActionListeners()[0]);
                    foundCanvasButtons.get(j).addActionListener(e1 -> {

                        selectedGrid = grids.getGrids().get(ind);
                        selectedGridIndex = ind;
                        CanvasSelected = true;
                    });
                    foundCanvasButtons.get(j).revalidate();
                    foundCanvasButtons.get(j).repaint();
                }

                for (int j = 0; j < trashButtons.size(); j++){
                    this.remove(trashButtons.get(j));
                }
                
                for (int j = trashButtons.size() - 1; j >= 0; j--){
                    if (j > index) {

                        trashButtons.set(j, trashButtons.get(j - 1));
                        trashButtons.get(j).removeActionListener(null);
                        
                    } 
                }   

                trashButtons.remove(index);
                
                for (int j = 0; j < trashButtons.size(); j++){                    
                    trashButtons.get(j).setBounds(610, 150 + (j*40), 30, 30);
                    this.add(trashButtons.get(j));

                }
                   


                this.revalidate();
                this.repaint();
            });

            trashButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    trashButton.setBounds(615, 150 + (index*40), 30, 30);
                }
            
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    trashButton.setBounds(610, 150 + (index*40), 30, 30);
                }
            });
            
            this.add(trashButton);
            trashButtons.add(trashButton);
        }

        ImageIcon backIcon = new ImageIcon("src/Main/Assets/backButton.png");
        JButton backButton = new JButton(backIcon);
        if (foundCanvasButtons.size() > 10){
            backButton.setBounds(150 - backIcon.getIconWidth()/2, 450 + ((foundCanvasButtons.size() - 9) *40), backIcon.getIconWidth(), backIcon.getIconHeight());
        } else {
            backButton.setBounds(150 - backIcon.getIconWidth()/2, 450, backIcon.getIconWidth(), backIcon.getIconHeight());
        }
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);

        backButton.addActionListener(e -> {
            backButtonClicked = true;
        });

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (foundCanvasButtons.size() > 10){
                    backButton.setBounds(150 - backIcon.getIconWidth()/2 + 10, 450 + ((foundCanvasButtons.size() - 9) *40), backIcon.getIconWidth(), backIcon.getIconHeight());
                } else {
                    backButton.setBounds(150 - backIcon.getIconWidth()/2 + 10, 450, backIcon.getIconWidth(), backIcon.getIconHeight());
                }
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (foundCanvasButtons.size() > 10){
                    backButton.setBounds(150 - backIcon.getIconWidth()/2, 450 + ((foundCanvasButtons.size() - 9) *40), backIcon.getIconWidth(), backIcon.getIconHeight());
                } else {
                    backButton.setBounds(150 - backIcon.getIconWidth()/2, 450, backIcon.getIconWidth(), backIcon.getIconHeight());
                }
            }
        });

        this.add(backButton);

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //draw components
        ImageIcon titleIcon = new ImageIcon("src/Main/Assets/loadTitle.png");
        Image image = titleIcon.getImage();
        g.drawImage(image, width/2 - titleIcon.getIconWidth()/2, 60, this);
    }

    public boolean getCanvasSelected(){
        return CanvasSelected;
    }
    public Grid getSelectedGrid(){
        return selectedGrid;
    }
    public void resetCanvasSelected(){
        CanvasSelected = false;
    }
    public int getSelectedGridIndex(){
        return selectedGridIndex;
    }
    public boolean getTrashClicked(){
        return trashClicked;
    }
    public int getTrashIndex(){
        return trashIndex;
    }
    public void resetTrashClicked(){
        trashClicked = false;
    }

    public boolean getBackButtonClicked(){
        return backButtonClicked;
    }
    public void resetBackButton(){
        backButtonClicked = false;
    }
}
