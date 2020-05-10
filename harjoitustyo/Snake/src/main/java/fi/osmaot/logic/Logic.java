package fi.osmaot.logic;

import java.util.Random;

/**
 *
 * @author osma contains methods for handling gameplay logic for this project
 */
public class Logic {

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private int[] snakeXLenght = new int[750];
    private int[] snakeYLenght = new int[750];
    private int snakeSize = 3;

    private int[] foodPossibleXPos;
    private int[] foodPossibleYPos;

    private int foodXPos;
    private int foodYPos;

    Random random = new Random();

    /**
     * Constructor for logic class
     */
    public Logic() {
        foodPossibleXPos = new int[34];
        foodPossibleYPos = new int[17];
        for (int i = 1; i <= 34; i++) {
            foodPossibleXPos[i - 1] = i * 25;
        }
        for (int i = 3; i <= 19; i++) {
            foodPossibleYPos[i - 3] = i * 25;
        }
        foodXPos = random.nextInt(34);
        foodYPos = random.nextInt(17);

    }

    public int[] getSnakeXLenght() {
        return snakeXLenght;
    }

    public int[] getSnakeYLenght() {
        return snakeYLenght;
    }

    public int getFoodXPos() {
        return foodXPos;
    }

    public int getFoodYPos() {
        return foodYPos;

    }

    public int getSnakeSize() {
        return snakeSize;
    }

    /**
     * Resets all necessary values for starting a new game
     */
    public void reset() {
        snakeXLenght[2] = 50;
        snakeXLenght[1] = 75;
        snakeXLenght[0] = 100;
        snakeYLenght[2] = 100;
        snakeYLenght[1] = 100;
        snakeYLenght[0] = 100;
        snakeSize = 3;
        right = false;
        left = false;
        up = false;
        down = false;
        foodXPos = random.nextInt(34);
        foodYPos = random.nextInt(17);
    }

    private boolean testSnakeCrash() {
        for (int i = 1; i < snakeSize; i++) {
            if (snakeXLenght[0] == snakeXLenght[i] && snakeYLenght[0] == snakeYLenght[i]) {
                return true;
            }
        }
        return false;
    }

    private boolean testEatFood() {
        if (foodPossibleXPos[foodXPos] == snakeXLenght[0]
                && foodPossibleYPos[foodYPos] == snakeYLenght[0]) {
            foodXPos = random.nextInt(34);
            foodYPos = random.nextInt(17);
            return true;
        }
        return false;
    }

    private void eatFood() {
        snakeSize++;
        while (true) {
            for (int i = 1; i < snakeSize; i++) {
                if (foodPossibleXPos[foodXPos] == snakeXLenght[i]
                        && foodPossibleYPos[foodYPos] == snakeYLenght[i]) {
                    foodXPos = random.nextInt(34);
                    foodYPos = random.nextInt(17);
                } else {
                    return;
                }
            }

        }

    }

    private void moveSnake() {
        if (right) {
            for (int i = snakeSize - 1; i >= 0; i--) {
                snakeYLenght[i + 1] = snakeYLenght[i];
            }
            for (int i = snakeSize; i >= 0; i--) {
                if (i == 0) {
                    snakeXLenght[i] = snakeXLenght[i] + 25;
                } else {
                    snakeXLenght[i] = snakeXLenght[i - 1];
                }
                if (snakeXLenght[i] > 850) {
                    snakeXLenght[i] = 25;
                }
            }

        }
        if (left) {
            for (int i = snakeSize - 1; i >= 0; i--) {
                snakeYLenght[i + 1] = snakeYLenght[i];
            }
            for (int i = snakeSize; i >= 0; i--) {
                if (i == 0) {
                    snakeXLenght[i] = snakeXLenght[i] - 25;
                } else {
                    snakeXLenght[i] = snakeXLenght[i - 1];
                }
                if (snakeXLenght[i] < 25) {
                    snakeXLenght[i] = 850;
                }
            }

        }
        if (down) {
            for (int i = snakeSize - 1; i >= 0; i--) {
                snakeXLenght[i + 1] = snakeXLenght[i];
            }
            for (int i = snakeSize; i >= 0; i--) {
                if (i == 0) {
                    snakeYLenght[i] = snakeYLenght[i] + 25;
                } else {
                    snakeYLenght[i] = snakeYLenght[i - 1];
                }
                if (snakeYLenght[i] > 475) {
                    snakeYLenght[i] = 75;
                }
            }

        }
        if (up) {
            for (int i = snakeSize - 1; i >= 0; i--) {
                snakeXLenght[i + 1] = snakeXLenght[i];
            }
            for (int i = snakeSize; i >= 0; i--) {
                if (i == 0) {
                    snakeYLenght[i] = snakeYLenght[i] - 25;
                } else {
                    snakeYLenght[i] = snakeYLenght[i - 1];
                }
                if (snakeYLenght[i] < 75) {
                    snakeYLenght[i] = 475;
                }
            }
        }
    }

    /**
     *
     * @param direction takes an integer between 1 and 4 as input to determine direction.
     * tells the snake to go right, left, up or down. 
     * 1 is right, 2 is left, 3 is up, 4 is down
     */
    public void setDirection(int direction) {
        //right
        if (direction == 1) {
            if (!left) {
                right = true;

            }
            up = false;
            down = false;
        }
        //left
        if (direction == 2) {
            if (!right) {
                left = true;

            }
            up = false;
            down = false;
        }
        //up
        if (direction == 3) {
            if (!down) {
                up = true;

            }
            left = false;
            right = false;
        }
        //down
        if (direction == 4) {
            if (!up) {
                down = true;

            }
            left = false;
            right = false;

        }

    }

    /**
     *
     * @return true if game keeps running, false if it is a gameover
     *  responsible for all the logic of the game
     */
    public boolean gameLogic() {
        moveSnake();
        if (testSnakeCrash()) {
            return false;
        }
        if (testEatFood()) {
            eatFood();
        }
        return true;
    }

    /**
     * A method purely for testing
     */
    public void eatFoodForTesting() {
        eatFood();
    }

}
