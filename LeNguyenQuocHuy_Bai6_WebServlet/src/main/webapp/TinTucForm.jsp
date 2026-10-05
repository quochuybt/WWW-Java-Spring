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

    <title>Thêm tin tức</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="container">

    <h1>THÊM TIN TỨC</h1>

    <c:if test="${not empty error}">
        <div class="error">
                ${error}
        </div>
    </c:if>

    <form
            action="${pageContext.request.contextPath}/tin-tuc-form"
            method="post"
            onsubmit="return validateForm()">

        <div class="form-group">

            <label>Mã tin tức:</label>

            <input
                    type="number"
                    id="maTinTuc"
                    name="maTinTuc">

        </div>

        <div class="form-group">

            <label>Tiêu đề:</label>

            <input
                    type="text"
                    id="tieuDe"
                    name="tieuDe">

        </div>

        <div class="form-group">

            <label>Nội dung:</label>

            <textarea
                    id="noiDungTinTuc"
                    name="noiDungTinTuc"></textarea>

        </div>

        <div class="form-group">

            <label>Liên kết:</label>

            <input
                    type="text"
                    id="lienKet"
                    name="lienKet"
                    placeholder="http://example.com">

        </div>

        <div class="form-group">

            <label>Danh mục:</label>

            <select id="maDM" name="maDM">

                <option value="">
                    -- Chọn danh mục --
                </option>

                <c:forEach
                        var="dm"
                        items="${danhSachDanhMuc}">

                    <option value="${dm.maDM}">
                            ${dm.tenDanhMuc}
                    </option>

                </c:forEach>

            </select>

        </div>

        <button type="submit">
            Thêm
        </button>

        <a class="btn"
           href="${pageContext.request.contextPath}/danh-sach-tin-tuc">
            Hủy
        </a>

    </form>

</div>

<script>

    function validateForm() {

        const maTinTuc =
            document.getElementById("maTinTuc").value.trim();

        const tieuDe =
            document.getElementById("tieuDe").value.trim();

        const noiDung =
            document.getElementById("noiDungTinTuc").value.trim();

        const lienKet =
            document.getElementById("lienKet").value.trim();

        const maDM =
            document.getElementById("maDM").value;

        // Bắt buộc nhập
        if (
            maTinTuc === "" ||
            tieuDe === "" ||
            noiDung === "" ||
            lienKet === "" ||
            maDM === ""
        ) {

            alert("Vui lòng nhập đầy đủ thông tin!");

            return false;
        }

        // Link phải bắt đầu bằng http://
        const regexURL = /^http:\/\/.+$/;

        if (!regexURL.test(lienKet)) {

            alert("Liên kết phải bắt đầu bằng http://");

            return false;
        }

        // Nội dung không quá 255 ký tự
        const regexNoiDung = /^.{1,255}$/s;

        if (!regexNoiDung.test(noiDung)) {

            alert("Nội dung không được vượt quá 255 ký tự!");

            return false;
        }

        return true;
    }

</script>

</body>

</html>
