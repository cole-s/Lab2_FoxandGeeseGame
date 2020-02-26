public class FoxAndGeese {
	public static void main(String[] args) {

		GamePiece[] pieces = new GamePiece[5];
		pieces[0] = new Fox(0, 2, 'F');
		pieces[1] = new Goose(7, 1, '1', 1);
		pieces[2] = new Goose(7, 3, '2', 2);
		pieces[3] = new Goose(7, 5, '3', 3);
		pieces[4] = new Goose(7, 7, '4', 4);

		BoardState root = new BoardState(pieces);
		
		Control.startGame(pieces, root);
	} // end of main
} // end of FoxAndGeese class
