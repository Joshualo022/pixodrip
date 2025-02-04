package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;



public class CreditsPanel extends JPanel{

        final int width = 800;
        final int height = 600;
        final Color bgColor = new Color(05, 05, 05);
        boolean backButtonClicked;

        
        public CreditsPanel() {
            this.setLayout(null);  
            this.setPreferredSize(new Dimension(width, height));
            this.setBackground(bgColor);
            this.setDoubleBuffered(true);
            this.setFocusable(true);
            backButtonClicked = false;



            // Create a back button

            ImageIcon backIcon = new ImageIcon("src/Main/Assets/backButton.png");
            JButton backButton = new JButton(backIcon);
            backButton.setBounds(150 - backIcon.getIconWidth()/2, 520, backIcon.getIconWidth(), backIcon.getIconHeight());
            backButton.setContentAreaFilled(false);
            backButton.setBorderPainted(false);



            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    backButtonClicked = true;
                };
            });

            backButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBounds(150 - backIcon.getIconWidth()/2, 520, backIcon.getIconWidth() + 20, backIcon.getIconHeight());
                }
            
                public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBounds(150 - backIcon.getIconWidth()/2, 520, backIcon.getIconWidth(), backIcon.getIconHeight());
                }
            });

            
            this.add(backButton);
        }

        public void resetBackButton(){
            backButtonClicked = false;
        }
        public boolean getBackButtonClicked(){
            return backButtonClicked;
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            //draw title
            ImageIcon titleIcon = new ImageIcon("src/Main/Assets/credits.png");
            Image image = titleIcon.getImage();
            g.drawImage(image, 100, 100, this);
        }

        
    }


