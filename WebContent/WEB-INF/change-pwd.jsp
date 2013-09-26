<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />

<p style="font-size: medium">Set your new password</p>

<p>
<form method="POST" action="change-pwd.do">
    <table>
        <tr>
            <td>Old Password:</td>
            <td><input type="password" name="oldPassword"
                value="${form.oldPassword}" /></td>
        </tr>
        <tr>
            <td>New Password:</td>
            <td><input type="password" name="newPassword" value="" /></td>
        </tr>
        <tr>
            <td>Confirm New Password:</td>
            <td><input type="password" name="confirmPassword" value="" /></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit"
                name="button" value="Change Password" /></td>
        </tr>
    </table>
</form>
</p>



<c:forEach var="error" items="${errors}">
    <h3 style="color: red">${error}</h3>
</c:forEach>

<jsp:include page="template-bottom.jsp" />
