
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private int[] snakexLenght = new int[750];
    private int[] snakeyLenght = new int[750];

    public int snakeSize = 3;

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    
    private int moves = 0;

    private Timer timer;
    private int speed = 100;

    private ImageIcon snakeImage;
    private ImageIcon snakeBody;

    private ImageIcon title;

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(speed, this);
        timer.start();
    }

    public void paint(Graphics g) {
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

        snakeImage = new ImageIcon("Images/snake.png");

        snakeImage.paintIcon(this, g, snakexLenght[0], snakeyLenght[0]);

        for (int i = 0; i < snakeSize; i++) {
            if (i == 0) {
                snakeImage = new ImageIcon("Images/snake.png");
                snakeImage.paintIcon(this, g, snakexLenght[0], snakeyLenght[0]);
            } else {
                snakeBody = new ImageIcon("Images/snakeBody.png");
                snakeBody.paintIcon(this, g, snakexLenght[i], snakeyLenght[i]);

            }
        }

        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
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
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
