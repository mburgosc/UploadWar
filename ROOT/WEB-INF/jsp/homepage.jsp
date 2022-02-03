<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="icon" type="image/x-icon" href="<c:url value=" /static/img/favicon.png" />">
  <link href="<c:url value=" /static/bootstrap-5.1.3-dist/css/bootstrap.min.css" />" rel="stylesheet">
  <title>Practica Bucket</title>
</head>

<body>

  <header>
    <div class="navbar navbar-dark bg-dark box-shadow">
      <div class="container d-flex justify-content-between">
        <a href="#" class="navbar-brand d-flex align-items-center">
          <strong>Practica Bucket</strong>
        </a>
      </div>
    </div>
  </header>

  <main role="main">

    <section class="jumbotron text-center">
      <div class="container">
        <h1 class="jumbotron-heading">Practica Bucket</h1>
        <p class="lead text-muted">Bienvenido a la practica Bucket, esta es una aplicacion web destinada a almacenar
          informacion de manera organizada, accede a tu espacio o registrate para crear uno nuevo si es la primera vez
          que nos visitas.</p>
        <p>
          <a href="/login" class="btn btn-primary my-2">Acceder</a>
          <a href="/register" class="btn btn-secondary my-2">Registrarse</a>
        </p>
      </div>
    </section>

  </main>

  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
    crossorigin="anonymous"></script>

  <script src="<c:url value=" /static/bootstrap-5.1.3-dist/js/bootstrap.min.js" />">
</body>

</html>