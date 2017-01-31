package de.f_estival.seed.hammock.service.monitor;

import javax.enterprise.context.RequestScoped;

/**
 * Only created to test CDI is working.
 */
@RequestScoped
public class MonitoringService {
    /**
     * Enum containing all states in which the service or one of its connected systems can be.
     */
    public enum ServiceStatus {
        DOWN, UP
    }

    public ServiceStatus getGeneralServiceStatus() {
        return ServiceStatus.UP;
    }

}
