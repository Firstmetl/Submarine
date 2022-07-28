package Submarine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Timer;


public class World  extends JPanel{



    public static final int WIDTH = 641;
    public static final int HEIGHT = 479;

    public static final int  GAMEOVER = 0;
    public static final int RUNNING = 1;
    public static final int PAUSE_GAME = -1;

    public int state  =RUNNING;

    private Battleship ship = new Battleship(); //战舰
    private SeaObject[] submarines = {}; //潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)
    private Mine[] mines = {}; //水雷
    private Bomb[] bombs = {}; //深水炸弹

    /** 随机生成潜艇 */
    public SeaObject nextSubmarine(){
        Random rand = new Random();
        int type = rand.nextInt(20);
        if(type<10){
            return new ObserveSubmarine();
        }else if(type<15){
            return new Torpeosubmarine();
        }else{
            return new MineSubmaribe();
        }
    }

    private int subEnterIndex = 0;
    /** 潜艇入场 */
    public void submarineEnterAction(){ //每10毫秒走一次
        subEnterIndex++;
        if(subEnterIndex%40==0){ //每400毫秒
            SeaObject obj = nextSubmarine();
            submarines = Arrays.copyOf(submarines,submarines.length+1);
            submarines[submarines.length-1] = obj;
        }
    }

    private int mineEnterIndex = 0;
    /** 雷(鱼雷、水雷)入场 */
    public void MineEnterAction(){
        mineEnterIndex++;
        if(mineEnterIndex%100==0){
            //如下代码暂时搁置-----人工智能自动输入
            for (int i = 0; i < submarines.length; i++) {
                if (submarines[i] instanceof MineSubmaribe){
                    MineSubmaribe ms = (MineSubmaribe) submarines[i];
                    Mine mine = ms.shootMine();
                    mines = Arrays.copyOf(mines,mines.length+1);
                    mines[mines.length-1] = mine;
                }
            }
        }
    }
    /** 海洋对象移动 */
    private void moveAction(){
        for(int i=0;i<submarines.length;i++){
            submarines[i].move();
        }
        for(int i=0;i<mines.length;i++){
            mines[i].move();
        }
        for(int i=0;i<bombs.length;i++){
            bombs[i].move();
        }
    }



    private void outOfBoundsAction(){//每10毫秒走一次
        //潜艇越界
        for (int i = 0; i <submarines.length ; i++) {
            if (submarines[i].isOutOfBounds() || submarines[i].isDead()){
                submarines[i] = submarines[submarines.length-1];
                submarines = Arrays.copyOf(submarines,submarines.length-1);
            }
        }
        //水雷越界
        for (int i = 0; i <mines.length ; i++) {
            if (mines[i].isOutOfBounds() || mines[i].isDead()){
                mines[i] = mines[mines.length-1];
                mines = Arrays.copyOf(mines,mines.length-1);
            }
        }
        //炸弹越界
        for (int i = 0; i <bombs.length ; i++) {
            if (bombs[i].isOutOfBounds() || bombs[i].isDead()){
                bombs[i] = bombs[bombs.length-1];
                bombs = Arrays.copyOf(bombs,bombs.length-1);
            }
        }
    }

    private int score = 0;
    private void bangBangAction() {
        for (int i = 0; i <bombs.length ; i++) {
            Bomb b = bombs[i];
            for (int j = 0; j < submarines.length; j++) {
                SeaObject s = submarines[j];
                if (b.isLive() && s.isLive() && s.isHit(b)){
                    b.goDead();
                    s.goDead();
                    if (s instanceof EnemyScore){
                        score += ((EnemyScore) s).getScore();
                    }
                    if (s instanceof EnemyLife){
                        int num = ((EnemyLife) s).getLife();
                        ship.addLife(num);
                    }
/*                    if (s instanceof ObserveSubmarine){
                        score += ((ObserveSubmarine) s).getScore();
                    }
                    if (s instanceof Torpeosubmarine){
                        score += ((Torpeosubmarine)s).getScore();
                    }
                    if (s instanceof MineSubmaribe){
                        int num = ((MineSubmaribe) s).getLife();
                        ship.addLife(num);
                    }*/
                }
            }
        }
    }

