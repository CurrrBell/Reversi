import static org.junit.Assert.*;

import java.util.Arrays;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;


public class BoardTest {
	private Board b;
	
	@Before
	public void setUp(){
		b = new Board();
	}
	
	
	@Test
	public void testFindLegalMoves() {
		b.state[4][3] = Board.EMPTY;
		b.state[4][4] = Board.EMPTY;
		Move[] actual = b.findLegalMoves();
		Move[] expected = {new Move(3, 2)};
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testFindLegalMovesLongChain(){
		b.state[4][3] = Board.EMPTY;
		b.state[4][4] = Board.EMPTY;
		b.state[3][2] = Board.WHITE;
		Move[] actual = b.findLegalMoves();
		Move[] expected = {new Move(3, 1)};
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testFindLegalMovesOutOfBounds(){
		//tests when a potential legal move would actually be off the board
		b.state[4][3] = Board.EMPTY;
		b.state[4][4] = Board.EMPTY;
		b.state[3][2] = Board.WHITE;
		b.state[3][1] = Board.WHITE;
		b.state[3][0] = Board.WHITE;
		Move[] actual = b.findLegalMoves();
		Move[] expected = {};
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testFindLegalMovesDiagonalChain(){
		b.state[3][4] = Board.WHITE;
		b.state[2][5] = Board.WHITE;
		Move[] actual = b.findLegalMoves();
		Move[] expected = {new Move(2, 3), new Move(1, 6), new Move(4, 5)};
		assertThat(Arrays.asList(actual), CoreMatchers.hasItems(expected));
	}
	
	
	@Test
	public void testUpdateBoardRight(){	//test flipping pieces to the right
		Move[] moves = b.findLegalMoves();
		assertEquals(new Move(2, 3), moves[0]);
		Board expected = Board.copyBoard(b);
		Board actual = b.updateBoard(moves[0]);		
		expected.state[2][3] = Board.BLACK;
		expected.state[3][3] = Board.BLACK;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdateBoardBottomRight(){
		b.state[5][5] = Board.BLACK;
		Move m = new Move(2, 2);
		Board actual = b.updateBoard(m);
		Board expected = Board.copyBoard(b);
		expected.state[2][2] = Board.BLACK;
		expected.state[3][3] = Board.BLACK;
		expected.state[4][4] = Board.BLACK;
		assertEquals(expected, actual);
	}
	
}
