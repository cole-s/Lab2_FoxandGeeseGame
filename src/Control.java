import java.util.Scanner;
import java.util.ArrayList;

public class Control {
	private static final int BOARD_SIZE = 8;
	private static boolean foxturn = true; // check if it's the fox's turn
	private static boolean foxplayer = true; // player is the fox

	private static boolean canMoveFox(ArrayList<String> moves, String input, GamePiece piece) {
		for (String move : moves) {
			if (input.equals(move.substring(0, 1))) {
				piece.movePieceTo(input);
				return true;
			}
		}

		System.out.println("Please select a valid number");
		return false;
	}

	private static boolean canMoveGoose(ArrayList<String> moves, String input, GamePiece[] pieces) {
		for (String move : moves) {

			if (input.equals(move.substring(0, 1))) {
				if (input.equals("1") || input.equals("2")) {

					pieces[1].movePieceTo(input);
					return true;
				}

				if (input.equals("3") || input.equals("4")) {

					String movenum;
					if (input.equals("3")) {
						movenum = "1";
					} else {
						movenum = "2";
					}

					pieces[2].movePieceTo(movenum);
					return true;
				}

				if (input.equals("5") || input.equals("6")) {
					String movenum;
					if (input.equals("5")) {
						movenum = "1";
					} else {
						movenum = "2";
					}

					pieces[3].movePieceTo(movenum);
					return true;
				}

				if (input.equals("7") || input.equals("8")) {
					String movenum;
					if (input.equals("7")) {
						movenum = "1";
					} else {
						movenum = "2";
					}

					pieces[4].movePieceTo(movenum);
					return true;
				}

				return false;
			}
		}

		System.out.println("Please select a valid number");
		return false;
	}

	private static boolean checkGameOver(GamePiece[] pieces) {
		if (pieces[0].getRowPos() == 7) {
			System.out.println("Fox Player Wins!");
			return true;
		}

		if (pieces[0].canMoveTo(pieces, 0, BOARD_SIZE).isEmpty()) {
			System.out.println("Geese Player Wins!");
			return true;
		}

		boolean goose1moves = pieces[1].canMoveTo(pieces, 1, BOARD_SIZE).isEmpty();
		boolean goose2moves = pieces[2].canMoveTo(pieces, 2, BOARD_SIZE).isEmpty();
		boolean goose3moves = pieces[3].canMoveTo(pieces, 3, BOARD_SIZE).isEmpty();
		boolean goose4moves = pieces[4].canMoveTo(pieces, 4, BOARD_SIZE).isEmpty();

		if (goose1moves & goose2moves & goose3moves & goose4moves) {
			System.out.println("Fox Player Wins!");
			return true;
		}

		return false;
	}

	public static void startGame(GamePiece[] pieces, BoardState board) {
		Scanner input = new Scanner(System.in);

		System.out.println("Please select which side you want to play as:");
		System.out.println("\t1) Fox");
		System.out.println("\t2) Geese");

		boolean selectside = true; // player must input a 1 or 2

		while (selectside) {
			switch (input.nextLine()) { // set foxplayer variable
			case "1":
				selectside = false; // default value already true
				break;
			case "2":
				foxplayer = false;
				selectside = false;
				break;
			default:
				System.out.println("Please select a 1 or 2:");
				break;
			}
		}
		board.printBoard();

		while (!checkGameOver(pieces)) { // if fox can move or not at the end ( Y = boardsize-1) then next turn

			boolean selectmove = true; // player must input a valid number

			if (foxturn) { // if fox's turn
				if (foxplayer) { // if player is fox

					ArrayList<String> foxmoves = pieces[0].canMoveTo(pieces, 0, BOARD_SIZE);

					while (selectmove) {
						System.out.println("Please select your move:");
						for (String move : foxmoves) {
							System.out.println("\t" + move);
						}
						String playerinput = input.nextLine();
						selectmove = !canMoveFox(foxmoves, playerinput, pieces[0]);
					}

				} else { // otherwise let computer go
					// some AI Program

					System.out.println("AI Has Moved.");
				}

			} else { // if geese's turn
				if (!foxplayer) { // if player is geese

					ArrayList<String> geesemoves = new ArrayList<String>();

					for (int geese = 1; geese < 5; geese++) {
						for (int movenum = 0; movenum < pieces[geese].canMoveTo(pieces, geese, BOARD_SIZE)
								.size(); movenum++) {
							geesemoves.add(pieces[geese].canMoveTo(pieces, geese, BOARD_SIZE).get(movenum));
						}
					}

					while (selectmove) {
						System.out.println("Please select your move:");
						for (String move : geesemoves) {
							System.out.println("\t" + move);
						}
						String playerinput = input.nextLine();

						selectmove = !canMoveGoose(geesemoves, playerinput, pieces);
					}

				} else { // otherwise let computer go
					// some AI Program

					System.out.println("AI Has Moved.");
				}

			}
			foxturn = !foxturn;

			board = new BoardState(pieces);
			board.printBoard();
		}

	}

}
