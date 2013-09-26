<jsp:include page="template-top.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2 align="center">User Login</h2>

<hr>

<p style="font-size: medium">
    Please login below or click <a href="register.do">here</a> to register
    as a new user.
</p>


<form action="login.do" method="POST">
    <table width="30%" cellspacing="15" align="center">
        <tr>
            <td style="font-size: large">E-mail Address:</td>
            <td><input type="text" name="email" value="${form.email}" /></td>
        </tr>
        <tr>
            <td style="font-size: large">Password:</td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit"
                name="button" value="Login" /></td>
        </tr>
    </table>
</form>


<c:forEach var="error" items="${errors}">
    <h3 style="color: red">${error}</h3>
</c:forEach>

<hr>


<jsp:include page="template-bottom.jsp" />
