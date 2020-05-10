package fi.osmaot.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author osma Contains methods for managing database communication for this
 * project
 *
 */
public class Database {

    private static Statement statement;
    private static Connection connection;

    /**
     * Constructor for database class
     */
    public Database() {
        try {
            String host = "jdbc:derby://localhost:1527/snake_scores;create=true";
            String username = "snake";
            String password = "snake";
            connection = DriverManager.getConnection(host, username, password);
        } catch (SQLException err) {
        }
    }

    /**
     *
     * @param score of player
     * @param name of table
     * @return true if successful, else false adds given score to table called
     * name, used to add new highscores
     */
    public boolean addHighScore(int score, String name) {
        try {
            statement = connection.createStatement();
            statement.execute("INSERT INTO " + name + " VALUES (" + score + ")");
            statement.close();
            return true;
        } catch (SQLException err) {
            return false;
        }

    }

    /**
     *
     * @param name of table
     * @return true if action is successful, else false loads the 5 highest
     * numbers from given table
     *
     */
    public int[] loadHighScores(String name) {
        int[] output = new int[5];
        try {

            statement = connection.createStatement();
            statement.execute("SELECT * FROM " + name + " ORDER BY SCORE DESC");
            ResultSet results = statement.getResultSet();
            int i = 0;
            while (results.next()) {
                output[i] = results.getInt("score");
                i++;
                if (i >= 5) {
                    break;
                }
            }
        } catch (SQLException err) {
        }
        return output;

    }

    /**
     *
     * @param name of table
     * @return true if action successful, otherwise false creates a table for
     * storing highscores
     */
    public boolean createHighScoreTable(String name) {
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE " + name + " (SCORE INT)");
            statement.close();
            return true;
        } catch (SQLException err) {
            return false;
        }
    }

    /**
     *
     * @param name of table drops a table, used for getting rid of test tables /
     * possible resetting scores
     */
    public void clearTable(String name) {
        try {

            statement = connection.createStatement();
            statement.execute("DROP TABLE " + name + "");
            statement.close();
        } catch (SQLException err) {
            System.out.println("fail");
        }
    }
}
