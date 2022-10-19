package com.up.tank.view.panel.nodyna;

import com.up.tank.core.Tp;
import com.up.tank.util.GameUtil;
import com.up.tank.view.panel.GamePanel;

/**
 * 墙块类型
 *
 * @ClassName Wall
 * @Author QQ 2481476621
 * @Date 2022/7/26  16:33
 **/
public class Wall extends StaticObject {
    public Wall(int x, int y, GamePanel zhanchang) {
        super(x, y, Tp.Wall, "qiang.gif", WALL_WIDTH, WALL_HEIGHT, zhanchang);
        this.dieAudio = GameUtil.loadAudio("walldie.wav");
    }
}
