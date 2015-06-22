
public class GameTree {
	State root;
	static int height;
	
	GameTree(){
		
	}
	
	GameTree(Board b){
		this.root = new State(b);
		GameTree.height = 0;
	}
	
	void goDeeper(){
		height++;
		int currentDepth = 0;
		State statePointer = root;
		
		while(currentDepth < height){
			statePointer = statePointer.neighbors[0];
			currentDepth++;
		}
		
		
		
	}
	
	class State{
		Board board;
		State[]	neighbors;
		State parent;
		int depth;
		
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
		}
		
		void populateNeighbors(){
			Board[] n = Board.findNeighbors(board);
			neighbors = new State[n.length];
			
			for(int i = 0; i < n.length; i++){
				neighbors[i] = new State(n[i]);
			}
		}
	}
}
