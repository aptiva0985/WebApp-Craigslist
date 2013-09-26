<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-top.jsp" />

<hr>

<h3>
    Search Result of "
    <c:out value="${requestScope.queryInput}" />
    ":
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
    </tr>

    <c:set var="i" scope="page" value="${1}" />
    <c:set var="resultList" value="${requestScope.resultList}" />
    <c:forEach var="cur" items="${resultList}">
        <tr>
            <td><c:out value="${pageScope.i}" /></td>
            <td><c:out value="${cur.getUserName()}" /></td>
            <td><a href="mailto:${cur.getEmail()}"><c:out
                        value="${cur.getEmail()}" /></a></td>
            <td><c:out value="${cur.getDescription()}" /></td>
            <td><c:out value="${cur.getPrice()}" /></td>
            <!-- <td><c:out value="${fn:substring(cur.getListDate(), 0, 15)}" /></td> -->
            <td><c:out value="${cur.getListDate()}" /></td>
            <!--<td><img src="image.do?id=${cur.getItemID()}" /></td> -->
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
