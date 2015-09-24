

public class test {
	public static void main(String[] args){
		Board b = new Board();

		GameTree treeTest = new GameTree(b);
		treeTest.goDeeper();
		treeTest.goDeeper();
		treeTest.goDeeper();
		//treeTest.goDeeper();
		System.out.println(treeTest.size);
		
		
	}	
}
