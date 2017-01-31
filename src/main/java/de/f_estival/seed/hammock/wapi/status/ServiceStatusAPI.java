package de.f_estival.seed.hammock.wapi.status;


import de.f_estival.seed.hammock.service.monitor.MonitoringService;
import ws.ament.hammock.web.undertow.UndertowWebServer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * API to get information about the service's {@link MonitoringService.ServiceStatus status}.
 */
@Path(ServiceStatusAPI.PATH)
@RequestScoped
public class ServiceStatusAPI {

    public static final String PATH = "/status";

    private MonitoringService monitoringService;

    @Inject
    public ServiceStatusAPI(MonitoringService monitoringService, UndertowWebServer undertowWebServer) {
        this.monitoringService = monitoringService;
    }

    public ServiceStatusAPI() {
        //necessary for CDI/JaxRS
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getStatusInPlainText() {
        String statusMessageBuilder = "Service Status:\n\n"
                + "General Status:\t\t"
                + monitoringService.getGeneralServiceStatus().toString()
                ;

        return Response
                .ok(statusMessageBuilder)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatusInJson() {
        ServiceStatusInformation statusDTO = new ServiceStatusInformation();
        statusDTO.setGeneralStatus( monitoringService.getGeneralServiceStatus().toString());

        return Response
                .ok(statusDTO)
                .build();
    }

}

