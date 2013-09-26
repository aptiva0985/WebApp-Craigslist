<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-top.jsp" />

<hr>
<font color="red"><c:out value="${sessionScope.message}" /></font>
<c:remove var="message" scope="session" />
<c:set var="user" scope="session" value="${sessionScope.user}" />

<h3>Add a item for sale:</h3>
<form method="post" action="upload.do" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Description:</td>
            <td><input type="text" name="description" value="${form.description}" /></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="text" name="price" value="${form.price}" /></td>
        </tr>
        <tr>
            <td>Picture file:</td>
            <td colspan="2"><input type="file" name="file"
                value="${filename}" /></td>
        </tr>
        <tr>
            <td colspan="3" align="center"><input type="submit"
                name="button" value="Add!" /></td>
        </tr>
    </table>
</form>
<hr>

<h3>
    Current items of
    <c:out value="${user.getUserName()}" />
    :
</h3>
<table rules="all" cellpadding="20">
    <tr>
        <th></th>
        <th>User Name</th>
        <th>User Email</th>
        <th>Description</th>
        <th>Price</th>
        <th>Add Date</th>
        <th>Picture</th>
        <th></th>
    </tr>

    <c:set var="i" scope="page" value="${1}" />
    <c:set var="itemList" value="${requestScope.usrItemList}" />
    <c:forEach var="cur" items="${itemList}">
        <tr>
            <td><c:out value="${pageScope.i}" /></td>
            <td><c:out value="${cur.getUserName()}" /></td>
            <td><a href="mailto:${cur.getEmail()}"><c:out
                        value="${cur.getEmail()}" /></a></td>
            <td><c:out value="${cur.getDescription()}" /></td>
            <td><c:out value="${cur.getPrice()}" /></td>
            <!--<td><c:out value="${fn:substring(cur.getListDate(), 0, 15)}" /></td> -->
            <td><c:out value="${cur.getListDate()}" /></td>
            <!--<td><img src="image.do?id=${cur.getItemID()}" /></td> -->
            <td><a
                href="image.do?id=${cur.getItemID()}" target="_blank">Link</a>
            </td>
            <td>
                <form method="POST" action="delete.do">
                    <input type="hidden" name="id" value="${cur.getItemID()}" /> <input
                        type="submit" value="Delete" />
                </form>
            </td>
        </tr>
        <c:set var="i" scope="page" value="${pageScope.i + 1}" />
    </c:forEach>

</table>
<c:forEach var="error" items="${errors}">
    <h3 style="color: red">${error}</h3>
</c:forEach>
<hr>

<jsp:include page="template-bottom.jsp" />
