package Main;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class SavePanel extends JPanel {
    final int width = 800;
    final int height = 600;    
    final Color bgColor = new Color(04, 04, 04);
    private boolean saveClicked = false;
    private boolean backClicked = false;
    private String gridName = "untitled";


    public SavePanel(String canvasName){

        //set up panel and boolean variables to check for buttons
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(bgColor);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        //need to set layout to null so that buttons can be placed at specific locations
        this.setLayout(null);

        //make save and back buttons
        ImageIcon saveIcon = new ImageIcon("src/Main/Assets/saveCanvasButton.png");
        JButton saveButton = new JButton(saveIcon);
        saveButton.setBounds(700 - saveIcon.getIconWidth()/2, 520, saveIcon.getIconWidth(), saveIcon.getIconHeight());
        saveButton.setContentAreaFilled(false);
        saveButton.setBorderPainted(false);

        ImageIcon backIcon = new ImageIcon("src/Main/Assets/backButton.png");
        JButton backButton = new JButton(backIcon);
        backButton.setBounds(130 - backIcon.getIconWidth()/2, 520, backIcon.getIconWidth(), backIcon.getIconHeight());
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        
        //create text input
        JTextField textField = new JTextField(20);
        textField.setVisible(true);
        textField.setBounds(300, 300, 200, 30); // Adjust these values as needed
        textField.setText(canvasName);
        this.add(textField);

        //add action listeners to buttons
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridName = textField.getText();
                saveClicked = true;
                
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //go back
            }
        });

        //add animation to buttons
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveButton.setBounds(700 - saveIcon.getIconWidth()/2, 520, saveIcon.getIconWidth() + 20, saveIcon.getIconHeight());
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveButton.setBounds(700 - saveIcon.getIconWidth()/2, 520, saveIcon.getIconWidth(), saveIcon.getIconHeight());
            }
        });
        

       


        //add buttons to panel
        this.add(saveButton);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //draw componenents
        ImageIcon titleIcon = new ImageIcon("src/Main/Assets/saveTitle.png");
        Image image = titleIcon.getImage();
        g.drawImage(image, width/2 - titleIcon.getIconWidth()/2, 250, this);
    }

    public boolean getSaveClicked(){
        return saveClicked;
    }
    public boolean getBackClicked(){
        return backClicked;
    }
    public void resetSaveClicked(){
        saveClicked = false;
    }
    public void resetBackClicked(){
        backClicked = false;
    }
    public String getGridName(){
        return gridName;
    }
}
