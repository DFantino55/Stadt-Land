package ch.bzz.StadtLand.Service;
import ch.bzz.StadtLand.Model.Land;
import ch.bzz.StadtLand.Data.DataHandler;
import ch.bzz.StadtLand.Model.Stadt;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
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

    /**
     * creates a new Land
     * @param land which is created
     * @return httpStatus
     */
    @PUT
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertLand(
            @Valid @BeanParam Land land
    ) {
        DataHandler.insertLand(land);

        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new land
     * @param land the land to update
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateLand(
            @Valid @BeanParam Land land,
            @NotEmpty
            @Pattern(regexp = "[A-Z]{2}-[A-Z]{3}-[0-9]{3}")
            @FormParam("laendercode") String laendercode
    ) {
        int httpStatus = 200;
        Land oldLand = DataHandler.readLandByLaendercode(land.getLaenderCode());
        if (oldLand != null) {
            oldLand.setBezeichnung(land.getBezeichnung());
            oldLand.setBevoelkerung(land.getBevoelkerung());
            oldLand.setFlaeche(land.getFlaeche());
            oldLand.setLaenderCode(land.getLaenderCode());
            oldLand.setGruendungsJahr(land.getGruendungsJahr());

            DataHandler.updateLand();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * Deletes a land identified by it laendercode
     * @param laendercode of land
     * @return httpStatus
     */

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLand(
            @NotEmpty
            @Pattern(regexp = "[A-Z]{2}-[A-Z]{3}-[0-9]{3}")
            @FormParam("laendercode") String laendercode
    ) {
        int httpStatus = 200;
        if (DataHandler.deleteLand(laendercode)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus).entity("").build();
    }
}

