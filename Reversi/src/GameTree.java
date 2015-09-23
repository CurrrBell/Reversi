import java.util.ArrayList;

/*
 * Class for storing the game tree. Each node is a board state. Going deeper one level means going one move in the future.
 */
public class GameTree {
	State root;
	static int height;
	
	GameTree(){
		
	}
	
	GameTree(Board b){
		GameTree.height = 0;
		this.root = new State(b);		
	}
	
	void goDeeper(){	//construct the tree one level at a time. used for iterative deepening in minmax
		
		int currentDepth = 0;
		State statePointer = root;
		
		while(currentDepth < height){	//traverse to the bottom level to begin population
			if(statePointer.hasChildren()){	//if what we are on is a terminal losing state, we need to move to the next neighbor.
				statePointer = statePointer.firstChild;
				currentDepth++;
			}
			
			else{
				int neighborCounter = 0;
				State originalState = statePointer;	//first state in the row
				
				while(true){
					statePointer = originalState.neighbors[neighborCounter];	//if the one next to it also has no children, 
					neighborCounter++;
					
					if(statePointer.hasChildren())
						break;
				}
				
				statePointer = statePointer.firstChild;
				currentDepth++;
			}
		}
		
		// now that we're at the bottom, populate each state with children.
		// TO DO: use a function after each round of children to check any of them for win conditions so we don't generate shit unnecessarily
		
		
		
		height++;
		
		
	}
	
	class State{
		Board board;	//data we care about
		State[] neighbors;	
		State parent;
		State firstChild;
		int depth;
		int children;
		int childNumber;	//used for traversing?
		int score;
		
		State(){			
		}
		
		State(Board b){
			this.board = b;
		}
		
		State(Board b, State p){
			this.board = b;
			this.parent = p;
			//Reversi.findScore(this.board);
			//this.score = this.board.score;
		}
		
		private boolean hasChildren(){	//helper function for goDeeper()
			if(this.children > 0)
				return true;
			
			return false;
		}
		
		private State[] findChildren(State s){
			ArrayList<State> cList = new ArrayList<State>();
			State[] children;
			
			Board[] boards = Board.findNeighbors(s.board);	//find all possible moves from s
			
			for(int i = 0; i < boards.length; i++){
				State tmp = new State(boards[i]);
				cList.add(tmp);
			}
			
			children = new State[cList.size()];
			
			for(int i = 0; i < children.length; i++){
				children[i] = cList.get(i);
			}
			
			return children;
		}
		/*void populateNeighbors(){
			Board[] n = Board.findNeighbors(board);
			this.neighbors = new State[n.length+1];
			
			for(int i = 0; i < n.length; i++){
				this.neighbors[i] = new State(n[i], this);
				this.neighbors[i].childNumber = i;
			}
			
			if(this.parent == null || this.parent.nextChild(this.childNumber) == null){
				neighbors[neighbors.length-1] = null;
			}
			
			else{
				neighbors[neighbors.length-1] = this.parent.nextChild(this.childNumber).neighbors[0];	//point to the first child of the state to the right
			}
			
			this.children = this.neighbors.length;
		}*/
		
		/*State nextChild(int i){
			return this.neighbors[i+1];
		}*/
	}
}
