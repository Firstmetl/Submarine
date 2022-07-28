package Submarine;

import javax.swing.*;
import java.awt.image.BufferedImage;

/*   图片类  */
public class Images {

    public static ImageIcon sea;  //海洋图片

    public static ImageIcon bomb; //  炸弹图片

    public static ImageIcon battleship;  //战舰图片

    public static ImageIcon gameover;  //游戏结束图片

    public static ImageIcon mine;  //水雷图片

    public static ImageIcon minesubm;  //  水雷潜艇图片

    public static ImageIcon obsersubm; //  侦察潜艇图片

    public static ImageIcon torpesubm; //  鱼雷潜艇图片

    public static ImageIcon start;

    static {
        battleship = new ImageIcon("src/Submarine/image/battleship.png");
        bomb = new ImageIcon("src/Submarine/image/bomb.png");
        mine = new ImageIcon("src/Submarine/image/mine.png");
        obsersubm = new ImageIcon("src/Submarine/image/obsersubm.png");
        torpesubm = new ImageIcon("src/Submarine/image/torpesubm.png");
        minesubm = new ImageIcon("src/Submarine/image/minesubm.png");
        gameover = new ImageIcon("src/Submarine/image/gameover.png");
        sea = new ImageIcon("src/Submarine/image/sea.png");
        start =new ImageIcon("src/Submarine/image/img.png");
    }

    public static void main(String[] args) {
       System.out.println(battleship.getImageLoadStatus());
        System.out.println(bomb.getImageLoadStatus());
        System.out.println(minesubm.getImageLoadStatus());
        System.out.println(mine.getImageLoadStatus());
        System.out.println(obsersubm.getImageLoadStatus());
        System.out.println(torpesubm.getImageLoadStatus());
        System.out.println(sea.getImageLoadStatus());
        System.out.println(gameover.getImageLoadStatus());
        System.out.println(start.getImageLoadStatus());
    }



    /*static{
        battleship = new ImageIcon("img/battleship.png");
        obsersubm = new ImageIcon("img/obsersubm.png");
        torpesubm = new ImageIcon("img/torpesubm.png");
        minesubm = new ImageIcon("img/minesubm.png");
        mine = new ImageIcon("img/mine.png");
        bomb = new ImageIcon("img/bomb.png");
        sea = new ImageIcon("img/sea.png");
        gameover = new ImageIcon("img/gameover.png");
    }

    public static void main(String[] args){
        System.out.println(battleship.getImageLoadStatus());
        System.out.println(obsersubm.getImageLoadStatus());
        System.out.println(torpesubm.getImageLoadStatus());
        System.out.println(minesubm.getImageLoadStatus());
        System.out.println(mine.getImageLoadStatus());
        System.out.println(bomb.getImageLoadStatus());
        System.out.println(sea.getImageLoadStatus());
        System.out.println(gameover.getImageLoadStatus());
    }*/






    /*图片也是数据
    * 基本类型装不下
    * 数组也装不下
    * image类
    * */
}
