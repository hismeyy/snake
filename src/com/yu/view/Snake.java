package com.yu.view;

import java.awt.*;

public class Snake {

    public static final int NOT_CONVERSION = 0; // 转换前的坐标
    public static final int CONVERSION = 1; // 转换后的坐标


    public static final int SNAKE_SIZE = 25;
    public static final Color SNAKE_HEAD_COLOR = new Color(0x518BD7);
    public static final Color SNAKE_BODY_COLOR = new Color(0x78CC5B);

    private int snakeLength;

    private int[] snakeLocationX = new int[950];
    private int[] snakeLocationY = new int[950];

    private char snakeDirection; // R右 L左 U上 D下



    public Snake(int snakeLength, char snakeDirection, int initLocationX, int initLocationY) {
        this.snakeLength = snakeLength;
        this.snakeDirection = snakeDirection;

        this.snakeLocationX[0] = getConversionLocation(initLocationX);
        this.snakeLocationY[0] = getConversionLocation(initLocationY);

        for(int i = 1; i < snakeLength; i++){
            this.snakeLocationX[i] = getConversionLocation(initLocationX-i);
            this.snakeLocationY[i] = getConversionLocation(initLocationY);
        }
    }

    /**
     * 位置到坐标
     * @param location
     * @return
     */
    private int getConversionLocation(int location){
        return (location - 1) * SNAKE_SIZE;
    }

    /**
     * 坐标到位置
     * @param conversionLocation
     * @return
     */
    private int getLocation(int conversionLocation){
        return conversionLocation / SNAKE_SIZE + 1;
    }

    public int getSnakeLength() {
        return snakeLength;
    }

    public void setSnakeLength(int snakeLength) {
        this.snakeLength = snakeLength;
    }

    public int getSnakeLocationX(int key, int isConversion) {
        if(isConversion == 1){
            return snakeLocationX[key];
        }else {
            return getLocation(snakeLocationX[key]);
        }

    }

    public void setSnakeLocationX(int key, int locationX) {
        this.snakeLocationX[key] = getConversionLocation(locationX);
    }

    public int getSnakeLocationY(int key, int isConversion) {
        if(isConversion == 1){
            return snakeLocationY[key];
        }else {
            return getLocation(snakeLocationY[key]);
        }
    }

    public void setSnakeLocationY(int key, int locationY) {
        this.snakeLocationY[key] = getConversionLocation(locationY);
    }

    public char getSnakeDirection() {
        return snakeDirection;
    }

    public void setSnakeDirection(char snakeDirection) {
        this.snakeDirection = snakeDirection;
    }

}
