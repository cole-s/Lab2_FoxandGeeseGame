import java.sql.SQLOutput;

import static java.lang.Integer.parseInt;

public class FoxAndGeese {
	public static void main(String[] args) {

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
		}
		Control.startGame(pieces, root, depth*2);
	} // end of main

	private static void checkFormat(int src){
		if(src < 0)
			throw new NumberFormatException();
	} // end of checkFormat method
} // end of FoxAndGeese class
