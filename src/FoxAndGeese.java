public class FoxAndGeese {
    public static void main(String[] args) {
        // will call control for start of game
        GamePiece test = new Goose(0, 0, 'G', 1);
        System.out.println(test.getName() + " " + ((Goose) test).getGooseNum());

        GamePiece[] pieces = new GamePiece[5];
        pieces[0] = new Fox(0, 2, 'F');
        pieces[1] = new Goose(7, 0, '1', 1);
        pieces[2] = new Goose(7, 2, '2', 2);
        pieces[3] = new Goose(7, 4, '3', 3);
        pieces[4] = new Goose(7, 6, '4', 4);

        BoardState root = new BoardState(pieces);
        root.printBoard();
        
        Control.startGame(pieces);
    } // end of main
} // end of FoxAndGeese class
