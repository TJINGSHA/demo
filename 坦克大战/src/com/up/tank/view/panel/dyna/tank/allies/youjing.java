package com.up.tank.view.panel.dyna.tank.allies;

import com.up.tank.core.Dir;
import com.up.tank.core.Tp;
import com.up.tank.util.GameUtil;
import com.up.tank.view.panel.GamePanel;
import com.up.tank.view.panel.dyna.tank.Tank;

public class youjing extends Tank {
    public youjing( int attk, int speed, Dir dir, int x, int y, String src, int width, int height,GamePanel zhanchang) {
        super(attk, speed, dir, x, y, Tp.Allies, src, width, height,zhanchang);
        this.dieAudio = GameUtil.loadAudio("cao.wav");
    }
}
