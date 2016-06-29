DeckREST

This project implements a RESTful microservice that manipulates decks of cards.

The project can be built and run using Ant. The available Ant targets are:

	ant compile									Compiles the source code to the 'bin' directory.
	ant jar										Creates an executable JAR in the base directory that can be used to run the service.
	ant javadoc									Compiles the Javadoc to the 'javadoc' directory.
	ant build									Compiles the source code and the Javadoc and creates the executable JAR.
	ant [-DconfigurationFile=<file path>] run	Runs the service.
	ant clean									Deletes all of the build files.

The service can also be run using the executable JAR:

	java [-DconfigurationFile=<file path>] -jar DeckREST.jar

The optional 'configurationFile' argument can be used to specify the location of a configuration file. If the 'configurationFile' argument is not specified, 'properties/configuration.properties' is used as the default configuration file. If the configuration file does not exist or cannot be parsed, the configuration reverts to a set of predefined defaults. The available configuration settings are:

	Port <port number>							The port that the service listens on. The default value is 4567.
	UseHandShuffler <true|false>				Flag indicating whether to shuffle decks using simulated hand shuffling algorithm or a randomize in place algorithm. The default value is true.

The service can be accessed using a RESTful API. All URLs are relative to http://<host>:<port>. The available HTTP commands are:

	PUT /decks/<deck name>						Creates a new named deck.
	POST /decks/<deck name>						Shuffles an existing named deck.
	GET /decks									Retrieves a list of the names of the existing decks.
	GET /decks/<deck name>						Retrieves an existing named deck.
	DELETE /decks/<deck name>					Deletes an existing named deck.