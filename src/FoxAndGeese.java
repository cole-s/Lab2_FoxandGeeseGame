public class FoxAndGeese {
    public static void main(String[] args) {
        // will call control for start of game
        GamePiece test = new Goose(0, 0, 'G', 1);
        System.out.println(test.getName() + " " + ((Goose) test).getGooseNum());
    } // end of main
} // end of FoxAndGeese class
