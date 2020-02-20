import java.util.Scanner;
import java.util.ArrayList;

public class Control {
    private static final int BOARD_SIZE = 8;
    private static boolean foxturn = true; // check if it's the fox's turn
    private static boolean foxplayer = true; // player is the fox
    
    private static boolean canMove(ArrayList<String> moves, String input) {
    	for (String move: moves) {
    		if (input.equals(move.substring(0,1))) {
    			// do the actual move (change piece coordinates
    			return true;
    		}
    	}
    	
    	System.out.println("Please select a valid number");
    	return false;
    }
    
    public static boolean startGame(GamePiece[] pieces) {
    	Scanner input = new Scanner(System.in);
    	
        System.out.println("Please select which side you want to play as:");
        System.out.println("\t1) Fox");
        System.out.println("\t2) Geese");
        
        boolean selectside = true; //player must input a 1 or 2
        
        while (selectside) {
            switch (input.nextLine()) { //set foxplayer variable
	        	case "1":	selectside = false; // default value already true
	        			break; 
	        	case "2":	foxplayer = false;
	        			selectside = false;
	        			break;
	        	default:System.out.println("Please select a 1 or 2:");
	        			break;
            }
        }
    	
    	while (true) { // if fox can move or not at the end ( Y = boardsize-1) then next turn
    		
            boolean selectmove = true; //player must input a valid number
            
    		if (foxturn) { // if fox's turn
    			if (foxplayer)  { // if player is fox

    				ArrayList<String> foxmoves = pieces[0].canMoveTo(pieces, 0, BOARD_SIZE);

    				
    	            while (selectmove) {
        	            System.out.println("Please select your move:");
    	            	for (String move: foxmoves) {
            	            System.out.println("\t" + move);
        	            }
        	            String playermove = input.nextLine();
        	            selectmove = !canMove(foxmoves, playermove);
    	            }
    				
    			} else { // otherwise let computer go
    				// some AI Program
    			}
    			
    		} else { // if geese's turn
    			if (!foxplayer) { // if player is geese
    				
    	            System.out.println("Please select your move:");
    				// We need to pull the moves from the Geese class + print here
    				
    	            while (selectmove) {
        	            switch (input.nextLine()) {
	        	            case "1":
	        	            		selectmove = false;
	        	            		break;
	        	            case "2":
        	            			selectmove = false;
	        	            		break;
	        	            case "3":
        	            			selectmove = false;
	        	            		break;
	        	            case "4":
        	            			selectmove = false;
	        	            		break;
	        	            default:System.out.println("Please select a valid number");
	    	            			break;
        	            }
    	            }
    	            
    			} else { // otherwise let computer go
    				// some AI Program
    			}
    			
    			
    		}
    		
    		
    	}
    
    	
    	
    }
    
    
}
