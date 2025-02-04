package Main;

import java.io.Serializable;

public class Tile implements Serializable {

    private int x;
    private int y;
    private float[] hsb = new float[3];
    private float baseH = 0.0f;

    public Tile(int x, int y, float[] hsb, float baseH) {
        this.x = x;
        this.y = y;
        this.hsb = hsb;
        this.baseH = baseH;
    }

    public float[] getHsb() {
        return hsb;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //methods to change the hsb values
    public void changeH(float h){
        hsb[0] += h;
        if (hsb[0] > 1.0f + baseH){
            hsb[0] = 0.0f + baseH;
        }
    }
    public void changeS(float s){
        hsb[1] += s;
        if (hsb[1] > 1.0f){
            hsb[1] = 1.0f;
        }
        if (hsb[1] < 0.0f){
            hsb[1] = 0.0f;
        }
    }
    public void changeB(float b){
        hsb[2] += b;
        if (hsb[2] > 1.0f){
            hsb[2] = 1.0f;
        }
        if (hsb[2] < 0.0f){
            hsb[2] = 0.0f;
        }
    }
    

}
