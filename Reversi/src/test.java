

public class test {
	public static void main(String[] args){
		Board b = new Board();
		Move[] m = b.findLegalMoves();

		GameTree treeTest = new GameTree(b);
		treeTest.goDeeper();
		System.out.println(treeTest.root.children);
	}	
}
