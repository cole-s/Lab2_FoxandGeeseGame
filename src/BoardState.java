import java.util.ArrayList;

public class BoardState {
    private final int BOARD_SIZE = 8;
    private final char B = '\u25A0';
    private ArrayList<BoardState> nextstates;
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
        nextstates = new ArrayList<BoardState>();
        copyPieces(pieces);
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
}
