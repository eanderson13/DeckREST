package main;

import spark.Spark;

/**
 * Exposes a RESTful API for the creation and shuffling of decks of cards.
 * 
 * @author Erik Anderson
 * @version 1.0 - original version
 */
public class DeckREST {

	/**
	 * Private constructor to prevent construction
	 */
	private DeckREST() {
		// Do nothing
	}

	/**
	 * Registers a RESTful function that creates a named deck of cards. The call returns the newly
	 * created deck of cards. If a deck with the specified name already exists, the existing deck is
	 * returned. This method uses HTTP PUT with a URL format of /decks/<deck name>.
	 */
	private static void registerPut() {
		Spark.put("/decks/:name", (request, response) -> DeckManager.put(request.params(":name")));
	}

	/**
	 * Registers a RESTful function that shuffles a named deck of cards. The call returns the
	 * shuffled deck of cards. This method uses HTTP POST with a URL format of /decks/<deck name>.
	 */
	private static void registerPost() {
		Spark.post("/decks/:name", (request, response) -> {
			String name = request.params(":name");
			Deck deck = DeckManager.shuffle(name);
			if (deck != null) {
				return deck;
			} else {
				response.status(404);
				return "A deck with the name " + name + " does not exist.";
			}
		});
	}

	/**
	 * Registers a RESTful function that retrieves a list of the names of the existing decks. This
	 * method uses HTTP GET with a URL format of /decks.
	 */
	private static void registerGetList() {
		Spark.get("/decks", (request, response) -> DeckManager.getList());
	}

	/**
	 * Registers a RESTful function that retrieves a named deck of cards. This method uses HTTP GET
	 * with a URL format of /decks/<deck name>.
	 */
	private static void registerGet() {
		Spark.get("/decks/:name", (request, response) -> {
			String name = request.params(":name");
			Deck deck = DeckManager.get(name);
			if (deck != null) {
				return deck;
			} else {
				response.status(404);
				return "A deck with the name " + name + " does not exist.";
			}
		});
	}

	/**
	 * Registers a RESTful function that deletes a named deck of cards. The call returns the deleted
	 * deck of cards. This method uses HTTP DELETE with a URL format of /decks/<deck name>.
	 */
	private static void registerDelete() {
		Spark.delete("/decks/:name", (request, response) -> {
			String name = request.params(":name");
			Deck deck = DeckManager.delete(name);
			if (deck != null) {
				return deck;
			} else {
				response.status(404);
				return "A deck with the name " + name + " does not exist.";
			}
		});
	}

	/**
	 * Registers the functionality of the RESTful API.
	 *
	 * @param args
	 *            Unused
	 */
	public static void main(String[] args) {
		Spark.port(ConfigurationManager.getPort());
		registerPut();
		registerPost();
		registerGetList();
		registerGet();
		registerDelete();
	}

}
