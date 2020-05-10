
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static Statement statement;
    private static Connection connection;

    public Database() {
        try {
            String host = "jdbc:derby://localhost:1527/snake_scores;create=true";
            String username = "snake";
            String password = "snake";
            connection = DriverManager.getConnection(host, username, password);
        } catch (SQLException err) {
            System.out.println("could not load highscores");
        }
    }

    public void addHighScore(int score) {
        try {
            statement = connection.createStatement();
            statement.execute("INSERT INTO SCORES VALUES (" + score + ")");
            statement.close();
        } catch (SQLException err) {
            System.out.println("highscore was not saved");
        }

    }

    public int[] loadHighScores() {
        int[] output = new int[5];
        try {

            statement = connection.createStatement();
            statement.execute("SELECT * FROM SCORES ORDER BY SCORE DESC");
            ResultSet results = statement.getResultSet();
            int i = 0;
            while (results.next()) {
                output[i] = results.getInt("score");
                i++;
                if(i>=5) {
                    break;
                }
            }
        } catch (SQLException err) {
            System.out.println("could not load other highscores");
        }
        return output;

    }

    public void createHighScoreTable() {
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE SCORES (SCORE INT)");
            statement.close();
        } catch (SQLException err) {
            System.out.println("database was allready created");
        }
    }
}
