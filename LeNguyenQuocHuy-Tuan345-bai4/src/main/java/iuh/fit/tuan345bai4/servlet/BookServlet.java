package iuh.fit.tuan345bai4.servlet;

import iuh.fit.tuan345bai4.beans.Book;
import iuh.fit.tuan345bai4.dao.BookDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private BookDAO bookDAO;

    @Resource(name = "jdbc/bookstoredb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            bookDAO = new BookDAO(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idstr = req.getParameter("id");
        if (idstr != null) {
            int id = Integer.parseInt(idstr);
            Book book = bookDAO.getBookById(id);
            if (book != null) {
                req.setAttribute("book", book);
                RequestDispatcher dispatcher =
                        getServletContext()
                                .getRequestDispatcher("/chitietsach.jsp");

                dispatcher.forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,"Book not found");
                return;
            }
        }

        List<Book> books = bookDAO.getAllBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("danhsach.jsp").forward(req, resp);
    }


}
