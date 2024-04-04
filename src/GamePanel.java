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
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    static final int DELAY = 75;
    final int x[] = new int[GAME_UNITS];  //array that hold all coordinates of body parts of snake including head
    final int y[] = new int[GAME_UNITS]; //the array that hold all y coordinates of the body parts for snake

    int BodyParts = 6;
    int FruitsEaten;
    int FruitsX;
    int FruitsY;
    char direction = 'Z';
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
        GameTimer = new Timer(DELAY, this);
        GameTimer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g) {
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }

        //Design and Color of Fruit / Apple
        g.setColor(Color.red);
        g.fillOval(FruitsX, FruitsY, UNIT_SIZE, UNIT_SIZE);

        //Design and color of Snake Body Parts
        for (int i = 0; i < BodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.darkGray);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            } else {
                g.setColor(Color.gray);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + FruitsEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + FruitsEaten)) / 2, g.getFont().getSize());
//    } else {
//        GameOver(g);
    }

    public void NewFruit() {
        FruitsX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        FruitsY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        for (int i = BodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'W':
                y[0] = y[0] - UNIT_SIZE; //UP
                break;
            case 'Z':
                y[0] = y[0] + UNIT_SIZE; //DOWN
                break;
            case 'A':
                x[0] = x[0] - UNIT_SIZE; //LEFT
                break;
            case 'D':
                x[0] = x[0] + UNIT_SIZE; //RIGHT
                break;
        }
    }

    public void CheckFruit() {
        if ((x[0] == FruitsX) && (y[0] == FruitsY)) {
            BodyParts++;
            FruitsEaten++;
            NewFruit();
        }
    }

    public void CheckCrash() {
        //checks if head collides with body
        for (int i = BodyParts; i > 0; i--) {
            if ((x[0] == x[i] && y[0] == y[i])) {
                Slithering = false;
            }
        }
        //checks if head touches LEFT border
        if (x[0] < 0) {
            Slithering = false;
        }
        //check to see if head touches RIGHT border
        if (x[0] > SCREEN_WIDTH) {
            Slithering = false;
        }
        //check to see if head touches TOP border
        if (y[0] < 0) {
            Slithering = false;
        }
        //check to see if head touches BOTTOM border
        if (y[0] > SCREEN_WIDTH) {
            Slithering = false;
        }
        if (!Slithering) {
            GameTimer.stop();
        }
    }

    public void GameOver(Graphics g) {
//Score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + FruitsEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + FruitsEaten)) / 2, g.getFont().getSize());
        //Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (Slithering) {
            move();
            CheckFruit();
            CheckCrash();
        }
        repaint();
    }

    public class KepAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_W:
                    if (direction != 'Z')  // Ensure the snake cannot reverse direction
                        direction = 'W';  // UP
                    break;
                case KeyEvent.VK_Z:
                    if (direction != 'W')
                        direction = 'Z';  // DOWN
                    break;
                case KeyEvent.VK_A:
                    if (direction != 'D')
                        direction = 'A';  // LEFT
                    break;
                case KeyEvent.VK_D:
                    if (direction != 'A')
                        direction = 'D';  // RIGHT
                    break;

            }
        }
    }
}

