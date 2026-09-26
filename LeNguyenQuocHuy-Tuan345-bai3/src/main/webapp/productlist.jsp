<%--
  Created by IntelliJ IDEA.
  User: acer-lap
  Date: 9/21/2026
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Product List</title>

    <style>
        .container {
            border: 2px solid green;
            padding: 15px;
            width: 95%;
            margin: 20px auto;
        }

        .product-list {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 10px;
        }

        .product-class {
            border: 1px solid #333;
            text-align: center;
            padding: 12px;
            min-height: 480px;
            box-sizing: border-box;
        }

        .product-class b {
            font-size: 18px;
        }

        .hinh {
            width: 150px;
            height: 150px;
            object-fit: contain;
            margin: 20px 0;
        }

        .price {
            margin: 10px 0;
            font-size: 16px;
        }

        .quantity {
            width: 50px;
            margin-bottom: 15px;
        }

        .product-class form {
            margin: 0;
        }

        .product-class input[type="submit"] {
            margin: 10px 0;
            padding: 4px 10px;
        }

        .product-detail {
            display: inline-block;
            margin-top: 5px;
        }
    </style>
</head>

<body>

<div class="container">

    <p>
        <a href="cart">View Cart</a>
    </p>

    <div class="product-list">

        <c:forEach items="${products}" var="p">

            <div class="product-class">

                <b>${p.model}</b>
                <br/>

                <img src="images/${p.image}" class="hinh">
                <br/>

                <div class="price">
                    Price: ${p.price}
                </div>

                <form action="${pageContext.request.contextPath}/cart" method="post">

                    <input
                            type="text"
                            size="2"
                            value="1"
                            name="quantity"
                            class="quantity">

                    <br/>

                    <input type="hidden" name="id" value="${p.id}">
                    <input type="hidden" name="price" value="${p.price}">
                    <input type="hidden" name="model" value="${p.model}">
                    <input type="hidden" name="action" value="add">

                    <input
                            type="submit"
                            name="addToCart"
                            value="Add To Cart">

                    <br/>

                </form>

                <a
                        href="${pageContext.request.contextPath}/product?id=${p.id}"
                        class="product-detail">
                    Product Detail
                </a>

            </div>

        </c:forEach>

    </div>

</div>

</body>
</html>