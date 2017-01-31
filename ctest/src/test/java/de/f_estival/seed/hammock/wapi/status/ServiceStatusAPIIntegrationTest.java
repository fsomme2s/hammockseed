package de.f_estival.seed.hammock.wapi.status;

import org.junit.jupiter.api.BeforeAll;

import de.f_estival.seed.hammock.service.monitor.MonitoringService;
import de.f_estival.seed.hammock.testutil.CTestTags;
import de.f_estival.seed.hammock.testutil.TestClientFactory;
import de.f_estival.seed.hammock.testutil.TestServer;
import de.f_estival.seed.hammock.testutil.commonasserts.SimpleResponseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration-Test for {@link ServiceStatusAPI}.
 */
public class ServiceStatusAPIIntegrationTest {

    @BeforeAll
    public static void archive() {
        TestServer.start();
    }

    @Test
    @DisplayName("ServiceStatusAPI should respond to accepted type 'text/plain'")
    @Tag(CTestTags.API)
    void testPlainText() {
        SimpleResponseTest.testGETRequestForMediaType(ServiceStatusAPI.class, MediaType.TEXT_PLAIN_TYPE);
    }

    @Test
    @DisplayName("ServiceStatusAPI should respond to accepted type 'text/plain'")
    @Tag(CTestTags.API)
    void testJson() {
        SimpleResponseTest.testGETRequestForMediaType(ServiceStatusAPI.class, MediaType.APPLICATION_JSON_TYPE);
    }

    @Test
    @DisplayName("ServiceStatus should be UP after starting the service.")
    @Tag(CTestTags.SEMANTIC)
    void testServiceStatusIsUpAfterStart() throws IOException {
        Response statusAPIResponse = TestClientFactory.target(ServiceStatusAPI.class)
                .request(MediaType.APPLICATION_JSON).get();
        ServiceStatusInformation responseBody = statusAPIResponse.readEntity(ServiceStatusInformation.class);
        assertEquals(MonitoringService.ServiceStatus.UP.toString(), responseBody.getGeneralStatus());
    }

}