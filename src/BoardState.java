import java.util.ArrayList;

public class BoardState {
    private final int BOARD_SIZE = 8;
    private final char B = '\u25A0';
    private ArrayList<BoardState> frontier;
    private int statevalue = -1; // value of the current boardstate node
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
    
    public GamePiece[] getPieces() {
    	return pieces;
    }
    public void setPieces(GamePiece[] pieces) { copyPieces(pieces); }
    public int getStateValue() { return statevalue; }
    public void setStateValue(int statevalue) { this.statevalue = statevalue; }

    /**
     *
     * @param pieces
     */
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

    /**
     *
     */
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

    /**
     *
     * @param foxturn
     * @param maxdepth
     * @return
     */
    public GamePiece[] getNextMove(boolean foxturn, int maxdepth) {
    	int best = 0;
    	
    	
    	
    	return frontier.get(best).getPieces();
    }

    /**
     *
     * @param curboard
     * @param foxturn
     * @param curdepth
     * @param maxdepth
     * @return
     */
    private int miniMax(BoardState curboard, boolean foxturn, int curdepth, int maxdepth) {
    	int value = -1; // defalut error value

        // base case(s)
    	if (curdepth == maxdepth) {
    		return curboard.getValue(curboard);
    	} // end of if statement
        // or if end of game

    	//find board states list here
        // loop through each pieces' for the corresponding turn and add valid pieces setup to new board state in frontier
        /*
        GamePiece[] temp = new GamePiece[5];
        if (foxturn) {
                temp = GamePiece.copyGamePieces(curboard.getPieces()[0].canAIMoveTo(pieces, index = 0, BOARD_SIZE));
                for each x in temp
                    frontier.add(new BoardState(x));
        }

        *** Same will be done for goose turn, but will loop for each index with a goose piece
         */


    	//for each state in frontier
            if (foxturn) { // if the current turn relates to fox (MAX part)
                // set value to node with lowest value (recursion)
                /*
                 for each state in frontier
                    state.setStateValue(miniMax(state, !foxturn, curdepth+1, maxdepth));


                 for each state in frontier
                        find state with best value
                 */

            } else { // if the turn relates to Geese (MIN part)
                // set value to node with highest value (recursion)
            } // end of if-else statements
        // end of for loop
    	
    	return value;
    } // end of miniMax

    /**
     *
     * @param board
     * @return
     */
    private int getValue(BoardState board) {
    	int value = 0;

    	// farther from goal (fox) is negative points by a factor of 10, ex: -70, -60, ..., etc.
        // subtract distance from geese to certain spots here as well (each corner of the fox corresponds to a certain
        // goose

    	return value;
    } // end of getValue
} // end of BoardState