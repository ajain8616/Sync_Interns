import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class Window extends JFrame {
    public static ArrayList<ArrayList<DataOfSquare>> Grid;
    public static int width = 20;
    public static int height = 20;
    private JLabel scoreLabel;

    public Window() {
        Grid = new ArrayList<ArrayList<DataOfSquare>>();
        ArrayList<DataOfSquare> data;
        for (int i = 0; i < width; i++) {
            data = new ArrayList<DataOfSquare>();
            for (int j = 0; j < height; j++) {
                DataOfSquare dataOfSquare = new DataOfSquare(2);
                data.add(dataOfSquare);
            }
            Grid.add(data);
        }
        getContentPane().setLayout(new GridLayout(20, 20, 0, 0));
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                getContentPane().add(Grid.get(i).get(j).square);
            }
        }
        Tuple position = new Tuple(10, 10);
        ThreadsController threadsController = new ThreadsController(position);
        threadsController.start();
        JOptionPane.showMessageDialog(this, "Press Space Bar Key to pause/resume the game.");
        this.addKeyListener(new KeyboardListener());
        setVisible(true);
        JOptionPane.showMessageDialog(this, "Game started!\nUse arrow keys to control the snake.");
        this.addKeyListener((KeyListener) new KeyboardListener());
    }

    public void updateScore(int score) {
         JOptionPane.showMessageDialog(this, "Score: " + score+"\n Press Enter for Continue the Game ");
         scoreLabel.setText("Score: " + score);
    }
}
