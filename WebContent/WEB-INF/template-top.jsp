<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="pragma" content="no-cache">
<title>My Craigslist</title>
<style>
.menu-head {
    font-size: 15pt;
    font-weight: bold;
    color: black;
    align: center;
}

.menu-item {
    font-size: 15pt;
    color: black;
    align: center;
}
</style>
</head>

<body>
    Deafult users' E-mails are "aaa@gmail.com" "bbb@gmail.com" and
    "ccc@gmail.com".
    <br /> All passwords are 111.
    <br />

    <table cellpadding="4" cellspacing="0" width=100%>
        <tr>
            <!-- Banner row across the top -->
            <td align="center" width=75%>
                <p>
                    <c:set var="user" scope="session" value="${sessionScope.user}" />
                    <c:set var="title" scope="session" value="${user.getUserName()}" />
                    <c:choose>
                        <c:when test="${title == null}">
                            <font size="5"><a href="showitem.do">My Craigslist</a></font>
                        </c:when>
                        <c:otherwise>
                            <font size="5">Hello! <c:out value="${title}" /><br /> <a
                                href="showitem.do">Back to homepage</a></font>
                        </c:otherwise>
                    </c:choose>
                </p>
            </td>

            <td align="center" width=25%>
                <p align="left">

                    <c:choose>
                        <c:when test="${user==null}">
                            <br />
                            <span class="menu-item"><a href="login.do">Login</a></span>
                            <br />
                            <span class="menu-item"><a href="register.do">Register</a></span>
                            <br />
                        </c:when>
                        <c:otherwise>
                            <br />
                            <span class="menu-item"><a href="manage.do">Manage
                                    Your items</a></span>
                            <br />
                            <span class="menu-item"><a href="change-pwd.do">Change
                                    Password</a></span>
                            <br />
                            <span class="menu-item"><a href="logout.do">Logout</a></span>
                            <br />
                        </c:otherwise>
                    </c:choose>

                </p>
            </td>
        </tr>
    </table>
    <hr>