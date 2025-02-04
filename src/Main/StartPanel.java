package Main;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartPanel extends JPanel{


    final int width = 800;
    final int height = 600;
    Color bgColor = new Color(05, 05, 05);

    boolean loadCanvasClicked;
    boolean newCanvasClicked;
    boolean creditsClicked;

    

    public StartPanel(){

        //set up panel and boolean variables to check for buttons
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(bgColor);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        loadCanvasClicked = false;
        newCanvasClicked = false;
        creditsClicked = false;

        //need to set layout to null so that buttons can be placed at specific locations
        this.setLayout(null);

        //create buttons
        ImageIcon loadIcon = new ImageIcon("src/Main/Assets/loadCanvasButton.png");
        JButton loadCanvasButton = new JButton(loadIcon);
        loadCanvasButton.setBounds(50, 420, loadIcon.getIconWidth(), loadIcon.getIconHeight());
        loadCanvasButton.setContentAreaFilled(false);
        loadCanvasButton.setBorderPainted(false);
        

        ImageIcon newIcon = new ImageIcon("src/Main/Assets/newCanvasButton.png");        
        JButton newCanvasButton = new JButton(newIcon);
        newCanvasButton.setBounds(50, 360, newIcon.getIconWidth(), newIcon.getIconHeight());
        newCanvasButton.setContentAreaFilled(false);
        newCanvasButton.setBorderPainted(false);
        
        ImageIcon creditsIcon = new ImageIcon("src/Main/Assets/creditsButton.png");
        JButton creditsButton = new JButton(creditsIcon);
        creditsButton.setBounds(50, 480, creditsIcon.getIconWidth(), creditsIcon.getIconHeight());
        creditsButton.setContentAreaFilled(false);
        creditsButton.setBorderPainted(false);

        //add mouse listeners to buttons for animation
        loadCanvasButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loadCanvasButton.setBounds(50 , 420 , loadIcon.getIconWidth() + 20, loadIcon.getIconHeight());
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loadCanvasButton.setBounds(50, 420, loadIcon.getIconWidth(), loadIcon.getIconHeight());
            }
        });
        
        newCanvasButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newCanvasButton.setBounds(50 , 360, newIcon.getIconWidth() + 20, newIcon.getIconHeight());
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                newCanvasButton.setBounds(50, 360, newIcon.getIconWidth(), newIcon.getIconHeight());
            }
        });
        creditsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            creditsButton.setBounds(50 , 480 , creditsIcon.getIconWidth() + 20, creditsIcon.getIconHeight());
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
            creditsButton.setBounds(50, 480, creditsIcon.getIconWidth(), creditsIcon.getIconHeight());
            }
        });

        

        //add action listeners to buttons
        loadCanvasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCanvasClicked = true;
            };
        });

        newCanvasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCanvasClicked = true;
            };
        });
        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creditsClicked = true;
            };
        });

        //add buttons to panel
        this.add(newCanvasButton);
        this.add(loadCanvasButton);
        this.add(creditsButton);
    }

    public boolean getLoadCanvasClicked(){
        return loadCanvasClicked;
    }

    public boolean getNewCanvasClicked(){
        return newCanvasClicked;
    }
    public boolean getCreditsClicked(){
        return creditsClicked;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //draw title
        ImageIcon titleIcon = new ImageIcon("src/Main/Assets/title.png");
        Image image = titleIcon.getImage();
        ImageIcon titleSideIcon = new ImageIcon("src/Main/Assets/titleSide.png");
        Image titleSide = titleSideIcon.getImage();
        g.drawImage(image, 100, 100, this);
        g.drawImage(titleSide, 450, 0, this);
    }

}


