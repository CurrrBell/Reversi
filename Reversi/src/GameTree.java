import java.util.ArrayList;

/*
 * Class for storing the game tree. Each node is a board state. Going deeper one level means going one move in the future.
 */
public class GameTree {
	State root;
	static int height;
	static int size;
	
	GameTree(){
		
	}
	
	GameTree(Board b){
		GameTree.height = 0;
		this.root = new State(b);	
		this.size = 1;
	}
	
	void goDeeper(){	//construct the tree one level at a time. used for iterative deepening in minmax
		
		int currentDepth = 0;
		State statePointer = root;
		//traverse to the bottom level to begin population
		while(currentDepth < height){
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
		
		while(true){
			State[] childrenHolder;
			State[] neighborHolder;
			childrenHolder = State.findChildren(statePointer);
			
			if(childrenHolder.length != 0){	//make sure there's children to populate with
				size += childrenHolder.length;
				statePointer.children = childrenHolder.length;
				statePointer.firstChild = childrenHolder[0];
				statePointer.lastChild = childrenHolder[childrenHolder.length - 1];
				statePointer.firstChild.childNumber = 0;
				statePointer.firstChild.depth = height + 1;
				statePointer.firstChild.parent = statePointer;
				statePointer.firstChild.rightNeighbor = childrenHolder[1];
				neighborHolder = new State[childrenHolder.length - 1];
				
				for(int i = 0; i < neighborHolder.length; i++){	//move data over to neighborHolder to trim off the first child
					neighborHolder[i] = childrenHolder[i+1];
				}
				
				//link up the children
				statePointer.firstChild.neighbors = neighborHolder;
				statePointer.firstChild.neighbors[0].leftNeighbor = statePointer.firstChild;
				statePointer.firstChild.neighbors[0].rightNeighbor = statePointer.firstChild.neighbors[1];
					
				for(int i = 1; i < statePointer.firstChild.neighbors.length - 1; i++){	
					statePointer.firstChild.neighbors[i].leftNeighbor = statePointer.firstChild.neighbors[i-1];
					statePointer.firstChild.neighbors[i].rightNeighbor = statePointer.firstChild.neighbors[i+1];	
									
				}
				
				statePointer.lastChild.leftNeighbor = statePointer.firstChild.neighbors[statePointer.firstChild.neighbors.length - 2];
				
				//link up neighbors first and last children
				if(statePointer.leftNeighbor != null){
					statePointer.firstChild.leftNeighbor = statePointer.leftNeighbor.lastChild;
					statePointer.leftNeighbor.lastChild.rightNeighbor = statePointer.firstChild;
				}
			}	
			
			if(statePointer.rightNeighbor == null)
				break;
			
			statePointer = statePointer.rightNeighbor;
		}
		
		height++;
		
		
	}
	
	static class State{
		Board board;	//data we care about
		State[] neighbors;
		State rightNeighbor;
		State leftNeighbor;
		State parent;
		State firstChild;
		State lastChild;
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
		
		private static State[] findChildren(State s){
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
