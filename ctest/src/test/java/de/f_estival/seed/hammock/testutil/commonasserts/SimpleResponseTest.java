package de.f_estival.seed.hammock.testutil.commonasserts;


import de.f_estival.seed.hammock.testutil.TestClientFactory;
import de.f_estival.seed.hammock.wapi._common.IAPI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Generic test that simply calls an endpoint and asserts basic behaviour.
 */
public class SimpleResponseTest {

    /**
     * Calls {@link #testGETRequestForMediaType(Class, MediaType, int)} with expectedStatus = 200.
     */
    public static void testGETRequestForMediaType(Class<? extends IAPI> apiToCallClass, MediaType acceptType) {
        testGETRequestForMediaType(apiToCallClass, acceptType, 200);
    }

    /**
     * Asserts that the API given in apiToCallClass successfully handles requests which accept
     * the media type given in acceptType.
     * <p>
     * Think of it as a <i>kind of smoke test</i> that simply asserts that a representation of a resource is
     * available in the proper media type (and is produced without internal server errors). <b>The test will fail  if
     * the response's content-type differs from the accepted type or if the response status differs from
     * expectedStatus</b>
     * </p>
     *
     * @param apiToCallClass Class of the Jax RS Resource that should be tested.
     * @param acceptType     the type that the request will accept and that should be the content type of the response.
     * @param expectedStatus the HTTP status code that is expected from the response. <b>Defaults to 200 (OK)</b>
     */
    public static void testGETRequestForMediaType(Class<? extends IAPI> apiToCallClass, MediaType acceptType,
                                                   int expectedStatus) {
        Response statusAPIResponse = TestClientFactory.target(apiToCallClass).request(acceptType).get();
        assertEquals(expectedStatus, statusAPIResponse.getStatus(), "Response Status Code...");
        assertEquals(acceptType, statusAPIResponse.getMediaType(), "Response Type should fit to accept type!");
    }
}
