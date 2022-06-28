document.addEventListener("DOMContentLoaded", () => {
    readStaedte();
});

/**
 * reads all books
 */
function readStaedte() {
    fetch("./resource/stadt/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showStadtList(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows the booklist as a table
 * @param data  the books
 */
function showStadtList(data) {
    let tBody = document.getElementById("stadt");
    data.forEach(stadt => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = stadt.uuid;
        row.insertCell(-1).innerHTML = stadt.bezeichnung;
        row.insertCell(-1).innerHTML = stadt.bevoelkerung;
        row.insertCell(-1).innerHTML = stadt.flaeche;
        row.insertCell(-1).innerHTML = stadt.land.bezeichnung;

        let button = document.createElement("button");
        button.innerHTML = "Bearbeiten ...";
        button.type = "button";
        button.name = "editStadt";
        button.setAttribute("data-stadtuuid", stadt.uuid);
        button.addEventListener("click", editStadt);
        row.insertCell(-1).appendChild(button);

        let button2 = document.createElement("button");
        button2.innerHTML = "Löschen ...";
        button2.type = "button";
        button2.name = "deleteStadt";
        button2.setAttribute("data-stadtuuid", stadt.uuid);
        button2.addEventListener("click", deleteStadt);
        row.insertCell(-1).appendChild(button2);

    });
}

/**
 * redirects to the edit-form
 * @param event  the click-event
 */
function editStadt(event) {
    const button = event.target;
    const uuid = button.getAttribute("data-stadtuuid");
    window.location.href = "./stadtedit.html?uuid=" + uuid;
}

/**
 * deletes a book
 * @param event  the click-event
 */
function deleteStadt(event) {
    const button = event.target;
    const bookUUID = button.getAttribute("data-bookuuid");

    fetch("./resource/book/delete?uuid=" + bookUUID,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./bookshelf.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}