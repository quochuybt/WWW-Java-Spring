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

    <title>Quản lý tin tức</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="container">

    <h1>QUẢN LÝ TIN TỨC</h1>

    <nav>

        <a href="${pageContext.request.contextPath}/danh-sach-tin-tuc">
            Danh sách
        </a>

        <a href="${pageContext.request.contextPath}/tin-tuc-form">
            Thêm tin
        </a>

        <a href="${pageContext.request.contextPath}/quan-ly-form">
            Quản lý
        </a>

    </nav>

    <h2>Danh sách tin tức</h2>

    <table>

        <thead>

        <tr>

            <th>Mã TT</th>

            <th>Tiêu đề</th>

            <th>Nội dung</th>

            <th>Danh mục</th>

            <th>Thao tác</th>

        </tr>

        </thead>

        <tbody>

        <c:forEach
                var="tin"
                items="${danhSachTin}">

            <tr>

                <td>
                        ${tin.maTinTuc}
                </td>

                <td>
                        ${tin.tieuDe}
                </td>

                <td>
                        ${tin.noiDungTinTuc}
                </td>

                <td>
                        ${tin.maDM}
                </td>

                <td>

                    <form
                            action="${pageContext.request.contextPath}/quan-ly-form"
                            method="post"
                            onsubmit="return confirm('Bạn có chắc muốn xóa tin này?')">

                        <input
                                type="hidden"
                                name="maTinTuc"
                                value="${tin.maTinTuc}">

                        <button
                                type="submit"
                                class="btn-delete">

                            Xóa

                        </button>

                    </form>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

</div>

</body>

</html>
