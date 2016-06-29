package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.ConfigurationManager;
import main.HandShuffler;
import main.InPlaceShuffler;

/**
 * Test class for {@link ConfigurationManager}.
 * 
 * @author Erik Anderson
 * @version 1.0 &#0150; original version
 */
public class ConfigurationManagerTest {

	/**
	 * Test method for {@link main.ConfigurationManager#loadConfiguration()} using the default
	 * configuration settings.
	 */
	@Test
	public final void testLoadDefaultConfiguration() {
		// Set configuration file to a nonexistent file so configuration reverts to default
		System.setProperty("configurationFile", "properties/test.properties");
		ConfigurationManager.reload();
		// Port should be 4567
		assertEquals(4567, ConfigurationManager.getPort());
		// Use hand shuffler flag should be true
		assertEquals(true, ConfigurationManager.getUseHandShuffler());
		// Shuffler type should match type specified by the use hand shuffler flag
		assertEquals(ConfigurationManager.getUseHandShuffler(),
				ConfigurationManager.getShuffler() instanceof HandShuffler);
		assertEquals(!ConfigurationManager.getUseHandShuffler(),
				ConfigurationManager.getShuffler() instanceof InPlaceShuffler);

		// Test setters
		ConfigurationManager.setPort(7654);
		// Port should be 7654
		assertEquals(7654, ConfigurationManager.getPort());
		ConfigurationManager.setUseHandShuffler(false);
		// Use hand shuffler flag should be true
		assertEquals(false, ConfigurationManager.getUseHandShuffler());
		// Shuffler type should match type specified by the use hand shuffler flag
		assertEquals(ConfigurationManager.getUseHandShuffler(),
				ConfigurationManager.getShuffler() instanceof HandShuffler);
		assertEquals(!ConfigurationManager.getUseHandShuffler(),
				ConfigurationManager.getShuffler() instanceof InPlaceShuffler);
	}

	/**
	 * Test method for {@link main.ConfigurationManager#loadConfiguration()} using the default
	 * configuration file location.
	 */
	@Test
	public final void testLoadDefaultFileConfiguration() {
		System.clearProperty("configurationFile");
		ConfigurationManager.reload();
		// Port should be 1234
		assertEquals(1234, ConfigurationManager.getPort());
		// Use hand shuffler flag should be true
		assertEquals(false, ConfigurationManager.getUseHandShuffler());
	}

	/**
	 * Test method for {@link main.ConfigurationManager#loadConfiguration()} using a custom
	 * configuration file location.
	 */
	@Test
	public final void testLoadFileConfiguration() {
		System.setProperty("configurationFile", "src/test/test.properties");
		ConfigurationManager.reload();
		// Port should be 6789
		assertEquals(6789, ConfigurationManager.getPort());
		// Use hand shuffler flag should be true
		assertEquals(false, ConfigurationManager.getUseHandShuffler());
	}

}
