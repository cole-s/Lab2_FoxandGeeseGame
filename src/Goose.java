import java.util.ArrayList;

public class Goose extends GamePiece {
	private final String MOVE_DOWNLEFT = "move down left";		// strings used to print move options for player
	private final String MOVE_DOWNRIGHT = "move down right";
	private int goosenum;	// the goose number for this particular object

	public Goose() {
		super();
		goosenum = 0;
	} // end of constructor

	public Goose(int xpos, int ypos, char name, int goosenum) {
		super(xpos, ypos, name);
		this.goosenum = goosenum;
	} // end of constructor

	// Get the goose number for this object
	public int getGooseNum() {
		return goosenum;
	} // end of getGooseNum

	// Set the goose number for this object
	public void setGooseNum(int goosenum) {
		this.goosenum = goosenum;
	} // end of setGooseNum

	@Override
	// Gets the list all possible available moves for all geese to print out for the user
	public ArrayList<String> canMoveTo(GamePiece[] pieces, int index, int boardsize) {
		ArrayList<String> movelist = new ArrayList<String>();	// List of the strings to be used in Control.java to print out

		// If upper left can be moved to, create a readable string and add it to movelist
		if (isFowLeftOpen(pieces, index, boardsize)) {
			movelist.add("" + (this.goosenum + index - 1) + ". Goose " + this.goosenum + " " + MOVE_DOWNLEFT);
		} // end of if statement

		// If upper right can be moved to, create a readable string and add it to movelist
		if (isFowRightOpen(pieces, index, boardsize)) {
			movelist.add("" + (this.goosenum + index) + ". Goose " + this.goosenum + " " + MOVE_DOWNRIGHT);
		} // end of if statement

		return movelist;
	} // end of canMoveTo method

	// Check if lower left corner of the Goose can be moved to
	private boolean isFowLeftOpen(GamePiece[] pieces, int sameindex, int boardsize) {
		// Goose is not at the left edge or bottom edge
		if (pieces[sameindex].getRowPos() == 0 || pieces[sameindex].getColPos() == 0) {
			return false;
		} // end of if statement

		for (int index = 0; index < pieces.length; index++) {
			if (index != sameindex) {
				// If there is another Goose or Fox in the lower left corner of this Goose, cannot move
				if (pieces[index].getRowPos() == pieces[sameindex].getRowPos() - 1
						&& pieces[index].getColPos() == pieces[sameindex].getColPos() - 1) {
					return false;
				} // end of if statement
			} // end of if statement
		} // end of for loop

		return true;
	} // end of isFowLeftOpen method

	// Check if lower right corner of the Goose can be moved to
	private boolean isFowRightOpen(GamePiece[] pieces, int sameindex, int boardsize) {
		// Goose is not at the right edge or bottom edge
		if (pieces[sameindex].getColPos() == boardsize - 1 || pieces[sameindex].getRowPos() == 0) {
			return false;
		} // end of if statement

		for (int index = 0; index < pieces.length; index++) {
			if (index != sameindex) {
				// If there is another Goose or Fox in the lower right corner of this Goose, cannot move
				if (pieces[index].getColPos() == pieces[sameindex].getColPos() + 1
						&& pieces[index].getRowPos() == pieces[sameindex].getRowPos() - 1) {
					return false;
				} // end of if statement
			} // end of if statement
		} // end of for loop

		return true;
	}// end of isFowRightOpen method

	// Takes the converted player input and calls the respective move methods to move the Goose
	public void movePieceTo(String input) {
		int goosemove = Integer.parseInt(input);

		switch (goosemove) {
		case 1:
			moveFowLeft();
			break;
		case 2:
			moveFowRight();
			break;
		default: // in the event some rare error happens
			System.out.println("Very Bad Goose Move Happened");
			break;
		} // end of switch statement
	} // end of movePieceTo

	// Moves the Goose by changing starting coordinates to the lower left coordinates
	private void moveFowLeft() {
		this.setRowPos(this.getRowPos() - 1);
		this.setColPos(this.getColPos() - 1);
	} // end of moveFowLeft

	// Moves the Goose by changing starting coordinates to the lower right coordinates
	private void moveFowRight() {
		this.setRowPos(this.getRowPos() - 1);
		this.setColPos(this.getColPos() + 1);
	} // end of moveFowRight
	
	// Gets all possible board states doing all of this Goose's available moves for the A.I. to use
	public ArrayList<GamePiece[]> canAIMoveTo(GamePiece[] pieces, int index, int boardsize) {
		ArrayList<GamePiece[]> piecelist = new ArrayList<GamePiece[]>(); // list of all board states from the Goose moving

		// If Goose can move to the lower left, create a copy of the game pieces, do the move, add new board state to piecelist
		if (isFowLeftOpen(pieces, index, boardsize)) {
			GamePiece[] temppieces = GamePiece.copyGamePieces(pieces);
			((Goose) temppieces[index]).moveFowLeft();
			
			piecelist.add(temppieces);
		} // end of if statement

		// If Goose can move to the lower right, create a copy of the game pieces, do the move, add new board state to piecelist
		if (isFowRightOpen(pieces, index, boardsize)) {
			GamePiece[] temppieces = GamePiece.copyGamePieces(pieces);
			((Goose) temppieces[index]).moveFowRight();
			
			piecelist.add(temppieces);
		} // end of if statement

		return piecelist;
	} // end of canAIMoveTo
} // end of Goose class
