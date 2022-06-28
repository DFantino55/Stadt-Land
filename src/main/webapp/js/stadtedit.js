/**
 * editiert Städte
 *
 * @author  Diego F.
 * @since   2022-06-28
 * @version 1.0
 */

document.addEventListener("DOMContentLoaded", () => {
    readLaender();
    readStadt();

    document.getElementById("bookeditForm").addEventListener("submit", saveStadt);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * speichert Daten einer Stadt
 */
function saveStadt(event) {
    event.preventDefault();

    const bookForm = document.getElementById("bookeditForm");
    const formData = new FormData(bookForm);
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
    window.location.href = "städte.html";
}

/**
 * liest eine Stadt
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
 * zeigt die Daten einer stadt
 * @param data daten der Stadt
 */
function showStadt(data) {
    document.getElementById("uuid").value = data.uuid;
    document.getElementById("bezeichnung").value = data.bezeichnung;
    document.getElementById("bevoelkerung").value = data.bevoelkerung;
    document.getElementById("flaeche").value = data.flaeche;

    var select = document.getElementById("land");
    for(var i = 0; i < select.options.length;i++) {
        if(select.options[i].text == data.land.bezeichnung) {
            select.options[i].selected = true;
        }
    }

}

/**
 * liest alle länder als array
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
 * zeigt alle länder als dropdown
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
 * verlinkt zu städte.html
 * @param event clickevent
 */
function cancelEdit(event) {
    window.location.href = "städte.html";
}