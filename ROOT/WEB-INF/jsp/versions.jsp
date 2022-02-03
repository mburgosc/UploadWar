<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" type="image/x-icon" href="<c:url value=" /static/img/favicon.png" />">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
        id="bootstrap-css">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
        integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
        crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/575a4511cb.js" crossorigin="anonymous"></script>
    <link href="<c:url value=" /static/css/items.css" />" rel="stylesheet">
    <title>${objectname}</title>
</head>

<body>

    <header class="py-3 pt-3 bg-dark text-white">
        <div class="d-flex justify-content-between">
            <div class="d-flex justify-content-center">
                <h2 class="text-light w-75">Bucket: ${bucketname}</h2>
                <nav class="text-dark text-decoration-none">
                    <a class="btn btn-light px-2 ml-2" href="/objects">Mis buckets</a>
                </nav>
            </div>

            <div class="d-flex text-decoration-none ">

                <a class="btn btn-outline-success" href="/settings"><i class="fas fa-user-cog"></i>
                    ${username}</a>
                <a class="btn btn-danger p-2 mx-2" href="/logOut"><i class="fas fa-sign-out-alt"></i></a>
            </div>
        </div>

    </header>

    <c:if test="${not empty message}">
        <div class="messageContainer text-center alert " style="background-color: ${messageColor};">
            ${message}
        </div>
    </c:if>



    <main class="container-lg  p-4 mx-4 mt-4">
        <header class="bg-light d-flex flex-inline w-100">
            <a class="btn btn-info" href="/objects/${bucketuri}/${parentdirectory}">
                <i class="fas fa-chevron-left"></i><i class='far fa-folder'></i>
            </a>
            <h3 class="ml-2">/${parentdirectory}${objectname}</h3>
        </header>
        <div class="bg-white mt-1 px-2 py-4">
            <div>
                <ul class="list-unstyled">
                    <li>Nombre del objeto: ${objectname}</li>
                    <li>Bucket: ${bucketuri}</li>
                    <li>Directorio padre: /${parentdirectory}</li>
                    <li>Tipo de archivo: ${versions[versionsnum-1].type}</li>
                    <li>Numero de versiones: ${versionsnum}</li>
                    <li>Fecha de creacion: ${versions[0].creationDate}</li>
                    <li>Ultima actualizacion: ${versions[versionsnum-1].creationDate}</li>
                </ul>
            </div>
            <ul class="list-group no-border mail-list bg-secondary">
                <c:forEach var="object" items="${versions}" varStatus="loop">
                    <li class="list-group-item">
                        <a class="bg-light row mb-1 px-4 pt-4 text-dark" href="/download/${object.id}">
                            <p class="col">Num. Version: ${loop.index + 1}</p>
                            <p class="col">Tama&ntilde;o: <span class="size">${object.size}</span></p>
                            <p class="col">A&ntilde;adida en: ${object.creationDate}</p>
                            <form class="col-2" method="post" action="/deleteversion/${object.id}">
                                <input name="csrftoken" type="hidden" value="${csrftoken}">
                                <input name="bucketuri" type="hidden" value="${bucketname}">
                                <input name="pathtoredirect" type="hidden" value="${parentdirectory}">
                                <button type="submit" class="btn btn-outline-danger"><i
                                        class="far fa-trash-alt"></i></button>
                            </form>
                        </a>

                    </li>
                </c:forEach>
        </div>
    </main>
    <script src="<c:url value=" /static/js/versionsIndex.js" />" type="module"></script>
</body>

</html>