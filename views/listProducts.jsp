<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            padding: 30px;
        }

        h1 {
            color: #333;
        }

        form {
            margin-bottom: 20px;
        }

        label {
            margin-right: 10px;
        }

        select, input[type="text"] {
            padding: 6px;
            margin-right: 10px;
        }

        button {
            padding: 6px 12px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 5px rgba(0,0,0,0.1);
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .action-links a {
            margin-right: 10px;
            color: #007bff;
        }

        .action-links a:hover {
            text-decoration: underline;
        }

        .add-button {
            display: inline-block;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<h1>Danh sách sản phẩm</h1>

<!-- Form lọc và tìm kiếm -->
<form method="get" action="${pageContext.request.contextPath}/products">
    <label>Danh mục:</label>
    <select name="categoryId">
        <option value="">-- Tất cả --</option>
        <c:forEach var="cate" items="${categories}">
            <option value="${cate.cateId}"
                    <c:if test="${cate.cateId == selectedCategoryId}">selected</c:if>>
                    ${cate.cateName}
            </option>
        </c:forEach>
    </select>

    <label>Tên sản phẩm:</label>
    <input type="text" name="searchTerm" value="${searchTerm}" placeholder="Nhập tên sản phẩm" />

    <label>Sắp xếp theo giá:</label>
    <select name="sortOrder">
        <option value="asc" <c:if test="${sortOrder == 'asc'}">selected</c:if>>Tăng dần</option>
        <option value="desc" <c:if test="${sortOrder == 'desc'}">selected</c:if>>Giảm dần</option>
    </select>

    <button type="submit">Lọc</button>
</form>

<!-- Nút thêm mới -->
<div class="add-button">
    <a href="${pageContext.request.contextPath}/products/add">Thêm mới sản phẩm</a>
</div>

<!-- Bảng danh sách sản phẩm -->
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên sản phẩm</th>
        <th>Mô tả</th>
        <th>Giá</th>
        <th>ID Danh mục</th>
        <th>Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.productId}</td>
            <td>${product.productName}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td>${product.categoryId}</td>
            <td class="action-links">
                <a href="${pageContext.request.contextPath}/products/edit/${product.productId}">Sửa</a>
                <a href="${pageContext.request.contextPath}/products/delete/${product.productId}"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này không?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- Hiển thị thông báo nếu có -->
<c:if test="${not empty message}">
    <script>alert("${message}");</script>
</c:if>
</body>
</html>