    private void minebangAction(){
        for (int i = 0; i < mines.length; i++) {
            Mine m = mines[i];
            if (m.isLive()&&ship.isLive()&&m.isHit(ship)){
                m.goDead();
                ship.subtractlife();
            }
        }
    }
    private void gameOver(){
        if (ship.getLife()<=0){
            state = GAMEOVER;

        }
    }

    public void puase(){
        state = PAUSE_GAME;
    }
    public void again(){
        state = RUNNING;
    }
    public void gameover(){
        state = GAMEOVER;
    }

    /** 启动程序的运行 */
    public void action(){
        System.out.println("```````");
        //事件监听
        KeyAdapter k = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode()==KeyEvent.VK_SPACE){
                    System.out.println("World.keyPressed");
                    Bomb obj = ship.shootBomb();
                    bombs = Arrays.copyOf(bombs,bombs.length+1);
                    bombs[bombs.length-1] = obj;
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    if (ship.x>=0){
                        ship.moveLeft();
                    }
                }
                if (e.getKeyCode()==KeyEvent.VK_RIGHT){
                    if (ship.x<=World.WIDTH-ship.width-20){
                        ship.moveRight();
                    }
                }
                if (e.getKeyCode()==KeyEvent.VK_P){

                    if (state == RUNNING){
                        state = PAUSE_GAME;
                    }else if (state==PAUSE_GAME){
                        state=RUNNING;
                    }
                }
            }
        };
        this.addKeyListener(k);

        Timer timer = new Timer();
        int interval = 10;
        timer.schedule(new TimerTask() {
            public void run() {
                if (state==RUNNING){
                    submarineEnterAction(); //潜艇(侦察、水雷、鱼雷)入场
                    MineEnterAction();      //水雷入场
                    moveAction();           //海洋对象移动
                    outOfBoundsAction();
                    minebangAction();
                    bangBangAction();
                    gameOver();
                    repaint();
                }
            }
        }, interval, interval);
        System.out.println("---");
    }

    public void paint(Graphics g){
        Images.sea.paintIcon(null,g,0,0); //画海洋图
        ship.paintImage(g);
        for(int i=0;i<submarines.length;i++){
            submarines[i].paintImage(g);
        }
        for(int i=0;i<mines.length;i++){
            mines[i].paintImage(g);
        }
        for(int i=0;i<bombs.length;i++){
            bombs[i].paintImage(g);
        }
        g.drawString("得分："+score,200,50);
        g.drawString("生命："+ship.getLife(),400,50);
        if (state==GAMEOVER){
            Images.gameover.paintIcon(null,g,0,0);
        }
    }

    public static void main(String[] args) {

        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("src/Submarine/image/subm_icon.png");
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame jFrame = new JFrame();
        World world = new World();
        world.setFocusable(true);
        jFrame.setIconImage(img);
        jFrame.add(world);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar jmb = new JMenuBar();
        JMenu j1 = new JMenu("开始");
        JMenu j2 = new JMenu("主题");
        JMenu j3 = new JMenu("帮助");
        JMenu j4 = new JMenu("说明");
        JMenuItem jm1 = new JMenuItem("开始新游戏");
        JMenuItem jm2 = new JMenuItem("暂停游戏");
        JMenuItem jm3 = new JMenuItem("继续游戏");
        JMenuItem jm4 = new JMenuItem("退出");
        j1.add(jm1);
        j1.add(jm2);
        j1.add(jm3);
        j1.add(jm4);
        JMenuItem j2m1 = new JMenuItem("系统平台风格");
        JMenuItem j2m2 = new JMenuItem("metal风格");
        JMenuItem j2m3 = new JMenuItem("motif风格");
        j2.add(j2m1);
        j2.add(j2m2);
        j2.add(j2m3);
        JMenuItem j3m = new JMenuItem("这是一个帮助！");
        jmb.add(j1);
        jmb.add(j2);
        jmb.add(j3);
        jmb.add(j4);
        jFrame.setJMenuBar(jmb);
        jFrame.setSize(WIDTH,HEIGHT+52);
        jFrame.setTitle("潜艇大作战");
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jm1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.action();
            }
        });
        jm2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.puase();
            }
        });
        jm3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.again();
            }
        });
        jm4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //world.action();
        

    }
}
