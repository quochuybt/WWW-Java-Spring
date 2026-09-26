package vn.edu.iuh.tuan345bai3.servlet;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.tuan345bai3.beans.Product;
import vn.edu.iuh.tuan345bai3.dao.ProductDAO;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet({"/products", "/product"})
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() {
        productDAO = new ProductDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idstr = req.getParameter("id");

        if (idstr != null) {
            int id = Integer.parseInt(idstr);

            Product product = productDAO.getProductById(id);

            if (product != null) {
                req.setAttribute("product", product);

                RequestDispatcher dispatcher =
                        getServletContext()
                                .getRequestDispatcher("/productdetail.jsp");

                dispatcher.forward(req, resp);
            } else {
                resp.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Product not found"
                );
                return;
            }
        }

        List<Product> products = productDAO.getAllProducts();

        req.setAttribute("products", products);

        RequestDispatcher dispatcher =
                getServletContext()
                        .getRequestDispatcher("/productlist.jsp");

        dispatcher.forward(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // super.doPost(req, resp);

    }
}