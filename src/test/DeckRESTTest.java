package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;

import main.ConfigurationManager;
import main.DeckManager;
import main.DeckREST;

/**
 * Test class for {@link DeckREST}.
 * 
 * @author Erik Anderson
 * @version 1.0 &#0150; original version
 */
public class DeckRESTTest {

	/**
	 * Start up the RESTful server before running the tests.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		DeckREST.main(null);
	}

	/**
	 * Test method for {@link main.DeckREST#registerPut()}, {@link main.DeckREST#registerPost()},
	 * {@link main.DeckREST#registerGetList()}, {@link main.DeckREST#registerGet()}, and
	 * {@link main.DeckREST#registerDelete()}.
	 */
	@Test
	public final void testDeckREST() {
		// There should initially be no decks
		assertEquals(DeckManager.getList().toString(), executeHTTP("GET", "decks"));
		assertEquals("A deck with the name Test-Deck does not exist.",
				executeHTTP("GET", "decks/Test-Deck"));
		String deck = executeHTTP("PUT", "decks/Test-Deck");
		// There should be 1 deck
		assertEquals(DeckManager.get("Test-Deck").toString(), deck);
		assertEquals(DeckManager.getList().toString(), executeHTTP("GET", "decks"));
		assertEquals(DeckManager.get("Test-Deck").toString(),
				executeHTTP("GET", "decks/Test-Deck"));
		assertEquals("A deck with the name Named-Deck does not exist.",
				executeHTTP("GET", "decks/Named-Deck"));
		// Repeated puts should be idempotent
		assertEquals(deck, executeHTTP("PUT", "decks/Test-Deck"));
		assertEquals(deck, executeHTTP("GET", "decks/Test-Deck"));
		assertEquals(DeckManager.getList().toString(), executeHTTP("GET", "decks"));

		String shuffled = executeHTTP("POST", "decks/Test-Deck");
		// The cards should be shuffled
		assertEquals(deck.length(), shuffled.length());
		assertNotEquals(deck, shuffled);
		assertEquals(DeckManager.get("Test-Deck").toString(), shuffled);
		// Shuffle should not create new decks
		assertEquals("A deck with the name Named-Deck does not exist.",
				executeHTTP("POST", "decks/Named-Deck"));

		String deck2 = executeHTTP("PUT", "decks/Named-Deck");
		// There should be 2 decks
		assertEquals(DeckManager.get("Named-Deck").toString(), deck2);
		assertEquals(DeckManager.getList().toString(), executeHTTP("GET", "decks"));
		assertEquals(DeckManager.get("Test-Deck").toString(),
				executeHTTP("GET", "decks/Test-Deck"));
		assertEquals(DeckManager.get("Named-Deck").toString(),
				executeHTTP("GET", "decks/Named-Deck"));
		assertNotEquals(deck, deck2);

		assertEquals(deck2, executeHTTP("DELETE", "decks/Named-Deck"));
		// There should be 1 deck
		assertEquals(DeckManager.getList().toString(), executeHTTP("GET", "decks"));
		assertEquals(DeckManager.get("Test-Deck").toString(),
				executeHTTP("GET", "decks/Test-Deck"));
		assertEquals("A deck with the name Named-Deck does not exist.",
				executeHTTP("GET", "decks/Named-Deck"));
		// Repeated deletes should be idempotent
		assertEquals("A deck with the name Named-Deck does not exist.",
				executeHTTP("DELETE", "decks/Named-Deck"));
		assertEquals(DeckManager.getList().toString(), executeHTTP("GET", "decks"));
		assertEquals(DeckManager.get("Test-Deck").toString(),
				executeHTTP("GET", "decks/Test-Deck"));
		assertEquals("A deck with the name Named-Deck does not exist.",
				executeHTTP("GET", "decks/Named-Deck"));
	}

	/**
	 * Executes an HTTP command.
	 *
	 * @param command
	 *            The type of command to execute
	 * @param path
	 *            The path to execute to
	 * @return The results of the command
	 */
	private static String executeHTTP(String command, String path) {
		URL url;
		try {
			url = new URL("http://localhost:" + ConfigurationManager.getPort() + "/" + path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			fail(e.getMessage());
			return null;
		}
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(command);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
			return null;
		}
		try (BufferedReader reader =
				new BufferedReader(new InputStreamReader(getStream(connection)))) {
			String result = "";
			String line;
			while ((line = reader.readLine()) != null) {
				result += line;
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
			return null;
		}
	}

	/**
	 * Determines whether to read a connections input stream or error stream.
	 *
	 * @param connection
	 *            The connection to read
	 * @return The appropriate stream
	 */
	private static InputStream getStream(HttpURLConnection connection) {
		try {
			if (connection.getResponseCode() >= 400) {
				return connection.getErrorStream();
			} else {
				return connection.getInputStream();
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
			return null;
		}
	}

}
