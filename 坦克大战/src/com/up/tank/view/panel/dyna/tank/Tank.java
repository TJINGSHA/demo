package com.up.tank.view.panel.dyna.tank;

import com.up.tank.core.Dir;
import com.up.tank.core.Tp;
import com.up.tank.view.panel.GameObject;
import com.up.tank.view.panel.GamePanel;
import com.up.tank.view.panel.dyna.Bullet;
import com.up.tank.view.panel.dyna.DynaObject;
import com.up.tank.view.panel.nodyna.Prop;

import java.awt.*;
import java.util.Random;


/**
 * 坦克类型
 * @ClassName Tank
 * @Author QQ 2481476621
 * @Date 2022/7/22  10:12
 **/
public class Tank extends DynaObject {

    public Tank(int attk, int speed, Dir dir, int x, int y, Tp type, String src, int width, int height, GamePanel zhanchang) {
        super(attk, speed, dir, x, y, type, src, width, height,zhanchang);

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 7, w, h-6, this);
        paintBlood(g);
    }

    /**
     * 坦克开火的方法
     */
    public void fire(){
        //attk = 4;
        // 构造一个子弹对象，并默认让子弹挂一档位
        Bullet bullet = new Bullet(this);
        zhanchang.add(bullet);
    }

    /**
     * 判断碰撞的是道具就吃
     * @param hitObj
     * @param oldx
     * @param oldy
     */
    @Override
    protected void dohit(GameObject hitObj, int oldx, int oldy) {
        if (hitObj.getType() == Tp.Prop) {
            eat(hitObj);
        }else {
            resetXy(oldx, oldy);
        }
    }

    /**
     * 吃道具
     * @param hitObj 被吃的道具
     */
    private void eat(GameObject hitObj) {
        hitObj.die();
        Prop p = (Prop) hitObj;
        int n = p.getNum();     //道具的具体数字，类别0-9
        switch (n) {
            case 0:
                changeSpead();
                break;
            case 1:
                eat1();
                break;
            case 2:
                eat2();
                break;
            case 3:
                changeAttk();
                break;
            case 4:
                changeDef();
                break;
            case 5:
                eat5();
                break;
            case 6:
                gaixue();
                break;
            case 7:
                die();
                break;
            case 8:
                eat8();
                break;
            case 9:
                eat9();
                break;

        }
    }

    /**
     * 正反双方各自改写
     */
    protected void eat1() {
    }

    /**
     * 正反双方各自改写五号道具
     */
    protected void eat5() {
    }

    /**
     * 正反双方各自改写八号道具
     */
    protected void eat8() {
    }

    /**
     * 正反双方各自改写九号道具
     */
    protected void eat9() {
    }

    /**
     * 正反双方各自改写二号道具
     */
    protected void eat2() {
    }

    /**
     * 改血
     * @param
     */
    private void gaixue() {
        hp += 30;
        hp = hp > FULL_HP ? FULL_HP : hp;
    }

    /**
     * 提升防御
     */
    private void changeDef() {
        def++;
        def = def > DEF.length - 1 ? DEF.length - 1 : def;
    }

    /**
     * 提升攻击
     */
    private void changeAttk() {
        attk++;
        attk = attk > ATTK.length - 1 ? ATTK.length - 1 : attk;

        if (getType()==Tp.Allies &&attk<4){     //我方坦克变大，敌方不可以吃
        w = w + 15;
        h = h + 15;}
    }

    /**
     * 提速
     */
    private void changeSpead() {
        speed++;
        speed = speed > 8 ? 8 : speed;
    }

    /**
     * 随机生成一个坐标
     * @param www 屏幕宽或高
     * @return
     */
    public int buildPoint(int www) {
        Random random = new Random();
        return random.nextInt(www - 200) + 100;
    }
}
