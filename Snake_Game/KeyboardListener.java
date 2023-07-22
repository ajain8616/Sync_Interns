import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class KeyboardListener extends KeyAdapter {
    private boolean isPaused = false;

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if (ThreadsController.directionSnake != 2)
                    ThreadsController.directionSnake = 1;
                break;
            case KeyEvent.VK_UP:
                if (ThreadsController.directionSnake != 4)
                    ThreadsController.directionSnake = 3;
                break;
            case KeyEvent.VK_LEFT:
                if (ThreadsController.directionSnake != 1)
                    ThreadsController.directionSnake = 2;
                break;
            case KeyEvent.VK_DOWN:
                if (ThreadsController.directionSnake != 3)
                    ThreadsController.directionSnake = 4;
                break;
            case KeyEvent.VK_SPACE: // Change to VK_SPACE for the Space bar key
                if (isPaused) {
                    resumeGame();
                } else {
                    pauseGame();
                }
                break;
            default:
                break;
        }
    }

    private void pauseGame() {
        ThreadsController.pauseGame(); // Call the static pauseGame method in ThreadsController class
        JOptionPane.showMessageDialog(null, "Game Paused While you click on Space Bar Key", "Pause", JOptionPane.INFORMATION_MESSAGE);
        isPaused = true;
    }

    private void resumeGame() {
        ThreadsController.resumeGame(); // Call the static resumeGame method in ThreadsController class
        JOptionPane.showMessageDialog(null, "Game Resumed using click on Space Bar Key", "Resume", JOptionPane.INFORMATION_MESSAGE);
        isPaused = false;
    }
}
