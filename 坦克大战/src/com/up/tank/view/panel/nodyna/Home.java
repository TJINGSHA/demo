package com.up.tank.view.panel.nodyna;

import com.up.tank.core.Tp;
import com.up.tank.view.panel.GamePanel;

/**
 * @ClassName Home
 * @Author QQ 2481476621
 * @Date 2022/7/26  19:32
 **/
public class Home extends StaticObject{

    public Home(int x, int y,String src ,GamePanel zhanchang) {
        super(x, y, Tp.Home, "huliGirl.png",HOME_WIDTH,HOME_HEIGHT, zhanchang);
    }
}
