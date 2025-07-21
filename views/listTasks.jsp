<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách công việc</title>
</head>
<body>
<a href="/tasks/add">Thêm công việc</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Mô tả</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>
    <c:forEach items="${tasks}" var="task">
        <tr>
            <td>${task.taskId}</td>
            <td>${task.taskName}</td>
            <td>${task.description}</td>
            <td><c:choose>
                <c:when test="${task.status}">Hoàn thành</c:when>
                <c:otherwise>Chưa hoàn thành</c:otherwise>
            </c:choose></td>
            <td>
                <a href="/tasks/edit/${task.taskId}">Sửa</a>
                <a href="/tasks/delete/${task.taskId}" onclick="return confirm('Xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
