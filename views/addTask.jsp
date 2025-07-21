
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm công việc</title>
</head>
<body>
<form action="/tasks/add" method="post">
    Tên: <input type="text" name="taskName"/><br/>
    Mô tả: <input type="text" name="description"/><br/>
    Trạng thái: <input type="checkbox" name="status" value="true"/><br/>
    <button type="submit">Thêm</button>
</form>
</body>
</html>
