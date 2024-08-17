<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="header.jsp" %>
  <body>
    <header>
      <nav class="container container--70">
        <ul class="nav--actions">
          <li><a href="<c:url value="/login"/>">Zaloguj</a></li>
          <li class="highlighted"><a href="<c:url value="/registration"/>">Załóż konto</a></li>
        </ul>

        <ul>
          <li><a href="/" class="btn btn--without-border active">Start</a></li>
          <li><a href="/#steps" class="btn btn--without-border">O co chodzi?</a></li>
          <li><a href="/#about-us" class="btn btn--without-border">O nas</a></li>
          <li><a href="/#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
          <li><a href="/#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
      </nav>
    </header>

    <c:if test="${not empty thanksMessage}">
      <script type="text/javascript">
        alert("<c:out value='${thanksMessage}'/>");
      </script>
    </c:if>

    <section class="login-page">
      <h2>Zaloguj się</h2>
      <form method="post" action="/perform_login">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <div class="form-group">
          <input type="text" name="username" required="required" placeholder="Email"/>
        </div>

        <div class="form-group">
          <input type="password" name="password" required="required" placeholder="Hasło"/>
          <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>

        <div class="form-group form-group--buttons">
          <a href="<c:url value="/registration"/>" class="btn btn--without-border">Załóż konto</a>
          <input type="submit" class="btn" value="Zaloguj się"/>
        </div>
      </form>
    </section>

    <%@ include file="footer.jsp" %>
  </body>
</html>
