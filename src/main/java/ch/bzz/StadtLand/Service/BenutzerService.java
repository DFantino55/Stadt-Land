package ch.bzz.StadtLand.Service;

import ch.bzz.StadtLand.Data.UserData;
import ch.bzz.StadtLand.Model.Benutzer;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * Service für Login und Logoff eines Benutzers
 */
@Path("benutzer")
public class BenutzerService {

    /**
     *loggt einen Benutzer mit Benutzernamen und Passwort ein
     *
     * @param benutzername des benutzers
     * @param passwort des benutzers
     * @return Response
     */
    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(
            @FormParam("benutzername") String benutzername,
            @FormParam("passwort") String passwort
    )
    {
        int httpStatus;
        Benutzer benutzer = UserData.findUser(benutzername,passwort);
        if (benutzer == null || benutzer.getRole() == null || benutzer.getRole().equals("guest")) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                benutzer.getRole(),
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        Response response = Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }

    /**
     * loggt aktuellen Benutzer aus
     *
     * @return Response
     */
    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout() {

        NewCookie cookie = new NewCookie(
                "userRole",
                "guest",
                "/",
                "",
                "Login-Cookie",
                1,
                false
        );

        Response response = Response
                .status(200)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }
}
