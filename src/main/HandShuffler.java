package main;

import java.util.ArrayList;
import java.util.Random;

/**
 * Shuffles a deck of cards using an algorithm that simulates hand shuffling.
 * 
 * @author Erik Anderson
 * @version 1.0 - original version
 */
public class HandShuffler implements Shuffler {

	/** Random number generator */
	private final Random random = new Random();

	/*
	 * (non-Javadoc)
	 * @see main.Shuffler#shuffle(java.util.ArrayList)
	 */
	@Override
	public void shuffle(ArrayList<Card> deck) {
		// Repeat the shuffling 2 to 5 times
		for (int loop = random.nextInt(4) + 1; loop >= 0; loop--) {
			// Split the deck approximately in half
			int split = random.nextInt(11) + 21;
			int index = 0;
			// Determine which half of the deck to start with
			if (random.nextBoolean()) {
				// 1 to 4 cards from the front half stay in place
				index += random.nextInt(4) + 1;
			}
			// Shuffle until one half of the deck is exhausted
			while (index < split) {
				// Insert 1 to 4 cards from the second half into the first half
				int count = random.nextInt(4);
				while (split < deck.size() && count >= 0) {
					deck.add(index++, deck.remove(split++));
					count--;
				}
				// 1 to 4 cards from the front half stay in place
				index += random.nextInt(4) + 1;
			}
		}
	}

}
