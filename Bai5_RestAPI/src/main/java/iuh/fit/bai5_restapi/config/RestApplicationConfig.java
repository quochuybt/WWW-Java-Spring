package iuh.fit.bai5_restapi.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class RestApplicationConfig extends ResourceConfig {

    public RestApplicationConfig() {
        packages("iuh.fit.bai5_restapi.resource");
    }
}
