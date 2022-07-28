package Submarine;

import javax.swing.*;
import java.util.Random;

public  class Mine extends SeaObject {

    public Mine(int x,int y) {

        super(11,11,x,y,1);

    }
    public void move(){

        y-=speed;
    }

    @Override
    public ImageIcon getImage() {
        return Images.mine;
    }

    public boolean isOutOfBounds(){
        return this.y<=150-this.height;
    }

}
