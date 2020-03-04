import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class: Control
 *
 * handles all interactions with all objects and main caller for AI functionality and overall operation of the game
 * @author Cole Schaar
 * @author Phillip N.
 */
public class Control {
    private static final int BOARD_SIZE = 8; // default size for board
    private static boolean foxturn = true; // check if it's the fox's turn
    private static boolean foxplayer = true; // player is the fox

	/**
	 * Method: canMoveFox
	 * @param moves - ArrayList - list of strings with all possible moves the player can make
	 * @param input - String - the move selected by the user
	 * @param piece - GamePiece - piece on the board to be moved from given options
	 * @return boolean - if move was valid then it returns true, else false
     *
     * Looks at given list of possible moves and checks the option the player inputted, if the input is valid then the
     * move is made and returns true, else it returns false
	 */
    private static boolean canMoveFox(ArrayList<String> moves, String input, GamePiece piece) {
        for (String move : moves) { // goes through all possible moves in the list given
            if (input.equals(move.substring(0, 1))) { // if the input is a valid move
                piece.movePieceTo(input);
                return true;
            } // end of if statement
        } // end of for loop

        System.out.println("Please select a valid number");
        return false;
    } // end of canMoveFox Method

    /**
     * @param moves - ArrayList - list of strings with all possible moves the player can make
     * @param input - String - the move selected by the user
     * @param pieces - GamePiece[] - list of pieces on the board that can be moved from given options
     * @return boolean - if move was valid then it returns true, else false
     *
     * Looks at given list of possible moves and checks the option the player inputted, if the input is valid then the
     * move is made and returns true, else it returns false
     */
    private static boolean canMoveGoose(ArrayList<String> moves, String input, GamePiece[] pieces) {
        for (String move : moves) { // goes through list of all possible moves given
            // if statements check if the given move is valid and helps identify which piece is actually moving
            if (input.equals(move.substring(0, 1))) {
                if (input.equals("1") || input.equals("2")) { // goose 1

                    pieces[1].movePieceTo(input);
                    return true;
                } // end of if statement

                if (input.equals("3") || input.equals("4")) { // goose 2

                    String movenum;
                    if (input.equals("3")) {
                        movenum = "1";
                    } else {
                        movenum = "2";
                    } // end of if-else statements

                    pieces[2].movePieceTo(movenum);
                    return true;
                } // end of if statement

                if (input.equals("5") || input.equals("6")) { // goose 3
                    String movenum;
                    if (input.equals("5")) {
                        movenum = "1";
                    } else {
                        movenum = "2";
                    } // end of if-else statements

                    pieces[3].movePieceTo(movenum);
                    return true;
                } // end of if statement

                if (input.equals("7") || input.equals("8")) { // goose 4
                    String movenum;
                    if (input.equals("7")) {
                        movenum = "1";
                    } else {
                        movenum = "2";
                    } // end of if-else statements

                    pieces[4].movePieceTo(movenum);
                    return true;
                } // end of if statement

                return false;
            } // end of if statement
        } // end of for loop

        System.out.println("Please select a valid number");
        return false;
    } // end of canMoveGoose

    /**
     * Method: checkGameOver
     * @param pieces - GamePiece[] - list of pieces on the board
     * @return int - returns 1 if fox wins - 2 if goose wins - and 0 if no winner
     *
     * checks pieces for a win condition and returns a non-zero number if a win condition is found
     */
    public static int checkGameOver(GamePiece[] pieces) {
        if (pieces[0].getRowPos() == 7) { // fox wins
            //System.out.println("Fox Player Wins!");
            return 1;
        } // end of if statement

        if (pieces[0].canMoveTo(pieces, 0, BOARD_SIZE).isEmpty()) { // geese win
            //System.out.println("Geese Player Wins!");
            return 2;
        } // end of if statement

        // check if the geese can move at all
        boolean goose1moves = pieces[1].canMoveTo(pieces, 1, BOARD_SIZE).isEmpty();
        boolean goose2moves = pieces[2].canMoveTo(pieces, 2, BOARD_SIZE).isEmpty();
        boolean goose3moves = pieces[3].canMoveTo(pieces, 3, BOARD_SIZE).isEmpty();
        boolean goose4moves = pieces[4].canMoveTo(pieces, 4, BOARD_SIZE).isEmpty();

        if (goose1moves & goose2moves & goose3moves & goose4moves) { // fox wins
            //System.out.println("Fox Player Wins!");
            return 1;
        } // end of if statement

        return 0; // no one won yet
    } // end of checkGameOver

