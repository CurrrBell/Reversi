import static org.junit.Assert.*;

import org.junit.Test;


public class ReversiTest {

	@Test
	public void testFindNeighbors(){
		Board b = new Board();
		Board[] expected = new Board[4];
		expected[0] = b.updateBoard(new Move(2, 3));
		expected[1] = b.updateBoard(new Move(3, 2));
		expected[2] = b.updateBoard(new Move(4, 5));
		expected[3] = b.updateBoard(new Move(5, 4));
		Board[] actual = Reversi.findNeighbors(b);
		
		assertArrayEquals(expected, actual);
	}

}
