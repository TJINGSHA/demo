package com.up.tank.view.panel.nodyna;

import com.up.tank.core.Tp;
import com.up.tank.util.GameUtil;
import com.up.tank.view.panel.GameObject;
import java.awt.*;
import java.util.Random;

/**
 * 道具类型
 */
public class Prop extends StaticObject {
    private int num ;           //当前道具的类别

    public int getNum() {
        return num;
    }

    public Prop(GameObject dieObi) {
        super(dieObi.getPx(), dieObi.getPy(), Tp.Prop,"x0.gif", PROP_W,PROP_H, dieObi.getzhanchang());
        Random r = new Random();
        num = r.nextInt(10);
        src = "x" + num + ".gif";
        img = GameUtil.loadImg(src);
        x += (dieObi.getWidth() - w) / 2;
        y += (dieObi.getHeight() - h) / 2;
        setBounds(x, y, w, h);
        dieAudio = GameUtil.loadAudio("x" + num + ".wav");
    }

    @Override
    protected void bao() {
        zhanchang.remove(this);     //从界面上消失
    }

    @Override
    public void paintBlood(Graphics g) {        //不需要血条

    }
}
