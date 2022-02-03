<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="icon" type="image/x-icon" href="<c:url value=" /static/img/favicon.png" />">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
        id="bootstrap-css">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
        integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
        crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value=" /static/css/settings.css" />">
    <script src="https://kit.fontawesome.com/575a4511cb.js" crossorigin="anonymous"></script>
    <title>Ajustes Usuario</title>
</head>

<body>
   <c:if test="${not empty message}">

      <div class="messageContainer text-center alert " style="background-color: ${messageColor};">

         ${message}

      </div>

   </c:if>
    <header class="py-3 pt-3 bg-dark text-white">
        <div class="d-flex justify-content-between">
            <div class="d-flex">
                <h2 class="text-light">Parametros de Usuario</h2>
                <nav class="text-dark text-decoration-none">
                    <a class="btn btn-light px-2" href="/objects">Mis buckets</a>
                </nav>
            </div>
            <div class="d-flex text-decoration-none ">
                <a class="btn btn-outline-success" href="/settings"><i class="fas fa-user-cog"></i>
                    ${username}</a>
                <a class="btn btn-danger p-2 mx-2" href="/logOut"><i class="fas fa-sign-out-alt"></i></a>
            </div>
        </div>

    </header>
    <main class="container-lg  p-4 mx-4 mt-4 d-flex justify-content-center">
        <div class="bg-white w-25 mt-1 px-2 py-4">
            <ul class="list-unstyled">
                <li>Nombre de usuario: ${uname}</li>
                <li>Nombre completo: ${name} ${surnames} </li>
            </ul>
            <div class="d-flex flex-inline">
                <button class="btn btn-info modal-butt"><i class="fas fa-user-edit"> Editar
                        parametros</i></button>
                <form action="/settings" method="POST">
                    <input name="csrftoken" type="hidden" value="${csrftoken}">
                    <input name="delete" type="hidden" value="true">
                    <button class="btn btn-danger ml-2" type="submit"><i class="fas fa-user-minus"></i>Eliminar
                        usuario</button>
                </form>
            </div>
        </div>
    </main>

    <div class="modal-background">
    </div>
    <div class="modal">
        <header>
            <button type="button" class="btn-close float-right" aria-label="Close"><i
                    class="fas fa-window-close"></i></button>
        </header>
        <main>
            <form action="/settings" method="POST">
                <input name="csrftoken" type="hidden" value="${csrftoken}">
                <div class="form-group">
                    <label>Nombre</label>
                    <input name="name" class="txt" value="${name}">
                    <p class="info-txt">usa solo letras y numeros y pon entre 3 y 12 caracteres</p>
                </div>
                <div class="form-group">
                    <label>Apellidos</label>
                    <input name="surnames" class="txt" value="${surnames}">
                    <p class="info-txt">usa solo letras y numeros y pon entre 3 y 12 caracteres</p>
                </div>

                <button class="btn btn-success" type="submit"><i class="fas fa-check"></i></button>
            </form>
        </main>
    </div>
    <script src="<c:url value=" /static/js/settingsIndex.js" />" type="module"></script>
</body>

</html>