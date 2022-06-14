package ch.bzz.StadtLand.Service;
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
 * Service für lesen, schreiben, bearbeiten und löschen von städten
 */
@Path("stadt")
public class StadtService {

    /**
     * liest alle städte der stadList
     * @return  städte als JSON
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
     * liest eine stadt durch uuid
     * @param stadtUUID uuid der stadt
     * @return stadt
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
     * fügt eine neue stadt ein
     * @param laendercode des dazugehörigen landes
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertStadt(
            @Valid @BeanParam Stadt stadt,
            @NotEmpty
            @Pattern(regexp = "[A-Z]{2}-[A-Z]{3}-[0-9]{3}")
            @FormParam("laendercode") String laendercode
    ) {
        stadt.setLaendercode(laendercode);

        DataHandler.insertStadt(stadt);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * aktualisiert eine stadt
     * @param laendercode des dazugehörigen landes
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateStadt(
            @Valid @BeanParam Stadt stadt,
            @Pattern(regexp = "[A-Z]{2}-[A-Z]{3}-[0-9]{3}")
            @FormParam("laendercode") String laendercode
    ) {
        int httpStatus = 200;
        Stadt oldStadt = DataHandler.readStadtByUUID(stadt.getUuid());
        if (oldStadt != null) {
            oldStadt.setBezeichnung(stadt.getBezeichnung());
            oldStadt.setBevoelkerung(stadt.getBevoelkerung());
            oldStadt.setFlaeche(stadt.getFlaeche());
            oldStadt.setLaendercode(laendercode);
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
     * löscht eine stadt durch uuid
     * @param stadtUUID uuid der stadt
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteStadt(
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String stadtUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteStadt(stadtUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}