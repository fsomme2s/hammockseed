package de.f_estival.seed.hammock.testutil;

/**
 * Starts the Server, configured for integration testing.
 *
 * <p>
 *     Arquillian+Junit5 does not seem to work right now.
 *     So we will simply call the main method, starting the real server... that's actually even cooler than Arquillian!
 * </p>
 */
public class TestServer {

    public static final String BASE_PATH = "http://localhost:8080";

    public static void start() {
        ws.ament.hammock.Bootstrap.main();
    }
}
