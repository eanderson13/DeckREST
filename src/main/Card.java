package main;

/**
 * Object representing a single playing card.
 * 
 * @author Erik Anderson
 * @version 1.0 - original version
 */
public class Card {

	/** The rank of the card */
	private final Rank rank;

	/** The suit of the card */
	private final Suit suit;

	/**
	 * Standard constructor
	 *
	 * @param rank
	 *            The rank of the card
	 * @param suit
	 *            The suit of the card
	 */
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * Retrieves the rank of the card
	 *
	 * @return The rank of the card
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * Retrieves the suit of the card
	 *
	 * @return The suit of the card
	 */
	public Suit getSuit() {
		return suit;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return rank + " of " + suit;
	}

}
