import java.util.Scanner;

public class TicTacToe {
	final String[][] board = new String[3][3];
	Player player1;
	Player player2;
	
	public static void main(String[] args) {
		TicTacToe ticTacToe = new TicTacToe();
		ticTacToe.run();
	}
	
	public void run() {
		fillBoard();
		Scanner input = new Scanner(System.in);  // Create a Scanner object
		
		System.out.println("Enter player1's name:");
		player1 = new Player(input.next());
		System.out.println("Enter player2's name:");
		player2 = new Player(input.next());
	    
	    Player currentPlayer = player1;
		int chosenMove;  // move with value 1-9
		int round = 1;
	    while(true) {
	    	System.out.println("\nx-------------------- ROUND " + round + " --------------------x\n" +
	    					   currentPlayer.getName() + ", Where do you want to place your move, 1-9?");
			chosenMove = getInput(input);
			changeBoard(chosenMove, currentPlayer);
			printBoard();
	    	if (checkForWin(currentPlayer)) {
	    		System.out.println(currentPlayer.getName() + ", won the game. Congratulations!");
	    		break;  // game over
	    	}
	    	if (round >= 9) {
	    		System.out.println("It's a tie!");
	    		break;  // game over
	    	}
	    	currentPlayer = changePlayer(currentPlayer);
	    	round++;
		}
		input.close();
	}
	
	public int getInput(Scanner scanner) {
		while(true) {
			try {
				int input = Integer.parseInt(scanner.nextLine());
				if (input <= 0 || input > 9) {
					throw new NumberFormatException();
				} else if (board[(int) Math.floor((input-1)/3)][(input-1)%3] != "-") {
					System.out.println("You can not override another player's move.");
				} else {
					return input;
				}
			}
			catch (NumberFormatException e){
				System.out.println("Type a number 1-9.");
			}
		}
	}
	
	public void fillBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = "-";
			}
		}
	}
	
	public Player changePlayer(Player player) {
		if (player == player1) {
			return player2;
		}
		return player1;
	}
	
	public void changeBoard(int move, Player player) {
		if(player == player1) {
			board[(int) Math.floor((move-1)/3)][(move-1)%3] = "X";
		} else {
			board[(int) Math.floor((move-1)/3)][(move-1)%3] = "O";
		}
	}
	
	public boolean checkForWin(Player player) {
		String turn;
		if (player == player1) {
			turn = "X";
		} else {
			turn = "O";
		}
		
		boolean win = false;
		// three in a row horizontally and vertically
		for (int i = 0; i < board.length; i++) {
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

	public void printBoard() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
