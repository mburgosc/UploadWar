<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <link rel="icon" type="image/x-icon" href="<c:url value=" /static/img/favicon.png" />">
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
  <link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
    integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
  <script src="https://kit.fontawesome.com/575a4511cb.js" crossorigin="anonymous"></script>
  <link href="<c:url value=" /static/css/items.css" />" rel="stylesheet">
  <title>${bucketname}</title>
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
    <header class="bg-light d-flex flex-inline w-100">
      <c:if test="${not empty objpath}">
        <a class="btn btn-info" href="/objects/${bucketuri}/${parentdirectory}">

          <i class="fas fa-chevron-left"></i><i class='far fa-folder'></i>
        </a>
      </c:if>
      <h3 class="ml-2">/${objpath}${name}</h3>
    </header>

    <div class="bg-white mt-1 px-2 py-4">

      <c:if test="${not empty objpath}">
        <div class="card col-md-3">
          <div class="card-body">
            <a class="nameContainer font-weight-bold" href="${parentpath}"> ../ Directorio anterior/</a>
          </div>
        </div>
      </c:if>
      <ul class="list-group no-border mail-list bg-secondary">
        <c:forEach var="name" varStatus="count" items="${objsname}">
          <li class="list-group-item">
            <a class="objContainer bg-light row mb-1 px-4 pt-4 text-dark"
              href="/objects/${bucketname}/${objpath}${name}">
              <p class="nameContainer col-9">${name}</p>
              <form class="col" action="/deleteobject/${objpath}${name}" method="POST">
                <!--js: Estas seguro?-->
                <input name="csrftoken" type="hidden" value="${csrftoken}">
                <input name="bucketuri" type="hidden" value="${bucketuri}">
                <input name="pathtoredirect" type="hidden" value="${parentpath}">
                <button type="submit" class="btn btn-outline-danger"><i class="far fa-trash-alt"></i></button>
              </form>
            </a>
          </li>
        </c:forEach>
      </ul>
    </div>
    <div class="bg-white container-sm mt-4 p-2 pl-4">
      <form action="/objects/${bucketname}" method="post" enctype="multipart/form-data">
        <div class="form-group">
          <label>Nombre del fichero</label>
          <input type="text" name="objectName" class="txt form-control">
          <p class="info text-secondary">Se puede dejar en blanco</p>
        </div>
        <div class="form-group">
          <label>Directorio</label>
          <input type="text" name="objectPath" value="${objpath}" class="txt form-control">
          <p class="info text-secondary">Si no sabes que poner dejalo como esta</p>
        </div>
        <div class="form-group">
          <label>fichero</label>
          <input type="file" name="file" class="form-control">
        </div>

        <div class="col-md-12 text-center mb-3">
          <input name="csrftoken" type="hidden" value="${csrftoken}">
          <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Subir fichero</button>
        </div>
      </form>
    </div>
  </main>
  <script src="<c:url value=" /static/js/objectsIndex.js" />" type="module"></script>
</body>

</html>