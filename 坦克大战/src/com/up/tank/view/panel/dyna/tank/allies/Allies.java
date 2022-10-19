package com.up.tank.view.panel.dyna.tank.allies;

import com.up.tank.core.Dir;
import com.up.tank.core.GameStat;
import com.up.tank.core.Tp;
import com.up.tank.util.GameUtil;
import com.up.tank.view.panel.GameObject;
import com.up.tank.view.panel.GamePanel;
import com.up.tank.view.panel.dyna.Big;
import com.up.tank.view.panel.dyna.tank.Tank;
import com.up.tank.view.panel.dyna.tank.emeny.Enemy;
import com.up.tank.view.panel.dyna.tank.emeny.Hero;
import com.up.tank.view.panel.dyna.tank.emeny.Normal;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 正方坦克类
 *
 * @ClassName Allies
 * @Author QQ 2481476621
 * @Date 2022/7/22  14:05
 **/
public class Allies extends Tank {

    public int relive;       // 复活机会
    public int bigkill;      // 大招数量
    private int score;        // 成绩积分
    public String name;      // 玩家姓名
    private int shanxian;

    public Allies(int x, int y,String name, String src,GamePanel zhanchang) {
        super(0, MOVE_SPEED, Dir.U, x, y, Tp.Allies, src, TANK_WIDTH+10, TANK_HEIGHT+10,zhanchang);
        relive = 3;
        bigkill = 3;
        this.name = name;
        this.dieAudio = GameUtil.loadAudio("cao.wav");
    }

    @Override
    protected void eat8() {
            createAllies(5);
    }

    private void createAllies(int n) {
        for (int i = 0; i < n; i++) {
            int x = buildPoint(zhanchang.getSw());
            int y = buildPoint(zhanchang.getSh());
            Enemy temp = new Normal(x,y,"tankD.gif",zhanchang);
            do {
                GameObject rssult = zhanchang.checkHit((temp));  //把新创建的坦克送检查坐标是否碰撞
                if (rssult == null) {
                    break;
                }
            } while (true);
            zhanchang.add(temp);        //显示
            zhanchang.getAllObjects().add(temp);    //参与碰撞
        }
    }

    /**
     * 开启闪现
     */
    @Override
    protected void eat5() {
        shanxian++;
    }

    /**
     * 道具一，复活机会增加
     */
    @Override
    protected void eat1() {
        relive++;
        relive = relive > 6 ? 6 : relive;
    }

    /**
     * 吃到二号道具将游戏状态修改为冰冻
     */
    @Override
    protected void eat2() {
        if (zhanchang.getGameState() == GameStat.fighting) {
            beginIcec();
        } else if (zhanchang.getGameState() == GameStat.ice) {
            zhanchang.resetSecond();        //修改秒数为5
        }
    }

    /**
     * 开始冰冻
     */
    private void beginIcec() {
        zhanchang.changeState(GameStat.ice);        //将游戏状态修改为冰冻
        zhanchang.runTimer();       //启动倒计时定时器解冻
    }

    /**
     * 复活方法
     */
    public void fuhuo(String src) {
        if (relive == 0 || hp > 0) return;
        hp = FULL_HP;
        attk = 0;
        def = 0;
        speed = 3;
        w = TANK_WIDTH;
        h = TANK_HEIGHT;
        bigkill = 3;
        l = false;
        r = false;
        u = false;
        d = false;
        GameObject obj = zhanchang.checkHit(this);
        while (obj != null) {
            x = buildPoint(zhanchang.getSw());
            y = buildPoint(zhanchang.getSh());
            obj = zhanchang.checkHit(this);
        }
        this.src = src;
        this.img = GameUtil.loadImg(src);
        setBounds(x,y,w,h);
        zhanchang.add(this);        //重新显示到桌面
        zhanchang.getAllObjects().add(this);    //加入到碰撞集合中
        this.begiMove();
        relive--;
    }

    /**
     * 扣血产生大范围爆炸
     */
    public void dazhao() {
        if (hp < 5 || bigkill == 0 ) return;
        hp -= 5;
        Big big = new Big(this);
        zhanchang.add(big);
        bigkill--;
    }

    /**
     * 坦克瞬移
     */
    public void shunyi() {

        if (dir == Dir.D && shanxian > 0) {
            y += 80;
        } else if (dir == Dir.U &&shanxian >0) {
            y -= 80;
        } else if (dir == Dir.R &&shanxian >0) {
            x += 80;
        }else if (dir == Dir.L && shanxian >0)
            x -= 80;
    }
}
