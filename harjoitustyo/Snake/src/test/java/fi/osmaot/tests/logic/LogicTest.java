/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.osmaot.tests.logic;

import fi.osmaot.logic.Logic;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author osma
 */
public class LogicTest {
     Logic logic;
    public LogicTest() {
        logic = new Logic();
        logic.reset();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void changeToOppositeDirectionTest() {
        logic.setDirection(1); // go right
        logic.setDirection(2); // try o go left
        logic.gameLogic();
        // the snake head should now be at x position 125 since it should have gone 1 step right
        assertEquals(125, logic.getSnakeXLenght()[0]);
    }
    @Test
    public void checkHitDetectionFalse() {
        logic.setDirection(1);
        assertTrue(logic.gameLogic());
    }
}
