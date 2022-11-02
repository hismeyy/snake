package com.yu.view;

import javax.swing.*;
import java.awt.*;

public class Win extends JFrame {

    private static final int WIN_WIDTH = 1000;
    private static final int WIN_HEIGHT = 750;
    private static final int WIN_LOCATION_X;
    private static final int WIN_LOCATION_Y;

    static {
        // 初始化位置
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIN_LOCATION_X = (int)(screenSize.getWidth()/2 - WIN_WIDTH/2);
        WIN_LOCATION_Y = (int)(screenSize.getHeight()/2 - WIN_HEIGHT/2);
    }

    /**
     * 加载游戏窗口
     */
    public void loadGameWin(){
        setTitle("贪吃蛇");
        setLocation(WIN_LOCATION_X,WIN_LOCATION_Y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIN_WIDTH,WIN_HEIGHT);
        setResizable(false);
        add(new GamePanel());
        setVisible(true);
    }

}
