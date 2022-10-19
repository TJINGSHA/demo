package com.up.tank.view.panel.dyna;

import com.up.tank.core.Dir;
import com.up.tank.core.GameStat;
import com.up.tank.core.Tp;
import com.up.tank.util.GameUtil;
import com.up.tank.view.panel.GameObject;
import com.up.tank.view.panel.GamePanel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 活动的物体对象
 *
 * @ClassName DynaObject
 * @Author QQ 2481476621
 * @Date 2022/7/22  10:05
 **/
public class DynaObject extends GameObject {

    protected int attk;           // 攻击等级
    public int speed;          // 移动速度
    protected Dir dir;            // 朝向
    protected boolean u, d, l, r;    // 档位,默认为false
    protected Timer timer;        // 控制行为的定时器

    public DynaObject(int attk, int speed, Dir dir, int x, int y, Tp type, String src, int width, int height, GamePanel zhanchang) {
        super(x, y, type, src, width, height, zhanchang);
        this.attk = attk;
        this.speed = speed;
        this.dir = dir;

        begiMove();
    }

    /**
     * 开始移动
     */
    public void begiMove() {
        timer = new Timer();
        timer.schedule(new MoveTask(), 10, MOVE_TIME);  // 每隔30毫秒执行一次任务
    }

    /**
     * 获取当前对象的攻击等级
     * @return
     */
    public int getAttk() {
        return this.attk;
    }

    /**
     * 获取当前对象的防御等级
     * @return
     */
    public int getDef() {
        return this.def;
    }

    /**
     * 移动的任务
     */
    private class MoveTask extends TimerTask {
        @Override
        public void run() {
            readyDong();
        }
    }

    /**
     * 准备移动
     */
    protected void readyDong() {
        dong();
    }
    /**
     * 物体移动
     */
    public void dong() {
        if(zhanchang.getGameState() == GameStat.pause)return; //暂停状态什么都不做
        if(type == Tp.Allies && zhanchang.getGameState() == GameStat.bbd)return;
        int oldx = x, oldy = y;   // 记录一下移动之前的位置
        y += u ? -speed : 0;
        y += d ? speed : 0;
        x += l ? -speed : 0;
        x += r ? speed : 0;
        setBounds(x, y, w, h);
        // 检查是否出界，逃逸出屏幕
        if (out()) {
            resetXy(oldx, oldy);
            return;
        }
        GameObject hitObj = hit();    // 碰撞检查，返回被撞的物体
        if (hitObj != null) {
            dohit(hitObj, oldx, oldy);
        }
        setBounds(x, y, w, h);
    }


    protected void dohit(GameObject hitObj, int oldx, int oldy) {
        resetXy(oldx, oldy);
    }

    /**
     * 物体碰撞后，默认让当前物体回到移动前的坐标，但子弹类可以重写此方法，实现减血等操作
     *
     * @param
     * @param oldx   碰撞前的坐标
     * @param oldy   碰撞前的坐标
     */
    public void resetXy(int oldx, int oldy) {
        this.x = oldx;
        this.y = oldy;
    }

    /**
     * 碰撞检查
     *
     * @return GameObject 被碰撞的物体
     */
    private GameObject hit() {
        return zhanchang.checkHit(this);
    }

    /**
     * 检查当前对象是否出界
     *
     * @return
     */
    private boolean out() {
        return x < 0 || y < 0 || x > zhanchang.getSw() - w || y > zhanchang.getSh() - h;
    }

    /**
     * 向上下左右移动,
     * 不将true的位置放第一行，那么按改按键会一直向其他放在第一行的方向移动
     */
    public void toUp() {
        u = true; d = !u;l = !u;r = !u;
        dir = Dir.U;
        changeImg();
    }

    public void toDown() {
        d = true; u = !d;l = !d;r = !d;
        dir = Dir.D;
        changeImg();
    }

    public void toLeft() {
        l = true; u = !l;d = !l;r = !l;
        dir = Dir.L;
        changeImg();
    }

    public void toRight() {
        r = true; u = !r;d = !r;l = !r;
        dir = Dir.R;
        changeImg();
    }

    /**
     * 将根据物体朝向修改图片
     */
    public void changeImg() {
        if (hp <= 0) return;
        // w2U.gif  w2D.gif  w2L.gif w2R.gif
        int i = src.lastIndexOf(".");   // 找小数点最后出现的位置，假如原文件名是w2L.gif,按向上键后
        String filename = src.substring(0, i - 1);  // 小数点前面1位
        String extname = src.substring(i);  // 计算出扩展名   .gif
        src = filename + dir + extname;     // w2U.gif
        img = GameUtil.loadImg(src);  // 重新加载一次新图
    }

    /**
     * 物体死亡后停止该物体的运行
     */
    @Override
    public void die() {
        super.die();
        if(timer != null){
        timer.cancel();
        timer = null;}
    }

    /**
     * 停止向某个方向移动
     */
    public void stopUp() {
        u = false;
    }

    public void stopDown() {
        d = false;
    }

    public void stopLeft() {
        l = false;
    }

    public void stopRight() {
        r = false;
    }
}
