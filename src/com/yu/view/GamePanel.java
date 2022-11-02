package com.yu.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel {
    private static final int GAME_WIN_WIDTH = 950;
    private static final int GAME_WIN_HEIGHT = 625;
    private static final int GAME_WIN_LOCATION_X = 15;
    private static final int GAME_WIN_LOCATION_Y = 65;

    private int snakeLength = 0;
    private boolean isStart = false;
    private int isSuccess = 3;

    Snake snake;
    Bean bean = new Bean();
    Random rand = new Random();

    Timer timer = new Timer(100, new GameActionListener());

    public GamePanel() {
        this.init();
        this.setFocusable(true);
        // 添加监听
        addKeyListener(new GameListener());
        // 事件监听
        timer.start();
    }

    /**
     * 初始化数据
     */
    private void init(){
        this.setBackground(Color.WHITE);
        // 初始化蛇
        snake = new Snake(3,'R',5,1);
        isStart = false;

    }

    /**
     * 绘画
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 游戏界面
        this.drawGameWin(g);
        this.drawBean(g);
        // 蛇
        this.drawSnake(g);

        this.drawStart(g);
    }

    /**
     * 画游戏窗口界面
     * @param g
     */
    private void drawGameWin(Graphics g){
        // 游戏界面
        g.setColor(Color.darkGray);
        g.fillRect(GAME_WIN_LOCATION_X,GAME_WIN_LOCATION_Y,GAME_WIN_WIDTH,GAME_WIN_HEIGHT);
        // 分数界面
        g.setFont(new Font("微软雅黑", Font.BOLD,24));
        g.drawString("当前长度：" + snakeLength,35,40);
    }

    /**
     * 绘制蛇
     * @param g
     */
    private void drawSnake(Graphics g){
        g.setColor(Snake.SNAKE_HEAD_COLOR);
        g.fillRect(getConversionLocationX(snake.getSnakeLocationX(0,Snake.CONVERSION)),
                getConversionLocationY(snake.getSnakeLocationY(0,Snake.CONVERSION)),
                Snake.SNAKE_SIZE,Snake.SNAKE_SIZE);

        g.setColor(Snake.SNAKE_BODY_COLOR);
        for (int i = 1; i < snake.getSnakeLength(); i++){
            g.fillRect(
                    getConversionLocationX(snake.getSnakeLocationX(i,Snake.CONVERSION)),
                    getConversionLocationY(snake.getSnakeLocationY(i,Snake.CONVERSION)),
                    Snake.SNAKE_SIZE,Snake.SNAKE_SIZE);
        }

    }

    /**
     * 绘制游戏状态
     * @param g
     */
    private void drawStart(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD,36));
        if(!isStart){
            g.drawString("按下空格后开始",380,350);
        }
        if(isSuccess == 1){ // 成功
            g.drawString("成功通关，太厉害啦!",350,350);
        }else if(isSuccess == 0){// 失败
            g.drawString("失败喽，按空格重开!",350,350);
        }

    }

    private void drawBean(Graphics g){
        g.setColor(Bean.SNAKE_HEAD_COLOR);
        g.fillOval(getConversionLocationX(bean.getBeanLocationX()),getConversionLocationY(bean.getBeanLocationY()),
                Bean.Bean_SIZE,Bean.Bean_SIZE);
    }

        /**
         * 对蛇X位置进行从设定
         * @param locationX
         * @return
         */
    private int getConversionLocationX(int locationX){
        return locationX + GAME_WIN_LOCATION_X;
    }
    /**
     * 对蛇Y位置进行从设定
     * @param locationY
     * @return
     */
    private int getConversionLocationY(int locationY){
        return locationY + GAME_WIN_LOCATION_Y;
    }

    private class GameListener extends KeyAdapter {


        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                isStart = !isStart;
                if(isSuccess != 3){
                    init();
                    isSuccess = 3;
                }
                GamePanel.this.repaint();
            }else if (e.getKeyCode() == KeyEvent.VK_W){
                snake.setSnakeDirection('U');
            }else if (e.getKeyCode() == KeyEvent.VK_S){
                snake.setSnakeDirection('D');
            }else if (e.getKeyCode() == KeyEvent.VK_A){
                snake.setSnakeDirection('L');
            }else if (e.getKeyCode() == KeyEvent.VK_D){
                snake.setSnakeDirection('R');
            }
        }
    }


    private class GameActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(isStart && isSuccess == 3){
                // 吃豆子
                eatBean();
                // 成功检测
                successCheck();
                // 蛇移动
                moveSnake();
                // 碰撞检测
                collisionCheck();
                snakeLength = snake.getSnakeLength();
                GamePanel.this.repaint();
            }

        }

        private void collisionCheck(){
            // 碰撞检测
            for(int i = 1; i <= snakeLength; i++){
                int snakeHeadLocationX = snake.getSnakeLocationX(0,Snake.CONVERSION);
                int snakeHeadLocationY = snake.getSnakeLocationY(0,Snake.CONVERSION);
                int snakeLocationX = snake.getSnakeLocationX(i,Snake.CONVERSION);
                int snakeLocationY = snake.getSnakeLocationY(i,Snake.CONVERSION);

                if(snakeLocationX == snakeHeadLocationX && snakeLocationY == snakeHeadLocationY){
                    GamePanel.this.isSuccess = 0;
                }

            }
        }

        private void moveSnake(){
            // 设置身体的位置
            for(int i = snake.getSnakeLength() - 1; i > 0; i--){
                snake.setSnakeLocationX(i,snake.getSnakeLocationX(i-1, Snake.NOT_CONVERSION));
                snake.setSnakeLocationY(i,snake.getSnakeLocationY(i-1, Snake.NOT_CONVERSION));
            }
            // 设置头的位置
            if(snake.getSnakeDirection() == 'U'){
                snake.setSnakeLocationY(0,snake.getSnakeLocationY(0, Snake.NOT_CONVERSION)-1);
            }else if(snake.getSnakeDirection() == 'D'){
                snake.setSnakeLocationY(0,snake.getSnakeLocationY(0, Snake.NOT_CONVERSION)+1);
            }else if(snake.getSnakeDirection() == 'L'){
                snake.setSnakeLocationX(0,snake.getSnakeLocationX(0, Snake.NOT_CONVERSION)-1);
            }else if(snake.getSnakeDirection() == 'R'){
                snake.setSnakeLocationX(0,snake.getSnakeLocationX(0, Snake.NOT_CONVERSION)+1);
            }

            // 超出后重新设置位置
            if(snake.getSnakeLocationX(0,Snake.NOT_CONVERSION) > 38){
                snake.setSnakeLocationX(0,1);
            }else if(snake.getSnakeLocationY(0,Snake.NOT_CONVERSION) > 25){
                snake.setSnakeLocationY(0,1);
            }else if(snake.getSnakeLocationX(0,Snake.NOT_CONVERSION) == 0){
                snake.setSnakeLocationX(0,38);
            }else if(snake.getSnakeLocationY(0,Snake.NOT_CONVERSION) == 0){
                snake.setSnakeLocationY(0,25);
            }
        }

        private void eatBean(){
            // 吃豆子
            for(int i = 0; i <= snakeLength; i++){
                int snakeLocationX = snake.getSnakeLocationX(i,Snake.CONVERSION);
                int snakeLocationY = snake.getSnakeLocationY(i,Snake.CONVERSION);
                int beanLocationX = bean.getBeanLocationX();
                int beanLocationY = bean.getBeanLocationY();

                if(snakeLocationX == beanLocationX && snakeLocationY == beanLocationY){
                    snake.setSnakeLength(snake.getSnakeLength() + 1);
                    // 重置豆子
                    // 取随机数
                    // 公式 int randNumber = rand.nextInt(MAX - MIN + 1) + MIN;
                    beanLocationX = rand.nextInt(38) + 1;
                    beanLocationY = rand.nextInt(25) + 1;;
                    bean.setBeanLocationX(beanLocationX);
                    bean.setBeanLocationY(beanLocationY);

                }
            }
        }

        private void successCheck(){
            if(snake.getSnakeLength() == 100){
                isSuccess = 1;
            }
        }
    }
}
