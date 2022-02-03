let searchParamElement = document.querySelector("#searchParam");
let search = document.querySelector("#suggest").value;
let searchButt = document.querySelector("#searchButt");
let searchParam = document.querySelector("#searchParam").value;
document.querySelector("#suggest").addEventListener("input", (event) => {
    searchParam = document.querySelector("#searchParam").value;
    switch (searchParam) {
        case "actor":
            $("#suggest").autocomplete({
                source: '/getactorsname'
            });
            break;
        case "character":
            $("#suggest").autocomplete({
                source: '/getcharacters'
            });
            break;
        case "title":
            $("#suggest").autocomplete({
                source: '/getmoviestitle'
            });
            break;
        case "genre":
            $("#suggest").autocomplete({
                source: '/getgenres'
            });
            break;
        case "director":
            $("#suggest").autocomplete({
                source: '/getdirectorsname'
            });
            break;
        default:
            $("#suggest").autocomplete({
                source: '/getmoviestitle'
            });
            break;
    }

})

$(document).ready(() => {
    let requestParams = new URLSearchParams(window.location.search);
    let searchParam = requestParams.get("searchParam");
    let search = requestParams.get("search");

    if (searchParam != null) {
        findMovies("/getmoviesby" + searchParam + "?search=" + search)
        document.querySelector("#filterInformation").innerHTML += "Filtered by the " + searchParam + " " + search;
    } else {
        findMovies("/getmovies")
    }
});

function findMovies(path) {
    let table = $("#tableid").DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": path,
        searching: false,
        "columns": [{
                "data": "movieId"
            },
            {
                "data": "title"
            },
            {
                "data": "budget"
            },
            {
                "data": "tagline"
            },
            {
                defaultContent: '<input type="button" class="redir" name="redir" value="Manage">'
            }
        ]
    });
    //Se le assigna al boton de cada columna la url a la que tiene que redirigir
    $('#tableid tbody').on('click', '.redir', function() {
        let row = $(this).closest('tr');

        let data = table.row(row).data().movieId;
        window.location.href = "/movie/" + data;
    });
}