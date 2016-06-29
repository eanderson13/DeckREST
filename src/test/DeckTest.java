package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import main.Card;
import main.Deck;
import main.Rank;
import main.Suit;

/**
 * Test class for {@link Deck}.
 * 
 * @author Erik Anderson
 * @version 1.0 &#0150; original version
 */
public class DeckTest {

	/**
	 * Test method for {@link main.Deck#Deck(java.lang.String, main.Shuffler)}.
	 */
	@Test
	public final void testDeck() {
		Deck deck = new Deck("Test Deck", null);
		// Deck should be properly named
		assertEquals("Test Deck", deck.getName());
		ArrayList<Card> cards = deck.getCards();
		int index = 0;
		// Deck should contain all of the cards in sorted order
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card card = cards.get(index++);
				assertEquals(rank, card.getRank());
				assertEquals(suit, card.getSuit());
			}
		}
		// Deck should not have extra cards
		assertEquals(index, cards.size());
	}

}
