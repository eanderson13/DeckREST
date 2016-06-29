package main;

import java.util.ArrayList;

/**
 * Interface for shuffling algorithms
 * 
 * @author Erik Anderson
 * @version 1.0 - original version
 */
public interface Shuffler {

	/**
	 * Shuffles a deck of cards.
	 *
	 * @param deck
	 *            The deck to shuffle
	 */
	public void shuffle(ArrayList<Card> deck);

}
