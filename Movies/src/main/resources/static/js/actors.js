document.querySelector("#suggest").addEventListener("input", (event) => {
    $("#suggest").autocomplete({
        source: '/getactorsname'
    });
})

$(document).ready(() => {

    let requestParams = new URLSearchParams(window.location.search);
    let search = requestParams.get("search");

    if (search != null) {
        document.querySelector("#filterInformation").innerHTML += "Filtered by the actor " + search;
        findMovies("/getactorsbyname?search=" + search)
    } else {

        findMovies("/getactors")
    }
});

function findMovies(path) {
    let table = $("#tableid").DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": path,
        searching: false,
        "columns": [{
                "data": "personId"
            },
            {
                "data": "personName"
            },
            {
                defaultContent: '<input type="button" class="redir" name="redir" value="Manage">'
            }
        ]
    });
    $('#tableid tbody').on('click', '.redir', function() {
        let row = $(this).closest('tr');
console.log(table.row(row).data().personId)
        let data = table.row(row).data().personId;
        window.location.href = "/actor/" + data;
    });
}