/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.osmaot.test.database;

import fi.osmaot.database.Database;
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
public class DatabaseTest {

    Database database;
    private String testTable = "testTable";
    
    public DatabaseTest() {
        database = new Database();

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

    
    // creating a new table is succesfull
    @Test
    public void createHighScoreTableTest() {
        assertTrue(database.createHighScoreTable(testTable));
        database.clearTable(testTable);
    }
    
    // add operation to database is successfull
    @Test
    public void addHighScoreTest() {
        database.createHighScoreTable(testTable);
        assertTrue(database.addHighScore(100, testTable));
        database.clearTable(testTable);

    }

    // highest scores loaded
    @Test
    public void loadHighScoresTest() {
        database.createHighScoreTable(testTable);
        database.addHighScore(90, testTable);
        database.addHighScore(100, testTable);
        database.addHighScore(110, testTable);
        database.addHighScore(140, testTable);
        database.addHighScore(130, testTable);
        database.addHighScore(150, testTable);
        database.addHighScore(110, testTable);
        int[] scores = database.loadHighScores(testTable);
        assertEquals(110, scores[4]);
        assertEquals(110, scores[3]);
        assertEquals(130, scores[2]);
        assertEquals(140, scores[1]);
        assertEquals(150, scores[0]);

        database.clearTable(testTable);

    }

    /**
     *missing scores are 0
     */
    @Test
    public void missingHighScoresTest() {
        database.createHighScoreTable(testTable);
        database.addHighScore(110, testTable);
        database.addHighScore(140, testTable);
        int[] scores = database.loadHighScores(testTable);
        assertEquals(0, scores[4]);
        assertEquals(0, scores[3]);
        assertEquals(0, scores[2]);
        assertEquals(110, scores[1]);
        assertEquals(140, scores[0]);
        database.clearTable(testTable);

    }
}
