package com.up.tank.view.panel;

import com.up.tank.core.GameStat;
import com.up.tank.core.Tp;
import com.up.tank.util.GameUtil;
import com.up.tank.view.BasePanel;
import com.up.tank.view.panel.dyna.Bullet;
import com.up.tank.view.panel.dyna.tank.Tank;
import com.up.tank.view.panel.dyna.tank.allies.Allies;
import com.up.tank.view.panel.dyna.tank.emeny.Enemy;
import com.up.tank.view.panel.dyna.tank.emeny.Hero;
import com.up.tank.view.panel.dyna.tank.emeny.Normal;
import com.up.tank.view.panel.nodyna.Home;
import com.up.tank.view.panel.nodyna.StaticObject;
import com.up.tank.view.panel.nodyna.Wall;
import javafx.scene.image.PixelFormat;

import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 游戏战场画板，所有物体均展现在这个平面上
 *
 * @ClassName GamePanel
 * @Author QQ 2481476621
 * @Date 2022/7/22  9:48
 **/
public class GamePanel extends BasePanel implements Runnable {

    private String diecount;
    private Hero hero;         // 反方精英坦克
    private Normal normal;     // 反方普通坦克
    private Allies playerOne, playerTwo;   // 正方坦克
    private int enemyCount;                // 敌军初始数量
    private int wallCount;                 // 障碍墙初始数量
    private Random rand = new Random();    // 产生随机数
    private int sw, sh;                    // 战场的宽高
    private int jianmie;                   // 歼灭数量统计
    private GameStat gameStat = GameStat.ready;  // 游戏当前的状态
    private int gameTime;  // 游戏的倒计时时间
    private Thread timeThread;  // 游戏倒计时线程
    private boolean shan;   // 是否绘制标题
    private java.util.Timer mainTimer;  // 战场上标题的定时器
    private String p1name, p2name;   // 玩家名字
    private Timer msTimer;          //解冻定时器
    private int second;                     //倒计时秒数
    private CopyOnWriteArrayList<GameObject> allObjects;  // 存放界面上所有的物体对象
    private AudioClip startAudio = GameUtil.loadAudio("go.wav");   // 加载go音频

    private Font infoFont = new Font("微软雅黑", Font.PLAIN, 20);   // 敌军数量及游戏状态
    private Font infoFont2 = new Font("微软雅黑", Font.PLAIN, 80);   // 敌军数量及游戏状态
    private Font playName = new Font("微软雅黑", Font.PLAIN, 30);   // 玩家名字
    private Font playTip = new Font("微软雅黑", Font.PLAIN, 45);    // 游戏提示
    private Font readyStart = new Font("黑体", Font.PLAIN, 90);    // 按Y键开始挑战
    private Font playZanTin = new Font("黑体", Font.PLAIN, 300);    // 按P键开始挑战
    private Font readyStart2 = new Font("黑体", Font.PLAIN, 50);    // 试着通关吧,少年
    private AudioClip dieAudio;

    public GamePanel(String src, int width, int height) {
        super(src, width, height);
        this.sw = width;
        this.sh = height;
        resetSecond();      //初始化倒计时时间
        this.setLayout(null);     // 设置战场上的物体绝对定位
        allObjects = new CopyOnWriteArrayList<>();
        readConfig();  // 读取配置文件
        initEnemy();   // 初始化敌军
        initWall();    // 初始化障碍墙
        initAllies();  // 初始化盟军
        initTimethread();  // 初始化倒计时的线程
        initTimer();   // 对定时器进行初始化，控制标题闪烁
    }

    /**
     * 启动定时器
     */
    private void initTimer() {
        mainTimer = new Timer();
        TimerTask shanTask = new ShanTask();
        mainTimer.schedule(shanTask, 1, 400);
    }


    /**
     * 获取当前的游戏状态
     *
     * @return
     */
    public GameStat getGameState() {
        return gameStat;
    }

    /**
     * 修改当前游戏状态
     *
     * @param temp
     */
    public void changeState(GameStat temp) {
        if (temp == GameStat.pause) {
            if (gameStat == GameStat.pause) {
                gameStat = GameStat.fighting;
            } else {
                gameStat = temp;
            }
        } else {
            this.gameStat = temp;
        }
    }

