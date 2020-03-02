import java.util.ArrayList;

public class Fox extends GamePiece {
	private final String MOVE_FOWLEFT = "1. move forward left";		// strings used to print move options for player
	private final String MOVE_FOWRIGHT = "2. move forward right";
	private final String MOVE_BACKLEFT = "3. move back left";
	private final String MOVE_BACKRIGHT = "4. move back right";

	public Fox() {
		super();
	} // end of constructor

	public Fox(int xpos, int ypos, char name) {
		super(xpos, ypos, name);
	} // end of constructor

	@Override
	// Gets the list all possible available moves to print out for the user
	public ArrayList<String> canMoveTo(GamePiece[] pieces, int index, int boardsize) {
		ArrayList<String> movelist = new ArrayList<String>(); // List of the Final Strings to be used in Control.java to print out

		// If upper left can be moved to, add the MOVE_FOWLEFT string to movelist
		if (isFowLeftOpen(pieces, index, boardsize)) {
			movelist.add(MOVE_FOWLEFT);
		} // end of if statement

		// If upper right can be moved to, add the MOVE_FOWRIGHT string to movelist
		if (isFowRightOpen(pieces, index, boardsize)) {
			movelist.add(MOVE_FOWRIGHT);
		} // end of if statement

		// If lower left can be moved to, add the MOVE_BACKLEFT string to movelist
		if (isBackLeftOpen(pieces, index, boardsize)) {
			movelist.add(MOVE_BACKLEFT);
		} // end of if statement

		// If lower right can be moved to, add the MOVE_BACKRIGHT string to movelist
		if (isBackRightOpen(pieces, index, boardsize)) {
			movelist.add(MOVE_BACKRIGHT);
		} // end of if statement

		return movelist;
	} // end of canMoveTo method
	
	// Check if upper left corner of Fox can be moved to
	private boolean isFowLeftOpen(GamePiece[] pieces, int sameindex, int boardsize) {
		// Fox is not at the left edge or top edge
		if (pieces[sameindex].getColPos() == 0 || pieces[sameindex].getRowPos() == boardsize - 1) {
			return false;
		} // end of if statement

		for (int index = 0; index < pieces.length; index++) {
			if (index != sameindex) {
				// If there is a Goose in the upper left corner of Fox, cannot move
				if (pieces[index].getColPos() == pieces[sameindex].getColPos() - 1
						&& pieces[index].getRowPos() == pieces[sameindex].getRowPos() + 1) {
					return false;
				} // end of if statement
			} // end of if statement
		} // end of for loop

		return true; // Upper left corner of Fox is a move option
	} // end of isFowLeftOpen method

	// Check if upper right corner of Fox can be moved to
	private boolean isFowRightOpen(GamePiece[] pieces, int sameindex, int boardsize) {
		// Fox is not at the right edge or top edge
		if (pieces[sameindex].getRowPos() == boardsize - 1 || pieces[sameindex].getColPos() == boardsize - 1) {
			return false;
		} // end of if statement

		for (int index = 0; index < pieces.length; index++) {
			if (index != sameindex) {
				// If there is a Goose in the upper right corner of Fox, cannot move
				if (pieces[index].getColPos() == pieces[sameindex].getColPos() + 1
						&& pieces[index].getRowPos() == pieces[sameindex].getRowPos() + 1) {
					return false;
				} // end of if statement
			} // end of if statement
		} // end of for loop

		return true; // Upper right corner of Fox is a move option
	} // end of isFowRightOpen method

	// Check if lower left corner of Fox can be moved to
	private boolean isBackLeftOpen(GamePiece[] pieces, int sameindex, int boardsize) {
		// Fox is not at the left edge or bottom edge
		if (pieces[sameindex].getRowPos() == 0 || pieces[sameindex].getColPos() == 0) {
			return false;
		} // end of if statement

		for (int index = 0; index < pieces.length; index++) {
			if (index != sameindex) {
				// If there is a Goose in the lower left corner of Fox, cannot move
				if (pieces[index].getColPos() == pieces[sameindex].getColPos() - 1
						&& pieces[index].getRowPos() == pieces[sameindex].getRowPos() - 1) {
					return false;
				} // end of if statement
			} // end of if statement
		} // end of for loop

		return true; // Lower left corner of Fox is a move option
	} // end of isBackLeftOpen method

