import java.util.ArrayList;
import java.awt.Color;

public class DataOfSquare {
	ArrayList<Color> colors =new ArrayList<Color>();
	int color; //2: snake , 1: food, 0:empty 
	SquarePanel square;
	public DataOfSquare(int col){
		colors.add(Color.RED);//0
		colors.add(Color.YELLOW);    //1
		colors.add(Color.DARK_GRAY);   //2
		color=col;
		square = new SquarePanel(colors.get(color));
	}
	public void lightMeUp(int c){
		square.ChangeColor(colors.get(c));
	}
}
