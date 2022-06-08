package ch.bzz.StadtLand.Service;
import ch.bzz.StadtLand.Model.Land;
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
 * Services for reading, adding, changing and deleting Laender
 *
 */
@Path("land")
public class LandService {

    /**
     *
     * reads a list of laender
     * @return laender as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listLaender() {
        List<Land> landList = DataHandler.readAllLaender();
        return Response
                .status(200)
                .entity(landList)
                .build();
    }


    /**
     *
     * reads a land by laendercode
     * @return land as JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readLand(
            @QueryParam("laendercode") String laenderCode
    ) {
        Land land = DataHandler.readLandByLaendercode(laenderCode);
        return Response
                .status(200)
                .entity(land)
                .build();
    }
}

