import * as checkInputs from "./checkInputFunctions.js";

//Directorys and files Styler
const nameContainers = document.querySelectorAll(".objContainer");

nameContainers.forEach(element => {
        
    const itemName = element.querySelector(".nameContainer").innerHTML;

    if (itemName.charAt(itemName.length-1) == "/") {
        element.querySelector(".nameContainer").innerHTML = "<i class='far fa-folder'></i> "+itemName.slice(0, -1);
        element.querySelector("button").style.display= "none";
    }else{
        element.querySelector(".nameContainer").innerHTML = "<i class='far fa-file'></i> "+itemName;
    }

});

//Inputs' checks
let textContainers = document.querySelectorAll(".txt");
let infoContainers = document.querySelectorAll(".info");

textContainers.forEach((textContainer,count) => {
    textContainer.addEventListener("input", () =>{
        if (checkInputs.onlyLettersAndNumbers(textContainer)) {
            infoContainers[count].style.visibility = "hidden";
        }else{
            infoContainers[count].style.visibility = "visible";
            infoContainers[count].classList.remove("text-secondary");
            infoContainers[count].style.color = "red";
            infoContainers[count].innerHTML = "usa solo letras y numeros";
        

        }
    }) 
});
