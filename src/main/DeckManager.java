package main;

import java.util.Set;
import java.util.TreeMap;

/**
 * Persistence manager for decks of cards.
 * 
 * @author Erik Anderson
 * @version 1.0 - original version
 */
public class DeckManager {

	/** Data store of decks of cards */
	private static final TreeMap<String, Deck> decks = new TreeMap<>();

	/**
	 * Private constructor to prevent construction
	 */
	private DeckManager() {
		// Do nothing
	}

	/**
	 * Creates a named deck of cards. The call returns the newly created deck of cards. If a deck
	 * with the specified name already exists, the existing deck is returned.
	 * 
	 * @param name
	 *            The name of the deck to create
	 * @return The created deck
	 */
	public static Deck put(String name) {
		if (decks.containsKey(name)) {
			return decks.get(name);
		} else {
			Deck deck = new Deck(name, ConfigurationManager.getShuffler());
			decks.put(name, deck);
			return deck;
		}
	}

	/**
	 * Shuffles a named deck of cards. The call returns the shuffled deck of cards, or null if a
	 * deck with the specified name does not exist.
	 * 
	 * @param name
	 *            The name of the deck to shuffle
	 * @return The shuffled deck
	 */
	public static Deck shuffle(String name) {
		if (decks.containsKey(name)) {
			Deck deck = decks.get(name);
			deck.shuffle();
			return deck;
		} else {
			return null;
		}
	}

	/**
	 * Retrieves a list of the names of the existing decks.
	 * 
	 * @return A list of the names of the existing decks
	 */
	public static Set<String> getList() {
		return decks.keySet();
	}

	/**
	 * Retrieves a named deck of cards. The call returns null if a deck with the specified name does
	 * not exist.
	 * 
	 * @param name
	 *            The name of the deck to retrieve
	 * @return The retrieved deck
	 */
	public static Deck get(String name) {
		return decks.get(name);
	}

	/**
	 * Deletes a named deck of cards. The call returns the deleted deck of cards, or null if a deck
	 * with the specified name does not exist.
	 * 
	 * @param name
	 *            The name of the deck to delete
	 * @return The deleted deck
	 */
	public static Deck delete(String name) {
		return decks.remove(name);
	}

}
