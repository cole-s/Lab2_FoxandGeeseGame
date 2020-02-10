import java.util.ArrayList;

public abstract class GamePiece {
    private int xpos;
    private int ypos;
    private char name;

    public GamePiece(){
        xpos = 0;
        ypos = 0;
        name = 'P';
    } // end of constructor

    public GamePiece(int xpos, int ypos, char name){
        this.xpos = xpos;
        this.ypos = ypos;
        this.name = name;
    } // end of constructor

    public ArrayList<String> canMoveTo(ArrayList<String> movelist, GamePiece[] pieces, int index, int boardsize){
        return null;
    } // end of canMoveTo method

    public int getXPos() { return xpos; }
    public void setXPos(int xpos) { this.xpos = xpos; }
    public int getYPos() { return ypos; }
    public void setYPos(int ypos) { this.ypos = ypos; }
    public char getName() { return name; }
    public void setName(char name) { this.name = name; }
} // end of GamePiece Class
