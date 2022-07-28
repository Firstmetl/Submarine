package Submarine;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class SeaObject {

    public static final int LIVE = 0;
    public static final int DEAD = 1;

    protected int state = LIVE;

    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected int speed;

    public SeaObject(){

    }
    public SeaObject(int width,int height){
        this.width = width;
        this.height = height;
        x=width;
        Random rand = new Random();
        y = rand.nextInt(World.HEIGHT-height-150+1)+150;
        speed = rand.nextInt(3)+1;

    }

    public SeaObject(int width,int height,int x,int y , int speed){
        this.width = width;
        this.height = height;
        this.x =x;
        this.y = y;
        this.speed = speed;

    }

    public abstract void move();

    public abstract ImageIcon getImage();

    public boolean isLive(){
        return state == LIVE;
    }
    public boolean isDead(){
        return state == DEAD;
    }
    public void paintImage(Graphics g){
        if (isLive()){

            this.getImage().paintIcon(null,g,x,y);

        }
    }

    //越界判断
    public boolean isOutOfBounds(){
        return this.x>=World.WIDTH;
    }


    //碰撞判断
    public boolean isHit(SeaObject other){
        //this 和 other 分别表示两个碰撞物体
/*        int x1 = this.x-other.width;
        int x2 = this.x+other.width;
        int y1 = this.y-other.height;
        int y2 = this.y+other.height;
        int x = other.x;
        int y = other.y;
        return x>=x1 &&x<=x2 &&y>=y1 && y<=y2;*/

        int x1 = other.x-this.width;
        int x2 = other.x+other.width;
        int y1 = other.y-this.height;
        int y2 = other.y+ other.height;
        int x = this.x;
        int y = this.y;
        return x>=x1 &&x<=x2 &&y>=y1&&y<=y2;
    }

    public void goDead(){
        state = DEAD;
    }

}
