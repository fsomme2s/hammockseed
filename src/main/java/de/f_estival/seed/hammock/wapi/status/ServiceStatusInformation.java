package de.f_estival.seed.hammock.wapi.status;


import de.f_estival.seed.hammock.wapi._common.IDTO;

import java.time.LocalDateTime;

/**
 * {@link IDTO DTO} for {@link ServiceStatusAPI}
 */
class ServiceStatusInformation implements IDTO {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String generalStatus;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getGeneralStatus() {
        return generalStatus;
    }

    public void setGeneralStatus(String generalStatus) {
        this.generalStatus = generalStatus;
    }
}
