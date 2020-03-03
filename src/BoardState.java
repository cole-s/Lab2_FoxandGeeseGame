import java.util.ArrayList;
import java.util.Random;

/**
 * Class: BoardState
 * Purpose: Keeps track of state of the board (either current or looking forward) for the AI to keep track of optimal
 *          moves.
 * @author Cole Schaar
 * @author Phillip N
 */
public class BoardState {
    private final int BOARD_SIZE = 8; // board size by default
    private final char B = '\u25A0'; // character for black tiles
    private ArrayList<BoardState> frontier;
    private int statevalue = -1; // value of the current boardstate node
    private GamePiece[] pieces = new GamePiece[5]; // array that holds all the game pieces for the state
    // current board is used for printing out the current board state when asked to be printed
    private char[][] currentBoard = {
            {B, ' ', B, ' ', B, ' ', B, ' '},
            {' ', B, ' ', B, ' ', B, ' ', B},
            {B, ' ', B, ' ', B, ' ', B, ' '},
            {' ', B, ' ', B, ' ', B, ' ', B},
            {B, ' ', B, ' ', B, ' ', B, ' '},
            {' ', B, ' ', B, ' ', B, ' ', B},
            {B, ' ', B, ' ', B, ' ', B, ' '},
            {' ', B, ' ', B, ' ', B, ' ', B}};

    /**
     * Method: Constructor - BoardState
     * @param pieces - array of the game pieces currently in play
     *
     * Constructor for the BoardState object
     */
    public BoardState(GamePiece[] pieces) {
        frontier = new ArrayList<BoardState>(); // creates new frontier node list
        copyPieces(pieces); // added pieces to current object by value
    } // end of constructor


    // Getters and Setters for the BoardState
    /**
     * Method: getFrontier
     * @return ArrayList of BoardState nodes
     *
     * returns the private variable frontier from the object BoardState
     */
    private ArrayList<BoardState> getFrontier() { return this.frontier; }

    /**
     * Method: getPieces
     * @return GamePieces[] - list of all currently in use pieces of the BoardState object
     *
     * returns the private variable pieces from the object BoardState
     */
    public GamePiece[] getPieces() { return pieces; }

    /**
     * Method: setPieces
     * @param pieces - GamePiece[] - list of current game pieces used in the BoardState Object
     *
     * copies the given GamePiece array by value into the BoardState's private GamePiece[]
     */
    public void setPieces(GamePiece[] pieces) { copyPieces(pieces); }

    /**
     * Method: getStateValue
     * @return int - value of the BoardState of the BoardState object
     *
     * returns the private variable statevalue from the object BoardState
     */
    public int getStateValue() { return statevalue; }

    /**
     * Method: setStateValue
     * @param statevalue - value of the board BoardState currenly holds
     *
     * sets the private variable statevalue to a new number
     */
    public void setStateValue(int statevalue) { this.statevalue = statevalue; }
// End of getters and setters

    /**
     * Method: copyPieces     *
     * @param pieces - array of GamePieces to be copied from
     *
     * copies array of GamePiece objects by value for use of multiple states
     */
    private void copyPieces(GamePiece[] pieces) {
        for (int index = 0; index < this.pieces.length; index++) { // goes through entire given GamePiece array
            if (index == 0) { // if the fox piece
                this.pieces[index] = new Fox(pieces[index].getRowPos(), pieces[index].getColPos(), pieces[index].getName());
            } else { // if a goose piece
                this.pieces[index] = new Goose(pieces[index].getRowPos(), pieces[index].getColPos(),
                        pieces[index].getName(), ((Goose) pieces[index]).getGooseNum());
            } // end of if-else statements

            // sets up the currentboard variable to be ready for printing out the current board state
            this.currentBoard[this.pieces[index].getRowPos()][this.pieces[index].getColPos()] = this.pieces[index].getName();
        } // end of for loop
    } // end of copyPieces method

