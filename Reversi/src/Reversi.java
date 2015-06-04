import java.awt.Point;
import java.util.*;


public class Reversi {
	public static final int WHITE = -1;
	public static final int EMPTY = 0;
	public static final int BLACK = 1;
	Board currentState;
	int turn;
	static int computerColor = BLACK;
	
	
	public static void main(String[] args) {

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
	
	public void findNeighbors(Board b){
		/*
		 * finds all possible board states one move away from current state b.
		 */
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
		
		int frontierSpaces = 0;
		
		for(int i = 0; i < b.state.length; i++){
			for(int j = 0; j < b.state[i].length; j++){
				if(b.state[i][j] == computerColor){	//look at all spaces around our piece
					//if any adjacent spots are empty, it is a frontier piece
					
					if(j > 0 && b.state[i][j-1] == EMPTY){
						frontierSpaces++;
					}
					
					else if((j > 0 && i < 7) && b.state[i+1][j-1] == EMPTY){
						frontierSpaces++;
					}
					
					else if(i < 7 && b.state[i+1][j] == EMPTY){
						frontierSpaces++;
					}
					
					else if((j < 7 && i < 7) && b.state[i+1][j+1] == -EMPTY){
						frontierSpaces++;
					}
					
					else if(j < 7 && b.state[i][j+1] == EMPTY){
						frontierSpaces++;
					}
					
					else if((j < 7 && i > 0) && b.state[i-1][j+1] == EMPTY){
						frontierSpaces++;						
					}
					
					else if(i > 0 && b.state[i-1][j] == EMPTY){
						frontierSpaces++;
					}
					
					else if((i > 0 && j > 0) && b.state[i-1][j-1] == EMPTY){
						frontierSpaces++;
					}
				}
			}
		}
		
		return frontierSpaces;
	}

	public static Point[] findLegalMoves(Board b){
		ArrayList<Point> tmp = new ArrayList<Point>();
		
		for(int i = 0; i < b.state.length; i++){
			for(int j = 0; j < b.state[i].length; j++){
				if(b.state[i][j] == EMPTY){
					//must check neighboring spaces (without going out of bounds) to determine if this spot is legal
					
					int offset;
					boolean confirmedLegal = false;
					//spot above
					if(j > 0 && b.state[i][j-1] == -b.turn){	//move can only be legal if there's a neighboring enemy piece (and isn't out of bounds)
						offset = 2;
						
						while(j-offset > 0){
							if(b.state[i][j-offset] == -b.turn){	//if it's another enemy piece, go further down the chain
								offset++;
							}
							
							else if(b.state[i][j-offset] == b.turn){	//if it's one of our pieces, we have a legal move
								Point p = new Point(i, j);
								tmp.add(p);
								confirmedLegal = true;
								break;
							}
							
							else{
								break;
							}
						}
					}
					
					//upper right
					
					if((j > 0 && i < 7) && !confirmedLegal && b.state[i+1][j-1] == -b.turn){	//if we've already determined this spot to be a legal move, dont bother checking the other cases
						offset = 2;
						
						while(j-offset > 0 && i+offset < 7){
							if(b.state[i+offset][j-offset] == -b.turn){
								offset++;
							}
							
							else if(b.state[i+offset][j-offset] == b.turn){
								Point p = new Point(i, j);
								tmp.add(p);
								confirmedLegal = true;
								break;
							}
							
							else{
								break;
							}
						}
					}
					
					//right
					
					if(i < 7 && !confirmedLegal && b.state[i+1][j] == -b.turn){
						offset = 2;
						
						while(i+offset < 7){
							if(b.state[i+offset][j] == -b.turn){
								offset++;
							}
							
							else if(b.state[i+offset][j] == b.turn){
								Point p = new Point(i, j);
								tmp.add(p);
								confirmedLegal = true;
								break;
							}
							
							else{
								break;
							}
						}
					}
					
					//bottom right
					
					if((j < 7 && i < 7) && !confirmedLegal && b.state[i+1][j+1] == -b.turn){
						offset = 2;
						
						while(j+offset < 7 && i+offset < 7){
							if(b.state[i+offset][j+offset] == -b.turn){
								offset++;
							}
							
							else if(b.state[i+offset][j+offset] == b.turn){
								Point p = new Point(i, j);
								tmp.add(p);
								confirmedLegal = true;
								break;
							}
							
							else{
								break;
							}
						}
					}
					
					//bottom
					
					if(j < 7 && !confirmedLegal && b.state[i][j+1] == -b.turn){
						offset = 2;
						
						while(j+offset < 7){
							if(b.state[i][j+offset] == -b.turn){
								offset++;
							}
							
							else if(b.state[i][j+offset] == b.turn){
								Point p = new Point(i, j);
								tmp.add(p);
								confirmedLegal = true;
								break;
							}
							
							else{
								break;
							}
						}
					}
					
					//bottom left
					
					if((j < 7 && i > 0) && !confirmedLegal && b.state[i-1][j+1] == -b.turn){
						offset = 2;
						
						while(j+offset < 7 && i-offset > 0){
							if(b.state[i-offset][j+offset] == -b.turn){
								offset++;
							}
							
							else if(b.state[i-offset][j+offset] == b.turn){
								Point p = new Point(i, j);
								tmp.add(p);
								confirmedLegal = true;
								break;
							}
							
							else{
								break;
							}
						}
					}
					
					//left
					
					if(i > 0 && !confirmedLegal && b.state[i-1][j] == -b.turn){
						offset = 2;
						
						while(i-offset > 0){
							if(b.state[i-offset][j] == -b.turn){
								offset++;
							}
							
							else if(b.state[i-offset][j] == b.turn){
								Point p = new Point(i, j);
								tmp.add(p);
								confirmedLegal = true;
								break;
							}
							
							else{
								break;
							}
						}
					}
					
					//upper left
					
					if((i > 0 && j > 0) && !confirmedLegal && b.state[i-1][j-1] == -b.turn){
						offset = 2;
						
						while(j-offset > 0 && i-offset > 0){
							if(b.state[i-offset][j-offset] == -b.turn){
								offset++;
							}
							
							else if(b.state[i-offset][j-offset] == b.turn){
								Point p = new Point(i, j);
								tmp.add(p);
								confirmedLegal = true;
								break;
							}
							
							else{
								break;
							}
						}
					}
				}
			}
		}
		
		Point[] Points = new Point[tmp.size()];
		
		for(int i = 0; i < Points.length; i++){
			Points[i] = tmp.get(i);
		}
		
		return Points;
	}

	public void updateTurn(){
		this.turn = currentState.turn;
	}
	
}
