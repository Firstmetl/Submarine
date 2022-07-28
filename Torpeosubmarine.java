package Submarine;

import javax.swing.*;


public  class Torpeosubmarine extends SeaObject implements EnemyScore{


    public Torpeosubmarine() {
        super(64, 20);
    }
    @Override
    public void move() {
        x+=speed;
    }

    @Override
    public ImageIcon getImage() {
        return Images.torpesubm;
    }

    public int getScore(){
        return 40;
    }
}
