import java.sql.SQLOutput;

import static java.lang.Integer.parseInt;

/**
 * Class: FoxAndGeese
 * Main method to start the actual game, also does initial value setup for the game
 *
 * @author Cole Schaar
 * @author Phillip N.
 */
public class FoxAndGeese {
	/**
	 * Method: main
	 * @param args - String[] - list of arguments when the program is executed
	 *
	 * Used to initially set up the game and start it for the user
	 */
	public static void main(String[] args) {

		// initial startup values
		GamePiece[] pieces = new GamePiece[5];
		pieces[0] = new Fox(0, 2, 'F');
		pieces[1] = new Goose(7, 1, '1', 1);
		pieces[2] = new Goose(7, 3, '2', 2);
		pieces[3] = new Goose(7, 5, '3', 3);
		pieces[4] = new Goose(7, 7, '4', 4);

		BoardState root = new BoardState(pieces);
		int depth = 0;
		try{
			int temp;
			temp = parseInt(args[0]);
			checkFormat(temp);
			depth = temp;
		} catch (NumberFormatException error) {
			System.out.println("Incorrect format for depth argument default value set to 0");
			depth = 0;
		} // end of try-catch statements

		// starts the game
		Control.startGame(pieces, root, depth*2);
	} // end of main

	/**
	 * Method: checkFormat
	 * @param src - argument give from user on start of program in args of main method
	 * @throws NumberFormatException - if src is invalid
	 * looks at the input given from the user at start of the program
	 */
	private static void checkFormat(int src){
		if(src < 0)
			throw new NumberFormatException();
	} // end of checkFormat method
} // end of FoxAndGeese class