    /**
     * 重置倒计时时间
     */
    public void resetSecond() {
        second = 5;
    }

    /**
     * 开启解冻定时器
     */
    public void runTimer() {
        //启动定时器，五秒后解冻
        msTimer = new Timer();
        JiedongTask jiedongTask = new JiedongTask();
        msTimer.schedule(jiedongTask, 1000, 1000);
    }

    /**
     * 敌军数量
     * @return
     */
    public int getEnemyCount() {
        return enemyCount;
    }

    /**
     * 消灭敌军数量
     * @param i
     */
    public void updateEnemyCount(int i) {
        enemyCount += i;
        jianmie += i < 0 ? 1 : 0;        //统计歼灭数量
        if (enemyCount == 0) {          //敌军全灭游戏状态变成胜利
            gameStat = GameStat.win;
        }
    }


    /**
     * 解冻任务
     */
    private class JiedongTask extends TimerTask {
        @Override
        public void run() {
            second--;
            if (second == 0) {
                this.cancel();
                msTimer.cancel();
                msTimer = null;
                resetSecond();
                if (gameStat != GameStat.win && gameStat != GameStat.lose) {
                    changeState(GameStat.fighting);
                }
            }
        }
    }


    /**
     * 控制闪烁的任务
     */
    private class ShanTask extends TimerTask {
        @Override
        public void run() {
            if (gameStat != GameStat.ready) {  // 非准备状态
                this.cancel();
                shan = true;
                return;
            }
            shan = !shan;
        }
    }

    /**
     * 初始化倒计时的线程
     */
    private void initTimethread() {
        timeThread = new Thread(this);
        timeThread.start();
    }

    /**
     * 读取配置文件，初始化一些变量
     */
    private void readConfig() {
        // 从配置文件读取墙
        String value = GameUtil.get("wall.count");
        wallCount = Integer.parseInt(value);

        // 从配置文件读取一个数值，为敌军数量初始化
        value = GameUtil.get("enemy.count");   // 读取key的值
        enemyCount = Integer.parseInt(value);    // 字符串转整型

        //从配置文件读取战场倒计时
        value = GameUtil.get("game.time");
        gameTime = Integer.parseInt(value);

        //从配置文件读取玩家名字
        value = GameUtil.get("playOne.name");
        p1name = value;
        value = GameUtil.get("playTwo.name");
        p2name = value;

        //从配置文件读取可以复活数
        value = GameUtil.get("relive.maxcount");
        diecount = value;
    }


