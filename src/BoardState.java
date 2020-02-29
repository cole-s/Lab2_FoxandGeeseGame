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
    } // end of constructor

    // Getters and Setters for the BoardState
    public GamePiece[] getPieces() {
        return pieces;
    }

    public void setPieces(GamePiece[] pieces) {
        copyPieces(pieces);
    }

    public int getStateValue() {
        return statevalue;
    }

    public void setStateValue(int statevalue) {
        this.statevalue = statevalue;
    }

    /**
     * Method copyPieces
     *
     * @param pieces copies array of GamePiece objects by value for use of multiple states
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
     * @param foxturn
     * @param maxdepth
     * @return
     */
    public GamePiece[] getNextMove(boolean foxturn, int maxdepth) {
        int bestvalue = miniMax(this, foxturn, 0, maxdepth);
        int bestindex = 0;

        for (int index = 0; index < frontier.size(); index++) {
            if (frontier.get(index).getStateValue() == bestvalue) {
                bestindex = index;
            } // end of if statement
        } // end of for loop


        return frontier.get(bestindex).getPieces();
    } // end of getNextMove

    /**
     * @param curboard
     * @param foxturn
     * @param curdepth
     * @param maxdepth
     * @return
     */
    private int miniMax(BoardState curboard, boolean foxturn, int curdepth, int maxdepth) {
        int value = -1; // default error value
        // base case(s)
        if (curdepth == maxdepth || Control.checkGameOver(curboard.getPieces()) != 0) {
            return curboard.getValue(curboard);
        } // end of if statement
        // or if end of game

        //find board states list here
        // loop through each pieces' for the corresponding turn and add valid pieces setup to new board state in frontier

        ArrayList<GamePiece[]> tempstates = new ArrayList<GamePiece[]>();
        if (foxturn) {
            tempstates = (GamePiece.copyGamePieces(curboard.getPieces())[0].canAIMoveTo(pieces, 0, BOARD_SIZE));
            for (GamePiece[] state : tempstates) {
                frontier.add(new BoardState(state));
            }

            for (int index = 0; index < frontier.size(); index++) {
                frontier.get(index).setStateValue(miniMax(frontier.get(index), !foxturn, curdepth + 1, maxdepth));
            }

            for (int index = 0; index < frontier.size(); index++) {
                if (index == 0) {
                    value = frontier.get(index).getStateValue();
                } // end of if statement

                if (value < frontier.get(index).getStateValue()) {
                    value = frontier.get(index).getStateValue();
                } // end of if statement
            }

        } else {
            for (int index = 1; index < 5; index++) {
                tempstates = (GamePiece.copyGamePieces(curboard.getPieces())[index].canAIMoveTo(pieces, index, BOARD_SIZE));
                for (GamePiece[] state : tempstates) {
                    frontier.add(new BoardState(state));
                }
            }

            for (int index = 0; index < frontier.size(); index++) {
                frontier.get(index).setStateValue(miniMax(frontier.get(index), !foxturn, curdepth + 1, maxdepth));
            }

            for (int index = 0; index < frontier.size(); index++) {
                if (index == 0) {
                    value = frontier.get(index).getStateValue();
                } // end of if statement

                if (value > frontier.get(index).getStateValue()) {
                    value = frontier.get(index).getStateValue();
                } // end of if statement
            }
        }

        return value;

    } // end of miniMax

    /**
     * @param board
     * @return
     */
    private int getValue(BoardState board) {
        int value = 0;

        int gameover = Control.checkGameOver(board.getPieces());
        if (gameover == 1) {
            value = 999;
            return value;
        } else if (gameover == 2) {
            value = -999;
            return value;
        } // end of if-else statements

        int fox = 0, go1 = 1, go2 = 2, go3 = 3, go4 = 4; // indexes for all the pieces

        value = (board.getPieces()[fox].getRowPos() * 10) - 70; // distance from goal
        // geese distance from trapping fox

        int rowtarget, coltarget, rowcurrent, colcurrent;

        rowtarget = board.getPieces()[fox].getRowPos();
        coltarget = board.getPieces()[fox].getColPos();
        rowcurrent = board.getPieces()[go1].getRowPos();
        colcurrent = board.getPieces()[go1].getColPos();
        value += getDistance(rowtarget + 1, coltarget - 1, rowcurrent, colcurrent);
        // upper left

        rowcurrent = board.getPieces()[go2].getRowPos();
        colcurrent = board.getPieces()[go2].getColPos();
        value += getDistance(rowtarget - 1, coltarget - 1, rowcurrent, colcurrent);
        // lower left

        rowcurrent = board.getPieces()[go3].getRowPos();
        colcurrent = board.getPieces()[go3].getColPos();
        value += getDistance(rowtarget - 1, coltarget + 1, rowcurrent, colcurrent);
        // lower right

        rowcurrent = board.getPieces()[go4].getRowPos();
        colcurrent = board.getPieces()[go4].getColPos();
        value += getDistance(rowtarget + 1, coltarget + 1, rowcurrent, colcurrent);
        // upper right

        return value;
    } // end of getValue

    private int getDistance(int rowtarget, int coltarget, int rowcurrent, int colcurrent) {
        int distance = -1;

        distance = (int) Math.abs(Math.sqrt(Math.pow(colcurrent - coltarget, 2) + Math.pow(rowcurrent - rowtarget, 2)));

        return distance;
    }
} // end of BoardState