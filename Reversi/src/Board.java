
public class Board {

	public static final int WHITE = -1;
	public static final int EMPTY = 0;
	public static final int BLACK = 1;
	
	int[][] state = new int[8][8];
	int turn;
	
	
	public Board(){
		//setup initial board state
		for(int i = 0; i < state.length; i++){
			for(int j = 0; j < state[i].length; j++){
				state[i][j] = EMPTY;
			}
		}
		
		state[3][3] = WHITE;
		state[3][4] = BLACK;
		state[4][3] = BLACK;
		state[4][4] = WHITE;
		
		turn = BLACK;
	}
	
	public void printBoard(){
		for(int j = 0; j < state.length; j++){
			for(int i = 0; i < state[j].length; i++){
				if(state[i][j] == WHITE){
					System.out.print("[W]");
				}
				
				else if(state[i][j] == BLACK){
					System.out.print("[B]");
				}
				
				else{
					System.out.print("[ ]");
				}
			}
			System.out.println();
		}
	}
}

