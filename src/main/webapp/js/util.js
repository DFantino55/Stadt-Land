/**
 * util Funktionen für mehrere Seiten
 *
 * @author  Diego F.
 * @since   2022-06-28
 * @version 1.0
 */

/**
 * Getter für wert eines url parameters identifiziert durch schlüssel
 * quelle: https://www.sitepoint.com/get-url-parameters-with-javascript/
 * @param key schlüssel mit dem gesucht wird
 * @returns wert als String oder null
 */
function getQueryParam (key) {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    return urlParams.get(key);
}

/**
 * Getter für den Wert des Cookies mit bestimmten Namen
 * quelle: https://www.w3schools.com/js/js_cookies.asp
 * @param cname name des cookies
 * @returns {string}
 */
function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let cookieArray = decodedCookie.split(';');
    for(let i = 0; i <cookieArray.length; i++) {
        let cookie = cookieArray[i];
        while (cookie.charAt(0) == ' ') {
            cookie = cookie.substring(1);
        }
        if (cookie.indexOf(name) == 0) {
            return cookie.substring(name.length, cookie.length);
        }
    }
    return "";
}