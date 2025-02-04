package Main;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel{
    
    final int width = 200;
    final int height = 600;    
    final Color bgColor = new Color(20, 20, 20);
    public static int brushID = 0;
    private boolean saveClicked = false;
    
    
    public SettingsPanel(){

        //set up panel and boolean variables to check for buttons
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(bgColor);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        //need to set layout to null so that buttons can be placed at specific locations
        this.setLayout(null);

        /*ImageIcon tileSizeIcon = new ImageIcon("src/Main/Assets/tileSizeButton.png");      
        JButton tileSizeButton = new JButton(tileSizeIcon);
        tileSizeButton.setBounds(width/2 - tileSizeIcon.getIconWidth()/2 - 30, 250, tileSizeIcon.getIconWidth(), tileSizeIcon.getIconHeight());
        tileSizeButton.setContentAreaFilled(false);
        tileSizeButton.setBorderPainted(false);*/


        //create buttons

        JButton[] buttons = new JButton[8];
        ImageIcon[] buttonIcons = {new ImageIcon("src/Main/Assets/hueButton.png"), new ImageIcon("src/Main/Assets/satUpButton.png"), 
                                    new ImageIcon("src/Main/Assets/satDownButton.png"), new ImageIcon("src/Main/Assets/brightUpButton.png"), 
                                    new ImageIcon("src/Main/Assets/brightDownButton.png"), new ImageIcon("src/Main/Assets/sizeUpButton.png"),
                                    new ImageIcon("src/Main/Assets/sizeDownButton.png"), new ImageIcon("src/Main/Assets/saveButton.png")};

        int offset = 63/2;

        int[] buttonX = {100 - offset,60 - offset,140 - offset,60 - offset,140 - offset,100 - offset,100 - offset, 40};
        int[] buttonY = {30,120,120,210,210,340,430,550};
        
        for (int i = 0; i < buttons.length; i++){

            Image img = buttonIcons[i].getImage(); 
            Image imgResized = img.getScaledInstance(130, 40,  java.awt.Image.SCALE_SMOOTH);
            if (!(i == 7)){
                imgResized = img.getScaledInstance(63, 65,  java.awt.Image.SCALE_SMOOTH);
            }
            ImageIcon resizedIcon = new ImageIcon(imgResized);

            buttons[i] = new JButton(resizedIcon);
            buttons[i].setBounds(buttonX[i], buttonY[i], resizedIcon.getIconWidth(), resizedIcon.getIconHeight());
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(false);
            if (i < 5){
                final int index = i;
                buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    brushID = index;
                    };
                });
            }
            if (i == 5){
                buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        KeyHandler.setBrushSize(KeyHandler.getBrushSize() + 1);
                    };
                });
            }
            if (i == 6){
                buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    KeyHandler.setBrushSize(KeyHandler.getBrushSize() - 1);
                    };
                });
            }
            if (i == 7){
                buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveClicked = true;
                    };
                });
            }

            
            
        }
        for (JButton button: buttons){
            this.add(button);
        }
    }

    public boolean getSaveClicked(){
        return saveClicked;
    }
    public void resetSaveClicked(){
        saveClicked = false;
    }
}

