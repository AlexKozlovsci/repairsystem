<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 23.04.2017
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Index Page</title>
</head>

<body>
<%--
<spring:form method="post"  modelAttribute="userJSP" action="check-user">

    Name: <spring:input path="name"/> (path="" - указывает путь, используемый в modelAttribute=''. в нашем случае User.name)  <br/>
    Password: <spring:input path="password"/>   <br/>
    <spring:button>Next Page</spring:button>

</spring:form>
--%>

<table>
    <thead>
        <td>
            <th>id_manager</th>
            <th>id_client</th>
            <th>id_engineer</th>
            <th>problem</th>
            <th>created_at</th>
            <th>finished_at</th>
        </td>
    </thead>
    <tbody>

    </tbody>
</table>

</body>

</html>