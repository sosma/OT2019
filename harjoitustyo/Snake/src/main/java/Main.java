import java.awt.Color;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame screen = new JFrame();
        Gameplay gameplay = new Gameplay();
        
        screen.setBounds(10, 10, 900, 500);
        screen.setBackground(Color.white);
        screen.setResizable(false);
        screen.setVisible(true);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.add(gameplay);
    }
    
}
