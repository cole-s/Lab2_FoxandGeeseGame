import java.util.ArrayList;

public abstract class GamePiece {
    private int currow;
    private int curcol;
    private char name;

    public GamePiece(){
        currow = 0;
        curcol = 0;
        name = 'P';
    } // end of constructor

    public GamePiece(int xpos, int ypos, char name){
        this.currow = xpos;
        this.curcol = ypos;
        this.name = name;
    } // end of constructor

    public ArrayList<String> canMoveTo(GamePiece[] pieces, int index, int boardsize){
        return null;
    } // end of canMoveTo method

    public int getRowPos() { return currow; }
    public void setXPos(int xpos) { this.currow = xpos; }
    public int getColPos() { return curcol; }
    public void setYPos(int ypos) { this.curcol = ypos; }
    public char getName() { return name; }
    public void setName(char name) { this.name = name; }
} // end of GamePiece Class
