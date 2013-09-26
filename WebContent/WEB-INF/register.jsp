<jsp:include page="template-top.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2 align="center">New User Register</h2>

<hr>

<p style="font-size: medium">Please register below.</p>


<form action="register.do" method="POST">
    <table width="30%" cellspacing="15" align="center">
        <tr>
            <td style="font-size: large">User Name:</td>
            <td><input type="text" name="newUserName"
                value="${form.newUserName}" /></td>
        </tr>
        <tr>
            <td style="font-size: large">E-mail Address:</td>
            <td><input type="text" name="newEmail" value="${form.newEmail}" /></td>
        </tr>
        <tr>
            <td style="font-size: large">First Name:</td>
            <td><input type="text" name="newFirstName"
                value="${form.newFirstName}" /></td>
        </tr>
        <tr>
            <td style="font-size: large">Last Name:</td>
            <td><input type="text" name="newLastName"
                value="${form.newLastName}" /></td>
        </tr>
        <tr>
            <td style="font-size: large">Password:</td>
            <td><input type="password" name="newPass" /></td>
        </tr>
        <tr>
            <td style="font-size: large">Confirm Password:</td>
            <td><input type="password" name="newPassCon" /></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit"
                name="button" value="Register" /></td>
        </tr>
    </table>
</form>


<c:forEach var="error" items="${errors}">
    <h3 style="color: red">${error}</h3>
</c:forEach>

<hr>


<jsp:include page="template-bottom.jsp" />
