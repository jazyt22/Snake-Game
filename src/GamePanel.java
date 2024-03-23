import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.awt.event.*;
import javax.swing.Timer;

import javax.swing.JPanel;
public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 30;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;
    final int x[] = new int[GAME_UNITS];  //array that hold all coordinates of body parts of snake including head
    final int y[] = new int[GAME_UNITS]; //the array that hold all y coordinates of the body parts for snake
    int BodyParts = 6;
    int FruitsEaten = 0;
    int FruitsX;
    int FruitsY;
    char direction = 'U';
    boolean Slithering = false;
    Timer GameTimer;
    Random random;
    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.pink);
        this.setFocusable(true);
        this.addKeyListener(new KepAdapter());
        StartGame();
    }

    public void StartGame() {
        NewFruit();
        Slithering = true;
        GameTimer = new Timer(DELAY,this);
        GameTimer.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        for(int i = 0; i<SCREEN_HEIGHT/UNIT_SIZE; i++){
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH , i*UNIT_SIZE);
        }
    }

    public void NewFruit() {
        FruitsX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE))*UNIT_SIZE;
        FruitsY = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE))*UNIT_SIZE;
    }
    public void Move(){
    }

    public void CheckFruit(){
    }
    public void CheckCrash(){
    }

    public void GameOver(Graphics g){
    }

    public void actionPerformed(ActionEvent e){
    }
    public class KepAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){

        }
    }


}

