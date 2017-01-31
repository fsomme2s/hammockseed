package de.f_estival.seed.hammock.xcon.logging;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * CDI Factory that produces a SLF4J {@link Logger}
 */
@RequestScoped
public class SLF4JLoggerFactory {

    @Produces
    public Logger producer(InjectionPoint ip) {
        return LoggerFactory.getLogger(ip.getMember().getDeclaringClass().getName
                ());
    }
}
