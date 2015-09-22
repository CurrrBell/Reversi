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
		
		while(currentDepth < height){
			statePointer = statePointer.neighbors[0];
			currentDepth++;
		}
		
		
		
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
