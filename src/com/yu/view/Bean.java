package com.yu.view;

import java.awt.*;

public class Bean {
    public static final int Bean_SIZE = 25;
    public static final Color SNAKE_HEAD_COLOR = new Color(0xDA213D);
    private int beanLocationX = 200;
    private int beanLocationY = 200;

    public int getBeanLocationX() {
        return beanLocationX;
    }

    public void setBeanLocationX(int beanLocationX) {
        this.beanLocationX = getConversionLocation(beanLocationX);
    }

    public int getBeanLocationY() {
        return beanLocationY;
    }

    public void setBeanLocationY(int beanLocationY) {
        this.beanLocationY = getConversionLocation(beanLocationY);
    }

    /**
     * 位置到坐标
     * @param location
     * @return
     */
    private int getConversionLocation(int location){
        return (location - 1) * Bean_SIZE;
    }
}
