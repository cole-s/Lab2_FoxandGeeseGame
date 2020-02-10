import java.util.ArrayList;

public class Goose extends GamePiece {
    private final String MOVE1 = "";
    private final String MOVE2 = "";
    private final String MOVE3 = "";
    private int goosenum;

    public Goose(){
        super();
        goosenum = 0;
    } // end of constructor

    public Goose(int xpos, int ypos, char name){
        super(xpos, ypos, name);
        goosenum = 0;
    } // end of constructor

    public int getGoosenum() { return goosenum; }
    public void setGoosenum(int goosenum) { this.goosenum = goosenum; }

    @Override
    public ArrayList<String> canMoveTo(GamePiece[] pieces, int index, int boardsize) {
        ArrayList<String> movelist = new ArrayList<String>();

        // private boolean methods here to figure out available moves for goose

        return movelist;
    } // end of canMoveTo method

    private boolean isLeftOpen(GamePiece[] pieces, int sameindex){
        if(pieces[sameindex].getXPos() == 0) {
            return false;
        } // end of if statement

        for(int index = 0; index < pieces.length; index++){
            if(index != sameindex){
                if(pieces[index].getXPos() == pieces[sameindex].getXPos()-1){
                    return false;
                } // end of if statement
            } // end of if statement
        } // end of for loop

        return true;
    } // end of isLeftOpen method

    private boolean isRightOpen(GamePiece[] pieces, int sameindex, int boardsize){
        if(pieces[sameindex].getXPos() == boardsize-1) {
            return false;
        } // end of if statement

        for(int index = 0; index < pieces.length; index++){
            if(index != sameindex){
                if(pieces[index].getXPos() == pieces[sameindex].getXPos()+1){
                    return false;
                } // end of if statement
            } // end of if statement
        } // end of for loop

        return true;
    } // end of isRightOpen method

    private boolean isForwardOpen(GamePiece[] pieces, int sameindex, int boardsize){
        if(pieces[sameindex].getYPos() == 0) {
            return false;
        } // end of if statement

        for(int index = 0; index < pieces.length; index++){
            if(index != sameindex){
                if(pieces[index].getYPos() == pieces[sameindex].getYPos()-1){
                    return false;
                } // end of if statement
            } // end of if statement
        } // end of for loop

        return true;
    } // end of isForwardOpen method
} // end of Goose class
