package Submarine;

import javax.swing.*;
import java.util.Random;

public  class ObserveSubmarine extends SeaObject implements EnemyScore{



    public ObserveSubmarine(){
        super(63,19);
    }
    @Override
    public void move() {
        x+=speed;
    }
    @Override
    public ImageIcon getImage() {
        return Images.obsersubm;
    }

    public int getScore(){
        return 10;
    }
}
