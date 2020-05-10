
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

    private int[] snakexLenght = new int[750];
    private int[] snakeyLenght = new int[750];

    public int snakeSize = 3;

    private int[] foodxPos;
    private int[] foodyPos;

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private int moves = 0;

    private Timer timer;
    private int speed = 100;

    private ImageIcon snakeImage;
    private ImageIcon foodImage;
    private Random random = new Random();

    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(17);
    Database database = new Database();

    private ImageIcon title;

    public Gameplay() {
        database.createHighScoreTable();
        foodxPos = new int[34];
        foodyPos = new int[17];
        for (int i = 1; i <= 34; i++) {
            foodxPos[i - 1] = i * 25;
        }
        for (int i = 3; i <= 19; i++) {
            foodyPos[i - 3] = i * 25;
        }
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(speed, this);
        timer.start();
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());

        if (moves == 0) {
            snakexLenght[2] = 50;
            snakexLenght[1] = 75;
            snakexLenght[0] = 100;
            snakeyLenght[2] = 100;
            snakeyLenght[1] = 100;
            snakeyLenght[0] = 100;

        }
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);
        title = new ImageIcon("Images/text.jpg");
        title.paintIcon(this, g, 25, 11);

        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);

        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);

        g.setColor(Color.black);
        g.setFont(new Font("arial", Font.BOLD, 16));
        g.drawString("Score: " + (snakeSize - 3), 780, 50);

        snakeImage = new ImageIcon("Images/snake.png");

        snakeImage.paintIcon(this, g, snakexLenght[0], snakeyLenght[0]);

        for (int i = 0; i < snakeSize; i++) {
            snakeImage = new ImageIcon("Images/snake.png");
            snakeImage.paintIcon(this, g, snakexLenght[i], snakeyLenght[i]);

        }
        foodImage = new ImageIcon("Images/food.png");
        if (foodxPos[xpos] == snakexLenght[0] && foodyPos[ypos] == snakeyLenght[0]) {
            snakeSize++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(17);
        }
        for (int i = 1; i < snakeSize; i++) {
            if (snakexLenght[0] == snakexLenght[i] && snakeyLenght[0] == snakeyLenght[i]) {
                timer.stop();
                database.addHighScore(snakeSize - 3);
                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD, 24));
                g.drawString("Your final score!: " + (snakeSize - 3), 300, 100);
                g.drawString("Top scores: ", 300, 150);
                int spot = 200;
                int[] scores = database.loadHighScores();
                for (int score : scores) {
                    g.drawString((spot-150)/50+" : "+score, 300, spot);
                    spot+=50;
                }
            }
            if (foodxPos[xpos] == snakexLenght[i] && foodyPos[ypos] == snakeyLenght[i]) {
                xpos = random.nextInt(34);
                ypos = random.nextInt(17);
            }
        }

        foodImage.paintIcon(this, g, foodxPos[xpos], foodyPos[ypos]);

        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e
    ) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            timer.start();
            moves = 0;
            snakeSize = 3;
            repaint();

        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves++;
            if (!left) {
                right = true;

            }
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moves++;
            if (!right) {
                left = true;

            }
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moves++;
            if (!down) {
                up = true;

            }
            left = false;
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moves++;
            if (!up) {
                down = true;

            }
            left = false;
            right = false;
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
        if (right) {
            for (int i = snakeSize - 1; i >= 0; i--) {
                snakeyLenght[i + 1] = snakeyLenght[i];
            }
            for (int i = snakeSize; i >= 0; i--) {
                if (i == 0) {
                    snakexLenght[i] = snakexLenght[i] + 25;
                } else {
                    snakexLenght[i] = snakexLenght[i - 1];
                }
                if (snakexLenght[i] > 850) {
                    snakexLenght[i] = 25;
                }
            }

        }
        if (left) {
            for (int i = snakeSize - 1; i >= 0; i--) {
                snakeyLenght[i + 1] = snakeyLenght[i];
            }
            for (int i = snakeSize; i >= 0; i--) {
                if (i == 0) {
                    snakexLenght[i] = snakexLenght[i] - 25;
                } else {
                    snakexLenght[i] = snakexLenght[i - 1];
                }
                if (snakexLenght[i] < 25) {
                    snakexLenght[i] = 850;
                }
            }

        }
        if (down) {
            for (int i = snakeSize - 1; i >= 0; i--) {
                snakexLenght[i + 1] = snakexLenght[i];
            }
            for (int i = snakeSize; i >= 0; i--) {
                if (i == 0) {
                    snakeyLenght[i] = snakeyLenght[i] + 25;
                } else {
                    snakeyLenght[i] = snakeyLenght[i - 1];
                }
                if (snakeyLenght[i] > 475) {
                    snakeyLenght[i] = 75;
                }
            }

        }
        if (up) {
            for (int i = snakeSize - 1; i >= 0; i--) {
                snakexLenght[i + 1] = snakexLenght[i];
            }
            for (int i = snakeSize; i >= 0; i--) {
                if (i == 0) {
                    snakeyLenght[i] = snakeyLenght[i] - 25;
                } else {
                    snakeyLenght[i] = snakeyLenght[i - 1];
                }
                if (snakeyLenght[i] < 75) {
                    snakeyLenght[i] = 475;
                }
            }
        }
        repaint();

    }
}
