package com.up.tank.view.panel.dyna;

import com.up.tank.core.Dir;
import com.up.tank.core.GameStat;
import com.up.tank.core.Tp;
import com.up.tank.view.panel.GameObject;
import com.up.tank.view.panel.GamePanel;
import com.up.tank.view.panel.dyna.tank.allies.Allies;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * 大招类型
 */
public class Big extends DynaObject{
    private Allies master;      //发大招的坦克
    public Big(Allies master) {
        super(master.attk, 0, master.dir, master.getPx(), master.getPy(), Tp.Boom, "b0.gif", BOOM_W, BOOM_H, master.getzhanchang());

        this.master = master;
        x -= (w - master.getWidth()) / 2;
        y -= (h - master.getHeight()) / 2;
        bao();

    }

    @Override
    public void dong() {
        if(zhanchang.getGameState() == GameStat.pause)return; //暂停状态什么都不做
        setBounds(x, y, w, h);
        List<GameObject> list = checHit();
        for (GameObject obj : list) {
            obj.changeBoold(master);
        }
    }

    /**
     * 碰撞检查，范围内所有被撞的物体记录下来
     * @return
     */
    private List checHit() {
        List<GameObject> list = new ArrayList<>();
        for (GameObject o : zhanchang.getAllObjects()) {
            if (o.equals(this)) continue;   // 跳过与自己碰撞
            if ( master.getType() == o.getType()) continue; //跳过和盟友的碰撞
            if( o.getType() == Tp.Prop) continue;     //跳过与道具的碰撞
            if (o.getRect().intersects(this.getRect())) {  // 判断当前对象与集合中的对象是否存在范围交集
                list.add(o);
            }
        }
        return list;
    }

    @Override
    public void paintBlood(Graphics g) { }
}
