package main;

import java.util.ArrayList;

/**
 * Object representing a standard deck 52 of playing cards.
 * 
 * @author Erik Anderson
 * @version 1.0 - original version
 */
public class Deck {

	/** The name of the deck */
	private final String name;

	/** The deck of cards */
	private ArrayList<Card> cards;

	/** The algorithm to use for shuffling the deck */
	private final Shuffler shuffler;

	/**
	 * Standard constructor
	 * 
	 * @param name
	 *            The name of the deck
	 * @param shuffler
	 *            The algorithm to use for shuffling the deck
	 */
	public Deck(String name, Shuffler shuffler) {
		this.name = name;
		this.shuffler = shuffler;
		// Initialize the deck
		cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
	}

	/**
	 * Retrieves the name of the deck
	 *
	 * @return The name of the deck
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the deck of cards
	 *
	 * @return The deck of cards
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}

	/**
	 * Shuffles the deck of cards
	 */
	public void shuffle() {
		shuffler.shuffle(cards);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + ":" + cards;
	}

}
