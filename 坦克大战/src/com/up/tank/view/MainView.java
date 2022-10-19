package com.up.tank.view;

import com.up.tank.view.panel.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainView extends JFrame {
    private GamePanel main;   // 游戏主战场
    public MainView() throws HeadlessException {
        init();
        initmain();
        setVisible(true);  // 窗体可见性,放在构造方法最后一句
    }

    /**
     * 初始化主战场
     */
    private void initmain() {
        // 主战场面板放进主界面，由于主界面有默认的边界布局，所以坐标无效
        main = new GamePanel("ditudark.jpg",this.getWidth(),this.getHeight());
        add(main);
    }

    /**
     * 对主界面初始化大小和位置
     */
    private void init() {
        //无外框
        setUndecorated(true);
        // 全屏
        getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        // 事件监听
        addKeyListener(new KeyAction());
    }

    /**
     * 按键事件监听器类
     */
    private class KeyAction extends KeyAdapter{
        @Override  // 按下ESC程序终止运行
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();  // 反馈按键的ASCII
            if (code == KeyEvent.VK_ESCAPE) {  // 校验ASCII
                System.exit(0);  // 终止程序
            }
            main.pressed(code);  // 将事件传播到主战场界面类中
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();
            main.release(code);  // 将事件传播到主战场界面类中

        }
    }
}
