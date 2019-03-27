import java.util.Scanner;

public class TicTacToe {
	static String[][] board = new String[3][3];
	static Player player1;
	static Player player2;
	
	public static void main(String[] args) {
		player1 = new Player();
		player2 = new Player();
		
		Scanner input = new Scanner(System.in);  // Create a Scanner object
		
		System.out.println("Enter player1's name:");
	    player1.name = input.next();
	    System.out.println("Enter player2's name:");
	    player2.name = input.next();
	    
	    Player currentPlayer = player1;
		int chosenMove;  // move with value 1-9
		int round = 1;
	    boolean successfulRound = true;  // true if no errors last iteration
	    while(true) {
	    	if (successfulRound) {
	    		System.out.println("\nx------------------ ROUND " + round + " ------------------x\n" +
	    						   currentPlayer.name + ", Where do you want to place your move, 1-9?");
	    	}
			try {
				chosenMove = Integer.parseInt(input.nextLine());
				if (chosenMove > 0 && chosenMove < 10) {
					changeBoard(chosenMove, currentPlayer);
					printBoard();
			    	if (checkForWin(currentPlayer)) {
			    		System.out.println(currentPlayer.name + ", won the game. Congratulations!");
			    		break;  // game over
			    	}
			    	currentPlayer = changePlayer(currentPlayer);
			    	round++;
					successfulRound = true;
				} else {
					throw new NumberFormatException();
				}
			}
			catch (NumberFormatException e){
				System.out.println("Type a number 1-9.");
				successfulRound = false;
			}
	    }
		input.close();	    
	}
	
	public static Player changePlayer(Player player) {
		if (player == player1) {
			return player2;
		}
		return player1;
	}
	
	public static void changeBoard(int move, Player player) {
		if(player == player1) {
			board[(int) Math.floor((move-1)/3)][(move-1)%3] = "X";
		} else {
			board[(int) Math.floor((move-1)/3)][(move-1)%3] = "O";
		}
	}
	
	public static boolean checkForWin(Player player) {
		String turn;
		if (player == player1) {
			turn = "X";
		} else {
			turn = "O";
		}
		
		boolean win = false;
		// three in a row horizontally and vertically
		for (int i = 0; i<board.length; i++) {
			if ((board[i][0] == turn && board[i][1] == turn && board[i][2] == turn) ||
				(board[0][i] == turn && board[1][i] == turn && board[2][i] == turn)) {
				win = true;
			}
		}
		// three in a row diagonally
		if ((board[0][0] == turn && board[1][1] == turn && board[2][2] == turn) ||
			(board[2][0] == turn && board[1][1] == turn && board[0][2] == turn)) {
			win = true;
		}
		return win;
	}

	public static void printBoard() {
		int count = 1;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if (board[i][j] == null) {
					System.out.print("- ");  // prints '-' if not played at.
				} else {
					System.out.print(board[i][j] + " ");
				}
				if (count%3 == 0) {  // making a 3x3 board
					System.out.println("");
				}
				count++;
			}
		}
	}
}
