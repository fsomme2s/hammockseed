package de.f_estival.seed.hammock.testutil;

/**
 * Test-Tags for @{@link org.junit.jupiter.api.Tag} used in component tests.
 */
public class CTestTags {
    /**
     * Testing a single JaxRS Resource, concentrating on <b>technical aspects</b> like HTTP communication,
     * [un]marshalling etc.
     */
    public static final String API = "API";

    /**
     * Testing the semantics inside the http messages to assert correct  <b>business logic</b>.
     */
    public static final String SEMANTIC = "Semantic";
}
