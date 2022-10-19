package com.up.tank.view.panel.dyna;

import com.up.tank.core.Dir;
import com.up.tank.core.Tp;
import com.up.tank.view.panel.GameObject;
import com.up.tank.view.panel.dyna.tank.Tank;

import java.awt.*;

/**
 * @ClassName Bullet 子弹类
 * @Author QQ 2481476621
 * @Date 2022/7/27  14:12
 **/
public class Bullet extends DynaObject {

    private Tank tk;  // 子弹的发射者
    private int[] ww = {5, 7, 9, 11, 13};
    private int[] hh = {10, 14, 18, 22, 26};

    public Bullet(Tank tk) {
        super(tk.attk,
                tk.speed * 3,
                tk.dir,
                tk.getPx(),
                tk.getPy(),
                Tp.Bullet,
                "m" + tk.attk + tk.dir + ".png",
                1,
                1,
                tk.getzhanchang());
        this.tk = tk;  // 保存子弹的所有者
        // 通过坦克朝向对应子弹的宽高，以及通过坦克等级改变子弹等级
        this.w = dir == Dir.U || dir == Dir.D ? ww[attk] : hh[attk];  // 5-10
        this.h = dir == Dir.U || dir == Dir.D ? hh[attk] : ww[attk];  // 10-5
        if (dir == Dir.R) {
            x += tk.getWidth() + 1;
            y += (tk.getHeight() - h) / 2 +3;
        } else if (dir == Dir.L) {
            x -= (w + 1);
            y += (tk.getHeight() - h) / 2 +3;
        } else if (dir == Dir.U) {
            x += (tk.getWidth() - w) / 2 +1;
            y -= (h + 1);
        } else if (dir == Dir.D) {
            x += (tk.getWidth()-w)/2 +1;
            y += tk.getHeight()+1;
        }
        u = dir == Dir.U;
        d = dir == Dir.D;
        l = dir == Dir.L;
        r = dir == Dir.R;
        setBounds(x, y, w, h);
    }

    @Override
    public void dohit(GameObject hitObj, int oldx, int oldy) {
        die();  // 先移除碰撞的子弹，再进行改血
        hitObj.changeBoold(this.tk); // 对被撞的物体进行改血
    }

    @Override
    public void resetXy(int oldx, int oldy) {
        die();
    }

    @Override
    public void paintBlood(Graphics g) {
        // 不需要血条，所以重写为空
    }

    @Override
    public void die() {
        super.die();
        zhanchang.remove(this);
    }

    @Override
    protected void bao() {
        // 重写为空，不爆炸
    }

    /**
     * 得到子弹的主人
     * @return 子弹的发射者
     */
    public GameObject getMaster() {
        return tk;
    }
}