    /**
     * Method: printBoard
     *
     * prints out the current BoardState with location of GamePiece objects in pieces array
     */
    public void printBoard() {
        // printed in this fashion for easier grid location when debugging
        for (int row = BOARD_SIZE - 1; row >= 0; row--) {
            System.out.println("\n---------------------------------"); // for presentation of board
            System.out.print("| "); // for presentation of board
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print((currentBoard[row][col]) + " | ");
            } // end of for loop
        } // end of for loop
        System.out.println("\n---------------------------------");
    } // end of printBoard Method

    /**
     * Method: getNextMove
     * @param foxturn - boolean - is it the fox's turn
     * @param maxdepth - int - how far down does the program look
     * @return GamePiece[] - array of optimal next move state of GamePiece objects
     *
     * Method to be called from Control to get the next move for the AI while looking ahead
     */
    public GamePiece[] getNextMove(boolean foxturn, int maxdepth) {
        int bestvalue = miniMax(this, foxturn, 1, maxdepth); // calls recursive algorithm miniMax
        int bestindex = 0; // index in frontier ArrayList of next move to make

        // if a win condition is found form miniMax method
        if (bestvalue == 999 && foxturn) {
            for (int index = 0; index < this.getFrontier().size(); index++) {
                if ((this.getFrontier().get(index).pieces[0].getRowPos() == 7)) {
                    bestindex = index;
                    return this.getFrontier().get(bestindex).getPieces();
                } // end of if statement
            } // end of for loop
        } else if (bestvalue == -999 && !foxturn) {
            for (int index = 0; index < this.getFrontier().size(); index++) {
            	if (this.getFrontier().get(index).pieces[0].canMoveTo(pieces, 0, BOARD_SIZE).isEmpty()) {
            		bestindex = index;
                    return this.getFrontier().get(bestindex).getPieces();
                } // end of if statement
            } // end of for loop
        } // end of if-else statements

        // goes through entire frontier to find best move
        for (int index = 0; index < this.getFrontier().size(); index++) {
            if ((this.getFrontier().get(index).getStateValue() == bestvalue)) {
                bestindex = index;
            } // end of if statement
        } // end of for loop

        return this.getFrontier().size() != 0? this.getFrontier().get(bestindex).getPieces() : getRandomMove(foxturn);
    } // end of getNextMove

    private GamePiece[] getRandomMove(boolean foxturn){
        ArrayList<GamePiece[]> temp = new ArrayList<>();
        if(foxturn){
            temp = GamePiece.copyGamePieces(this.getPieces())[0].canAIMoveTo(this.getPieces(), 0, BOARD_SIZE);
            for (GamePiece[] state : temp) {
                this.getFrontier().add(new BoardState(state));
            } // end of for loop
        } else {
            for (int index = 1; index < 5; index++) { // goes through each move possible for each goose GamePiece object
                temp = (GamePiece.copyGamePieces(this.getPieces())[index].canAIMoveTo(this.getPieces(), index, BOARD_SIZE));
                for (GamePiece[] state : temp) {
                    this.getFrontier().add(new BoardState(state));
                } // end of for loop
            } // end of for loop
        }
        Random rand = new Random(System.nanoTime());
        int random = rand.nextInt(this.getFrontier().size());

        return this.getFrontier().get(random).getPieces();
    }

    /**
     * Method: miniMax
     * @param curboard - BoardState - current BoardState being examined for best valued move from state
     * @param foxturn - boolean - is it the fox's turn
     * @param curdepth - int - current depth looked into
     * @param maxdepth - int - maximum depth decided by player
     * @return int - best value of the optimal move the AI should make
     *
     * Goes through each move possible from current board state to find best course of action from a given state based
     * on current player
     */
    private int miniMax(BoardState curboard, boolean foxturn, int curdepth, int maxdepth) {
        int value = -1; // default error value

        // base case(s)
        if (curdepth >= maxdepth || Control.checkGameOver(curboard.getPieces()) != 0) {
            return curboard.getValue(curboard);
        } // end of if statement
        // or if end of game

        //find board states list here
        // loop through each pieces' for the corresponding turn and add valid pieces setup to new board state in frontier

        // temp array before adding to frontier to ensure of all possible GamePiece object setups that could be created
        ArrayList<GamePiece[]> tempstates = new ArrayList<GamePiece[]>();

        // if fox's turn
        if (foxturn) {
            // gets list of all possible moves from current board state
            tempstates = (GamePiece.copyGamePieces(curboard.getPieces())[0].canAIMoveTo(curboard.getPieces(), 0, BOARD_SIZE));

            // adds each possible board state to frontier node ArrayList
            for (GamePiece[] state : tempstates) {
                curboard.getFrontier().add(new BoardState(state));
            } // end of for loop

            // recursive call to get the value of the next board states based on moves previously found
            for (int index = 0; index < curboard.getFrontier().size(); index++) {
                curboard.getFrontier().get(index).setStateValue(miniMax(curboard.getFrontier().get(index), !foxturn, curdepth + 1, maxdepth));
            } // end of foor loop

            // get best value for the fox player's turn based on best moves of next states
            for (int index = 0; index < curboard.getFrontier().size(); index++) {
                if (index == 0) {
                    value = curboard.getFrontier().get(index).getStateValue();
                } // end of if statement

                if (value < curboard.getFrontier().get(index).getStateValue()) {
                    value = curboard.getFrontier().get(index).getStateValue();
                } // end of if statement
            } // end of for loop
        } else { // Geese Player's turn
            for (int index = 1; index < 5; index++) { // goes through each move possible for each goose GamePiece object
                tempstates = (GamePiece.copyGamePieces(curboard.getPieces())[index].canAIMoveTo(curboard.getPieces(), index, BOARD_SIZE));
                for (GamePiece[] state : tempstates) {
                    curboard.getFrontier().add(new BoardState(state));
                } // end of for loop
            } // end of for loop

            // recursive call for each state made from current node to get value for each state
            for (int index = 0; index < curboard.getFrontier().size(); index++) {
                curboard.getFrontier().get(index).setStateValue(miniMax(curboard.getFrontier().get(index), !foxturn, curdepth + 1, maxdepth));
            } // end of for loop

            // gets best value for the player based on board state
            for (int index = 0; index < curboard.getFrontier().size(); index++) {
                if (index == 0) {
                    value = curboard.getFrontier().get(index).getStateValue();
                } // end of if statement

                if (value > curboard.getFrontier().get(index).getStateValue()) {
                    value = curboard.getFrontier().get(index).getStateValue();
                } // end of if statement
            } // end of for loop
        } // end of if-else statements

        return value;
    } // end of miniMax

    /**
     * Method: getValue
     * @param board - BoardState - current BoardState of the game for value to be calculated
     * @return int - value of the current BoardState object based on heuristic algorithm
     *
     * Gets the value of the current board state given and calculates it to be used to determine best possible move for
     * A.I. Fox goes for largers value, while the geese try to get the lowest possible value
     */
    private int getValue(BoardState board) {
        int value = 0; // value to be set

        int gameover = Control.checkGameOver(board.getPieces()); // is this a win condition
        if (gameover == 1) { // if fox wins
            value = 999;
            return value;
        } else if (gameover == 2) { // if geese win
            value = -999;
            return value;
        } // end of if-else statements

        int fox = 0, go1 = 1, go2 = 2, go3 = 3, go4 = 4; // indexes for all the pieces

        value = (board.getPieces()[fox].getRowPos() * 100) - 700; // distance from goal

        // geese distance from trapping fox
        int rowtarget, coltarget, rowcurrent, colcurrent;

        rowtarget = board.getPieces()[fox].getRowPos();
        coltarget = board.getPieces()[fox].getColPos();
        rowcurrent = board.getPieces()[go1].getRowPos();
        colcurrent = board.getPieces()[go1].getColPos();
        value -= getDistance(rowtarget + 1, coltarget - 1, rowcurrent, colcurrent);
        // upper left of fox

        rowcurrent = board.getPieces()[go2].getRowPos();
        colcurrent = board.getPieces()[go2].getColPos();
        value -= getDistance(rowtarget - 1, coltarget - 1, rowcurrent, colcurrent);
        // lower left of fox

        rowcurrent = board.getPieces()[go3].getRowPos();
        colcurrent = board.getPieces()[go3].getColPos();
        value -= getDistance(rowtarget - 1, coltarget + 1, rowcurrent, colcurrent);
        // lower right of fox

        rowcurrent = board.getPieces()[go4].getRowPos();
        colcurrent = board.getPieces()[go4].getColPos();
        value -= getDistance(rowtarget + 1, coltarget + 1, rowcurrent, colcurrent);
        // upper right of fox

        return value;
    } // end of getValue

    /**
     * Method: getDistance
     *
     * @param rowtarget - int - which row piece wants to be in
     * @param coltarget - int - which column piece wants to be in
     * @param rowcurrent - int - current row piece is in
     * @param colcurrent - int - current column piece is in
     * @return int - distance from desired location
     *
     * using currently known location and known target location a distance is calculated from desired location
     */
    private int getDistance(int rowtarget, int coltarget, int rowcurrent, int colcurrent) {
        int distance = -1; // error/defalut value

        // calculate distance for desired location
        distance = (int) Math.abs(Math.sqrt(Math.pow(colcurrent - coltarget, 2) + Math.pow(rowcurrent - rowtarget, 2)));

        return distance;
    } // end of getDistance method
} // end of BoardState Class