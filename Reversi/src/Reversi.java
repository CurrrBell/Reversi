import java.util.*;


public class Reversi {
	public static final int WHITE = -1;
	public static final int EMPTY = 0;
	public static final int BLACK = 1;
	static Board currentState;
	static int[][] spaceWeights = new int[8][8];
	static Move[] possibleMoves;
	int turn;
	static int computerColor = BLACK;
	
	
	public static void main(String[] args) {
		currentState = new Board();
		Board.populateWeights(spaceWeights);
		
	}
	
	public Reversi(){
		currentState = new Board();
		updateTurn();
		Scanner sc = new Scanner(System.in);
		System.out.println("What color would you like to play? (B or W)");
		
		if(sc.nextLine().equals("B")){
			computerColor = WHITE;
		}
		else{
			computerColor = BLACK;
		}
		sc.close();
	}
	
	public int[] findMove(){
		/*
		 * implementation of minimax algorithm with alpha-beta pruning.
		 * 
		 */
		
		int[] derp = {};
		return derp;
	}
	
	public static Board[] findNeighbors(Board b){
		
		
		
		return new Board[1];
	}
	
	public static int findScore(Board b){
		/*
		 * Analyzes the board state and gives it a score of how favorable the state is for the computer player
		 * 
		 * This analysis is based on minimizing so-called "frontier pieces", that is, pieces that have empty
		 * adjacent spaces. A board state with many frontier pieces has a low score, as it gives more 
		 * opportunities for the opponent. Having mainly, or entirely, interior spaces occupied is usually
		 * advantageous as it limits the opponent's options.
		 */
		
		int score = 0;		
		Move testMove = new Move();
		
		for(int i = 0; i < possibleMoves.length; i++){
			if(possibleMoves[i].isNew()){	//make sure we don't check the same move twice
				testMove = possibleMoves[i];
				possibleMoves[i].nowTested();
			}
		}
		
		score += spaceWeights[testMove.getX()][testMove.getY()];
		
		for(int i = 0; i < b.state.length; i++){
			for(int j = 0; j < b.state[i].length; j++){
				if(b.state[i][j] == computerColor){	//look at all spaces around our piece
					//if any adjacent spots are empty, it is a frontier piece
					
					if(j > 0 && b.state[i][j-1] == EMPTY){
						score--;
					}
					
					else if((j > 0 && i < 7) && b.state[i+1][j-1] == EMPTY){
						score--;
					}
					
					else if(i < 7 && b.state[i+1][j] == EMPTY){
						score--;
					}
					
					else if((j < 7 && i < 7) && b.state[i+1][j+1] == -EMPTY){
						score--;
					}
					
					else if(j < 7 && b.state[i][j+1] == EMPTY){
						score--;
					}
					
					else if((j < 7 && i > 0) && b.state[i-1][j+1] == EMPTY){
						score--;						
					}
					
					else if(i > 0 && b.state[i-1][j] == EMPTY){
						score--;
					}
					
					else if((i > 0 && j > 0) && b.state[i-1][j-1] == EMPTY){
						score--;
					}
				}
			}
		}
		
		return score;
	}
	
	public Board generateNeighbor(Move m){	//generate a board object that represents what the board would look like if you made move m
		Board b = currentState;
		int offset;
		
		if(m.getY() > 0 && b.state[m.getY()-1][m.getX()] == -computerColor){	//if we make a move here, is there a chain of pieces above that need to be flipped?
			boolean validChain = false;
			offset = 2;
			
			while(true){	//walk down the chain until you find either an empty space or a computer piece
				if((m.getY()-offset > -1) && b.state[m.getY()-offset][m.getX()] == computerColor)
					validChain = true;
				else if((m.getY()-offset > -1) && b.state[m.getY()-offset][m.getX()] == -computerColor)	//if its another enemy piece, keep walking
					offset++;
				else{	//if the space is empty, we don't have a valid chain
					break;
				}
			}
			
			if(validChain){	//flip the pieces
				offset--;	//account for the fact that we're on our own piece -- don't want to flip that
				
				while(offset > 0){
					b.state[m.getY()-offset][m.getX()] *= -1;
					offset--;					
				}
			}
		}
		
		if((m.getY() > 0 && m.getX() < 7) && b.state[m.getY()-1][m.getX()+1] == -computerColor){ //top right
			boolean validChain = false;
			offset = 2;
			
			while(true){
				if((m.getY()-offset > -1 && m.getX()+offset < 8) && b.state[m.getY()-offset][m.getX()+offset] == computerColor)
					validChain = true;
				else if((m.getY()-offset > -1 && m.getX()+offset < 8) && b.state[m.getY()-offset][m.getX()+offset] == -computerColor)
					offset++;
				else{
					break;
				}
			}
			
			if(validChain){
				offset--;
				
				while(offset > 0){
					b.state[m.getY()-offset][m.getX()+offset] *= -1;
					offset--;					
				}
			}
		}
		
		
		
		return b;
	}

	public void updateTurn(){
		this.turn = currentState.turn;
	}
	
}
