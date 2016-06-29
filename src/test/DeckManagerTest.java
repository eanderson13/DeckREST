package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import main.Card;
import main.Deck;
import main.DeckManager;

/**
 * Test class for {@link DeckManager}.
 * 
 * @author Erik Anderson
 * @version 1.0 &#0150; original version
 */
public class DeckManagerTest {

	/**
	 * Test method for {@link main.DeckManager#put(java.lang.String)},
	 * {@link main.DeckManager#shuffle(java.lang.String)}, {@link main.DeckManager#getList()},
	 * {@link main.DeckManager#get(java.lang.String)}, and
	 * {@link main.DeckManager#delete(java.lang.String)}.
	 */
	@Test
	public final void testDeckManager() {
		// There should initially be no decks
		assertTrue(DeckManager.getList().isEmpty());
		assertNull(DeckManager.get("Test Deck"));
		Deck deck = DeckManager.put("Test Deck");
		// There should be 1 deck
		assertNotNull(deck);
		assertEquals(1, DeckManager.getList().size());
		assertTrue(DeckManager.getList().contains("Test Deck"));
		assertEquals(deck, DeckManager.get("Test Deck"));
		assertNull(DeckManager.get("Named Deck"));
		// Repeated puts should be idempotent
		assertEquals(deck, DeckManager.put("Test Deck"));
		assertEquals(deck, DeckManager.get("Test Deck"));
		assertEquals(1, DeckManager.getList().size());
		assertTrue(DeckManager.getList().contains("Test Deck"));

		ArrayList<Card> sorted = new ArrayList<>(deck.getCards());
		ArrayList<Card> shuffled = new ArrayList<>(DeckManager.shuffle("Test Deck").getCards());
		// The cards should be shuffled
		assertEquals(sorted.size(), shuffled.size());
		assertTrue(shuffled.containsAll(sorted));
		assertNotEquals(sorted, shuffled);
		assertEquals(shuffled, DeckManager.get("Test Deck").getCards());
		// Shuffle should not create new decks
		assertNull(DeckManager.shuffle("Named Deck"));

		assertNotNull(DeckManager.put("Named Deck"));
		// There should be 2 decks
		assertEquals(2, DeckManager.getList().size());
		assertTrue(DeckManager.getList().contains("Test Deck"));
		assertTrue(DeckManager.getList().contains("Named Deck"));
		assertEquals(deck, DeckManager.get("Test Deck"));
		assertNotNull(DeckManager.get("Named Deck"));
		assertNotEquals(deck, DeckManager.get("Named Deck"));

		assertEquals(deck, DeckManager.delete("Test Deck"));
		// There should be 1 deck
		assertEquals(1, DeckManager.getList().size());
		assertFalse(DeckManager.getList().contains("Test Deck"));
		assertTrue(DeckManager.getList().contains("Named Deck"));
		assertNull(DeckManager.get("Test Deck"));
		assertNotNull(DeckManager.get("Named Deck"));
		// Repeated deletes should be idempotent
		assertNull(DeckManager.delete("Test Deck"));
		assertEquals(1, DeckManager.getList().size());
		assertFalse(DeckManager.getList().contains("Test Deck"));
		assertTrue(DeckManager.getList().contains("Named Deck"));
		assertNull(DeckManager.get("Test Deck"));
		assertNotNull(DeckManager.get("Named Deck"));
	}

}
