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
		
		while(currentDepth < height){
			statePointer = statePointer.neighbors[0];
			currentDepth++;
		}
		
		while(statePointer != null){	//terminate once we reach end of the chain of states to populate
			statePointer.populateNeighbors();	
			
			if(statePointer.parent != null)	//if we're at the root, there's no more nodes to populate
				statePointer = statePointer.parent.nextChild(statePointer.childNumber);
			else
				break;
		}
		
		height++;
	}
	
	class State{
		Board board;
		State[]	neighbors;
		State parent;
		int depth;
		int children;
		int childNumber;
		int score;
		
		State(){			
		}
		
		State(Board b){
			this.board = b;
			this.depth = GameTree.height;
		}
		
		State(Board b, State p){
			this.board = b;
			this.depth = GameTree.height;
			this.parent = p;
			//Reversi.findScore(this.board);
			//this.score = this.board.score;
		}
		
		void populateNeighbors(){
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
		}
		
		State nextChild(int i){
			return this.neighbors[i+1];
		}
	}
}
