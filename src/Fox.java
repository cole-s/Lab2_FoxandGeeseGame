import java.util.ArrayList;

public class Fox extends GamePiece {
    private final String MOVE_FOWLEFT = "move forward left";
    private final String MOVE_FOWRIGHT = "move forward right";
    private final String MOVE_BACKLEFT = "move back left";
    private final String MOVE_BACKRIGHT = "move back right";
    
    public Fox(){
        super();
    } // end of constructor

    public Fox(int xpos, int ypos, char name){
        super(xpos, ypos, name);
    } // end of constructor

    @Override
    public ArrayList<String> canMoveTo(GamePiece[] pieces, int index, int boardsize) {
        ArrayList<String> movelist = new ArrayList<String>();

        // private boolean methods here to figure out available moves for Fox

        return movelist;
    } // end of canMoveTo method

    private boolean isFowLeftOpen(GamePiece[] pieces, int sameindex, int boardsize){
        if(pieces[sameindex].getXPos() == 0 || pieces[sameindex].getYPos() == boardsize-1) {
            return false;
        } // end of if statement

        for(int index = 0; index < pieces.length; index++){
            if(index != sameindex){
                if(pieces[index].getXPos() == pieces[sameindex].getXPos()-1 && pieces[index].getYPos() == pieces[sameindex].getYPos()+1){
                    return false;
                } // end of if statement
            } // end of if statement
        } // end of for loop

        return true;
    } // end of isFowLeftOpen method

    private boolean isFowRightOpen(GamePiece[] pieces, int sameindex, int boardsize){
        if(pieces[sameindex].getXPos() == boardsize-1 || pieces[sameindex].getYPos() == boardsize-1) {
            return false;
        } // end of if statement

        for(int index = 0; index < pieces.length; index++){
            if(index != sameindex){
                if(pieces[index].getXPos() == pieces[sameindex].getXPos()+1 && pieces[index].getYPos() == pieces[sameindex].getYPos()+1){
                    return false;
                } // end of if statement
            } // end of if statement
        } // end of for loop

        return true;
    } // end of isFowRightOpen method

    private boolean isBackLeftOpen(GamePiece[] pieces, int sameindex, int boardsize){
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
    } // end of isBackLeftOpen method
    
    private boolean isBackRightOpen(GamePiece[] pieces, int sameindex, int boardsize){
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
    } // end of isBackRightOpen method
    
    
} // end of Fox class
