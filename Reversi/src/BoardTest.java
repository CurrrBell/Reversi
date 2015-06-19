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
	public void testUpdateBoard(){
		Move[] moves = b.findLegalMoves();
		Board actual = Board.updateBoard(b, moves[0]);
		Board expected = new Board();
		expected.state[2][3] = Board.BLACK;
		expected.state[3][3] = Board.BLACK;
		assertEquals(actual.state[2][3], expected.state[2][3]);
		assertEquals(actual.state[3][3], expected.state[3][3]);
	}
	
}
