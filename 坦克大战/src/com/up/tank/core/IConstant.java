package com.up.tank.core;

/**
 * 活动的物体对象
 *
 * @ClassName DynaObject
 * @Author QQ 2481476621
 * @Date 2022/7/22  09:54
 **/
public interface IConstant {

    public final static String IMG_PATH = "com/up/tank/images/";   // 图片文件位置
    public final static String AUD_PATH = "com/up/tank/sounds/";   // 声音文件位置

    public final static int FULL_HP = 100;        // 满血
    public final static int[] ATTK = {12, 17, 22, 27, 32}; // 攻击等级对应的点数
    public final static int[] DEF = {0, 2, 5, 8, 10}; // 防御等级对应的点数

    public final static int BOOM_W = 300;        // 爆炸的宽
    public final static int BOOM_H = 300;        // 爆炸的高

    public final static int PLANE_WIDTH = 60;     // 飞机宽
    public final static int PLANE_HEIGHT = 66;    // 飞机高

    public final static int TANK_WIDTH = 50;      // 坦克宽
    public final static int TANK_HEIGHT = 57;     // 坦克高

    public final static int WALL_WIDTH = 40;      // 墙壁宽
    public final static int WALL_HEIGHT = 46;     // 墙壁高

    public final static int HOME_WIDTH = 160;      // 家的宽
    public final static int HOME_HEIGHT = 160;     // 家的高

    public final static int MOVE_TIME = 20;       // 移动频率
    public final static int MOVE_SPEED = 6;       // 移动速度

    public final static int PROP_W = 30;       // 道具宽
    public final static int PROP_H = 30;       // 道具高
}
