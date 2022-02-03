import * as checkInputs from "./checkInputFunctions.js";


let textContainers = document.querySelectorAll(".txt");
let infoContainers = document.querySelectorAll(".info-txt");

textContainers.forEach((container,counter) => {
    container.addEventListener("input", () =>{
        if (checkInputs.lengthBetween(container,4,20) 
        && checkInputs.onlyLettersAndNumbers(container)) {
            infoContainers[counter].style.display = "none";
        }else{
            
            infoContainers[counter].style.display = "block";
        }
    })    
});

let passwd = document.querySelector(".passwd");
let repeatPasswd = document.querySelector(".repeat-passwd");

let TrueSendButt = document.querySelector(".trueSend");
let infoReCont = document.querySelector(".info-re-passwd");

if (repeatPasswd) {
    repeatPasswd.addEventListener("input", () =>{
        if (checkInputs.sameInput(repeatPasswd.innerHTML,passwd.innerHTML)) { 
            infoReCont.style.display = "none";
        }else{
            infoReCont.style.display = "block";
        }
    })
}

let sendButt = document.querySelector(".send");

if (sendButt) {
    sendButt.addEventListener("click", () => {

        if (checkInputs.sameInput(repeatPasswd,passwd)) { 
            TrueSendButt.click();
        }else{
            infoReCont.style.display = "block";
        }
});
}