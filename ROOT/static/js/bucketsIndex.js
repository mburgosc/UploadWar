import * as checkInputs from "./checkInputFunctions.js";


let textContainer = document.querySelector(".txt");
let infoContainer = document.querySelector(".info-txt");


textContainer.addEventListener("input", () =>{
    if (checkInputs.lengthBetween(textContainer,3,12)
    && checkInputs.onlyLettersAndNumbers(textContainer)) {

        infoContainer.style.visibility = "hidden";
    }else{
       infoContainer.style.visibility = "visible";

    }
})    
