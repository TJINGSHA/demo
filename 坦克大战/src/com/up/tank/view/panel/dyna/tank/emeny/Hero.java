package com.up.tank.view.panel.dyna.tank.emeny;

import com.up.tank.core.Dir;
import com.up.tank.core.Tp;
import com.up.tank.view.panel.GamePanel;

import java.awt.*;

/**
 * 敌军中的精英坦克
 * @ClassName Hero
 * @Author QQ 2481476621
 * @Date 2022/7/22  10:19
 **/
public class Hero extends Enemy{
    public Hero(int x, int y, String src, GamePanel zhanchang) {
        super(true, 2,MOVE_SPEED,Dir.D, x, y,Tp.Enemy,src, TANK_WIDTH+10, TANK_HEIGHT+10,zhanchang);
        this.def = 2;    // 精英坦克防御默认是2级（0~4）
    }
}
