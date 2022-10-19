package com.up.tank.view.panel;

import com.up.tank.core.IConstant;
import com.up.tank.core.Tp;
import com.up.tank.util.GameUtil;
import com.up.tank.view.BasePanel;
import com.up.tank.view.panel.dyna.tank.Tank;
import com.up.tank.view.panel.nodyna.Prop;

import java.applet.AudioClip;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 游戏的角色物种
 *
 * @ClassName GameObject
 * @Author QQ 2481476621
 * @Date 2022/7/22  9:57
 **/
public class GameObject extends BasePanel implements IConstant {

    protected int hp;     // 生命值
    protected int def;    // 防御等级
    protected Tp type;    // 物种类别
    protected int x, y;    // 坐标
    protected AudioClip dieAudio;  // 物体死亡

    private Timer boomTimer;   // 爆炸定时器
    private int imgIndex;      // 爆炸图的编号
    private GameObject me = this;

    public GameObject(int x, int y, Tp type, String src, int width, int height, GamePanel zhanchang) {
        super(src, width, height, zhanchang);
        hp = FULL_HP;   // 初始化生命值满血
        def = 0;        // 防御为0
        this.x = x;
        this.y = y;
        this.type = type;
        setBounds(x, y, w, h);    // 给每个物体定个位置和大小
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBlood(g);
    }

    /**
     * 绘制血条的方法
     *
     * @param g 画笔
     */
    public void paintBlood(Graphics g) {  // 公共的，方便子类重写
        // 通过物体宽度x当前血量百分比得出绘制血条的长度
        int bw = (int) (w * ((hp + 0.0) / FULL_HP));
        Color c = Color.GREEN;
        c = hp > 80 ? c : Color.YELLOW;
        c = hp > 60 ? c : Color.ORANGE;
        c = hp > 40 ? c : Color.red;
        g.setColor(c);
        g.fill3DRect(0, 0, bw, 5, true);

    }

    protected void bao() {
        updateImg();   // 换图
        imgIndex = 0;  // 重置图片
        boomTimer = new Timer();
        BoomTask task = new BoomTask();
        boomTimer.schedule(task, 1, 100);
    }

    /**
     * 换图方法
     */
    private void updateImg() {
        src = "b" + imgIndex + ".gif";
        img = GameUtil.loadImg(src);     // 换图
    }

    /**
     * 读取当前坐标 x
     *
     * @return int
     */
    public int getPx() {
        return this.x;
    }

    /**
     * 读取当前坐标 y
     *
     * @return int
     */
    public int getPy() {
        return this.y;
    }

    /**
     * 获取游戏战场
     *
     * @return
     */
    public GamePanel getzhanchang() {
        return this.zhanchang;
    }

    /**
     * 当前对象改血操作
     * @param tk 发射子弹的坦克
     */
    public void changeBoold(Tank tk) {
        int xue = ATTK[tk.getAttk()] - DEF[this.def];  // 攻击点数-防御点数
        this.hp -= xue;
        if (hp <= 0) {
            die();
        }
    }

    /**
     * 获得当前物体的类别
     * @return Tp
     */
    public Tp getType() {
        return this.type;
    }

    /**
     * 爆炸特效
     */
    public class BoomTask extends TimerTask {
        @Override
        public void run() {
            imgIndex++;
            if (imgIndex > 8) {
                this.cancel();
                boomTimer.cancel();  // 停止这个定时器
                zhanchang.remove(me); // 从界面上消失
                if (type != Tp.Boom)    //大招不掉落道具
                buildProp();            //掉落道具
                imgIndex = 0;       //下次爆炸图片从零开始
                return;
            }
            updateImg();
            repaint();     // 刷新
        }
    }

    /**
     * 生成一个道具
     */
    static int ccc = 0;
    private void buildProp() {
        Prop p = new Prop(this);
        zhanchang.add(p);
        ccc++;
        System.out.println(ccc);
        zhanchang.getAllObjects().add(p);       //添加到所有物体集合中，参与碰撞

    }

    @Override
    public String toString() {
        return "这是一个" + getClass().getName() + "类型的对象。";
    }

    /**
     * 返回没个物体所占用的范围
     * @return Rectangle
     */
    public Rectangle getRect() {
        return new Rectangle(this.x, this.y, this.w, this.h);
    }

    /**
     * 物体死亡
     */
    public synchronized void die() {
        if (!zhanchang.getAllObjects().contains(this)) return;
        if (type == Tp.Enemy) {
            zhanchang.updateEnemyCount(-1);     //累计死多一辆
        }
        hp = 0;
        if (dieAudio != null)
        dieAudio.play();
        zhanchang.removeObject(this);  // 从集合中删除这个死亡的对象，避免二次碰撞和死亡
        bao();
    }
}
