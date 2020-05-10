
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private int[] snakeXLenght;
    private int[] snakeYLenght;

    public int snakeSize = 3;

    private int[] foodPossibleXPos;
    private int[] foodPossibleYPos;

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private Timer timer;
    private int speed = 50;

    private ImageIcon snakeImage;
    private ImageIcon foodImage;

    private int foodXPos;
    private int foodYPos;

    private Logic logic = new Logic();
    Database database = new Database();

    private ImageIcon title;

    public Gameplay() {
        logic.Reset();
        UpdateVariables();
        database.createHighScoreTable();
        foodPossibleXPos = new int[34];
        foodPossibleYPos = new int[17];
        for (int i = 1; i <= 34; i++) {
            foodPossibleXPos[i - 1] = i * 25;
        }
        for (int i = 3; i <= 19; i++) {
            foodPossibleYPos[i - 3] = i * 25;
        }
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(speed, this);
        logic.Reset();
        timer.start();
    }

    public void UpdateVariables() {
        foodXPos = logic.GetFoodXPos();
        foodYPos = logic.GetFoodYPos();
        snakeYLenght = logic.GetSnakeyLenght();
        snakeXLenght = logic.GetSnakexLenght();
        snakeSize = logic.GetSnakeSize();

    }

    private void PaintBackground(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);
        title = new ImageIcon("Images/text.jpg");
        title.paintIcon(this, g, 25, 11);

        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);

        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);
    }

    private void PaintScore(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("arial", Font.BOLD, 16));
        g.drawString("Score: " + (snakeSize - 3), 780, 50);

    }

    private void PaintFood(Graphics g) {
        foodImage = new ImageIcon("Images/food.png");
        foodImage.paintIcon(this, g, foodPossibleXPos[foodXPos], foodPossibleYPos[foodYPos]);

    }

    private void PaintSnake(Graphics g) {
        snakeImage = new ImageIcon("Images/snake.png");
        snakeImage.paintIcon(this, g, snakeXLenght[0], snakeYLenght[0]);
        for (int i = 0; i < snakeSize; i++) {
            snakeImage = new ImageIcon("Images/snake.png");
            snakeImage.paintIcon(this, g, snakeXLenght[i], snakeYLenght[i]);

        }
    }

    private void PaintHighscores(Graphics g) {
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
        UpdateVariables();

        boolean state = logic.GameLogic();

        // paint game window
        PaintBackground(g);
        // paint score
        PaintScore(g);

        // paint snake
        PaintSnake(g);

        // paint food
        PaintFood(g);
        // paint highscores
        if (!state) {
            timer.stop();
            PaintHighscores(g);
        }

        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e
    ) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            timer.start();
            logic.Reset();
            UpdateVariables();

        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            logic.SetDirection(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            logic.SetDirection(2);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            logic.SetDirection(3);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            logic.SetDirection(4);
        }
    }

    @Override
    public void keyReleased(KeyEvent e
    ) {
    }

    @Override
    public void actionPerformed(ActionEvent e
    ) {
        timer.start();
        logic.MoveSnake();
        repaint();

    }
}
