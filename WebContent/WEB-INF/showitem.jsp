<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-top.jsp" />

<hr>
<font color="red"><c:out value="${sessionScope.message}" /></font>
<c:remove var="message" scope="session" />

<h3>Search for item:</h3>
<form method="POST" action="search.do">
    <table>
        <tr>
            <td>Keywords (separate by space):</td>
            <td><input type="text" name="query" value="${form.query}" /></td>
        </tr>
        <tr>
            <td colspan="3" align="center"><input type="submit"
                name="button" value="Search" /></td>
        </tr>
    </table>
</form>
<hr>

<h3>Items for sell:</h3>
<table rules="all" cellpadding="20">
    <tr>
        <th></th>
        <th>User Name</th>
        <th>User Email</th>
        <th>Description</th>
        <th>Price</th>
        <th>Add Date</th>
        <th>Picture</th>
    </tr>

    <c:set var="i" scope="page" value="${1}" />
    <c:set var="itemList" value="${requestScope.itemList}" />
    <c:forEach var="cur" items="${itemList}">
        <tr>
            <td><c:out value="${pageScope.i}" /></td>
            <td><c:out value="${cur.getUserName()}" /></td>
            <td><a href="mailto:${cur.getEmail()}"><c:out
                        value="${cur.getEmail()}" /></a></td>
            <td><c:out value="${cur.getDescription()}" /></td>
            <td><c:out value="${cur.getPrice()}" /></td>
            <!-- <td><c:out value="${fn:substring(cur.getListDate(), 0, 15)}" /></td> -->
            <td><c:out value="${cur.getListDate()}" /></td>
            <!-- <td><img src="image.do?id=${cur.getItemID()}" /></td> -->
            <td><a href="image.do?id=${cur.getItemID()}" target="_blank">Link</a>
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
