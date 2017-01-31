package de.f_estival.seed.hammock.wapi._common;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.f_estival.seed.hammock.xcon.logging.LogTags;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Producing the {@link ObjectMapper Jackson ObjectMapper} instance which should be used by Jax-RS to
 * consume/produceconsume/produce JSON.<br/>
 * <b>Use this to do configure the ObjectMapper for all resources.</b>
 */
@Provider
@ApplicationScoped
public class JacksonMapperResolver implements ContextResolver<ObjectMapper> {

    @Inject
    protected Logger logger;

    private ObjectMapper mapper;

    @PostConstruct
    protected void init() {
        /* Creating the ObjectMapper inside postconstruct; this way, it will be instantiated and configured
         * exactly one time, when the first resource is loaded.
         * ! Not using the constructor as we don't have dependencies injected there !
         */
        logger.info(LogTags.TECH + "Creating configured Jackson Object Mapper.");
        createObjectMapper();
    }

    private void createObjectMapper() {
        this.mapper = new ObjectMapper();

        //find the JSR310 data type module of jackson (marshalling Java 8 DateTime API):
        mapper.findAndRegisterModules();

        //render DateTimes not as array of attributes ([2017,1,29,...]) but as String ('2017-01-29...'):
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }
}
