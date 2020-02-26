import java.util.ArrayList;

public class Goose extends GamePiece {
	private final String MOVE_DOWNLEFT = "move down left";
	private final String MOVE_DOWNRIGHT = "move down right";
	private int goosenum;

	public Goose() {
		super();
		goosenum = 0;
	} // end of constructor

	public Goose(int xpos, int ypos, char name, int goosenum) {
		super(xpos, ypos, name);
		this.goosenum = goosenum;
	} // end of constructor

	public int getGooseNum() {
		return goosenum;
	}

	public void setGooseNum(int goosenum) {
		this.goosenum = goosenum;
	}

	@Override
	public ArrayList<String> canMoveTo(GamePiece[] pieces, int index, int boardsize) {
		ArrayList<String> movelist = new ArrayList<String>();

		// private boolean methods here to figure out available moves for goose
		if (isFowLeftOpen(pieces, index, boardsize)) {
			movelist.add("" + (this.goosenum + index - 1) + ". Goose " + this.goosenum + " " + MOVE_DOWNLEFT);
		}

		if (isFowRightOpen(pieces, index, boardsize)) {
			movelist.add("" + (this.goosenum + index) + ". Goose " + this.goosenum + " " + MOVE_DOWNRIGHT);
		}

		return movelist;
	} // end of canMoveTo method

	private boolean isFowLeftOpen(GamePiece[] pieces, int sameindex, int boardsize) {
		if (pieces[sameindex].getRowPos() == 0 || pieces[sameindex].getColPos() == 0) {
			return false;
		} // end of if statement

		for (int index = 0; index < pieces.length; index++) {
			if (index != sameindex) {
				if (pieces[index].getRowPos() == pieces[sameindex].getRowPos() - 1
						&& pieces[index].getColPos() == pieces[sameindex].getColPos() - 1) {
					return false;
				} // end of if statement
			} // end of if statement
		} // end of for loop

		return true;
	} // end of isFowLeftOpen method

	private boolean isFowRightOpen(GamePiece[] pieces, int sameindex, int boardsize) {
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
	}// end of isFowRightOpen method

	public void movePieceTo(String input) {
		int goosemove = Integer.parseInt(input);

		switch (goosemove) {
		case 1:
			moveFowLeft();
			break;
		case 2:
			moveFowRight();
			break;
		default:
			System.out.println("Very Bad Goose Move Happened");
			break;
		}
	}

	private void moveFowLeft() {
		this.setRowPos(this.getRowPos() - 1);
		this.setColPos(this.getColPos() - 1);
	}

	private void moveFowRight() {
		this.setRowPos(this.getRowPos() - 1);
		this.setColPos(this.getColPos() + 1);
	}
	
	public ArrayList<GamePiece[]> canAIMoveTo(GamePiece[] pieces, int index, int boardsize) {
		ArrayList<GamePiece[]> piecelist = new ArrayList<GamePiece[]>();

		// private boolean methods here to figure out available moves for Fox
		if (isFowLeftOpen(pieces, index, boardsize)) {
			GamePiece[] temppieces = GamePiece.copyGamePieces(pieces);
			((Goose) temppieces[index]).moveFowLeft();
			
			piecelist.add(temppieces);
		}

		if (isFowRightOpen(pieces, index, boardsize)) {
			GamePiece[] temppieces = GamePiece.copyGamePieces(pieces);
			((Goose) temppieces[index]).moveFowRight();
			
			piecelist.add(temppieces);
		}

		return piecelist;
	}
} // end of Goose class
