<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <link rel="icon" type="image/x-icon" href="<c:url value=" /static/img/favicon.png" />">
   <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
   <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
   <link href="<c:url value=" /static/css/loginAndRegister.css" />" rel="stylesheet">
   <title>Register</title>
</head>

<body>

   <c:if test="${not empty message}">

      <div class="messageContainer text-center alert " style="background-color: ${messageColor};">

         ${message}

      </div>

   </c:if>
   <div class="container">
      <div class="row">
         <div class="col-md-5 mx-auto">
            <div id="first">
               <div class="myform form ">
                  <div class="logo mb-3">
                     <div class="col-md-12 text-center">
                        <h1>Registrarse</h1>
                     </div>
                  </div>
                  <form action="/register" method="post">
                     <div class="form-group">
                        <label>Nombre de usuario</label>
                        <input type="text" name="username" class="txt form-control"
                           placeholder="Introduce nombre de usuario">
                        <p class="info-txt">usa solo letras y numeros y pon entre 4 y 20 caracteres</p>
                     </div>
                     <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" name="name" class="txt form-control" placeholder="Nombre propio">
                        <p class="info-txt">usa solo letras y numeros y pon entre 3 y 20 caracteres</p>
                        <label>Apellidos</label>
                        <input type="text" name="surnames" class="txt form-control" placeholder="Apellidos">
                        <p class="info-txt">usa solo letras y numeros y pon entre 3 y 20 caracteres</p>
                     </div>
                     <div class="form-group">
                        <label>Contrase&ntilde;a</label>
                        <input type="password" name="password" class="txt passwd form-control" id="password"
                           placeholder="Introduce  contrase&ntilde;a">
                        <p class="info-txt">usa solo letras y numeros y pon entre 5 y 20 caracteres</p>
                        <label>Repetir Contrase&ntilde;a</label>
                        <input type="password" name="repeat" class="txt repeat-passwd form-control" id="repeat"
                           placeholder="Repite la contrase&ntilde;a">
                        <p class="info-txt">usa solo letras y numeros y pon entre 5 y 20 caracteres</p>
                        <p class="info-re-passwd">las contrase&ntilde;as no coinciden</p>
                     </div>
                     <div class="col-md-12 text-center mb-3">
                        <input name="csrftoken" type="hidden" value="${csrftoken}">
                        <div class=" btn btn-block mybtn btn-primary tx-tfm send">Registrarse</div>
                        <button type="submit" class="trueSend"></button>

                     </div>
                     <div class="col-md-12 ">
                        <div class="form-group">
                           <p class="text-center"><a href="/login" id="signin">Ya tienes una cuenta?</a></p>
                        </div>
                     </div>
               </div>
               </form>
            </div>
         </div>
      </div>
   </div>
   <script src="<c:url value=" /static/js/authIndex.js" />" type="module"></script>
</body>

</html>