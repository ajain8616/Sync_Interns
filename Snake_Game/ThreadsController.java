import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ThreadsController extends Thread {
    ArrayList<ArrayList<DataOfSquare>> Squares = new ArrayList<ArrayList<DataOfSquare>>();
    Tuple headSnakePos;
    int sizeSnake = 3;
    long speed = 50;
    public static int directionSnake;
    ArrayList<Tuple> positions = new ArrayList<Tuple>();
    Tuple foodPosition;
    int score = 0;
    private ArrayList<Integer> highScores;
    private int totalScore = 0;
    private boolean isPaused = false;

    ThreadsController(Tuple positionDepart) {
        Squares = Window.Grid;

        headSnakePos = new Tuple(positionDepart.x, positionDepart.y);
        directionSnake = 2;

        Tuple headPos = new Tuple(headSnakePos.getX(), headSnakePos.getY());
        positions.add(headPos);

        foodPosition = new Tuple(Window.height - 1, Window.width - 1);
        spawnFood(foodPosition);

        highScores = new ArrayList<Integer>();
    }
    public void run() {
        while (true) {
            if (!isPaused) {
                moveInterne(directionSnake);
                checkCollision();
                moveExterne();
                deleteTail();
            }
            pauser();
        }
    }
    private void pauser() {
        try {
            sleep(speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void checkCollision() {
        Tuple posCritique = positions.get(positions.size() - 1);
        for (int i = 0; i <= positions.size() - 2; i++) {
            boolean biteItself = posCritique.getX() == positions.get(i).getX()
                    && posCritique.getY() == positions.get(i).getY();
            if (biteItself) {
                stopTheGame();
            }
        }
        boolean eatingFood = posCritique.getX() == foodPosition.y && posCritique.getY() == foodPosition.x;
        if (eatingFood) {
            System.out.println("Yummy!");
            sizeSnake = sizeSnake + 1;
            score++;
            totalScore += score;
            foodPosition = getValAleaNotInSnake();
            spawnFood(foodPosition);
            displayScore();
        }
    }
    private void stopTheGame() {
        if (score > getHighestScore()) {
            setHighestScore(score);
            JOptionPane.showMessageDialog(null, "New High Score: " + getHighestScore());
        }
        JOptionPane.showMessageDialog(null, "Game Over!\nTotal Score: " + totalScore);
        System.out.println("Game Over!\nTotal Score: " + totalScore + "\n");
        highScores.add(totalScore);
        printHighScores();
        while (true) {
            pauser();
        }
    }
    private void spawnFood(Tuple foodPositionIn) {
        Squares.get(foodPositionIn.x).get(foodPositionIn.y).lightMeUp(1);
    }
    private Tuple getValAleaNotInSnake() {
        Tuple p;
        int ranX = 0 + (int) (Math.random() * 19);
        int ranY = 0 + (int) (Math.random() * 19);
        p = new Tuple(ranX, ranY);
        for (int i = 0; i <= positions.size() - 1; i++) {
            if (p.getY() == positions.get(i).getX() && p.getX() == positions.get(i).getY()) {
                ranX = 0 + (int) (Math.random() * 19);
                ranY = 0 + (int) (Math.random() * 19);
                p = new Tuple(ranX, ranY);
                i = 0;
            }
        }
        return p;
    }
    private void moveInterne(int dir) {
        switch (dir) {
            case 4:
                headSnakePos.ChangeData(headSnakePos.x, (headSnakePos.y + 1) % 20);
                positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
                break;
            case 3:
                if (headSnakePos.y - 1 < 0) {
                    headSnakePos.ChangeData(headSnakePos.x, 19);
                } else {
                    headSnakePos.ChangeData(headSnakePos.x, Math.abs(headSnakePos.y - 1) % 20);
                }
                positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
                break;
            case 2:
                if (headSnakePos.x - 1 < 0) {
                    headSnakePos.ChangeData(19, headSnakePos.y);
                } else {
                    headSnakePos.ChangeData(Math.abs(headSnakePos.x - 1) % 20, headSnakePos.y);
                }
                positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
                break;
            case 1:
                headSnakePos.ChangeData(Math.abs(headSnakePos.x + 1) % 20, headSnakePos.y);
                positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
                break;
        }
    }
    private void moveExterne() {
        for (Tuple t : positions) {
            int y = t.getX();
            int x = t.getY();
            Squares.get(x).get(y).lightMeUp(0);
        }
    }
    private void deleteTail() {
        int cmpt = sizeSnake;
        for (int i = positions.size() - 1; i >= 0; i--) {
            if (cmpt == 0) {
                Tuple t = positions.get(i);
                Squares.get(t.y).get(t.x).lightMeUp(2);
            } else {
                cmpt--;
            }
        }
        cmpt = sizeSnake;
        for (int i = positions.size() - 1; i >= 0; i--) {
            if (cmpt == 0) {
                positions.remove(i);
            } else {
                cmpt--;
            }
        }
    }
    private void displayScore() {
        System.out.println("Score: " + score);
    }
    public int getHighestScore() {
        if (highScores.isEmpty()) {
            return 0;
        } else {
            return highScores.get(0);  
        }
    }
    public void setHighestScore(int score) {
        highScores.clear();
        JOptionPane.showMessageDialog(null, "High Score: " + score);
        highScores.add(score);
    }
    public void printHighScores() {
        for (int score : highScores) {
            System.out.println("Total High Score : " + score);
        }
    }
    public static void pauseGame() {
        directionSnake = 0;
    }
    public static void resumeGame() {
        directionSnake = 1;
    }
}
