<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />

<c:set var="errors" scope="page" value="${requestScope.errors}" />

<c:if test="${errors != null && errors.size() > 0}">
    <p style="font-size: medium; color: red">
        <c:forEach var="cur" items="${errors}">
            <c:out value="${cur}" />
            <br />
        </c:forEach>
    </p>
</c:if>

<p>
    <a href="showitem.do">Back to homepage</a>
</p>

<jsp:include page="template-bottom.jsp" />
