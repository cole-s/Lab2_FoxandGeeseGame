import java.util.Scanner;

public class Control {
    private final int BOARD_SIZE = 8;
    private static boolean foxturn = true; // check if it's the fox's turn
    private static boolean foxplayer = true; // player is the fox
    
    public static boolean startGame() {
    	Scanner input = new Scanner(System.in);
    	
        System.out.println("Please select which side you want to play as:");
        System.out.println("\t1) Fox");
        System.out.println("\t2) Geese");
        
        boolean selectside = true; //player must input a 1 or 2
        
        while (selectside) {
            switch (input.nextInt()) { //set foxplayer variable
	        	case 1:	selectside = false; // default value already true
	        			break; 
	        	case 2:	foxplayer = false;
	        			selectside = false;
	        			break;
	        	default:System.out.println("Please select a 1 or 2:");
	        			break;
            }
        }
    	
    	while (true) { // we need to create a method to check board state if end of game, then replace this true
    		
            boolean selectmove = true; //player must input a valid number
            
    		if (foxturn) { // if fox's turn
    			if (foxplayer)  { // if player is fox
    				
    	            System.out.println("Please select your move:");
    				// We need to pull the moves from the Fox class + print here
    	            // discuss with Cole on figuring out logic for not allowing invalid moves to be selected in switch
    	            
    	            while (selectmove) {
        	            switch (input.nextInt()) {
	        	            case 1:
	        	            		selectmove = false;
	        	            		break;
	        	            case 2:
        	            			selectmove = false;
	        	            		break;
	        	            case 3:
        	            			selectmove = false;
	        	            		break;
	        	            case 4:
        	            			selectmove = false;
	        	            		break;
	        	            default:System.out.println("Please select a valid number");
	    	            			break;
        	            }
    	            }

    	            
    				
    			} else { // otherwise let computer go
    				// some AI Program
    			}
    			
    		} else { // if geese's turn
    			if (!foxplayer) { // if player is geese
    				
    	            System.out.println("Please select your move:");
    				// We need to pull the moves from the Geese class + print here
    				
    	            while (selectmove) {
        	            switch (input.nextInt()) {
	        	            case 1:
	        	            		selectmove = false;
	        	            		break;
	        	            case 2:
        	            			selectmove = false;
	        	            		break;
	        	            case 3:
        	            			selectmove = false;
	        	            		break;
	        	            case 4:
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
