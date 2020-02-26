import java.util.ArrayList;

public abstract class GamePiece {
	private int currow;
	private int curcol;
	private char name;

	public GamePiece() {
		currow = 0;
		curcol = 0;
		name = 'P';
	} // end of constructor

	public GamePiece(int xpos, int ypos, char name) {
		this.currow = xpos;
		this.curcol = ypos;
		this.name = name;
	} // end of constructor

	public ArrayList<String> canMoveTo(GamePiece[] pieces, int index, int boardsize) {
		return null;
	} // end of canMoveTo method

	public void movePieceTo(String input) {

	}

	public ArrayList<GamePiece[]> canAIMoveTo(GamePiece[] pieces, int index, int boardsize) {
		System.out.println("canAIMoveTo error");
		return null;
	}
	
	public int getRowPos() {
		return currow;
	}

	public void setRowPos(int row) {
		this.currow = row;
	}

	public int getColPos() {
		return curcol;
	}

	public void setColPos(int col) {
		this.curcol = col;
	}

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}
	
	public static GamePiece[] copyGamePieces (GamePiece[] src) {
		GamePiece[] dest = new GamePiece[5];
		
		for (int index = 0; index < src.length; index++) {
			if (index == 0) {
				dest[0] = new Fox(src[0].getRowPos(), src[0].getColPos(), 'F');
			} else {
				dest[index] = new Goose(src[index].getRowPos(), src[index].getColPos(), src[index].getName(), index);
			}
		}
		
		return dest;
	}
} // end of GamePiece Class
