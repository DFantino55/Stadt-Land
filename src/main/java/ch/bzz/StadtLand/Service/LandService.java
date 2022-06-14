package ch.bzz.StadtLand.Service;


import ch.bzz.StadtLand.Data.DataHandler;
import ch.bzz.StadtLand.Model.Land;
import ch.bzz.StadtLand.Model.Stadt;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * services for reading, adding, changing and deleting books
 */
@Path("land")
public class LandService {

    /**
     * reads a list of all books
     * @return  books as JSON
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
     * reads a book identified by the uuid
     * @param bookUUID the key
     * @return book
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
     * inserts a new book
     * @param publisherUUID the uuid of the publisher
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertLand(
            @Valid @BeanParam Land land
            /*
            ,@NotEmpty
            @Pattern(regexp = "[A-Z]{2}-[A-Z]{3}-[0-9]{3}")
            @FormParam("laendercode") String laendercode
             */
    ) {

        //stadt.setLaendercode(laendercode);

        DataHandler.insertLand(land);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new book
     * @param publisherUUID the uuid of the publisher
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateLand(
            @Valid @BeanParam Land land
            /**,
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("laendercode") String laendercode */

    ) {
        int httpStatus = 200;
        Land oldLand = DataHandler.readLandByLaendercode(land.getLaenderCode());
        if (oldLand != null) {
            oldLand.setBezeichnung(land.getBezeichnung());
            oldLand.setGruendungsJahr(land.getGruendungsJahr());
            oldLand.setBevoelkerung(land.getBevoelkerung());
            oldLand.setFlaeche(land.getFlaeche());
            /*
            oldBook.setPrice(book.getPrice());
            oldBook.setIsbn(book.getIsbn());
            oldBook.setRelease(book.getRelease());
             */
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
     * deletes a book identified by its uuid
     * @param bookUUID  the key
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