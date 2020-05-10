package fi.osmaot.gui;


import fi.osmaot.logic.Logic;
import fi.osmaot.database.Database;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author osma
 * 
 * Contains methods for handling the gui for this project
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private int[] snakeXLenght;
    private int[] snakeYLenght;

    private final int mapXSize = 34;
    private final int mapYSize = 17;

    public int snakeSize;
    
    private boolean gameState;

    private int[] foodPossibleXPos;
    private int[] foodPossibleYPos;

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private Timer timer;
    private final int speed = 100;

    private ImageIcon snakeImage;
    private ImageIcon foodImage;

    private int foodXPos;
    private int foodYPos;

    private Logic logic = new Logic();
    Database database = new Database();

    private ImageIcon title;

    public Gameplay() {
        logic.reset();
        updateVariables();
        database.createHighScoreTable();
        foodPossibleXPos = new int[mapXSize];
        foodPossibleYPos = new int[mapYSize];
        for (int i = 1; i <= mapXSize; i++) {
            foodPossibleXPos[i - 1] = i * 25;
        }
        for (int i = 3; i <= mapYSize+2; i++) {
            foodPossibleYPos[i - 3] = i * 25;
        }
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(speed, this);
        logic.reset();
        timer.start();
    }

    public void updateVariables() {
        foodXPos = logic.getFoodXPos();
        foodYPos = logic.getFoodYPos();
        snakeYLenght = logic.getSnakeYLenght();
        snakeXLenght = logic.getSnakeXLenght();
        snakeSize = logic.getSnakeSize();

    }

    private void paintBackground(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);
        title = new ImageIcon("Images/text.jpg");
        title.paintIcon(this, g, 25, 11);

        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);

        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);
    }

    private void paintScore(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("arial", Font.BOLD, 16));
        g.drawString("Score: " + (snakeSize - 3), 780, 50);

    }

    private void paintFood(Graphics g) {
        foodImage = new ImageIcon("Images/food.png");
        foodImage.paintIcon(this, g, foodPossibleXPos[foodXPos], foodPossibleYPos[foodYPos]);

    }

    private void paintSnake(Graphics g) {
        snakeImage = new ImageIcon("Images/snake.png");
        snakeImage.paintIcon(this, g, snakeXLenght[0], snakeYLenght[0]);
        for (int i = 0; i < snakeSize; i++) {
            snakeImage = new ImageIcon("Images/snake.png");
            snakeImage.paintIcon(this, g, snakeXLenght[i], snakeYLenght[i]);

        }
    }

    private void paintHighscores(Graphics g) {
        database.addHighScore(snakeSize - 3);
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.BOLD, 24));
        g.drawString("Your final score: " + (snakeSize - 3), 300, 100);
        g.drawString("Top scores! ", 300, 150);
        int spot = 200;
        int[] scores = database.loadHighScores();
        for (int score : scores) {
            g.drawString((spot - 150) / 50 + " : " + score, 300, spot);
            spot += 50;
        }

    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        updateVariables();

        // paint game window
        paintBackground(g);
        // paint score
        paintScore(g);

        // paint snake
        paintSnake(g);

        // paint food
        paintFood(g);
        
        // paint highscores
        if(!gameState) {
            paintHighscores(g);
        }

        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e
    ) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            timer.start();
            logic.reset();
            updateVariables();

        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            logic.setDirection(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            logic.setDirection(2);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            logic.setDirection(3);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            logic.setDirection(4);
        }
    }

    @Override
    public void keyReleased(KeyEvent e
    ) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        gameState = logic.gameLogic();
        if(!gameState) {
            timer.stop();
        }
        repaint();

    }
}
