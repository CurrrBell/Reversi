import java.awt.Point;
import java.util.ArrayList;


public class Reversi {
	public static final int WHITE = -1;
	public static final int EMPTY = 0;
	public static final int BLACK = 1;
	Board currentState;
	int turn;
	
	public static void main(String[] args) {

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
	

	public Point[] findLegalMoves(Board b){
		ArrayList<Point> tmp = new ArrayList<Point>();
		
		for(int i = 0; i < b.state.length; i++){
			for(int j = 0; j < b.state[i].length; j++){
				if(b.state[i][j] == EMPTY){
					//must check neighboring spaces (without going out of bounds) to determine if this spot is legal
					
					//spot above
					if(j > 0 && b.state[i][j-1] == -b.turn){	//move can only be legal if there's a neighboring enemy piece (and isn't out of bounds)
						
					}
					
					//upper right
					
					else if((j > 0 && i < 7) && b.state[i+1][j-1] == -b.turn){
						
					}
					
					//right
					
					else if(i < 7 && b.state[i+1][j] == -b.turn){
						
					}
					
					//bottom right
					
					else if((j < 7 && i < 7) && b.state[i+1][j+1] == -b.turn){
						
					}
					
					//bottom
					
					else if(j < 7 && b.state[i][j+1] == -b.turn){
						
					}
					
					//bottom left
					
					else if((j < 7 && i > 0) && b.state[i-1][j+1] == -b.turn){
						
					}
					
					//left
					
					else if(i > 0 && b.state[i-1][j] == -b.turn){
						
					}
					
					//upper left
					
					else if((i > 0 && j > 0) && b.state[i-1][j-1] == -b.turn){
						
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

	
	
}
