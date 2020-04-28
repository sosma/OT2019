/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import javax.swing.JFrame;
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
public class GameplayTest {

    public GameplayTest() {
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
    public void testCorrectStartPosition() {
        JFrame screen = new JFrame();
        Gameplay gameplay = new Gameplay();

        screen.setBounds(10, 10, 900, 500);
        screen.setBackground(Color.white);
        screen.setResizable(false);
        screen.setVisible(true);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.add(gameplay);
        assertEquals(gameplay.snakeSize, 3);
    }
}
