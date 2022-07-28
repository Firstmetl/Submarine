package Submarine;

import javax.swing.*;


public  class Bomb extends SeaObject{
    public Bomb(int x,int y) {
        super(9,12,x,y,3);
    }

    public void move(){
        y+=speed;
    }

    @Override
    public ImageIcon getImage() {
        return Images.bomb;
    }

    public boolean isOutOfBounds(){
        return this.y>=World.HEIGHT;
    }

}
