package ch.bzz.StadtLand.Service;
import ch.bzz.StadtLand.Data.DataHandler;
import ch.bzz.StadtLand.Model.Land;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Service für lesen, schreiben, bearbeiten und löschen von ländern
 */
@Path("land")
public class LandService {

    /**
     * liest alle länder
     * @return  länder als JSON
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
     * reads a land by laendercode
     * @param laendercode des landes
     * @return land
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readLand(
            @NotEmpty
            @Pattern(regexp = "[A-Z]{2}-[A-Z]{3}-[0-9]{3}")
            @QueryParam("laendercode") String laendercode
    ) {
        int httpStatus = 200;
        Land land = DataHandler.readLandByLaendercode(laendercode);
        if (land == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(land)
                .build();
    }

    /**
     * fügt ein neues land ein
     * @param land das eingefügt wird
     * @return Response
     */
    @POST
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
     * aktualisiert ein land
     * @param land das aktualisiert wird
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateLand(
            @Valid @BeanParam Land land
    ) {
        int httpStatus = 200;
        Land oldLand = DataHandler.readLandByLaendercode(land.getLaenderCode());
        if (oldLand != null) {
            oldLand.setBezeichnung(land.getBezeichnung());
            oldLand.setGruendungsJahr(land.getGruendungsJahr());
            oldLand.setBevoelkerung(land.getBevoelkerung());
            oldLand.setFlaeche(land.getFlaeche());
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
     * löscht ein land durch laendercode
     * @param laendercode des lande
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLand(
            @Pattern(regexp = "[A-Z]{2}-[A-Z]{3}-[0-9]{3}")
            @QueryParam("laendercode") String laendercode
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteLand(laendercode)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}