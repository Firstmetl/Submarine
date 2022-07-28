package Submarine;

import javax.swing.*;

public  class Battleship extends SeaObject{

    private int life;


     Battleship() {
        super(66,26,270,124,20);
        life =5;
    }
    public void move(){
    }
    @Override
    public ImageIcon getImage() {
        return Images.battleship;
    }
    /*  发射炸弹*/
    public Bomb shootBomb(){
        return new Bomb(this.x,this.y);
    }
    public void moveRight(){
        x+=speed;
    }
    public void moveLeft(){
        x -= speed;
    }

    public void addLife(int num){
        life+=num;
    }

    public int getLife(){
        return life;
    }

    public int subtractlife(){return life--;}



}