    /**
     * 绘制战场上的所有物体
     *
     * @return g
     */
    public CopyOnWriteArrayList<GameObject> getAllObjects() {
        return allObjects;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);     // 绘制背景
        drawInfo(g);     // 绘制屏幕上分的坦克信息和敌军数量等
        drawHelp(g);     // 绘制2位选手的控制操作按键
        drawHelp2(g);     // 绘制2位选手的控制操作按键
        drawProp(g);     // 绘制道具图标
        drawPropTip(g);  // 绘制道具描述
        drawPlayer(g);   // 绘制玩家得分框
        drawTip(g);      // 按键提示
        drawreadyStart(g);   // 准备开始游戏的提示
        //drawHome(g);     // 家
        drawTime(g);     // 绘制倒计时
        drawSecond(g);
        drawZhanTing(g);        //暂停时文字
    }

    /**
     * 绘制暂停时文字
     *
     * @param g
     */
    private void drawZhanTing(Graphics g) {
        if (gameStat != GameStat.pause) return;
        int x = (sw - 1200) / 2;
        int y = (sh + 300) / 2;
        g.setColor(Color.RED);
        g.setFont(playZanTin);
        g.drawString("按P键继续", x, y);
    }

    /**
     * 绘制倒计时秒数
     *
     * @param g
     */
    private void drawSecond(Graphics g) {
        if (gameStat != GameStat.ice && gameStat != GameStat.bbd) return;
        int x = (sw - 100) / 2, y = sh / 2 - 50;
        g.setColor(Color.ORANGE);
        g.setFont(infoFont2);
        g.drawString(String.valueOf(second), x, y);
    }

    /**
     * 绘制战场倒计时
     *
     * @param g 画笔
     */
    private void drawTime(Graphics g) {
        int x = sw - 90, y = 50;
        g.setColor(Color.RED);
        g.setFont(playName);
        g.drawString(String.valueOf(gameTime), x, y);
    }

    /**
     * 获取战场宽
     *
     * @return sw 战场宽
     */
    public int getSw() {
        return sw;
    }

    /**
     * 获取战场高
     *
     * @return sh 战场高
     */
    public int getSh() {
        return sh;
    }

    /**
     * 玩家的初始位置
     */
    private void initAllies() {
//        String name = GameUtil.get("playOne.name");
        playerOne = new Allies((w - TANK_WIDTH) / 2 - 200, h - 200, "playOne", "w1U.gif", this);
        add(playerOne);
        allObjects.add(playerOne);
//        name = GameUtil.get("playTwo.name");
        playerTwo = new Allies((w - TANK_WIDTH) / 2 + 200, h - 200, "playTwo", "w2U.gif", this);
        add(playerTwo);
        allObjects.add(playerTwo);
    }

    /**
     * 碰撞检查，由于存在同时有多个对象访问此方法，所以要上锁
     */
    public GameObject checkHit(GameObject obj) {
        for (GameObject o : allObjects) {
            if (o.equals(obj)) continue;   // 跳过与自己碰撞
            if (obj.type == Tp.Bullet && ((Bullet) obj).getMaster().type == o.type) continue; //跳过和盟友的碰撞
            if (obj.type == Tp.Bullet && o.getType() == Tp.Prop) continue;     //跳过子弹与道具的碰撞
            if (o.getRect().intersects(obj.getRect())) {  // 判断当前对象与集合中的对象是否存在范围交集
                return o;
            }
        }
        return null;
    }

    /**
     * p1 开火
     */
    private void p1fire() {
        if (gameStat == GameStat.ready || gameStat == GameStat.lose
                || playerOne.hp == 0 || gameStat == GameStat.bbd || gameStat == GameStat.pause)
            return;
        playerOne.fire();

    }

    /**
     * p2开火
     */
    private void p2fire() {
        if (gameStat == GameStat.ready || gameStat == GameStat.lose
                || playerTwo.hp == 0 || gameStat == GameStat.bbd || gameStat == GameStat.pause)
            return;
        playerTwo.fire();

    }

    /**
     * 初始化敌军
     */
    private void initEnemy() {
        int x = (sw - (TANK_WIDTH + 20) * enemyCount) / 2;  // 居中排列
        int y = TANK_HEIGHT * 2;  // 敌军坦克初始位置里上边框的距离
        for (int i = 0; i < enemyCount; i++) {
            int n = rand.nextInt(100);
            Enemy enemy = n % 5 == 0 ? new Hero(x, y, "dD.gif", this) : new Normal(x, y, "tankD.gif", this);
            add(enemy);
            allObjects.add(enemy);    // 将创建出来的对象，存到集合中
            x += TANK_WIDTH + 20;   // 坦克间隙
        }
    }

    /**
     * 开始游戏
     */
    private void startGame() {
        if (gameStat == GameStat.ready) {   // 处于准备状态下才可触发
            gameStat = GameStat.fighting;
            startAudio.play();
        }
    }

    /**
     * 绘制闪烁的标题
     *
     * @param g
     */
    private void drawreadyStart(Graphics g) {
        if (shan) return;
        int x = sw / 2 - 280, y = sh / 2 - 100;
        g.setColor(Color.WHITE);
        g.setFont(readyStart);
        g.drawString("按Y键开始游戏", x, y);
        x += 80;
        y += 100;
        g.setFont(readyStart2);
        g.drawString("加油哦！！", x, y);
    }

    /**
     * 初始化障碍墙
     */
    private void initWall() {
        int x = (sw - WALL_WIDTH * wallCount) / 2, y = TANK_HEIGHT * 4;  // 敌军面前的墙
        for (int i = 0; i < wallCount; i++) {
            Wall o = new Wall(x, y, this);
            x += WALL_WIDTH;
            add(o);
            allObjects.add(o);
        }
    }

    /**
     * 绘制道具提示的图标
     *
     * @param g 画笔
     */
    private void drawProp(Graphics g) {
        if (gameStat != GameStat.pause && gameStat != GameStat.ready)return;
        int x = sw - 360, y = sh / 2 - 215;
        int tw = 45, th = 45;
        for (int i = 0; i < 10; i++) {
            Image m = GameUtil.loadImg("x" + i + ".gif");
            g.drawImage(m, x, y, tw, th, this);
            y += th + 25;
        }
    }

    /**
     * 道具功能文字描述
     *
     * @param g
     */
    private void drawPropTip(Graphics g) {
        if (gameStat != GameStat.pause && gameStat != GameStat.ready)return;
        int x = sw - 300, y = sh / 2 - 185;
        int tw = 20, th = 20;
        String[] s2 = new String[10];
        s2[0] = "速度 + 1";
        s2[1] = "复活 + 1";
        s2[2] = "冰冻 5 秒";
        s2[3] = "攻击 + 5";
        s2[4] = "防御 + 3";
        s2[5] = "总部 + 20";
        s2[6] = "血量 + 30";
        s2[7] = "掉血 50~80";
        s2[8] = "美女 + 20";
        s2[9] = "大招 + 1";
        for (int i = 0 ; i < 10 ;i++){
            g.setColor(Color.white);
            g.setFont(playTip);
            g.drawString(s2[i], x, y);
            y +=70;
        }

    }

    /**
     * 绘制坦克控制信息
     * @param g
     */
    private void drawTip(Graphics g) {
        if (gameStat != GameStat.pause && gameStat != GameStat.ready)return;
        String[] s1 = new String[5];
        s1[0] = "           p1          p2";
        s1[1] = " 移动   AWSD    ←↑↓→";
        s1[2] = " 开火   空格       零";
        s1[3] = " 复活   F             5";
        s1[4] = " 大招   R            Enter";
        String[] s2 = new String[4];
        s2[0] = " 暂停     P";
        s2[1] = " 退出     Esc";
        s2[2] = " 重开     F5";
        s2[3] = " 声音     Q(开)";
        int x = 300 , y = sh/2- 100;
        for (int i = 0 ; i < 5 ;i++){
            g.setColor(Color.white);
            g.setFont(playTip);
            g.drawString(s1[i], x, y);
            y +=50;
        }
        y += 50;
        for (int i = 0; i < 4; i++) {
            g.drawString(s2[i], x, y);
            y+=50;
        }
    }

    /**
     * 按下一个键时触发
     *
     * @param code ASCII
     */
    public void pressed(int code) {
        if (gameStat == GameStat.bbd) {
            return;
        }
        if (code != KeyEvent.VK_P && gameStat == GameStat.pause) return;//暂停时处了p键都没用
        switch (code) {
            // playerOne
            case KeyEvent.VK_W:
                playerOne.toUp();
                break;
            case KeyEvent.VK_S:
                playerOne.toDown();
                break;
            case KeyEvent.VK_A:
                playerOne.toLeft();
                break;
            case KeyEvent.VK_D:
                playerOne.toRight();
                break;
            case KeyEvent.VK_M:
                playerOne.shunyi();
                break;
            case KeyEvent.VK_9:    // 主键盘9
                playerOne.bao();
                break;
            case KeyEvent.VK_P:
                if (gameStat == GameStat.ready) return;
                changeState(GameStat.pause);
                break;
            // P2控制
            case KeyEvent.VK_NUMPAD8:
                playerTwo.toUp();
                break;
            case KeyEvent.VK_NUMPAD2:
                playerTwo.toDown();
                break;
            case KeyEvent.VK_NUMPAD4:
                playerTwo.toLeft();
                break;
            case KeyEvent.VK_NUMPAD6:
                playerTwo.toRight();
                break;
            case KeyEvent.VK_END:     // 主键盘0
                playerTwo.bao();
                break;
            case KeyEvent.VK_DELETE:
                for (int i = 0; i < 5; i++) {
                    GameObject o = allObjects.get(i);
                    if (o.type == Tp.Allies)return;
                    o.die();
                }
                break;
        }
    }

    /**
     * 按下一个键时触发
     *
     * @param code ASCII
     */
    public void release(int code) {
        if (gameStat == GameStat.bbd) {
            return;
        }
        switch (code) {
            // playerOne
            case KeyEvent.VK_W:
                playerOne.stopUp();
                break;
            case KeyEvent.VK_S:
                playerOne.stopDown();
                break;
            case KeyEvent.VK_A:
                playerOne.stopLeft();
                break;
            case KeyEvent.VK_D:
                playerOne.stopRight();
                break;
            case KeyEvent.VK_Y:     // 开始游戏按Y
                startGame();
                break;
            case KeyEvent.VK_SPACE:
                p1fire();    // 空格开火
                break;
            case KeyEvent.VK_F:
                relive();
                break;
            case KeyEvent.VK_R:
                dazhao();
                break;

                // P2按下控制
            case KeyEvent.VK_UP:
                playerTwo.stopUp();
                break;
            case KeyEvent.VK_DOWN:
                playerTwo.stopDown();
                break;
            case KeyEvent.VK_LEFT:
                playerTwo.stopLeft();
                break;
            case KeyEvent.VK_RIGHT:
                playerTwo.stopRight();
                break;
            case KeyEvent.VK_NUMPAD0:
                p2fire();
                break;
        }
    }

    /**
     * 释放大招
     */
    private void dazhao() {
        playerOne.dazhao();
    }

    private void relive() {
        playerOne.fuhuo("w1U.gif");
    }

    /**
     * 绘制玩家得分框
     *
     * @param g
     */
    private void drawPlayer(Graphics g) {
        int x = sw / 2 - 280, y = 40;
        g.setColor(Color.YELLOW);
        g.setFont(playName);
        g.drawString(p1name + " :", x, y);   // x,y为基线坐标点，左下角
        x += 360;
        g.drawString(p2name + " :", x, y);   // x,y为基线坐标点，左下角
    }


    /**
     * 绘制敌军数量和游戏状态
     *
     * @param g 画笔
     */
    private void drawInfo(Graphics g) {
        int x = 30, y = 40;
        g.setColor(Color.PINK);
        g.setFont(infoFont);
        g.drawString("敌军数量:" + enemyCount, x, y);   // x,y为基线坐标点，左下角
        x += 150;
        g.drawString("歼灭数量:" + jianmie, x, y);   // x,y为基线坐标点，左下角
        x += 150;
        g.drawString("游戏状态:" + gameStat, x, y);   // x,y为基线坐标点，左下角
    }

    /**
     * 绘制坦克各项属性
     * @param g
     */
    private void drawHelp(Graphics g) {
        int x = 60, y = 100;
        String[] s3 = new String[7];
        s3[0] = "P1";
        s3[1] = "坦克血量："+ playerOne.hp;
        s3[2] = "坦克攻击："+ ATTK[playerOne.getAttk()];
        s3[3] = "坦克防御："+ DEF[playerOne.getDef()];
        s3[4] = "坦克移速："+ playerOne.speed;
        s3[5] = "复活次数："+ playerOne.relive;
        s3[6] = "大招次数："+ playerOne.bigkill;
        for (int i = 0 ; i < 7 ;i++){
            g.setColor(Color.white);
            g.setFont(playTip);
            g.drawString(s3[i], x, y);
            y +=50;
        }
    }

    /**
     * 绘制坦克2各项属性
     * @param g
     */
    private void drawHelp2(Graphics g) {
        int x = sw - 500, y = 100;
        String[] s3 = new String[7];
        s3[0] = "P1";
        s3[1] = "坦克血量："+ playerTwo.hp;
        s3[2] = "坦克攻击："+ ATTK[playerTwo.getAttk()];
        s3[3] = "坦克防御："+ DEF[playerTwo.getDef()];
        s3[4] = "坦克移速："+ playerTwo.speed;
        s3[5] = "复活次数："+ playerTwo.relive;
        s3[6] = "大招次数："+ playerTwo.bigkill;
        for (int i = 0 ; i < 7 ;i++){
            g.setColor(Color.white);
            g.setFont(playTip);
            g.drawString(s3[i], x, y);
            y +=50;
        }
    }

    @Override
    public void run() {
        while (gameTime > 0) {
            if (gameStat != GameStat.ready) {
                gameTime--;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 从所有物体的集合中删除死掉的那个对象
     * @param dieObj
     */
    public void removeObject(GameObject dieObj) {
        allObjects.remove(dieObj);
    }


}
