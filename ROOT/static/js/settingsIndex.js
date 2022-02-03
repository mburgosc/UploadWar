import {modalOpener} from "./styleFunctions.js";
import * as checkInputs from "./checkInputFunctions.js";

//Modal Styler
let openButt = document.querySelector(".modal-butt");
let modalBg = document.querySelector(".modal-background");
let modal = document.querySelector(".modal");
let closeButt = document.querySelector(".btn-close");
modalOpener(openButt,modalBg,modal,closeButt);

//Input Checks

//Inputs' checks
let textContainers = document.querySelectorAll(".txt");
let infoContainers = document.querySelectorAll(".info-txt");

textContainers.forEach((textContainer,count) => {
    textContainer.addEventListener("input", () =>{
        if (checkInputs.onlyLettersAndNumbers(textContainer)) {
            infoContainers[count].style.visibility = "hidden";
        }else{
            infoContainers[count].style.visibility = "visible";
        }
    }) 
});