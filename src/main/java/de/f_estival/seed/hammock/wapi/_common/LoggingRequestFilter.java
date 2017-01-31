package de.f_estival.seed.hammock.wapi._common;



import de.f_estival.seed.hammock.xcon.logging.LogTags;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * A Request Filter logging every request.
 */
@Provider
@RequestScoped
public class LoggingRequestFilter implements ContainerRequestFilter {

    @Inject
    protected Logger logger;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        //Log request's: method + path + Accept + Content-Type
        logger.debug(LogTags.TECH + "Request: {} {} (accept: {}; content: {})",
                requestContext.getMethod(),
                requestContext.getUriInfo().getPath(),
                requestContext.getAcceptableMediaTypes(),
                requestContext.getMediaType()
        );
    }
}
