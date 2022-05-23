package ch.bzz.StadtLand.Service;

import ch.bzz.StadtLand.Model.Stadt;
import ch.bzz.StadtLand.Data.DataHandler;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * Services for reading, adding, changing and deleting books
 *
 */
@Path("stadt")
public class StadtService {

    /**
     *
     * reads a list of staedte
     * @return staedte as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listStaedte() {
        List<Stadt> stadtList = DataHandler.getInstance().readAllStaedte();
        return Response
                .status(200)
                .entity(stadtList)
                .build();
    }
}
