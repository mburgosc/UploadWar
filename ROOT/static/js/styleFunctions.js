// Hace que el tamaño de los archivos sea mas legible
export function sizeConverter(sizeContainers) {

    sizeContainers.forEach(size => {
        switch (size.innerHTML.length) {
            case 4:
            case 5:
            case 6:
                size.innerHTML = (parseInt(size.innerHTML,10)/1000).toFixed(2)+" KB"
                break;

            case 7:
            case 8:
            case 9:
                size.innerHTML = (parseInt(size.innerHTML,10)/1000000).toFixed(2)+" MB"
                break;

            case 10:
            case 11:
            case 12:
            /*
            *  En realidad no se puede subir un archivo tan grande porque se agota el tiempo de espera
            *  del servidor ya que hasta que no se sube el archivo no se sirven mas paginas.
            *  Esto se solucionará mas adelante
            */
                size.innerHTML = (parseInt(size.innerHTML,10)/1000000000).toFixed(2)+" GB"
                break;
            default:
                size.innerHTML += " B"
                break;
        }
    });
}

export function modalOpener(openButt,modalBg,modal,closeButt) {

    //OPEN
    openButt.addEventListener("click", () => {
        modalBg.style.display = "flex";
        modal.style.display = "flex";

    })

    //CLOSE
    modalBg.addEventListener("click", () => {
        modalBg.style.display = "none";
        modal.style.display = "none";

    })

    closeButt.addEventListener("click", () => {
        modalBg.style.display = "none";
        modal.style.display = "none";

    })
}