package de.f_estival.seed.hammock.testutil;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JsonEndpointConfig;
import de.f_estival.seed.hammock.wapi._common.IAPI;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;

import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.lang.annotation.Annotation;

/**
 * Produces JaxRS Clients and WebTargets used by
 */
public class TestClientFactory {

    private static ClientBuilder configuredClientBuilder;

    public static Client newClient() {
        if (configuredClientBuilder == null) {
            createBuilder();
        }
        return configuredClientBuilder.build();
    }

    private static void createBuilder() {
        ResteasyJackson2Provider resteasyJacksonProvider = new ResteasyJackson2Provider() {
            @Override
            protected JsonEndpointConfig _configForReading(ObjectMapper mapper, Annotation[] annotations, Class<?> defaultView) {
                //make it find JSR310 extension for proper DateTime de-serialization:
                mapper.findAndRegisterModules();
                return super._configForReading(mapper, annotations, defaultView);
            }
        };

        configuredClientBuilder = ClientBuilder.newBuilder().register(resteasyJacksonProvider);
    }

    /**
     * Create a WebTarget for an {@link IAPI} Jax-RS Class.
     *
     * @param targetAPIClass The Jax-RS class for which the WebTarget should be created. The @{@link Path} information
     *                       on its <i>class level</i> is used to determine the request URI.
     * @return the WebTarget that can be used to do the actual API call.
     */
    public static WebTarget target(Class<? extends IAPI> targetAPIClass) {
        String targetUri = TestServer.BASE_PATH + targetAPIClass.getAnnotation(Path.class).value();
        return newClient().target(targetUri);
    }

}
