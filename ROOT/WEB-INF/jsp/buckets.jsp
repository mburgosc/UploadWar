<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" type="image/x-icon" href="<c:url value=" /static/img/favicon.png" />">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
        integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/575a4511cb.js" crossorigin="anonymous"></script>
    <link href="<c:url value=" /static/css/items.css" />" rel="stylesheet">
    <title>Buckets</title>
</head>

<body>
    <header class="py-3 pt-3 bg-dark text-white">
        <div class="d-flex justify-content-between">
            <div class="d-flex justify-content-center">
                <h2 class="text-light">Home de ${username}</h2>
            </div>

            <div class="d-flex text-decoration-none ">

                <a class="btn btn-outline-success" href="/settings"><i class="fas fa-user-cog"></i> ${username}</a>
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
        <ul class="list-group no-border mail-list bg-secondary pb-2 ">

            <c:forEach var="bucket" items="${buckets}">
                <li class="list-group-item">
                    <a href="/objects/${bucket.name}" class="bg-light d-flex flex-row mb-1 px-4 pt-4 text-dark">
                        <div class="item-container row">
                            <p class="col">Nombre: <span class="font-weight-bold">${bucket.name}</span></p>
                            <p class="col-6">
                                Ultima modificacion:
                                <span class="text-secondary">
                                    <c:choose>
                                        <c:when test="${not empty bucket.lastObjectModified}">
                                            ${bucket.lastObjectModified}
                                        </c:when>
                                        <c:when test="${empty bucket.lastObjectModified}">
                                            -
                                        </c:when>
                                    </c:choose>
                                </span>
                            </p>
                            <p class="col">
                                en la fecha:
                                <span class="text-secondary">
                                    <c:choose>
                                        <c:when test="${not empty bucket.lastModification}">
                                            ${bucket.lastModification}
                                        </c:when>
                                        <c:when test="${empty bucket.lastModification}">
                                            -
                                        </c:when>
                                    </c:choose>
                                </span>
                            </p>

                            <form class action="/deletebucket/${bucket.name}" method="POST">
                                <input name="csrftoken" type="hidden" value="${csrftoken}">
                                <button type="submit" class="btn btn-outline-danger"><i
                                        class="far fa-trash-alt"></i></button>
                            </form>
                        </div>
                    </a>
                </li>
            </c:forEach>
        </ul>
        <div class="bg-white mt-4 p-2 pl-4">
            <h2 class="text-nowrap"> Crear un nuevo bucket</h2>
            <form class="d-flex align-items-center bucket-form" action="/objects" method="post">

                <label class="text-dark mr-4">Nombre</label>
                <div class="text-container">
                    <input type="text" name="name" class="txt form-control" placeholder="Nombre unico">
                    <p class="info-txt">usa solo letras y numeros y pon entre 3 y 12 caracteres</p>
                </div>
                <input name="csrftoken" type="hidden" value="${csrftoken}">
                <button type="submit" class="btn btn-block btn-primary ">Crear</button>

            </form>
        </div>
    </main>
    <script src="<c:url value=" /static/js/bucketsIndex.js" />" type="module"></script>
</body>

</html>