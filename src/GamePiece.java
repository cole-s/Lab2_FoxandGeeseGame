import java.util.ArrayList;

/**
 * Class: GamePiece
 * Purpose: to hold the information on the current location of a game piece and character to represent it
 * 			This class holds many placeholder methods to be overridden by children classes
 *
 * @author Cole Schaar
 * @author Phillip N.
 */
public abstract class GamePiece {
	private int currow; // current row piece is located in
	private int curcol; // current column piece is located in
	private char name; // character to be used to represent the piece on the board

	/**
	 * Constructor: GamePiece
	 *
	 * gives initial values for each private variables belonging to the object
	 */
	public GamePiece() {
		currow = 0;
		curcol = 0;
		name = 'P';
	} // end of constructor

	/**
	 * Constructor: GamePiece
	 * @param row - int - row location of the piece
	 * @param col - int - column location of the piece
	 * @param name - char - symbol used to represent the piece
	 *
	 * gives initial values for each private variables belonging to the object
	 */
	public GamePiece(int row, int col, char name) {
		this.currow = row;
		this.curcol = col;
		this.name = name;
	} // end of constructor

	/**
	 * Method: canMoveTo
	 * @param pieces - GamePiece[] - array of GamePiece objects to be used for move validation
	 * @param index - int - index of the current piece being moved to avoid conflict of interest
	 * @param boardsize - int - size of the board to validate moves
	 * @return ArrayList - list of Strings to be given to user to share valid possible moves
	 *
	 * *** currently this is a placeholder for Fox and Goose children classes ***
	 */
	public ArrayList<String> canMoveTo(GamePiece[] pieces, int index, int boardsize) {
		return null;
	} // end of canMoveTo method

	/**
	 * Method: movePieceTo
	 * @param input - String - user input for which move player would like to make
	 *
	 * *** currently this is a placeholder for Fox and Goose children classes ***
	 */
	public void movePieceTo(String input) {}

	/**
	 * Method: canAIMoveTo
	 * @param pieces - GamePiece[] - array of currently in place GamePiece objects
	 * @param index - int - index of current piece to be moved
	 * @param boardsize - int - size of the board being used
	 * @return ArrayList - list of all possible moves the A.I. can make
	 *
	 * *** currently this is a placeholder for Fox and Goose children classes ***
	 */
	public ArrayList<GamePiece[]> canAIMoveTo(GamePiece[] pieces, int index, int boardsize) {
		System.out.println("canAIMoveTo error");
		return null;
	} // end of canAIMoveTo


	// series of getters and setters for GamePiece objects to grab private variables
	public int getRowPos() { return currow; }
	public void setRowPos(int row) { this.currow = row;	}
	public int getColPos() { return curcol;	}
	public void setColPos(int col) { this.curcol = col;	}
	public char getName() { return name; }
	public void setName(char name) { this.name = name; }
	// end of getters and setters

	/**
	 * Method: copyGamePieces
	 * @param src - GamePiece[] - array to be copied from
	 * @return GamePiece[] - a new array of GamePiece copied from src
	 *
	 * copies the src array by value to create a new array to avoid by reference issues when changing values for each new
	 * state of a board
	 */
	public static GamePiece[] copyGamePieces (GamePiece[] src) {
		GamePiece[] dest = new GamePiece[5];
		
		for (int index = 0; index < src.length; index++) { // go through each piece in src array
			if (index == 0) {
				dest[0] = new Fox(src[0].getRowPos(), src[0].getColPos(), 'F');
			} else {
				dest[index] = new Goose(src[index].getRowPos(), src[index].getColPos(), src[index].getName(), index);
			} // end of if-else statements
		} // end of for loop
		
		return dest;
	} // end of copyGamePieces method
} // end of GamePiece Class
