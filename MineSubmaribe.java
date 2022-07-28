package Submarine;

import javax.swing.*;
import java.util.Random;

public  class MineSubmaribe extends SeaObject implements EnemyLife{

    public MineSubmaribe() {
        super(63,19);
    }
    public void move() {
        x+=speed;
    }
    @Override
    public ImageIcon getImage() {
        return Images.minesubm;
    }

    /** 生成水雷对象 */
    public Mine shootMine(){
        int x = this.x+this.width; //x:潜艇的x+潜艇的宽
        int y = this.y-5;          //y:潜艇的y-5
        return new Mine(x,y);
    }

    public int getLife(){
        return 1;
    }
}