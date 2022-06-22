/**
 * View-Controller für login.html
 *
 * @author Diego Fantino
 */

/**
 * registriere Listener
 */
$(document).ready(function () {

    $("#loginForm").submit(sendLogin);
});

/**
 * sendet login anfrage
 * @param form mit benutzername und passwort
 */
function sendLogin(form) {
    form.preventDefault();
    $
        .ajax({
            url: "./resource/benutzer/login",
            dataType: "text",
            type: "POST",
            data: $("#loginForm").serialize()
        })
        .done(function () {
            window.location.href = "./stadtland.html";
        })
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Benutzername/Passwort unbekannt");
            } else {
                $("#message").text("Es ist ein Fehler aufgetreten");
            }
        })
}



/**
 * sendet logoff anfrage
 */
function sendLogoff() {

}
