package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    private boolean toggleBrush = true;
    private static int brushSize = 1;
    private boolean spaceHeld = false;
    private int brightnessBrush = 0;
    
    @Override
    public void keyPressed(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            if (KeyHandler.brushSize > 1) {
                KeyHandler.brushSize--;
                System.out.println(KeyHandler.brushSize);
            }
        } else if (key == KeyEvent.VK_RIGHT) {
            if (KeyHandler.brushSize < 6) {
                KeyHandler.brushSize++;
            }
        }
    }
    

    public boolean getToggleBrush() {
        return toggleBrush;
    }

    public static int getBrushSize() {
        return KeyHandler.brushSize;
    }

    public void resetSpaceHeld() {
        spaceHeld = false;
    }
    public boolean getSpaceHeld() {
        return spaceHeld;
    }
    public void resetBrightnessBrush() {
        brightnessBrush = 0;
    }
    public int getBrightnessBrush() {
        return brightnessBrush;
    }

    public static void setBrushSize(int brushSize) {
        if (brushSize >= 1 && brushSize <= 6) {
            KeyHandler.brushSize = brushSize;
        }
    }
}
