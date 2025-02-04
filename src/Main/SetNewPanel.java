package Main;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SetNewPanel extends JPanel{

    final int width = 800;
    final int height = 600;
    Color bgColor = new Color(04, 04, 04);

    boolean tileSizeClicked;
    boolean newCanvasClicked;
    boolean baseHClicked;
    boolean backButtonClicked;


    public SetNewPanel(){

        //set up panel and boolean variables to check for buttons
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(bgColor);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        tileSizeClicked = false;
        newCanvasClicked = false;
        baseHClicked = false; 
        backButtonClicked = false;  


        //need to set layout to null so that buttons can be placed at specific locations
        this.setLayout(null);

        //create buttons
        ImageIcon tileSizeIcon = new ImageIcon("src/Main/Assets/tileSizeButton.png");      
        JButton tileSizeButton = new JButton(tileSizeIcon);
        tileSizeButton.setBounds(250 - tileSizeIcon.getIconWidth()/2, 250, tileSizeIcon.getIconWidth(), tileSizeIcon.getIconHeight());
        tileSizeButton.setContentAreaFilled(false);
        tileSizeButton.setBorderPainted(false);
        tileSizeButton.setFocusPainted(false);

        ImageIcon baseHIcon = new ImageIcon("src/Main/Assets/baseHButton.png");      
        JButton baseHButton = new JButton(baseHIcon);
        baseHButton.setBounds(550 - baseHIcon.getIconWidth()/2, 250, baseHIcon.getIconWidth(), baseHIcon.getIconHeight());
        baseHButton.setContentAreaFilled(false);
        baseHButton.setBorderPainted(false);
        baseHButton.setFocusPainted(false);
        
        ImageIcon createCanvasIcon = new ImageIcon("src/Main/Assets/CreateCanvasButton.png");
        JButton createCanvasButton = new JButton(createCanvasIcon);
        createCanvasButton.setBounds(680 - createCanvasIcon.getIconWidth()/2, 520, createCanvasIcon.getIconWidth(), createCanvasIcon.getIconHeight());
        createCanvasButton.setContentAreaFilled(false);
        createCanvasButton.setBorderPainted(false);

        ImageIcon backIcon = new ImageIcon("src/Main/Assets/backButton.png");
        JButton backButton = new JButton(backIcon);
        backButton.setBounds(150 - backIcon.getIconWidth()/2, 520, backIcon.getIconWidth(), backIcon.getIconHeight());
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);


        //add action listeners to buttons
        tileSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tileSizeClicked = true;
            };
        });
        
        baseHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            baseHClicked = true;
            };
        });

        createCanvasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCanvasClicked = true;
            };
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backButtonClicked = true;
            };
        });

        //add animation to buttons
        createCanvasButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            createCanvasButton.setBounds(680 - createCanvasIcon.getIconWidth()/2, 520, createCanvasIcon.getIconWidth() + 20, createCanvasIcon.getIconHeight());
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
            createCanvasButton.setBounds(680 - createCanvasIcon.getIconWidth()/2, 520, createCanvasIcon.getIconWidth(), createCanvasIcon.getIconHeight());
            }
        });
        baseHButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            baseHButton.setBounds(550 - baseHIcon.getIconWidth()/2, 250 , baseHIcon.getIconWidth() + 5, baseHIcon.getIconHeight() + 5);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
            baseHButton.setBounds(550 - baseHIcon.getIconWidth()/2, 250, baseHIcon.getIconWidth(), baseHIcon.getIconHeight());
            }
        });
        tileSizeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            tileSizeButton.setBounds(250 - tileSizeIcon.getIconWidth()/2, 250, tileSizeIcon.getIconWidth() + 5, tileSizeIcon.getIconHeight()+ 5);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
            tileSizeButton.setBounds(250 - tileSizeIcon.getIconWidth()/2, 250, tileSizeIcon.getIconWidth(), tileSizeIcon.getIconHeight());
            }
        });
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            backButton.setBounds(150 - backIcon.getIconWidth()/2, 520, backIcon.getIconWidth() + 20, backIcon.getIconHeight());
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
            backButton.setBounds(150 - backIcon.getIconWidth()/2, 520, backIcon.getIconWidth(), backIcon.getIconHeight());
            }
        });


        this.add(createCanvasButton);
        this.add(baseHButton);
        this.add(tileSizeButton);
        this.add(backButton);
    }

    public boolean getTileSizeClicked(){
        return tileSizeClicked;
    }

    public boolean getNewCanvasClicked(){
        return newCanvasClicked;
    }
    public boolean getBaseHClicked(){
        return baseHClicked;
    }
    public boolean getBackButtonClicked(){
        return backButtonClicked;
    }
    
    public void resetTileSizeButton(){
        tileSizeClicked = false;
    }
    public void resetBaseHButton(){
        baseHClicked = false;
    }
    public void resetBackButton(){
        backButtonClicked = false;
    }


    

    //paint components
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        int bgColorHSB = Color.HSBtoRGB(Main.backgroundHue, 0.5f, 0.8f);
        g.setColor(new Color(bgColorHSB));
        g.fillRect(width/2 - (Main.tileSize/2),280 - (Main.tileSize/2), Main.tileSize, Main.tileSize);
    }
}


