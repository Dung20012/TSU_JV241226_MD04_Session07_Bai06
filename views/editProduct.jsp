<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Sửa sản phẩm</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 20px;
    }
    h1 {
      color: #333;
    }
    form {
      background: #fff;
      width: 40%;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }
    label {
      display: block;
      margin: 10px 0 5px;
    }
    input[type="text"], select {
      width: 100%;
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    button {
      background-color: #007bff;
      color: white;
      border: none;
      padding: 10px 15px;
      border-radius: 4px;
      cursor: pointer;
    }
    button:hover {
      background-color: #0056b3;
    }
    .error-message {
      color: red;
      margin-bottom: 15px;
    }
  </style>
</head>
<body>
<h1 style="text-align: center">Sửa sản phẩm</h1>
<div  style="display: flex ; justify-content: center; align-items: center">
  <form action="${pageContext.request.contextPath}/products/edit/${product.productId}" method="post">
    <c:if test="${not empty error}">
      <p class="error-message"><span>${error}</span></p>
    </c:if>
    <input type="hidden" name="productId" value="${product.productId}">

    <label for="productName">Tên sản phẩm:</label>
    <input type="text" id="productName" name="productName" value="${product.productName}" required />

    <label for="description">Mô tả:</label>
    <input type="text" id="description" name="description" value="${product.description}" />

    <label for="price">Giá:</label>
    <input type="text" id="price" name="price" value="${product.price}" required />

    <label for="categoryId">Danh mục:</label>
    <select id="categoryId" name="categoryId">
      <option value="">Chọn danh mục</option>
      <c:forEach var="category" items="${categories}">
        <option value="${category.cateId}" <c:if test="${product.categoryId == category.cateId}">selected</c:if>>${category.cateName}</option>
      </c:forEach>
    </select>

    <button type="submit">Cập nhật</button>
    <a href="/products/">Quay lại</a>
  </form>
</div>

</body>
</html>