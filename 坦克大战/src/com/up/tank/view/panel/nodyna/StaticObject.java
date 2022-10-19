package com.up.tank.view.panel.nodyna;

import com.up.tank.core.Tp;
import com.up.tank.view.panel.GameObject;
import com.up.tank.view.panel.GamePanel;

/**
 * 静止物体
 * @ClassName StaticObject
 * @Author QQ 2481476621
 * @Date 2022/7/26  16:34
 **/
public class StaticObject extends GameObject {
    public StaticObject(int x, int y, Tp type, String src, int width, int height, GamePanel zhanchang) {
        super(x, y, type, src, width, height, zhanchang);
    }
}
