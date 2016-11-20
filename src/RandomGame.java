/*****************************************************************************************************************************/
/* AUTHOR: STEVEN BISHOP 																									 */
/* PROGRAM: Plays a random game of tic tac toe and stops when someone has won												 */
/* PURPOSE: Create an AI that will pay tic tac toe against a user															 */
/*****************************************************************************************************************************/

import java.awt.Graphics;


// This will run a random game that will stop once either X or O has won
public class RandomGame {

	public static final int SIZE = 200;  // SIZE will change the length of the sides of each box
	public static final int LENGTH = 3;  // LENGTH is the number of boxes per side

	public static void main(String[] args) {

		DrawingPanel board = new DrawingPanel(SIZE * LENGTH, SIZE * LENGTH);  // Construct a DrawingPanel object for the game
		Graphics g = board.getGraphics();  // Initialize the graphics
		setUp(g);  // Draws the lines on the board
		int[][] squares = new int[LENGTH][LENGTH];  // Initializes an array of integers that will store the values of the plays
		int player = 0;  // Prime the loop
		// This loop will play the game
		while (player < LENGTH * LENGTH && !win(squares)){  // Make sure that there is an open box and that the game hasn't been won
			playRandom(g, squares, player % 2);  // This method will draw in the box and change the value of the array
			player++;  // Change players
		}

		// For debugging
		for (int row = 0; row < LENGTH; row++){  // Traverse the rows of the array
			for (int col = 0; col < LENGTH; col++){  // Traverse the columns of the array
				System.out.print(squares[row][col] + ", ");  // Print the element of the array in that index
			}
			System.out.println();  // Go to the next line
		}

	}

	// This method will draw the lines on the DrawingPanel
	public static void setUp(Graphics g){

		// This loop draws the lines
		for (int i = 1; i < LENGTH; i++){  // Go through one fewer than LENGTH times
			g.drawLine(SIZE * i, 0, SIZE * i, SIZE * LENGTH);  // Draw a horizontal line
			g.drawLine(0, SIZE * i, SIZE * LENGTH, SIZE * i);  // Draw a vertical line
		}

	}

	// This method will play the game. It will draw on the board and will change the values of the array
	public static void playRandom(Graphics g, int[][] squares, int player){

		int xChoice = (int) (Math.random() * LENGTH);  // The x value where the player will play
		int yChoice = (int) (Math.random() * LENGTH);  // The y value where the player will play
		
		// This loop checks to make sure that square hasn't already been played in
		while (squares[yChoice][xChoice] != 0){  // While the square has been played in
			xChoice = (int) (Math.random() * LENGTH);  // Choose a new x value
			yChoice = (int) (Math.random() * LENGTH);  // Choose a new y value
		}
		
		squares[yChoice][xChoice] = player * 2 - 1;  // Assign a value of -1 for the first player and 1 for the second player
		int xCoordinate = xChoice * SIZE;  // Get the x coordinate of the left side of the box we will draw in
		int yCoordinate = yChoice * SIZE;  // Get the y coordinate of the top side of the box we will draw in
		
		// This will decide whether to draw an X or and O
		if (player == 0){  // See if it's the first player
			g.drawLine(xCoordinate, yCoordinate, xCoordinate + SIZE, yCoordinate + SIZE);  // Draws a line from the top left to the bottom right
			g.drawLine(xCoordinate + SIZE, yCoordinate, xCoordinate, yCoordinate + SIZE);  // Draws a line from the top right to the bottom left
		} else {  // If it's not the first player it will be the second player
			g.drawOval(xCoordinate, yCoordinate, SIZE, SIZE);  // Draw an oval that will start in the top left corner and fill the box
		}

	}

	// This method checks to see if the game has already been won
	public static boolean win(int[][] squares){

		boolean flag = true;  // Prime the loop

		// Check the rows of the array for both players to see if there is a victory
		for (int player = 0; player < 2; player++){  // Check for both players
			for (int row = 0; row < LENGTH; row++){  // Traverse the rows of the array
				for (int col = 0; col < LENGTH; col++){  // Traverse the columns of the array
					if (squares[row][col] != (player * 2) - 1){  // See if the square is not the value that the players holds
						flag = false;  // If one of the values is not equal to the player's value then we set it to false
					}
				}
				if (flag){  // See if it made it through the whole row without finding a square that did not contain the player's value
					return flag;  // Return true
				}
				flag = true;  // Prime the loop again for the second pass
			}
		}

		// Check the columns of the array for both players to see if there is a victory
		for (int player = 0; player < 2; player++){  // Check for both players
			for (int col = 0; col < LENGTH; col++){  // Traverse the columns of the array
				for (int row = 0; row < LENGTH; row++){  // Traverse the rows of the array
					if (squares[row][col] != (player * 2) - 1){  // See if the square is not the value that the players holds
						flag = false;  // If one of the values is not equal to the player's value then we set it to false
					}
				}
				if (flag){  // See if it made it through the whole row without finding a square that did not contain the player's value
					return flag;  // Return true
				}
				flag = true;  // Prime the loop again for the second pass
			}
		}

		// Check the top left to bottom right diagonal of the array for both players to see if there is a victory
		for (int player = 0; player < 2; player++){ // Check for both players
			for (int i = 0; i < LENGTH; i++){  // We only do this once this time because we only need to check one set of values
				if (squares[i][i] != (player * 2) - 1){  // See if the square is not the value that the players holds
					flag = false;  // If one of the values is not equal to the player's value then we set it to false
				}
			}
			if (flag){  // See if it made it through the whole row without finding a square that did not contain the player's value
				return flag;  // Return true
			}
			flag = true;  // Prime the loop again for the second pass
		}
		
		// Check the top left to bottom right diagonal of the array for both players to see if there is a victory
		for (int player = 0; player < 2; player++){ // Check for both players
			for (int i = 0; i < LENGTH; i++){  // We only do this once this time because we only need to check one set of values
				if (squares[LENGTH - 1 - i][i] != (player * 2) - 1){  // See if the square is not the value that the players holds
					flag = false;  // If one of the values is not equal to the player's value then we set it to false
				}
			}
			if (flag){  // See if it made it through the whole row without finding a square that did not contain the player's value
				return flag;  // Return true
			}
			flag = true;  // Prime the loop again for the second pass
		}

		return false;  // If we have not returned anything yet there has not been a victory

	}

}
