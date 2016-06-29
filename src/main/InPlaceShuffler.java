package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Shuffles a deck of cards using a randomize in place algorithm.
 * 
 * @author Erik Anderson
 * @version 1.0 - original version
 */
public class InPlaceShuffler implements Shuffler {

	/** Random number generator */
	private final Random random = new Random();

	/*
	 * (non-Javadoc)
	 * @see main.Shuffler#shuffle(java.util.ArrayList)
	 */
	@Override
	public void shuffle(ArrayList<Card> deck) {
		for (int index = deck.size() - 1; index > 0; index--) {
			Collections.swap(deck, index, random.nextInt(index + 1));
		}
	}

}
