let dateInput = document.querySelector("#releaseDate");

dateInput.value = "[[${movie.releaseDate}]]".split(" ")[0];


let keywords = document.querySelectorAll("#keyword");

keywords.forEach((keyword) => {
    keyword.addEventListener("click", () => {
        if (keyword.classList.contains("selected")) {
            keyword.classList.remove("selected")
            keyword.classList.add("notSelected")
        } else {
            keyword.classList.remove("notSelected")
            keyword.classList.add("selected")
        }

    })
})

document.querySelector("#remove").addEventListener("click", () => {

    let selectedKeywords = Array.prototype.slice.call(document.querySelectorAll(".selected"));

    let jsonKeywords = selectedKeywords.map((keyword) => keyword.innerHTML);

    jsonKeywords = JSON.stringify(jsonKeywords);
    if (selectedKeywords.length > 0) {
        $.ajax({
            url: "/removemoviekeywords",
            type: "POST",
            data: {
                movieId: "[[${movie.movieId}]]",
                JsonKeywords: jsonKeywords
            }
        });
        selectedKeywords.forEach((keyword) => {
            keyword.parentNode.removeChild(keyword);
        })
    } else {
        alert("To remove select unless one keyword");
    }
})