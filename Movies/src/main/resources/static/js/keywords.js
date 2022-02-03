document.querySelector("#suggest").addEventListener("input", (event) => {
    $("#suggest").autocomplete({
        source: '/getkeywordsname'
    });
})

$(document).ready(() => {

    let requestParams = new URLSearchParams(window.location.search);
    let search = requestParams.get("search");

    if (search != null) {
        findMovies("/getkeywordsbyname?search=" + search)
        document.querySelector("#filterInformation").innerHTML += "Filtered by '" + search + "'";
    } else {
        findMovies("/getkeywords")
    }
});

function findMovies(path) {
    let table = $("#tableid").DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": path,
        searching: false,
        "columns": [{
                "data": "keywordName"
            },
            {
                defaultContent: '<input type="button" class="redir" name="redir" value="Manage">'
            }
        ]
    });
    $('#tableid tbody').on('click', '.redir', function() {
        let row = $(this).closest('tr');
        let data = table.row(row).data().keywordId;
        window.location.href = "/keyword/" + data;
    });
}