package com.up.tank.view.panel.dyna.tank.emeny;

import com.up.tank.core.Dir;
import com.up.tank.core.GameStat;
import com.up.tank.core.Tp;
import com.up.tank.util.GameUtil;
import com.up.tank.view.panel.GameObject;
import com.up.tank.view.panel.GamePanel;
import com.up.tank.view.panel.dyna.tank.Tank;

import java.awt.*;
import java.util.Random;

/**
 * 敌军坦克类型
 * @ClassName Enemy
 * @Author QQ 2481476621
 * @Date 2022/7/22  10:15
 **/
public class Enemy extends Tank {

    protected boolean ishero;      // 是不是精英

    public Enemy(boolean ishero, int attk, int speed, Dir dir, int x, int y, Tp type, String src, int width, int height,GamePanel zhanchang) {
        super(attk, speed, dir, x, y, type, src, width, height,zhanchang);
        this.ishero = ishero;
        this.dieAudio = GameUtil.loadAudio("cao.wav");
    }


    @Override
    protected void readyDong() {
        if (zhanchang.getGameState() == GameStat.ice
        || zhanchang.getGameState() == GameStat.ready
        || zhanchang.getGameState() == GameStat.pause) return;
        domove();
        dofile();
        dong();

    }

    /**
     * 敌军开火算法
     */
    private void dofile() {
        Random random = new Random();
        int n = random.nextInt(100);
        if (n % 17 == 0) {
            fire();
        }
    }

    /**
     * 敌军移动算法
     */
    private void domove() {
        Random random = new Random();
        int n = random.nextInt(100);
        int m = random.nextInt(4);
        if (n % 11 == 0) {
            if (m == 0) {
                toLeft();
            } else if (m == 2) {
                toUp();
            } else if (m == 1) {
                toRight();
            }else if (m == 3){
                toDown();
            }
        }
    }

    /**
     * 反方吃一号道具，战场上多出五个敌军
     */
    @Override
    protected void eat1() {
        int maxCount = 30;
        if (zhanchang.getEnemyCount() >= maxCount)return;
        createEnemy(5);
    }

    private void createEnemy(int n) {

        for (int i = 0; i < n; i++) {
            int x = buildPoint(zhanchang.getSw());
            int y = buildPoint(zhanchang.getSh());
            boolean ishero = x % 3 == 0;
            Enemy temp = ishero ? new Hero(x, y, "dD.gif",zhanchang) : new Normal(x,y,"tankD.gif",zhanchang);
            do {
                GameObject rssult = zhanchang.checkHit((temp));  //把新创建的坦克送检查坐标是否碰撞
                if (rssult == null) {
                    break;
                }
            } while (true);
            zhanchang.add(temp);        //显示
            zhanchang.getAllObjects().add(temp);    //参与碰撞
            zhanchang.updateEnemyCount(1);      //战场上敌军数量要更新+1
        }
    }



    /**
     * 反方吃2号猪头道具
     */
    @Override
    protected void eat2() {
        if (zhanchang.getGameState() == GameStat.fighting) {
            beginBbd();
        } else if (zhanchang.getGameState() == GameStat.ice) {
            zhanchang.resetSecond();        //修改秒数为5
        }
    }

    /**
     * 开始冰冻
     */
    private void beginBbd() {
        zhanchang.changeState(GameStat.bbd);        //将游戏状态修改为被冰冻
        zhanchang.runTimer();       //启动倒计时定时器解冻
    }
}
