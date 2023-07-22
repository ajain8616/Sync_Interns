import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        // Creating the window with all its awesome snaky features
        Window f1 = new Window();

        // Setting up the window settings
        f1.setTitle("Snake Game");
        f1.setSize(500, 500);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Adding a WindowListener to display a dialog message on window close
        f1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(f1, "Are you sure do you want to close the window?");
                if (option == JOptionPane.YES_OPTION) {
                    f1.dispose(); // Close the window
                    System.out.println("Window is closed.");
                } else {
                    System.out.println("Window is not closed.");
                }
            }
        });
    }
}
