import java.awt.Graphics;


public class RandomGame {

	public static final int SIZE = 300;
	public static final int LENGTH = 3;

	public static void main(String[] args) {

		DrawingPanel board = new DrawingPanel(SIZE * LENGTH, SIZE * LENGTH);
		Graphics g = board.getGraphics();
		setUp(g);
		int[][] squares = new int[LENGTH][LENGTH];
		int player = 0;
		while (player < LENGTH * LENGTH && !win(squares)){
			playRandom(g, squares, player % 2);
			player++;
		}

		for (int row = 0; row < LENGTH; row++){
			for (int col = 0; col < LENGTH; col++){
				System.out.print(squares[row][col] + ", ");
			}
			System.out.println();
		}

	}

	public static void setUp(Graphics g){

		for (int i = 1; i < LENGTH; i++){
			g.drawLine(SIZE * i, 0, SIZE * i, SIZE * LENGTH);
			g.drawLine(0, SIZE * i, SIZE * LENGTH, SIZE * i);
		}

	}

	public static void playRandom(Graphics g, int[][] squares, int player){

		int xChoice = (int) (Math.random() * LENGTH);
		int yChoice = (int) (Math.random() * LENGTH);
		while (squares[yChoice][xChoice] != 0){
			xChoice = (int) (Math.random() * LENGTH);
			yChoice = (int) (Math.random() * LENGTH);
		}
		squares[yChoice][xChoice] = player * 2 - 1;
		int xCoordinate = xChoice * SIZE;
		int yCoordinate = yChoice * SIZE;
		if (player == 0){
			g.drawLine(xCoordinate, yCoordinate, xCoordinate + SIZE, yCoordinate + SIZE);
			g.drawLine(xCoordinate + SIZE, yCoordinate, xCoordinate, yCoordinate + SIZE);
		} else {
			g.drawOval(xCoordinate, yCoordinate, SIZE, SIZE);
		}

	}

	public static boolean win(int[][] squares){

		boolean flag = true;

		for (int player = 0; player < 2; player++){
			for (int row = 0; row < LENGTH; row++){
				for (int col = 0; col < LENGTH; col++){
					if (squares[row][col] != (player * 2) - 1){
						flag = false;
					}
				}
				if (flag){
					return flag;
				}
				flag = true;
			}
		}

		for (int player = 0; player < 2; player++){
			for (int col = 0; col < LENGTH; col++){
				for (int row = 0; row < LENGTH; row++){
					if (squares[row][col] != (player * 2) - 1){
						flag = false;
					}
				}
				if (flag){
					return flag;
				}
				flag = true;
			}
		}

		for (int player = 0; player < 2; player++){
			for (int i = 0; i < LENGTH; i++){
				if (squares[i][i] != (player * 2) - 1){
					flag = false;
				}
			}
			if (flag){
				return flag;
			}
			flag = true;
		}
		
		for (int player = 0; player < 2; player++){
			for (int i = 0; i < LENGTH; i++){
				if (squares[LENGTH - 1 - i][i] != (player * 2) - 1){
					flag = false;
				}
			}
			if (flag){
				return flag;
			}
			flag = true;
		}

		return false;

	}

}
