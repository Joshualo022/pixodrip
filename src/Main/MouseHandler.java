package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;



public class MouseHandler implements MouseMotionListener, MouseListener{
    
    public int mousex;
    public int mousey;
    public boolean isPressed = false;
    @Override
    public void mouseDragged(MouseEvent e) {
        isPressed = true;
        mousex = e.getX();
        mousey = e.getY();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        isPressed = false;
        mousex = e.getX();
        mousey = e.getY();
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        isPressed = true;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        isPressed = false;
    }



}