	// Check if lower right corner of Fox can be moved to
	private boolean isBackRightOpen(GamePiece[] pieces, int sameindex, int boardsize) {
		// Fox is not at the right edge or bottom edge
		if (pieces[sameindex].getColPos() == boardsize - 1 || pieces[sameindex].getRowPos() == 0) {
			return false;
		} // end of if statement

		for (int index = 0; index < pieces.length; index++) {
			if (index != sameindex) {
				// If there is a Goose in the lower right corner of Fox, cannot move
				if (pieces[index].getColPos() == pieces[sameindex].getColPos() + 1
						&& pieces[index].getRowPos() == pieces[sameindex].getRowPos() - 1) {
					return false;
				} // end of if statement
			} // end of if statement
		} // end of for loop

		return true; // Lower right corner of Fox is a move option
	} // end of isBackRightOpen method

	// Takes the converted player input and calls the respective move methods to move the Fox
	public void movePieceTo(String input) {
		switch (input) {
		case "1":
			moveFowLeft();
			break;
		case "2":
			moveFowRight();
			break;
		case "3":
			moveBackLeft();
			break;
		case "4":
			moveBackRight();
			break;
		default: // in the event some rare error happens
			System.out.println("Very Bad Fox Move Happened");
			break;
		} // end of switch statement
	}  // end of movePieceTo

	// Moves the fox by changing starting coordinates to the upper left coordinates
	private void moveFowLeft() {
		this.setRowPos(this.getRowPos() + 1);
		this.setColPos(this.getColPos() - 1);
	} // end of moveFowLeft

	// Moves the fox by changing starting coordinates to the upper right coordinates
	private void moveFowRight() {
		this.setRowPos(this.getRowPos() + 1);
		this.setColPos(this.getColPos() + 1);
	} // end of moveFowRight

	// Moves the fox by changing starting coordinates to the lower left coordinates
	private void moveBackLeft() {
		this.setRowPos(this.getRowPos() - 1);
		this.setColPos(this.getColPos() - 1);
	} // end of moveBackLeft

	// Moves the fox by changing starting coordinates to the lower right coordinates
	private void moveBackRight() {
		this.setRowPos(this.getRowPos() - 1);
		this.setColPos(this.getColPos() + 1);
	} // end of moveBackRight

	
	// Gets all possible board states doing all of the Fox's available moves for the A.I. to use
	public ArrayList<GamePiece[]> canAIMoveTo(GamePiece[] pieces, int index, int boardsize) {
		ArrayList<GamePiece[]> piecelist = new ArrayList<GamePiece[]>(); // list of all board states from the fox moving

		// If fox can move to the upper left, create a copy of the game pieces, do the move, add new board state to piecelist
		if (isFowLeftOpen(pieces, index, boardsize)) {
			GamePiece[] temppieces = GamePiece.copyGamePieces(pieces);
			((Fox) temppieces[0]).moveFowLeft();
			
			piecelist.add(temppieces);
		} // end of if statement

		// If fox can move to the upper right, create a copy of the game pieces, do the move, add new board state to piecelist
		if (isFowRightOpen(pieces, index, boardsize)) {
			GamePiece[] temppieces = GamePiece.copyGamePieces(pieces);
			((Fox) temppieces[0]).moveFowRight();
			
			piecelist.add(temppieces);
		} // end of if statement

		// If fox can move to the lower left, create a copy of the game pieces, do the move, add new board state to piecelist
		if (isBackLeftOpen(pieces, index, boardsize)) {
			GamePiece[] temppieces = GamePiece.copyGamePieces(pieces);			
			((Fox) temppieces[0]).moveBackLeft();
			
			piecelist.add(temppieces);
		} // end of if statement

		// If fox can move to the lower right, create a copy of the game pieces, do the move, add new board state to piecelist
		if (isBackRightOpen(pieces, index, boardsize)) {
			GamePiece[] temppieces = GamePiece.copyGamePieces(pieces);
			((Fox) temppieces[0]).moveBackRight();
			
			piecelist.add(temppieces);
		} // end of if statement

		return piecelist;
	} // end of canAIMoveTo
} // end of Fox class
