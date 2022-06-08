package ch.bzz.StadtLand.Service;
import ch.bzz.StadtLand.Model.Stadt;
import ch.bzz.StadtLand.Data.DataHandler;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * Services for reading, adding, changing and deleting staedte
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
        List<Stadt> stadtList = DataHandler.readAllStaedte();
        return Response
                .status(200)
                .entity(stadtList)
                .build();
    }


    /**
     *
     * reads a stadt by uuid
     * @return stadt as JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readStadt(
            @QueryParam("uuid") String stadtUUID
    ) {
        Stadt stadt = DataHandler.readStadtByUUID(stadtUUID);
        return Response
                .status(200)
                .entity(stadt)
                .build();
    }
}
