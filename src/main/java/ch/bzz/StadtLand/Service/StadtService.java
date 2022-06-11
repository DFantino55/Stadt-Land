package ch.bzz.StadtLand.Service;
import ch.bzz.StadtLand.Model.Stadt;
import ch.bzz.StadtLand.Data.DataHandler;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


import javax.validation.Valid;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 *
 * Services for reading, adding, changing and deleting staedte
 *
 */
@Path("stadt")
public class StadtService {

    /**
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
     * reads a stadt by uuid
     * @return stadt as JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readStadt(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String stadtUUID
    ) {
        int httpStatus = 200;
        Stadt stadt = DataHandler.readStadtByUUID(stadtUUID);
        if (stadt == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(stadt)
                .build();

    }

    /**
     * creates a new stadt
     * @param stadt which is created
     * @param laendercode of the stadt
     * @return httpStatus
     */
    @PUT
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertStadt(
            @Valid @BeanParam Stadt stadt,
            @NotEmpty
            @Pattern(regexp = "[A-Z]{2}-[A-Z]{3}-[0-9]{3}")
            @FormParam("laendercode") String laendercode
    ) {
        stadt.setLandByLaendercode(laendercode);
        DataHandler.insertStadt(stadt);

        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new stadt
     * @param stadt the stadt to update
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateStadt(
            @Valid @BeanParam Stadt stadt,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("stadtUuid") String StadtUuid
    ) {
        int httpStatus = 200;
        Stadt oldStadt = DataHandler.readStadtByUUID(stadt.getUuid());
        if (oldStadt != null) {
            oldStadt.setBezeichnung(stadt.getBezeichnung());
            oldStadt.setBevoelkerung(stadt.getBevoelkerung());
            oldStadt.setFlaeche(stadt.getFlaeche());

            DataHandler.updateStadt();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * Deletes a book identified by it uuid
     * @param stadtUUID uuid of stadt
     * @return httpStatus
     */

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteStadt(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String stadtUUID
     ) {
        int httpStatus = 200;
            if (!DataHandler.deleteStadt(stadtUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus).entity("").build();
     }

}
