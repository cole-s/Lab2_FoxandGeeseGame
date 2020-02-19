import java.util.ArrayList;

public class Goose extends GamePiece {
    private final String MOVE_FOWLEFT = "move forward left";
    private final String MOVE_FOWRIGHT = "move forward right";
    private int goosenum;

    public Goose(){
        super();
        goosenum = 0;
    } // end of constructor

    public Goose(int xpos, int ypos, char name, int goosenum){
        super(xpos, ypos, name);
        this.goosenum = goosenum;
    } // end of constructor

    public int getGooseNum() { return goosenum; }
    public void setGooseNum(int goosenum) { this.goosenum = goosenum; }

    @Override
    public ArrayList<String> canMoveTo(GamePiece[] pieces, int index, int boardsize) {
        ArrayList<String> movelist = new ArrayList<String>();

        // private boolean methods here to figure out available moves for goose

        return movelist;
    } // end of canMoveTo method


    private boolean isFowLeftOpen(GamePiece[] pieces, int sameindex, int boardsize){
        if(pieces[sameindex].getXPos() == 0 || pieces[sameindex].getYPos() == 0) {
            return false;
        } // end of if statement

        for(int index = 0; index < pieces.length; index++){
            if(index != sameindex){
                if(pieces[index].getXPos() == pieces[sameindex].getXPos()-1 && pieces[index].getYPos() == pieces[sameindex].getYPos()-1){
                    return false;
                } // end of if statement
            } // end of if statement
        } // end of for loop

        return true;
    } // end of isFowLeftOpen method


    private boolean isFowRightOpen(GamePiece[] pieces, int sameindex, int boardsize){
        if(pieces[sameindex].getXPos() == boardsize-1 || pieces[sameindex].getYPos() == 0) {
            return false;
        } // end of if statement

        for(int index = 0; index < pieces.length; index++){
            if(index != sameindex){

                if(pieces[index].getXPos() == pieces[sameindex].getXPos()+1 && pieces[index].getYPos() == pieces[sameindex].getYPos()-1){
                    return false;
                } // end of if statement
            } // end of if statement
        } // end of for loop
        
        return true;
    }// end of isFowRightOpen method
} // end of Goose class
