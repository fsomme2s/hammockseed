package de.f_estival.seed.hammock.wapi._common;

import de.f_estival.seed.hammock.testutil.TestTags;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * TODO jdoc
 */
class LoggingRequestFilterTest {

    public static final String PATH_CONTAINING_ALL_ALLOWED_URI_CHARS =
            "http://localhost:4711/foobar/42?ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-" +
                    "._~:/#[]@!$&'()*+,;=`.";

    @Test
    @Tag(TestTags.TECH)
    @DisplayName("LoggingRequestFilter should not throw format exceptions")
    public void testLoggingRequestFilter() throws URISyntaxException, IOException {
        LoggingRequestFilter underTest = new LoggingRequestFilter();
        underTest.logger = LoggerFactory.getLogger(LoggingRequestFilter.class);

        ContainerRequestContext rCtx = mock(ContainerRequestContext.class);
        UriInfo uriInfo = Mockito.mock(UriInfo.class);
        when(rCtx.getUriInfo()).thenReturn(uriInfo);
        when(uriInfo.getPath()).thenReturn(PATH_CONTAINING_ALL_ALLOWED_URI_CHARS);

        underTest.filter(rCtx);
    }
}