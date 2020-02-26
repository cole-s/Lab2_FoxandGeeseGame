import java.util.ArrayList;

public class BoardState {
    private final int BOARD_SIZE = 8;
    private final char B = '\u25A0';
    private ArrayList<BoardState> frontier;
    private GamePiece[] pieces = new GamePiece[5];
    private char[][] currentBoard = {
            {B, ' ', B, ' ', B, ' ', B, ' '},
            {' ', B, ' ', B, ' ', B, ' ', B},
            {B, ' ', B, ' ', B, ' ', B, ' '},
            {' ', B, ' ', B, ' ', B, ' ', B},
            {B, ' ', B, ' ', B, ' ', B, ' '},
            {' ', B, ' ', B, ' ', B, ' ', B},
            {B, ' ', B, ' ', B, ' ', B, ' '},
            {' ', B, ' ', B, ' ', B, ' ', B}};

    public BoardState(GamePiece[] pieces) {
        frontier = new ArrayList<BoardState>();
        copyPieces(pieces);
    }
    
    private GamePiece[] getPieces() {
    	return pieces;
    }
    
    private void copyPieces(GamePiece[] pieces) {
        for (int index = 0; index < this.pieces.length; index++) {
            if (index == 0) {
                this.pieces[index] = new Fox(pieces[index].getRowPos(), pieces[index].getColPos(), pieces[index].getName());
            } else {
                this.pieces[index] = new Goose(pieces[index].getRowPos(), pieces[index].getColPos(),
                        pieces[index].getName(), ((Goose) pieces[index]).getGooseNum());
            }

            currentBoard[this.pieces[index].getRowPos()][this.pieces[index].getColPos()] = this.pieces[index].getName();
        }
    }

    public void printBoard() {
        for (int row = BOARD_SIZE - 1; row >= 0; row--) {
            System.out.println("\n---------------------------------");
            System.out.print("| ");
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print((currentBoard[row][col]) + " | ");
            }
        }
        System.out.println("\n---------------------------------");
    }
    
    public GamePiece[] getNextMove(boolean foxturn, int maxdepth) {
    	int best = 0;
    	
    	
    	
    	return frontier.get(best).getPieces();
    }
    
    private int miniMax(BoardState curboard, boolean foxturn, int curdepth, int maxdepth) {
    	int value = -1;
    	if (curdepth == maxdepth) {
    		return curboard.getValue(curboard);
    	}
    		
    	//find board states list here
    	//go through the list
    		
    	// this if statement is under the for-loop from comment above
    	if (foxturn) {
    		//set value to node with lowest value (recursion)
    	} else {
    		//set value to node with highest value (recursion)
    	}
    	
    	return value;
    }
    
    private int getValue(BoardState board) {
    	int value = 0;
    	
    	return value;
    }
}