    /**
     * Method: startGame
     * @param pieces - GamePiece[] - array of game pieces active on the board
     * @param board - BoardState - current board to be played on
     * @param maxdepth - int - maximum depth the A.I. is allowed to look
     *
     * called by main to start the game
     */
    public static void startGame(GamePiece[] pieces, BoardState board, int maxdepth) {
        Scanner input = new Scanner(System.in);

        // gets which player the user wants to be
        System.out.println("Please select which side you want to play as:");
        System.out.println("\t1) Fox");
        System.out.println("\t2) Geese");

        boolean selectside = true; // player must input a 1 or 2

        while (selectside) { // while input is still not valid
            switch (input.nextLine()) { // set foxplayer variable
                case "1":
                    selectside = false; // default value already true
                    break;
                case "2":
                    foxplayer = false;
                    selectside = false;
                    break;
                default:
                    System.out.println("Please select a 1 or 2:");
                    break;
            } // end of switch-case statements
        } // end of while loop
        board.printBoard(); // print current board

        while (checkGameOver(pieces) == 0) { // if fox can move or not at the end ( Y = boardsize-1) then next turn

            boolean selectmove = true; // player must input a valid number

            if (foxturn) { // if fox's turn
                if (foxplayer) { // if player is fox

                    ArrayList<String> foxmoves = pieces[0].canMoveTo(pieces, 0, BOARD_SIZE);

                    while (selectmove) {
                        System.out.println("Please select your move:");
                        for (String move : foxmoves) {
                            System.out.println("\t" + move);
                        } // end of for loop

                        String playerinput = input.nextLine();
                        selectmove = !canMoveFox(foxmoves, playerinput, pieces[0]);
                    } // end of while loop

                } else { // otherwise let computer go
                    // some AI Program
                    pieces = board.getNextMove(true, maxdepth);
                    System.out.println("AI Has Moved.");
                } // end of if-else statements

            } else { // if geese's turn
                if (!foxplayer) { // if player is geese

                    ArrayList<String> geesemoves = new ArrayList<String>();

                    for (int geese = 1; geese < 5; geese++) { // get possible moves for geese player
                        for (int movenum = 0; movenum < pieces[geese].canMoveTo(pieces, geese, BOARD_SIZE)
                                .size(); movenum++) {
                            geesemoves.add(pieces[geese].canMoveTo(pieces, geese, BOARD_SIZE).get(movenum));
                        } // end of for loop
                    } // end of for loop

                    while (selectmove) {
                        System.out.println("Please select your move:");
                        for (String move : geesemoves) {
                            System.out.println("\t" + move);
                        } // end of for loop

                        String playerinput = input.nextLine();
                        selectmove = !canMoveGoose(geesemoves, playerinput, pieces);
                    } // end of while loop

                } else { // otherwise let computer go
                    // some AI Program
                    pieces = board.getNextMove(false, maxdepth);
                    System.out.println("AI Has Moved.");
                } // end of if-else statements
            } // end of if-else statements
            foxturn = !foxturn;

            board = new BoardState(pieces); // create new current board
            board.printBoard();
        } // end of while loop

        // print who won
        if(checkGameOver(pieces) == 1){
            System.out.println("Fox Player Wins!");
        } else {
            System.out.println("Geese Player Wins!");
        } // end of if-else statements
    } // end of startGame
} // end of Control Class
