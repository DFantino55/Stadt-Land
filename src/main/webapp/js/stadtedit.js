document.addEventListener("DOMContentLoaded", () => {
    readLaender();
    readStadt();

    document.getElementById("bookeditForm").addEventListener("submit", saveStadt);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * saves the data of a book
 */
function saveStadt(event) {
    event.preventDefault();

    const bookForm = document.getElementById("bookeditForm");
    const formData = new FormData(stadtForm);
    const data = new URLSearchParams(formData);

    let method;
    let url = "./resource/stadt/";
    const uuid = getQueryParam("uuid");
    if (uuid == null) {
        method = "POST";
        url += "create";
    } else {
        method = "PUT";
        url += "update";
    }

    fetch(url,
        {
            method: method,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: data
        })
        .then(function (response) {
            if (!response.ok) {
                console.log(response);
            } else return response;
        })
        .then()
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * reads a book
 */
function readStadt() {
    const uuid = getQueryParam("uuid");
    fetch("./resource/stadt/read?uuid=" + uuid)
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showStadt(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of a book
 * @param data  the book-data
 */
function showStadt(data) {
    document.getElementById("uuid").value = data.uuid;
    document.getElementById("bezeichnung").value = data.bezeichnung;
}

/**
 * reads all publishers as an array
 */
function readLaender() {

    fetch("./resource/land/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showLaender(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows all publishers as a dropdown
 * @param data
 */
function showLaender(data) {
    let dropdown = document.getElementById("land");
    data.forEach(land => {
        let option = document.createElement("option");
        option.text = land.bezeichnung;
        option.value = land.laendercode;
        dropdown.add(option);
    })
}

/**
 * redirects to the bookshelf
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "städte.html";
}