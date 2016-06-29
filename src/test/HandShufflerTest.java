package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import main.Card;
import main.Deck;
import main.HandShuffler;

/**
 * Test class for {@link HandShuffler}.
 * 
 * @author Erik Anderson
 * @version 1.0 - original version
 */
public class HandShufflerTest {

	/**
	 * Test method for {@link main.HandShuffler#shuffle(java.util.ArrayList)}.
	 */
	@Test
	public final void testShuffle() {
		Deck deck = new Deck("Test Deck", new HandShuffler());
		ArrayList<Card> sorted = new ArrayList<>(deck.getCards());
		deck.shuffle();
		ArrayList<Card> shuffled1 = new ArrayList<>(deck.getCards());
		// Shuffled deck should have the same number of cards
		assertEquals(sorted.size(), shuffled1.size());
		// Shuffled deck should contain all the same cards
		assertTrue(shuffled1.containsAll(sorted));
		// Shuffled deck should be in a different order
		assertNotEquals(sorted, shuffled1);
		// Shuffling should be repeatable
		deck.shuffle();
		ArrayList<Card> shuffled2 = new ArrayList<>(deck.getCards());
		// Shuffled deck should have the same number of cards
		assertEquals(sorted.size(), shuffled2.size());
		// Shuffled deck should contain all the same cards
		assertTrue(shuffled2.containsAll(sorted));
		// Shuffled deck should be in a different order
		assertNotEquals(sorted, shuffled2);
		assertNotEquals(shuffled1, shuffled2);
	}

}
