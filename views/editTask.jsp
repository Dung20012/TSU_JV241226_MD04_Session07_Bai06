<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sửa công việc</title>
</head>
<body>
<h2>Sửa Công Việc</h2>
<form action="/tasks/edit/${task.taskId}" method="post">
  Tên: <input type="text" name="taskName" value="${task.taskName}"/><br/>
  Mô tả: <input type="text" name="description" value="${task.description}"/><br/>
  Trạng thái:
  <input type="checkbox" name="status" value="true" <c:if test="${task.status}">checked</c:if>/><br/>
  <button type="submit">Cập nhật</button>
</form
</body>
</html>
