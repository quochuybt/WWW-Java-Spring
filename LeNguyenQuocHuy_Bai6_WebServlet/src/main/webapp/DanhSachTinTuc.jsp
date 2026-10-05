<%--
  Created by IntelliJ IDEA.
  User: acer-lap
  Date: 10/5/2026
  Time: 11:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Danh sách tin tức</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="container">

    <h1>QUẢN LÝ TIN TỨC</h1>

    <nav>
        <a href="${pageContext.request.contextPath}/danh-sach-tin-tuc">
            Danh sách tin
        </a>

        <a href="${pageContext.request.contextPath}/tin-tuc-form">
            Thêm tin tức
        </a>

        <a href="${pageContext.request.contextPath}/quan-ly-form">
            Quản lý / Xóa
        </a>
    </nav>

    <h2>Danh sách tin tức</h2>

    <div class="category">

        <strong>Danh mục:</strong>

        <a href="${pageContext.request.contextPath}/danh-sach-tin-tuc">
            Tất cả
        </a>

        <c:forEach var="dm" items="${danhSachDanhMuc}">

            <a href="${pageContext.request.contextPath}/danh-sach-tin-tuc?maDM=${dm.maDM}">
                    ${dm.tenDanhMuc}
            </a>

        </c:forEach>

    </div>

    <table>

        <thead>
        <tr>
            <th>Mã TT</th>
            <th>Tiêu đề</th>
            <th>Nội dung</th>
            <th>Liên kết</th>
            <th>Mã DM</th>
        </tr>
        </thead>

        <tbody>

        <c:forEach var="tin" items="${danhSachTin}">

            <tr>
                <td>${tin.maTinTuc}</td>

                <td>${tin.tieuDe}</td>

                <td>${tin.noiDungTinTuc}</td>

                <td>
                    <a href="${tin.lienKet}" target="_blank">
                        Xem
                    </a>
                </td>

                <td>${tin.maDM}</td>
            </tr>

        </c:forEach>

        </tbody>

    </table>

</div>

</body>
</html>
