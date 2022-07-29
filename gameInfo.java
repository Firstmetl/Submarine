package Submarine;

import java.io.Serializable;

public class gameInfo implements Serializable {
    private Battleship ship = new Battleship(); //战舰
    private SeaObject[] submarines = {}; //潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)
    private Mine[] mines = {}; //水雷
    private Bomb[] bombs = {}; //深水炸弹
    private int subEnterIndex;
    private int mineEnterIndex;
    private int score;

    public gameInfo(Battleship ship, SeaObject[] submarines, Mine[] mines, Bomb[] bombs, int subEnterIndex, int mineEnterIndex, int score) {
        this.ship = ship;
        this.submarines = submarines;
        this.mines = mines;
        this.bombs = bombs;
        this.subEnterIndex = subEnterIndex;
        this.mineEnterIndex = mineEnterIndex;
        this.score = score;
    }

    public Battleship getShip() {
        return ship;
    }

    public void setShip(Battleship ship) {
        this.ship = ship;
    }

    public SeaObject[] getSubmarines() {
        return submarines;
    }

    public void setSubmarines(SeaObject[] submarines) {
        this.submarines = submarines;
    }

    public Mine[] getMines() {
        return mines;
    }

    public void setMines(Mine[] mines) {
        this.mines = mines;
    }

    public Bomb[] getBombs() {
        return bombs;
    }

    public void setBombs(Bomb[] bombs) {
        this.bombs = bombs;
    }

    public int getSubEnterIndex() {
        return subEnterIndex;
    }

    public void setSubEnterIndex(int subEnterIndex) {
        this.subEnterIndex = subEnterIndex;
    }

    public int getMineEnterIndex() {
        return mineEnterIndex;
    }

    public void setMineEnterIndex(int mineEnterIndex) {
        this.mineEnterIndex = mineEnterIndex;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
