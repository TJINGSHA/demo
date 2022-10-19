package com.up.tank.view.panel.dyna.tank.emeny;

import com.up.tank.core.Dir;
import com.up.tank.core.Tp;
import com.up.tank.view.panel.GamePanel;

import java.awt.*;

/**
 * @ClassName Normal
 * @Author QQ 2481476621
 * @Date 2022/7/22  10:24
 **/
public class Normal extends Enemy{

    public Normal(int x, int y, String src, GamePanel zhanchang) {
        super(false,0,MOVE_SPEED,Dir.D, x, y,Tp.Enemy, src, TANK_WIDTH, TANK_HEIGHT,zhanchang);
    }
}
