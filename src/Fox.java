import java.util.ArrayList;

public class Fox extends GamePiece {
	private final String MOVE_FOWLEFT = "1. move forward left";
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
	public ArrayList<String> canMoveTo(GamePiece[] pieces, int index, int boardsize) {
		ArrayList<String> movelist = new ArrayList<String>();

		// private boolean methods here to figure out available moves for Fox
		if (isFowLeftOpen(pieces, index, boardsize)) {
			movelist.add(MOVE_FOWLEFT);
		}

		if (isFowRightOpen(pieces, index, boardsize)) {
			movelist.add(MOVE_FOWRIGHT);
		}

		if (isBackLeftOpen(pieces, index, boardsize)) {
			movelist.add(MOVE_BACKLEFT);
		}

		if (isBackRightOpen(pieces, index, boardsize)) {
			movelist.add(MOVE_BACKRIGHT);
		}

		return movelist;
	} // end of canMoveTo method

	private boolean isFowLeftOpen(GamePiece[] pieces, int sameindex, int boardsize) {
		if (pieces[sameindex].getColPos() == 0 || pieces[sameindex].getRowPos() == boardsize - 1) {
			return false;
		} // end of if statement

		for (int index = 0; index < pieces.length; index++) {
			if (index != sameindex) {
				if (pieces[index].getColPos() == pieces[sameindex].getColPos() - 1
						&& pieces[index].getRowPos() == pieces[sameindex].getRowPos() + 1) {
					return false;
				} // end of if statement
			} // end of if statement
		} // end of for loop

		return true;
	} // end of isFowLeftOpen method

	private boolean isFowRightOpen(GamePiece[] pieces, int sameindex, int boardsize) {
		if (pieces[sameindex].getRowPos() == boardsize - 1 || pieces[sameindex].getColPos() == boardsize - 1) {
			return false;
		} // end of if statement

		for (int index = 0; index < pieces.length; index++) {
			if (index != sameindex) {
				if (pieces[index].getColPos() == pieces[sameindex].getColPos() + 1
						&& pieces[index].getRowPos() == pieces[sameindex].getRowPos() + 1) {
					return false;
				} // end of if statement
			} // end of if statement
		} // end of for loop

		return true;
	} // end of isFowRightOpen method

	private boolean isBackLeftOpen(GamePiece[] pieces, int sameindex, int boardsize) {
		if (pieces[sameindex].getRowPos() == 0 || pieces[sameindex].getColPos() == 0) {
			return false;
		} // end of if statement

		for (int index = 0; index < pieces.length; index++) {
			if (index != sameindex) {
				if (pieces[index].getColPos() == pieces[sameindex].getColPos() - 1
						&& pieces[index].getRowPos() == pieces[sameindex].getRowPos() - 1) {
					return false;
				} // end of if statement
			} // end of if statement
		} // end of for loop

		return true;
	} // end of isBackLeftOpen method

	private boolean isBackRightOpen(GamePiece[] pieces, int sameindex, int boardsize) {
		if (pieces[sameindex].getColPos() == boardsize - 1 || pieces[sameindex].getRowPos() == 0) {
			return false;
		} // end of if statement

		for (int index = 0; index < pieces.length; index++) {
			if (index != sameindex) {
				if (pieces[index].getColPos() == pieces[sameindex].getColPos() + 1
						&& pieces[index].getRowPos() == pieces[sameindex].getRowPos() - 1) {
					return false;
				} // end of if statement
			} // end of if statement
		} // end of for loop

		return true;
	} // end of isBackRightOpen method

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
		default:
			System.out.println("Very Bad Fox Move Happened");
			break;
		}
	}

	private void moveFowLeft() {
		this.setRowPos(this.getRowPos() + 1);
		this.setColPos(this.getColPos() - 1);
	}

	private void moveFowRight() {
		this.setRowPos(this.getRowPos() + 1);
		this.setColPos(this.getColPos() + 1);
	}

	private void moveBackLeft() {
		this.setRowPos(this.getRowPos() - 1);
		this.setColPos(this.getColPos() - 1);
	}

	private void moveBackRight() {
		this.setRowPos(this.getRowPos() - 1);
		this.setColPos(this.getColPos() + 1);
	}

	public ArrayList<GamePiece[]> canAIMoveTo(GamePiece[] pieces, int index, int boardsize) {
		ArrayList<GamePiece[]> piecelist = new ArrayList<GamePiece[]>();

		// private boolean methods here to figure out available moves for Fox
		if (isFowLeftOpen(pieces, index, boardsize)) {
			GamePiece[] temppieces = GamePiece.copyGamePieces(pieces);
			((Fox) temppieces[0]).moveFowLeft();
			
			piecelist.add(temppieces);
		}

		if (isFowRightOpen(pieces, index, boardsize)) {
			GamePiece[] temppieces = GamePiece.copyGamePieces(pieces);
			((Fox) temppieces[0]).moveFowRight();
			
			piecelist.add(temppieces);
		}

		if (isBackLeftOpen(pieces, index, boardsize)) {
			GamePiece[] temppieces = GamePiece.copyGamePieces(pieces);			
			((Fox) temppieces[0]).moveBackLeft();
			
			piecelist.add(temppieces);
		}

		if (isBackRightOpen(pieces, index, boardsize)) {
			GamePiece[] temppieces = GamePiece.copyGamePieces(pieces);
			((Fox) temppieces[0]).moveBackRight();
			
			piecelist.add(temppieces);
		}

		return piecelist;
	}
} // end of Fox class
