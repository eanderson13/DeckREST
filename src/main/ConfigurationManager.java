package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sets default configuration settings and loads configuration settings from a properties file.
 * 
 * @author Erik Anderson
 * @version 1.0 - original version
 */
public class ConfigurationManager {

	/** Logger for the class */
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationManager.class);

	/** Configuration file JVM argument name */
	private static final String ConfigurationFileArg = "configurationFile";

	/** Default configuration file location */
	private static final String DefaultConfigurationFile = "properties/configuration.properties";

	/** Property tag for the port to listen on */
	private static final String PortTag = "Port";

	/** Default port to listen on */
	private static final int DefaultPort = 4567;

	/** Property tag for the flag indicating whether to use a {@link HandShuffler} for shuffling */
	private static final String UseHandShufflerTag = "UseHandShuffler";

	/** Default flag indicating whether to use a {@link HandShuffler} for shuffling */
	private static final boolean DefaultUseHandShuffler = true;

	/** The configuration settings */
	private static Properties properties = loadConfiguration();

	/**
	 * Loads the default configuration settings and then attempts to load the configuration settings
	 * from a configuration file
	 *
	 * @return The configuration settings
	 */
	private static Properties loadConfiguration() {
		Properties properties = new Properties();
		ConfigurationManager.properties = properties;
		// Load defaults
		setPort(DefaultPort);
		setUseHandShuffler(DefaultUseHandShuffler);
		// Load configuration file
		String filename = System.getProperty(ConfigurationFileArg, DefaultConfigurationFile);
		try (FileInputStream stream = new FileInputStream(filename)) {
			properties.load(stream);
			logger.info("Loaded configuration settings from file " + filename);
		} catch (IOException e) {
			logger.warn("Failed to load configuration settings from file " + filename
					+ ". Reverting to default settings.");
		}
		logger.info("Port: " + getPort());
		logger.info("UseHandShuffler: " + getUseHandShuffler());
		return properties;
	}

	/**
	 * Reloads the configuration settings from the defaults or the configuration file specified by
	 * the system properties.
	 */
	public static void reload() {
		properties = loadConfiguration();
	}

	/**
	 * Retrieves the port to listen on
	 *
	 * @return Port to listen on
	 */
	public static int getPort() {
		return Integer.parseInt(properties.getProperty(PortTag));
	}

	/**
	 * Sets the port to listen on
	 *
	 * @param port
	 *            Port to listen on
	 */
	public static void setPort(int port) {
		properties.setProperty(PortTag, String.valueOf(port));
	}

	/**
	 * Retrieves the flag indicating whether to use a {@link HandShuffler} for shuffling
	 *
	 * @return Flag indicating whether to use a {@link HandShuffler} for shuffling
	 */
	public static boolean getUseHandShuffler() {
		return Boolean.parseBoolean(properties.getProperty(UseHandShufflerTag));
	}

	/**
	 * Sets the flag indicating whether to use a {@link HandShuffler} for shuffling
	 *
	 * @param useHandShuffler
	 *            Flag indicating whether to use a {@link HandShuffler} for shuffling
	 */
	public static void setUseHandShuffler(boolean useHandShuffler) {
		properties.setProperty(UseHandShufflerTag, String.valueOf(useHandShuffler));
	}

	/**
	 * Returns a shuffler of the type specified by the configuration settings.
	 *
	 * @return The appropriate shuffler
	 */
	public static Shuffler getShuffler() {
		if (getUseHandShuffler()) {
			return new HandShuffler();
		} else {
			return new InPlaceShuffler();
		}
	}

}
