package com.up.tank.util;

import com.up.tank.core.IConstant;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 工具类，用于读取图片，音频配置文件等资源
 * @ClassName GameUtil
 * @Author QQ 2481476621
 * @Date 2022/7/22  10:37
 **/
public final class GameUtil {

    static Toolkit kit = Toolkit.getDefaultToolkit();
    static ResourceBundle bundle = null;
    static {   // 读取应用程序的配置文件
        bundle = ResourceBundle.getBundle("com/up/tank/config/tankwar");
    }

    /**
     * 获取一个图片资源
     * @param name 文件名
     * @return Image
     */
    public static Image loadImg(String name) {
        // 打印输出图片路径，显示什么图片发生的异常
        // System.out.println("path"+IConstant.IMG_PATH +name);  // 打印图片
        URL url = GameUtil.class.getClassLoader().getResource(IConstant.IMG_PATH + name);
        return kit.getImage(url);
    }

    /**
     * 从配置文件中读取一个数据
     * @param key 指定的键
     * @return 值 String
     */
    public static String get(String key) {
        return bundle.getString(key);
    }

    /**
     * 加载一个图片文件，以icon返回
     * @param name 文件名
     * @return Icon
     */
    public static Icon loadIcon(String name) {
        Image img = loadImg(name);
        return new ImageIcon(img);
    }

    /**
     * 加载一个音频文档
     * @param name 文件名
     * @return AudioClip
     */
    public static AudioClip loadAudio(String name) {
        URL url = GameUtil.class.getClassLoader().getResource(IConstant.AUD_PATH + name);
        AudioClip aud = Applet.newAudioClip(url);
        return aud;
    }
}
