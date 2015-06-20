import java.util.ArrayList;
import java.util.Arrays;


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
		System.out.println("   0  1  2  3  4  5  6  7");
		for(int j = 0; j < state.length; j++){
			System.out.print(j + " ");
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
			System.out.print(" " + j);
			System.out.println();
		}
		System.out.println("   0  1  2  3  4  5  6  7");
	}
	
	public static void populateWeights(int[][] a){
		a[0][0] = 99;
		a[0][1] = -8;
		a[0][2] = 8;
		a[0][3] = 6;
		a[0][4] = 6;
		a[0][5] = 8;
		a[0][6] = -8;
		a[0][7] = 99;
		a[1][0] = -8;
		a[1][1] = -24;
		a[1][2] = -4;
		a[1][3] = -3;
		a[1][4] = -3;
		a[1][5] = -4;
		a[1][6] = -24;
		a[1][7] = -8;
		a[2][0] = 8;
		a[2][1] = -4;
		a[2][2] = 7;
		a[2][3] = 4;
		a[2][4] = 4;
		a[2][5] = 7;
		a[2][6] = -4;
		a[2][7] = 8;
		a[3][0] = 6;
		a[3][1] = -3;
		a[3][2] = 4;
		a[3][3] = 0;
		a[3][4] =	0;
		a[3][5] = 4;
		a[3][6] = -3;
		a[3][7] = 6;
		a[4][1] = -3;
		a[4][2] = 4;
		a[4][3] = 0;
		a[4][4] =	0;
		a[4][5] = 4;
		a[4][6] = -3;
		a[4][7] = 6;
		a[5][0] = 8;
		a[5][1] = -4;
		a[5][2] = 7;
		a[5][3] = 4;
		a[5][4] = 4;
		a[5][5] = 7;
		a[5][6] = -4;
		a[5][7] = 8;
		a[6][0] = -8;
		a[6][1] = -24;
		a[6][2] = -4;
		a[6][3] = -3;
		a[6][4] = -3;
		a[6][5] = -4;
		a[6][6] = -24;
		a[6][7] = -8;
		a[7][0] = 99;
		a[7][1] = -8;
		a[7][2] = 8;
		a[7][3] = 6;
		a[7][4] = 6;
		a[7][5] = 8;
		a[7][6] = -8;
		a[7][7] = 99;
		
	}

	public Move[] findLegalMoves(){
		ArrayList<Move> tmp = new ArrayList<Move>();
		
		for(int i = 0; i < this.state.length; i++){
			for(int j = 0; j < this.state[i].length; j++){
				if(this.state[i][j] == EMPTY){
					//must check neighboring spaces (without going out of bounds) to determine if this spot is legal
					
					int offset;
					boolean confirmedLegal = false;
					//spot above
					if(j > 0 && this.state[i][j-1] == -this.turn){	//move can only be legal if there's a neighboring enemy piece (and isn't out of bounds)
						offset = 2;
						
						while(j-offset > 0){
							if(this.state[i][j-offset] == -this.turn){	//if it's another enemy piece, go further down the chain
								offset++;
							}
							
							else if(this.state[i][j-offset] == this.turn){	//if it's one of our pieces, we have a legal move
								Move p = new Move(i, j);
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
					
					if((j > 0 && i < 7) && !confirmedLegal && this.state[i+1][j-1] == -this.turn){	//if we've already determined this spot to be a legal move, dont bother checking the other cases
						offset = 2;
						
						while(j-offset > 0 && i+offset < 7){
							if(this.state[i+offset][j-offset] == -this.turn){
								offset++;
							}
							
							else if(this.state[i+offset][j-offset] == this.turn){
								Move p = new Move(i, j);
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
					
					if(i < 7 && !confirmedLegal && this.state[i+1][j] == -this.turn){
						offset = 2;
						
						while(i+offset < 7){
							if(this.state[i+offset][j] == -this.turn){
								offset++;
							}
							
							else if(this.state[i+offset][j] == this.turn){
								Move p = new Move(i, j);
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
					
					if((j < 7 && i < 7) && !confirmedLegal && this.state[i+1][j+1] == -this.turn){
						offset = 2;
						
						while(j+offset < 7 && i+offset < 7){
							if(this.state[i+offset][j+offset] == -this.turn){
								offset++;
							}
							
							else if(this.state[i+offset][j+offset] == this.turn){
								Move p = new Move(i, j);
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
					
					if(j < 7 && !confirmedLegal && this.state[i][j+1] == -this.turn){
						offset = 2;
						
						while(j+offset < 7){
							if(this.state[i][j+offset] == -this.turn){
								offset++;
							}
							
							else if(this.state[i][j+offset] == this.turn){
								Move p = new Move(i, j);
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
					
					if((j < 7 && i > 0) && !confirmedLegal && this.state[i-1][j+1] == -this.turn){
						offset = 2;
						
						while(j+offset < 7 && i-offset > 0){
							if(this.state[i-offset][j+offset] == -this.turn){
								offset++;
							}
							
							else if(this.state[i-offset][j+offset] == this.turn){
								Move p = new Move(i, j);
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
					
					if(i > 0 && !confirmedLegal && this.state[i-1][j] == -this.turn){
						offset = 2;
						
						while(i-offset > 0){
							if(this.state[i-offset][j] == -this.turn){
								offset++;
							}
							
							else if(this.state[i-offset][j] == this.turn){
								Move p = new Move(i, j);
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
					
					if((i > 0 && j > 0) && !confirmedLegal && this.state[i-1][j-1] == -this.turn){
						offset = 2;
						
						while(j-offset > 0 && i-offset > 0){
							if(this.state[i-offset][j-offset] == -this.turn){
								offset++;
							}
							
							else if(this.state[i-offset][j-offset] == this.turn){
								Move p = new Move(i, j);
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
		Move[] moves = new Move[tmp.size()];
		
		for(int i = 0; i < moves.length; i++){
			moves[i] = tmp.get(i);
		}
		
		return moves;
		
	}

	public static Board copyBoard(Board b){
		Board newB = new Board();
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				newB.state[i][j] = b.state[i][j];
			}
		}
		
		newB.turn = b.turn;
		
		return newB;
	}
	public Board updateBoard(Move m){
		/*
		 * takes in a given move (*ASSUMED LEGAL*) for a given player and returns the board after appropriate pieces are flipped
		 */
		
		Board after = copyBoard(this);
		after.state[m.getX()][m.getY()] = this.turn;	//make the actual Move m
		int offset = 0;
		//check for flippable pieces starting on the right
		if((m.getX() < 7) && this.state[m.getX()+1][m.getY()] == -this.turn){
			//now we have to make sure one of the moving player's pieces is on the other side of this/these flippable piece(s)
			boolean validChain = false;
			offset = 2;
			
			while(!validChain){	//walk down the chain until you find either an empty space or a computer piece
				if((m.getX()+offset < 8) && this.state[m.getX()+offset][m.getY()] == this.turn)
					validChain = true;
				else if((m.getX()+offset < 8) && this.state[m.getX()+offset][m.getY()] == -this.turn)	//if its another enemy piece, keep walking
					offset++;
				else{	//if the space is empty, we don't have a valid chain
					break;
				}
			}
			
			if(validChain){	//flip the pieces
				offset--;	//account for the fact that we're on our own piece -- don't want to flip that
				
				while(offset > 0){
					after.state[m.getX()+offset][m.getY()] *= -1;
					offset--;					
				}
			}			
		}
		
		//bottom right
		if((m.getX() < 7 && m.getY() < 7) && this.state[m.getX()+1][m.getY()+1] == -this.turn){
			
			boolean validChain = false;
			offset = 2;
			
			while(!validChain){	
				if((m.getX()+offset < 8 && m.getY()+offset < 8) && this.state[m.getX()+offset][m.getY()+offset] == this.turn)
					validChain = true;
				else if((m.getX()+offset < 8 && m.getY()+offset < 8) && this.state[m.getX()+offset][m.getY()+offset] == -this.turn)	
					offset++;
				else{	
					break;
				}
			}
			
			if(validChain){	
				offset--;	
				
				while(offset > 0){
					after.state[m.getX()+offset][m.getY()+offset] *= -1;
					offset--;					
				}
			}			
		}

		//bottom
		if((m.getY() < 7) && this.state[m.getX()][m.getY()+1] == -this.turn){
			
			boolean validChain = false;
			offset = 2;
			
			while(!validChain){	
				if((m.getY()+offset < 8) && this.state[m.getX()][m.getY()+offset] == this.turn)
					validChain = true;
				else if((m.getY()+offset < 8) && this.state[m.getX()][m.getY()+offset] == -this.turn)	
					offset++;
				else{	
					break;
				}
			}
			
			if(validChain){	
				offset--;	
				
				while(offset > 0){
					after.state[m.getX()][m.getY()+offset] *= -1;
					offset--;					
				}
			}			
		}
		
		//bottom left
		if((m.getX() > 0 && m.getY() < 7) && this.state[m.getX()-1][m.getY()+1] == -this.turn){
			
			boolean validChain = false;
			offset = 2;
			
			while(!validChain){	
				if((m.getX()-offset > -1 && m.getY()+offset < 8) && this.state[m.getX()-offset][m.getY()+offset] == this.turn)
					validChain = true;
				else if((m.getX()-offset > -1 && m.getY()+offset < 8) && this.state[m.getX()-offset][m.getY()+offset] == -this.turn)	
					offset++;
				else{	
					break;
				}
			}
			
			if(validChain){	
				offset--;	
				
				while(offset > 0){
					after.state[m.getX()-offset][m.getY()+offset] *= -1;
					offset--;					
				}
			}			
		}
		
		//left
		if((m.getX() > 0) && this.state[m.getX()-1][m.getY()] == -this.turn){
			//now we have to make sure one of the moving player's pieces is on the other side of this/these flippable piece(s)
			boolean validChain = false;
			offset = 2;
			
			while(!validChain){	//walk down the chain until you find either an empty space or a computer piece
				if((m.getX()-offset > -1) && this.state[m.getX()-offset][m.getY()] == this.turn)
					validChain = true;
				else if((m.getX()-offset > -1) && this.state[m.getX()-offset][m.getY()] == -this.turn)	//if its another enemy piece, keep walking
					offset++;
				else{	//if the space is empty, we don't have a valid chain
					break;
				}
			}
			
			if(validChain){	//flip the pieces
				offset--;	//account for the fact that we're on our own piece -- don't want to flip that
				
				while(offset > 0){
					after.state[m.getX()-offset][m.getY()] *= -1;
					offset--;					
				}
			}			
		}

		//top left
		if((m.getX() > 0 && m.getY() > 0) && this.state[m.getX()-1][m.getY()-1] == -this.turn){
			
			boolean validChain = false;
			offset = 2;
			
			while(!validChain){	
				if((m.getX()-offset > -1 && m.getY()-offset > -1) && this.state[m.getX()-offset][m.getY()-offset] == this.turn)
					validChain = true;
				else if((m.getX()-offset > -1 && m.getY()-offset > -1) && this.state[m.getX()-offset][m.getY()-offset] == -this.turn)	
					offset++;
				else{	
					break;
				}
			}
			
			if(validChain){	
				offset--;	
				
				while(offset > 0){
					after.state[m.getX()-offset][m.getY()-offset] *= -1;
					offset--;					
				}
			}			
		}
		
		//Above
		if((m.getY() > 0) && this.state[m.getX()][m.getY()-1] == -this.turn){
			
			boolean validChain = false;
			offset = 2;
			
			while(!validChain){	
				if((m.getY()-offset > -1) && this.state[m.getX()][m.getY()-offset] == this.turn)
					validChain = true;
				else if((m.getY()-offset > -1) && this.state[m.getX()][m.getY()-offset] == -this.turn)	
					offset++;
				else{	
					break;
				}
			}
			
			if(validChain){	
				offset--;	
				
				while(offset > 0){
					after.state[m.getX()][m.getY()-offset] *= -1;
					offset--;					
				}
			}			
		}
		
		//top right
		if((m.getX() < 7 && m.getY() > 0) && this.state[m.getX()+1][m.getY()-1] == -this.turn){
			
			boolean validChain = false;
			offset = 2;
			
			while(!validChain){	
				if((m.getX()+offset < 8 && m.getY()-offset > -1) && this.state[m.getX()+offset][m.getY()-offset] == this.turn)
					validChain = true;
				else if((m.getX()+offset < 8 && m.getY()-offset > -1) && this.state[m.getX()+offset][m.getY()-offset] == -this.turn)	
					offset++;
				else{	
					break;
				}
			}
			
			if(validChain){	
				offset--;	
				
				while(offset > 0){
					after.state[m.getX()+offset][m.getY()-offset] *= -1;
					offset--;					
				}
			}			
		}

		return after;
	}
	
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Board){
			if(this.turn == ((Board) o).turn){
				for(int i = 0; i < 8; i++){
					for(int j = 0; j < 8; j++){
						if(this.state[i][j] != ((Board) o).state[i][j]){
							return false;
						}
					}
				}
				return true;
			}
		}
		
		return false;
	}
}
