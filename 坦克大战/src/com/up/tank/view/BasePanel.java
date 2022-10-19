package com.up.tank.view;

import com.up.tank.core.IConstant;
import com.up.tank.util.GameUtil;
import com.up.tank.view.panel.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * 绘制主战场
 * @author QQ 1820802236
 * @date 2022-07-22
 */
public class BasePanel extends JPanel implements IConstant {
    protected Image img;      // 图片
    protected String src;     // 保存每个物体的图片文件名
    protected int w, h;       // 当前对象的宽高

    protected GamePanel zhanchang;   // 主界面对象

    public BasePanel(String src, int width, int height) {  // 给GamePanel子类用
        this.setOpaque(false);   // 设置不透明，false就是透明
        this.img = GameUtil.loadImg(src);    // src = "w2U.gif"
        this.src = src;
        this.w = width;
        this.h = height;
    }

    public BasePanel(String src, int width, int height, GamePanel zhanchang) {
        this.setOpaque(false);   // 设置不透明，false就是透明
        this.img = GameUtil.loadImg(src);    // src = "w2U.gif"
        this.src = src;
        this.w = width;
        this.h = height;
        this.zhanchang = zhanchang;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, w, h, this);
    }
}
