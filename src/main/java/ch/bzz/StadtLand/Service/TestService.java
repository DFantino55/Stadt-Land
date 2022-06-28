package ch.bzz.StadtLand.Service;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * test service
 */
@Path("test")
public class TestService {

    /**
     * testet ob Applikation funktioniert
     * @return  mitteilung
     */
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {

        return Response
                .status(200)
                .entity("Test erfolgreich")
                .build();
    }
}

